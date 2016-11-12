package com.bu.ece.interactiveMap.cropPrediction;
import java.io.*; 
public class CropPropertyBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String CropName = "";
	private double Ca_min = 0;
	private double Ca_max = 0;
	private double P_min = 0;
	private double P_max = 0;
	private double PH_min = 0;
	private double PH_max = 0;
	private double Sand_min = 0;
	private double Sand_max = 0;
	private double SOC_min = 0;
	private double SOC_max = 0;
	private double Al_min = 0;
	private double Al_max = 0;
	private double Mg_min = 0;
	private double Mg_max = 0;
	
	public String getCropName() {
		return CropName;
	}
	public void setCropName(String cropName) {
		CropName = cropName;
	}
	public double getCa_min() {
		return Ca_min;
	}
	public void setCa_min(double ca_min) {
		Ca_min = ca_min;
	}
	public double getCa_max() {
		return Ca_max;
	}
	public void setCa_max(double ca_max) {
		Ca_max = ca_max;
	}
	public double getP_min() {
		return P_min;
	}
	public void setP_min(double p_min) {
		P_min = p_min;
	}
	public double getP_max() {
		return P_max;
	}
	public void setP_max(double p_max) {
		P_max = p_max;
	}
	public double getPH_min() {
		return PH_min;
	}
	public void setPH_min(double pH_min) {
		PH_min = pH_min;
	}
	public double getPH_max() {
		return PH_max;
	}
	public void setPH_max(double pH_max) {
		PH_max = pH_max;
	}
	public double getSand_min() {
		return Sand_min;
	}
	public void setSand_min(double sand_min) {
		Sand_min = sand_min;
	}
	public double getSand_max() {
		return Sand_max;
	}
	public void setSand_max(double sand_max) {
		Sand_max = sand_max;
	}
	public double getSOC_min() {
		return SOC_min;
	}
	public void setSOC_min(double sOC_min) {
		SOC_min = sOC_min;
	}
	public double getSOC_max() {
		return SOC_max;
	}
	public void setSOC_max(double sOC_max) {
		SOC_max = sOC_max;
	}
	public double getAl_min() {
		return Al_min;
	}
	public void setAl_min(double al_min) {
		Al_min = al_min;
	}
	public double getAl_max() {
		return Al_max;
	}
	public void setAl_max(double al_max) {
		Al_max = al_max;
	}
	public double getMg_min() {
		return Mg_min;
	}
	public void setMg_min(double mg_min) {
		Mg_min = mg_min;
	}
	public double getMg_max() {
		return Mg_max;
	}
	public void setMg_max(double mg_max) {
		Mg_max = mg_max;
	}

}