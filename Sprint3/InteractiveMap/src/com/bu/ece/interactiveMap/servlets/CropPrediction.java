package com.bu.ece.interactiveMap.servlets;
import java.util.ArrayList;

public class CropPrediction {
	public SoilPropertyBean soilpropertybean = new SoilPropertyBean();
	public ArrayList<PredictionResultBean> PredictionResult = new ArrayList();
	public ArrayList<CropPropertyBean> CropProperty = new ArrayList();
	public ReadInCropProperty myReadInCropProperty = new ReadInCropProperty();
	
	public CropPrediction(SoilPropertyBean mySoilPropertyBean) {
		this.soilpropertybean = mySoilPropertyBean;
		CropProperty = myReadInCropProperty.getCropProperty();
		
		// TODO Auto-generated constructor stub
		}
	public ArrayList<PredictionResultBean> Predict(){
		//count for how many properties of the soil property lies within the range
		int count = 0;
		if(this.soilpropertybean.DataSource == "REST"){
			//loop for 5 Crop properties
			for(int i = 0;i<5;i++){	
				// check for Ca 
				if(this.soilpropertybean.Ca>CropProperty.get(i).Ca_min&&this.soilpropertybean.Ca<CropProperty.get(i).Ca_max){
					count ++;
				}
				// check for PH
				if(this.soilpropertybean.PH>CropProperty.get(i).PH_min&&this.soilpropertybean.PH<CropProperty.get(i).PH_max){
					count ++;
				}
				// check for Sand
				if(this.soilpropertybean.Sand>CropProperty.get(i).Sand_min&&this.soilpropertybean.Sand<CropProperty.get(i).Sand_max){
					count ++;
				}
				// check for Soc
				if(this.soilpropertybean.SOC>CropProperty.get(i).SOC_min&&this.soilpropertybean.SOC<CropProperty.get(i).SOC_max){
					count ++;
				}
				// check for Al
				if(this.soilpropertybean.Al>CropProperty.get(i).Al_min&&this.soilpropertybean.Al<CropProperty.get(i).Al_max){
					count ++;
				}
				// check for Mg
				if(this.soilpropertybean.Mg>CropProperty.get(i).Mg_min&&this.soilpropertybean.Mg<CropProperty.get(i).Mg_max){
					count ++;
				}
				PredictionResultBean predictionResultBean = new PredictionResultBean();
				predictionResultBean.CropName = CropProperty.get(i).CropName;
				// If the count >3 ,then this Crop is cultivable with certain fertilizer
				if(count>=3){
					predictionResultBean.Is_Caltivatible = true;
					predictionResultBean.Accuracy = 0.5+0.2*(count-3);
					}
				else {
					predictionResultBean.Is_Caltivatible = false;
				}
				PredictionResult.add(predictionResultBean);
				}	
			
		}
		else{
			for(int i = 0;i<5;i++){	
				// check for Ca 
				if(this.soilpropertybean.Ca>CropProperty.get(i).Ca_min&&this.soilpropertybean.Ca<CropProperty.get(i).Ca_max){
					count ++;
				}
				// check for PH
				if(this.soilpropertybean.PH>CropProperty.get(i).PH_min&&this.soilpropertybean.PH<CropProperty.get(i).PH_max){
					count ++;
				}
				// check for Sand
				if(this.soilpropertybean.Sand>CropProperty.get(i).Sand_min&&this.soilpropertybean.Sand<CropProperty.get(i).Sand_max){
					count ++;
				}
				// check for Soc
				if(this.soilpropertybean.SOC>CropProperty.get(i).SOC_min&&this.soilpropertybean.SOC<CropProperty.get(i).SOC_max){
					count ++;
				}
				// check for P
				if(this.soilpropertybean.P>CropProperty.get(i).P_min&&this.soilpropertybean.P<CropProperty.get(i).P_max){
					count ++;
				}
				PredictionResultBean predictionResultBean = new PredictionResultBean();
				predictionResultBean.CropName = CropProperty.get(i).CropName;
				// If the count >3 ,then this Crop is cultivable with certain fertilizer
				if(count>=3){
					predictionResultBean.Is_Caltivatible = true;
					predictionResultBean.Accuracy = 0.5+0.2*(count-3);
					}
				else {
					predictionResultBean.Is_Caltivatible = false;
				}
				PredictionResult.add(predictionResultBean);
				}				
		}
				return PredictionResult;		
	}

}
