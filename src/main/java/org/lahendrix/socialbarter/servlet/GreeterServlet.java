package org.lahendrix.socialbarter.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.lahendrix.socialbarter.Greeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class GreeterServlet extends HttpServlet {

	@Autowired
	private Greeter greeter;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
//		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
//		this.greeter = context.getBean("greeter", Greeter.class);
		
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().println(this.greeter.greet());
	}
}
