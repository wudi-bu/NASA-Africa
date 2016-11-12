package com.bu.ece.interactiveMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.bu.ece.interactiveMap.connections.DBUtils;
import com.bu.ece.interactiveMap.soilgrid.SoilGridIntegration;

public class ProcessLocationInformation extends HttpServlet {

	
	private static final long serialVersionUID = -1147420863477563782L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		System.out.println("Latitude="+latitude);
		System.out.println("Longitude="+longitude);
		
		JSONObject jsonObject = SoilGridIntegration.getSoilProperties(latitude, longitude);
		JSONObject properties = (JSONObject)jsonObject.get("properties");
		
		
		String sand = ((JSONObject)((JSONObject)properties.get("SNDPPT")).get("M")).get("sl1").toString();
		String pH = ((JSONObject)((JSONObject)properties.get("PHIHOX")).get("M")).get("sl1").toString();
		String aluminum = ((JSONObject)((JSONObject)properties.get("ALUM3S")).get("M")).get("xd1").toString();
		String calcium = ((JSONObject)((JSONObject)properties.get("ECAX")).get("M")).get("xd1").toString();
		String magnesium = ((JSONObject)((JSONObject)properties.get("EMGX")).get("M")).get("xd1").toString();
		String soc = ((JSONObject)((JSONObject)properties.get("ORCDRC")).get("M")).get("sl1").toString();
		
		System.out.println("sand="+sand);
		System.out.println("pH="+pH);
		System.out.println("aluminum="+aluminum);
		System.out.println("calcium="+calcium);
		System.out.println("magnesium="+magnesium);
		System.out.println("soc="+soc);
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
		
		double latitudeAsDouble = 0;
		try {
			latitudeAsDouble = Double.parseDouble(latitude);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		if(latitudeAsDouble > 14) {
			out.println("<h3>Predicted Crop = None</h3>");
		} else {
			out.println("<h3>Predicted Crop = Wheat</h3>");
		}
	}
}
