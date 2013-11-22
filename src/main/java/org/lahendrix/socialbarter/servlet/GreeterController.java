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
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreeterController {

	@Autowired
	private Greeter greeter;
	
	@RequestMapping( value="/greeter/", method = {RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView getGreeterMessage(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().println(greeter.greet());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*@Override
	public void init(ServletConfig config) throws ServletException {
//		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
//		this.greeter = context.getBean("greeter", Greeter.class);
		
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().println(this.greeter.greet());
	}*/
}
