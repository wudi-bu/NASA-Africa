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
	private Boolean Is_Ca_OK = false;
	private Boolean Is_P_OK = false;
	private Boolean Is_PH_OK = false;
	private Boolean Is_Sand_OK = false;
	private Boolean Is_SOC_OK = false;
	private Boolean Is_Al_OK = false;
	private Boolean Is_Mg_OK = false;
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
	public Boolean getIs_Ca_OK() {
		return Is_Ca_OK;
	}
	public void setIs_Ca_OK(Boolean is_Ca_OK) {
		Is_Ca_OK = is_Ca_OK;
	}
	public Boolean getIs_P_OK() {
		return Is_P_OK;
	}
	public void setIs_P_OK(Boolean is_P_OK) {
		Is_P_OK = is_P_OK;
	}
	public Boolean getIs_PH_OK() {
		return Is_PH_OK;
	}
	public void setIs_PH_OK(Boolean is_PH_OK) {
		Is_PH_OK = is_PH_OK;
	}
	public Boolean getIs_Sand_OK() {
		return Is_Sand_OK;
	}
	public void setIs_Sand_OK(Boolean is_Sand_OK) {
		Is_Sand_OK = is_Sand_OK;
	}
	public Boolean getIs_SOC_OK() {
		return Is_SOC_OK;
	}
	public void setIs_SOC_OK(Boolean is_SOC_OK) {
		Is_SOC_OK = is_SOC_OK;
	}
	public Boolean getIs_Al_OK() {
		return Is_Al_OK;
	}
	public void setIs_Al_OK(Boolean is_Al_OK) {
		Is_Al_OK = is_Al_OK;
	}
	public Boolean getIs_Mg_OK() {
		return Is_Mg_OK;
	}
	public void setIs_Mg_OK(Boolean is_Mg_OK) {
		Is_Mg_OK = is_Mg_OK;
	}
	

}
