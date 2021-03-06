package org.lahendrix.socialbarter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private DataSource dataSource;
	
	@RequestMapping( value="/users/", method = {RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)

	public ModelAndView getUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			try {
				Connection connection = this.dataSource.getConnection("social_admin", "develop");
				Statement query = connection.createStatement();
				query.execute("SELECT username FROM user");
				ResultSet results = query.getResultSet();
				
				List<String> names = new ArrayList();
				while (results.next()) {
					String name = results.getString("username");
					names.add(name);
				}
				
				PrintWriter writer = response.getWriter();
				writer.println("<html><head><title>User List</title></head><body>");
				writer.println("<h3>Names</h3>");
				writer.println("<ul>");
				for (String name : names) {
					writer.println("<li>");
					writer.println(name);
					writer.println("</li>");
				}
				writer.println("</ul>");
				writer.println("</body></html>");
				
				
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	@Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			Connection connection = this.dataSource.getConnection("social_admin", "develop");
			Statement query = connection.createStatement();
			query.execute("SELECT username FROM user");
			ResultSet results = query.getResultSet();
			
			List<String> names = new ArrayList();
			while (results.next()) {
				String name = results.getString("username");
				names.add(name);
			}
			
			PrintWriter writer = resp.getWriter();
			writer.println("<html><head><title>User List</title></head><body>");
			writer.println("<h3>Names</h3>");
			writer.println("<ul>");
			for (String name : names) {
				writer.println("<li>");
				writer.println(name);
				writer.println("</li>");
			}
			writer.println("</ul>");
			writer.println("</body></html>");
			
			
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		
	}*/

	
}
