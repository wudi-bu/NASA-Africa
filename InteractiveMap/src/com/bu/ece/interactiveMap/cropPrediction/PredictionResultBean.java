package com.bu.ece.interactiveMap.cropPrediction;
import java.io.*; 
public class PredictionResultBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean Is_Cultivatible = true;
	private String CropName = "";
	private double Accuracy = 0;
	public Boolean getIs_Cultivatible() {
		return Is_Cultivatible;
	}
	public void setIs_Cultivatible(Boolean is_Cultivatible) {
		Is_Cultivatible = is_Cultivatible;
	}
	public String getCropName() {
		return CropName;
	}
	public void setCropName(String cropName) {
		CropName = cropName;
	}
	public double getAccuracy() {
		return Accuracy;
	}
	public void setAccuracy(double accuracy) {
		Accuracy = accuracy;
	}
	

}
