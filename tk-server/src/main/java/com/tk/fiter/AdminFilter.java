package com.tk.fiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台登录
 * 
 * @author Administrator
 *
 */
public class AdminFilter extends OncePerRequestFilter {

	private final static Logger logger = LoggerFactory.getLogger(AdminFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		logger.info(request.getRequestURI());
		if (null == request.getSession().getAttribute("admin") || null == request.getSession().getAttribute("modules")) {
			response.sendRedirect("/adminlogin");
		} else {
			//解决首页模块选中
			request.setAttribute("pcode", request.getParameter("pcode"));
			request.setAttribute("subcode", request.getParameter("subcode"));
			filterChain.doFilter(request, response);
		}
	}
}
