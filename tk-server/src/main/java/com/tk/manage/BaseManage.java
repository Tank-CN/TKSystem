package com.tk.manage;

import com.tk.cache.UserInfoCache;
import com.tk.model.User;
import com.tk.util.CommonUtils;
import com.tk.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class BaseManage {

    @Autowired
    UserInfoCache userInfoCache;


    /**
     * Mysql分页字符串
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public String getPage(Integer pageNumber, Integer pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        StringBuffer sb = new StringBuffer();
        sb.append(" id desc ");
        if (offset >= 0) {
            sb.append("limit ").append(offset).append(",").append(pageSize);
        } else {
            sb.append("limit ").append(0).append(",").append(pageSize);
        }
        return sb.toString();
    }

    /**
     * Mysql分页字符串
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public String getPage(String orderStr, Integer pageNumber, Integer pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        StringBuffer sb = new StringBuffer();
        if (CommonUtils.isNull(orderStr)) {
            sb.append(" id desc ");
        } else {
            sb.append(" ").append(orderStr);
        }

        if (offset >= 0) {
            sb.append(" limit ").append(offset).append(",").append(pageSize);
        } else {
            sb.append(" limit ").append(0).append(",").append(pageSize);
        }
        return sb.toString();
    }

    /**
     * 得到开始索引
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public int getOffset(Integer pageNumber, Integer pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        if (offset >= 0) {
            return offset;
        }
        return 0;
    }


    public UserVo getUserVo(Long uid) {
        UserVo userVo = userInfoCache.get(uid);
        return userVo;
    }

    public UserVo getUserVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setNickname(user.getNickname());
        userVo.setHeader(user.getHeader());
        userVo.setBirthdate(user.getBirthdate());
        userVo.setMobile(user.getMobile());
        userVo.setId(user.getId());
        userVo.setInfo(user.getInfo());
        userVo.setSexcode(user.getSexcode());
        userVo.setVip(user.getVip());
        return userVo;
    }

    public void restUserCache(Long uid){
        userInfoCache.resetCache(uid);
    }



}
