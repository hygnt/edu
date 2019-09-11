package com.shareku.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shareku.util.Const;

public class WebAppContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		Const.WEB_APP_CONTEXT = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
	}

}
