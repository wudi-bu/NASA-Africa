package com.bu.ece.interactiveMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bu.ece.interactiveMap.connections.DBUtils;

public class ProcessLocationInformation extends HttpServlet {

	
	private static final long serialVersionUID = -1147420863477563782L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		System.out.println("Latitude="+latitude);
		System.out.println("Longitude="+longitude);
		
		try {
			Connection connections = DBUtils.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//Get crop information from database
		
		//Set response content type
		response.setContentType("text/html");
		
		//Set crop information in the response
		PrintWriter out = response.getWriter();
		out.println("<h3>Predicted Crop = Wheat</h3>");
	}
}
