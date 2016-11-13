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
		
		JSONObject jsonObject = SoilGridIntegration.getSoilProperties(latitude, longitude);
		JSONObject properties = (JSONObject)jsonObject.get("properties");
		
		Object sand = null;
		Object pH = null;
		Object aluminum = null;
		Object calcium = null;
		Object magnesium = null;
		Object soc = null;
		
		if (properties.get("SNDPPT") != null) {
			sand = ((JSONObject)((JSONObject)properties.get("SNDPPT")).get("M")).get("sl1");
		}
		
		if (properties.get("PHIHOX") != null) {
			pH = ((JSONObject)((JSONObject)properties.get("PHIHOX")).get("M")).get("sl1");
		}
		
		if (properties.get("ALUM3S") != null) {
			aluminum = ((JSONObject)((JSONObject)properties.get("ALUM3S")).get("M")).get("xd1");
		}
		
		if (properties.get("ECAX") != null) {
			calcium = ((JSONObject)((JSONObject)properties.get("ECAX")).get("M")).get("xd1");
		}
		
		if(properties.get("EMGX") != null) {
			magnesium = ((JSONObject)((JSONObject)properties.get("EMGX")).get("M")).get("xd1");
		}
		
		if(properties.get("ORCDRC") != null) {
			soc = ((JSONObject)((JSONObject)properties.get("ORCDRC")).get("M")).get("sl1");
		}
		
		SoilPropertyBean soilPropertyBean = new SoilPropertyBean();
		soilPropertyBean.setAl(ESoilUtils.convertStringToDouble((aluminum == null)?"":aluminum.toString(), 0));
		soilPropertyBean.setCa(ESoilUtils.convertStringToDouble((calcium == null)?"":calcium.toString(), 0));
		soilPropertyBean.setMg(ESoilUtils.convertStringToDouble((magnesium == null)?"":magnesium.toString(), 0));
		soilPropertyBean.setPH(ESoilUtils.convertStringToDouble((pH == null)?"":pH.toString(), 0));
		soilPropertyBean.setSand(ESoilUtils.convertStringToDouble((sand == null)?"":sand.toString(), 0));
		soilPropertyBean.setSOC(ESoilUtils.convertStringToDouble((soc == null)?"":soc.toString(), 0));
		
		CropPrediction cropPrediction = new CropPrediction(soilPropertyBean);
		ArrayList<PredictionResultBean> predictionResults = cropPrediction.Predict();
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
