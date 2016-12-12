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
						
						if(!cropsPredicted) {
							out.println("<div id='content'><style> .progress { height: 50%; width: 20%; } table { font-family: arial, sans-serif; border-collapse: collapse; text-align: center; width: 50%; } td, th { border: 1px solid #dddddd; text-align: center; }  tr:nth-child(even) { height: 10% background-color: #dddddd; } table.ex1 { table-layout: fixed; } </style><div id='siteNotice'><meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1'><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>");
							out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script><script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script></div><div id='bodyContent'><table class='ex1'>");
							out.println("<tr><th width='10%' style='text-align:center;font-size:15px;'>Crops Possible</th><th width='30%' style='text-align:center;font-size:15px;'>Accuracy of prediction</th><th width='20%' style='text-align:center;font-size:15px;'>Extra nutrients needed for cultivation</th></tr>");
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
						System.out.println("Crop="+prediction.getCropName()+", Accuracy="+prediction.getAccuracy()+", Deficiencies="+deficienciesAsString);
						out.println("<tr><td width='10%'><i style='font-size:12px;'>"+prediction.getCropName()+"</i></td>");
						out.println("<td width='30%'><div class='container'><div class='progress'>");
						out.println("<div class="+style+" role='progressbar' aria-valuenow="+accuracyPercentage+" aria-valuemin='0' aria-valuemax='100' style='width:"+accuracyPercentage+"%'>");
						out.println(accuracyPercentage);
						out.println("</div>");
						out.println("</div></div></td>");
						out.println("<td width='20%'><i style='font-size:12px;'>"+deficienciesAsString+"</td>");
						out.println("</tr>");
					}
				}
			}
			
			if(cropsPredicted) {
				out.println("</table></div></div>");
			}
		}

		if(!cropsPredicted) {
			System.out.println("No crops predicted");
			out.println("<h4>No crops predicted</h4>");
		}
	}
}
