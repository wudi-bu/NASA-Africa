package com.bu.ece.interactiveMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bu.ece.interactiveMap.cropPrediction.CropPredictionHelper;
import com.bu.ece.interactiveMap.cropPrediction.PredictionResultBean;

public class ProcessLocationInformation extends HttpServlet {

	
	private static final long serialVersionUID = -1147420863477563782L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Set response content type
		response.setContentType("text/html");
		
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		CropPredictionHelper cropPredictionHelper = new CropPredictionHelper();
		ArrayList<PredictionResultBean> predictionResults = cropPredictionHelper.predictCrops(latitude, longitude);
		boolean cropsPredicted = false;
		
		//Set crop information in the response
		PrintWriter out = response.getWriter();
		
		if(predictionResults != null) {
			for(PredictionResultBean prediction : predictionResults) {
				if(prediction != null) {
					if(prediction.getIs_Cultivatible()) {
						cropsPredicted = true;
						
						ArrayList<String> soilDeficiencies = new ArrayList<String>();
						StringBuilder deficiencies = new StringBuilder();
						if(!prediction.getIs_Al_OK()) {
							soilDeficiencies.add("Aluminium");
							deficiencies.append("Aluminium, ");
						}
						if(!prediction.getIs_Ca_OK()) {
							soilDeficiencies.add("Calcium");
							deficiencies.append("Calcium, ");
						}
						if(!prediction.getIs_P_OK()) {
							soilDeficiencies.add("Phosphorus");
							deficiencies.append("Phosphorus, ");
						}
						if(!prediction.getIs_PH_OK()) {
							soilDeficiencies.add("Ph");
							deficiencies.append("Ph, ");
						}
						if(!prediction.getIs_Mg_OK()) {
							soilDeficiencies.add("Magnesium");
							deficiencies.append("Magnesium, ");
						}
						if(!prediction.getIs_Sand_OK()) {
							soilDeficiencies.add("Sand");
							deficiencies.append("Sand, ");
						}
						if(!prediction.getIs_SOC_OK()) {
							soilDeficiencies.add("SOC");
							deficiencies.append("SOC, ");
						}
						String deficienciesAsString = deficiencies.toString();
						if(deficienciesAsString.length() > 2) {
							deficienciesAsString = deficienciesAsString.substring(0, (deficienciesAsString.length() - 2));
						}
						
						System.out.println("Crop="+prediction.getCropName()+", Accuracy="+prediction.getAccuracy()+", Deficiencies="+deficienciesAsString);
						out.println("<h6>Crop="+prediction.getCropName()+", Accuracy="+prediction.getAccuracy()+", Deficiencies="+deficienciesAsString+"</h6>");
					}
				}
			}
		}
		
		if(!cropsPredicted) {
			System.out.println("No crops predicted");
			out.println("<h4>No crops predicted</h4>");
		}
	}
}
