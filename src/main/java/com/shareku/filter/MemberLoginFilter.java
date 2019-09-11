package com.shareku.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shareku.entity.auth.User;
import com.shareku.util.Const;

public class MemberLoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse resp = (HttpServletResponse)response;
			HttpSession session = req.getSession(true);
			User user = (User)session.getAttribute(Const.SESSION_USER);
			if(user == null){
				resp.sendRedirect(req.getContextPath()+"/member/login.shtml");
				//req.getRequestDispatcher(req.getContextPath()+"/member/login.shtml").(req, resp);
			}
			chain.doFilter(req, resp);
			return;
	
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
