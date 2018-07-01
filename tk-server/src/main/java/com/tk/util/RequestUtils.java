package com.tk.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	/**
	 * 
	 * 获取客户端IP方法
	 * 
	 * @param request
	 * @return
	 * @update [修改人] [修改时间] [变更描述]
	 */
	public static String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
			ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
			ip = request.getRemoteAddr();
		return ip;
	}

	public static boolean isEmpty(HttpServletRequest request, String... arrs) {
		int len = arrs.length;
		for (int i = 0; i < len; i++) {
			if (isEmpty(request, arrs[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEmpty(HttpServletRequest request, String name) {
		return CommonUtils.isNull(request.getParameter(name));
	}

	public static String getValue(HttpServletRequest request, String name) {
		return request.getParameter(name);
	}

}
