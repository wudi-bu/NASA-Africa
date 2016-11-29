package com.bu.ece.interactiveMap.cropPrediction;
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
		
		if(this.soilpropertybean.DataSource == "REST"){
			//loop for 5 Crop properties
			for(int i = 0;i<5;i++){	
				int count = 0;
				// check for Ca 
				if(this.soilpropertybean.getCa()>CropProperty.get(i).getCa_min()&&this.soilpropertybean.getCa()<CropProperty.get(i).getCa_max()){
					count ++;
				}
				// check for PH
				if(this.soilpropertybean.getPH()>CropProperty.get(i).getPH_min()&&this.soilpropertybean.getPH()<CropProperty.get(i).getPH_max()){
					count ++;
				}
				// check for Sand
				if(this.soilpropertybean.getSand()>CropProperty.get(i).getSand_min()&&this.soilpropertybean.getSand()<CropProperty.get(i).getSand_max()){
					count ++;
				}
				// check for Soc
				if(this.soilpropertybean.getSOC()>CropProperty.get(i).getSOC_min()&&this.soilpropertybean.getSOC()<CropProperty.get(i).getSOC_max()){
					count ++;
				}
				// check for Al
				if(this.soilpropertybean.getAl()>CropProperty.get(i).getAl_min()&&this.soilpropertybean.getAl()<CropProperty.get(i).getAl_max()){
					count ++;
				}
				// check for Mg
				if(this.soilpropertybean.getMg()>CropProperty.get(i).getMg_min()&&this.soilpropertybean.getMg()<CropProperty.get(i).getMg_max()){
					count ++;
				}
				PredictionResultBean predictionResultBean = new PredictionResultBean();
				predictionResultBean.setCropName(CropProperty.get(i).getCropName());
				// If the count >3 ,then this Crop is cultivable with certain fertilizer
				if(count>=3){
					predictionResultBean.setIs_Cultivatible(true);
					predictionResultBean.setAccuracy(0.5+0.15*(count-3));
					}
				else {
					predictionResultBean.setIs_Cultivatible(false);
				}
				PredictionResult.add(predictionResultBean);
				}	
			
		}
		else{
			for(int i = 0;i<5;i++){	
				int count = 0;
				// check for Ca 
				if(this.soilpropertybean.getCa()>CropProperty.get(i).getCa_min()&&this.soilpropertybean.getCa()<CropProperty.get(i).getCa_max()){
					count ++;
				}
				// check for PH
				if(this.soilpropertybean.getPH()>CropProperty.get(i).getPH_min()&&this.soilpropertybean.getPH()<CropProperty.get(i).getPH_max()){
					count ++;
				}
				// check for Sand
				if(this.soilpropertybean.getSand()>CropProperty.get(i).getSand_min()&&this.soilpropertybean.getSand()<CropProperty.get(i).getSand_max()){
					count ++;
				}
				// check for Soc
				if(this.soilpropertybean.getSOC()>CropProperty.get(i).getSOC_min()&&this.soilpropertybean.getSOC()<CropProperty.get(i).getSOC_max()){
					count ++;
				}
				// check for P
				if(this.soilpropertybean.getP()>CropProperty.get(i).getP_min()&&this.soilpropertybean.getP()<CropProperty.get(i).getP_max()){
					count ++;
				}
				PredictionResultBean predictionResultBean = new PredictionResultBean();
				predictionResultBean.setCropName(CropProperty.get(i).getCropName());
				// If the count >3 ,then this Crop is cultivable with certain fertilizer
				if(count>=3){
					predictionResultBean.setIs_Cultivatible(true);
					predictionResultBean.setAccuracy(0.5+0.15*(count-3));
					}
				else {
					predictionResultBean.setIs_Cultivatible(false);
				}
				PredictionResult.add(predictionResultBean);
				}				
		}
				return PredictionResult;		
	}

}
