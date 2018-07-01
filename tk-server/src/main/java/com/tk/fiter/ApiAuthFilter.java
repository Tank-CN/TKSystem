package com.tk.fiter;

import com.tk.Constants;
import com.tk.manage.UserManage;
import com.tk.model.User;
import com.tk.util.CommonUtils;
import com.tk.util.RequestUtils;
import com.tk.util.ResponseUtils;
import com.tk.util.ResultCode;
import com.tk.util.encryption.DESUtils;
import com.tk.util.encryption.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 判断授权
 *
 * @author Administrator
 */
@Component("apiFilter")
public class ApiAuthFilter extends OncePerRequestFilter {


    @Autowired
    UserManage userManage;

    //时间戳超时时间
    private final static long timeout = 30 * 60 * 1000;

    //不需要验证的url
    String[] unAuthUrls = {"/api/auth/device"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info(request.getMethod() + "    " + request.getRequestURI());
        // 测试
        if ("1".equals(RequestUtils.getValue(request, "t"))) {
            filterChain.doFilter(request, response);
            return;
        }
        //测试阶段放开Get和Post的验证
//		if (!"POST".equals(request.getMethod())) {
//			ResponseUtils.response(ResultCode.METHOD_MUST_POST, response);
//			return;
//		}
        //判断公共参数是否存在
        if (RequestUtils.isEmpty(request, "sign", "timestamp", "sn", "device", "token")) {
            // 公共参数缺失
            ResponseUtils.response(ResultCode.PARAMETERS_MISSING, response);
            return;
        } else {
            //判断时间戳有效期-30分钟
            String timestamp = RequestUtils.getValue(request, "timestamp");
            long currentTimes = System.currentTimeMillis();
            if (Math.abs(currentTimes - Long.valueOf(timestamp)) > timeout) {
                //超时提示
                ResponseUtils.response(ResultCode.REQCODE_TIME_EXPIRED, response);
                return;
            }
            // 开始验证
            //判断签名sign是否正确
            if (isAuthUrl(request.getRequestURI()) && !RequestUtils.getValue(request, "sign").equals(MD5Utils.getMD5(getSign(request)))) {
                //签名不正确
                ResponseUtils.response(ResultCode.SIGN_ERROR, response);
                return;
            }
            String sn = RequestUtils.getValue(request, "sn");
            String[] snArry = new DESUtils(Constants.DES_KEY).decryptStr(sn).split(";");
            if (null != snArry && snArry.length == 4) {
                // 判断是否是同一设备
                String oldToken = RequestUtils.getValue(request, "token");
                User user = userManage.getUserById(Long.valueOf(snArry[0]));
                String newToken = null;
                if (CommonUtils.isNotNull(user)) {
                    newToken = user.getToken();
                }
                if (oldToken.equals(newToken)) {
                    request.setAttribute("id", snArry[0]);
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    // 登录设备不一样
                    ResponseUtils.response(ResultCode.REQCODE_DEVICE_DISAFFINITY, response);
                    return;
                }
            } else {
                ResponseUtils.response(ResultCode.SIGN_ERROR, response);
                return;
            }
        }
    }


    public String getSign(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        Map map = request.getParameterMap();
        List keys = new ArrayList(map.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            //去除sign
            if (!"sign".equals(keys.get(i).toString())) {
                sb.append(((String[]) map.get(keys.get(i)))[0]);
//                try {
//                    sb.append(URLDecoder.decode(((String[]) map.get(keys.get(i)))[0],"UTF-8"));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
            }
        }
        return sb.toString();
    }

    /**
     * 需要验证  返回true
     * 不需要验证  返回false
     *
     * @param url
     * @return
     */
    public boolean isAuthUrl(String url) {
        for (int i = 0; i < unAuthUrls.length; i++) {
            if (url.equals(unAuthUrls[i])) {
                return false;
            }
        }
        return true;
    }
}
