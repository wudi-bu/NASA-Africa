package com.bu.ece.interactiveMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.bu.ece.interactiveMap.connections.DBUtils;
import com.bu.ece.interactiveMap.cropPrediction.CropPrediction;
import com.bu.ece.interactiveMap.cropPrediction.PredictionResultBean;
import com.bu.ece.interactiveMap.cropPrediction.SoilPropertyBean;
import com.bu.ece.interactiveMap.soilgrid.SoilGridIntegration;
import com.bu.ece.interactiveMap.utils.ESoilUtils;

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
		
		SoilPropertyBean soilPropertyBean = new SoilPropertyBean();
		soilPropertyBean.setAl(ESoilUtils.convertStringToDouble(aluminum, 0));
		soilPropertyBean.setCa(ESoilUtils.convertStringToDouble(calcium, 0));
		soilPropertyBean.setMg(ESoilUtils.convertStringToDouble(magnesium, 0));
		soilPropertyBean.setPH(ESoilUtils.convertStringToDouble(pH, 0));
		soilPropertyBean.setSand(ESoilUtils.convertStringToDouble(sand, 0));
		soilPropertyBean.setSOC(ESoilUtils.convertStringToDouble(soc, 0));
		
		CropPrediction cropPrediction = new CropPrediction(soilPropertyBean);
		ArrayList<PredictionResultBean> predictionResults = cropPrediction.Predict();
		if(predictionResults != null) {
			for(PredictionResultBean prediction : predictionResults) {
				System.out.println(prediction.CropName);
			}
		}
		
		/*
		try {
			Connection connections = DBUtils.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
		
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
