package com.bu.ece.interactiveMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.bu.ece.interactiveMap.cropPrediction.CropPrediction;
import com.bu.ece.interactiveMap.cropPrediction.CropPredictionHelper;
import com.bu.ece.interactiveMap.cropPrediction.PredictionResultBean;
import com.bu.ece.interactiveMap.cropPrediction.SoilPropertyBean;
import com.bu.ece.interactiveMap.soilgrid.SoilGridIntegration;
import com.bu.ece.interactiveMap.utils.ESoilUtils;

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
						System.out.println("Crop="+prediction.getCropName()+", Accuracy="+prediction.getAccuracy());
						out.println("<h4>Crop="+prediction.getCropName()+", Accuracy="+prediction.getAccuracy()+"</h4>");
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
