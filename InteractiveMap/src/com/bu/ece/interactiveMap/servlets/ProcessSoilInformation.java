package com.bu.ece.interactiveMap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bu.ece.interactiveMap.cropPrediction.CropPrediction;
import com.bu.ece.interactiveMap.cropPrediction.PredictionResultBean;
import com.bu.ece.interactiveMap.cropPrediction.SoilPropertyBean;
import com.bu.ece.interactiveMap.utils.ESoilUtils;

/**
 * Servlet implementation class ProcessSoilInformation
 */
@WebServlet("/ProcessSoilInformation")
public class ProcessSoilInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Set response content type
		response.setContentType("text/html");
		
		String calcium = request.getParameter("calcium");
		String soc = request.getParameter("soc");
		String phosphorus = request.getParameter("phosphorus");
		String aluminium = request.getParameter("aluminium");
		String sand = request.getParameter("sand");
		String magnesium = request.getParameter("magnesium");
		String pH = request.getParameter("pH");
		
		
		SoilPropertyBean soilPropertyBean = new SoilPropertyBean();
		soilPropertyBean.setCa(ESoilUtils.convertStringToDouble((ESoilUtils.isEmpty(calcium))?"":calcium.toString(), 0));
		soilPropertyBean.setSOC(ESoilUtils.convertStringToDouble((ESoilUtils.isEmpty(soc))?"":soc.toString(), 0));
		soilPropertyBean.setP(ESoilUtils.convertStringToDouble((ESoilUtils.isEmpty(phosphorus))?"":phosphorus.toString(), 0));
		soilPropertyBean.setAl(ESoilUtils.convertStringToDouble((ESoilUtils.isEmpty(aluminium))?"":aluminium.toString(), 0));
		soilPropertyBean.setSand(ESoilUtils.convertStringToDouble((ESoilUtils.isEmpty(sand))?"":sand.toString(), 0));
		soilPropertyBean.setMg(ESoilUtils.convertStringToDouble((ESoilUtils.isEmpty(magnesium))?"":magnesium.toString(), 0));
		soilPropertyBean.setPH(ESoilUtils.convertStringToDouble((ESoilUtils.isEmpty(pH))?"":pH.toString(), 0));
		
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
