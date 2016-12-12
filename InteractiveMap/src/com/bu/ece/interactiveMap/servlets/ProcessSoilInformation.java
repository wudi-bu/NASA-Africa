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
						if(!cropsPredicted) {
							out.println("<table class='ex1'><tr><th width='15%' style='text-align:center;font-size:15px;'>Crops Possible</th><th width='45%' style='text-align:center;font-size:15px;'>Accuracy of prediction</th><th width='40%' style='text-align:center;font-size:15px;'>Extra nutrients needed for cultivation</th></tr>");
						}
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
						int accuracyPercentage = (int)(prediction.getAccuracy() * 100);
						String style = "progress-bar progress-bar-danger progress-bar-striped";
						if(accuracyPercentage > 70) {
							style = "progress-bar progress-bar-success progress-bar-striped";
						} else if(accuracyPercentage >= 50) {
							style = "progress-bar progress-bar-warning progress-bar-striped";
						}
						System.out.println("Crop="+prediction.getCropName()+", Accuracy="+prediction.getAccuracy());
						out.println("<tr><td width='15%'><i style='font-size:12px;'>"+prediction.getCropName()+"</i></td>");
						out.println("<td width='45%'><div class='container'><div class='progress'>");
						out.println("<div class='"+style+"' role='progressbar' aria-valuenow="+accuracyPercentage+" aria-valuemin='0' aria-valuemax='100' style='width:"+accuracyPercentage+"%'>");
						out.println(accuracyPercentage+"%");
						out.println("</div>");
						out.println("</div></div></td>");
						out.println("<td width='40%'><i style='font-size:12px;'>"+deficienciesAsString+"</td></tr>");
					}
				}
			}
			if(!cropsPredicted) {
				out.println("</table>");
			}
		}
		
		if(!cropsPredicted) {
			System.out.println("No crops predicted");
			out.println("<h4>No crops predicted</h4>");
		}
	}

}
