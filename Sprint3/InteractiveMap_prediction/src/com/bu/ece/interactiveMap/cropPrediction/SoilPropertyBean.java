package com.bu.ece.interactiveMap.cropPrediction;
import java.io.*; 

public class SoilPropertyBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String DataSource = "";
	private double Ca = 0;
	private double P = 0;
	private double PH = 0;
	private double Sand = 0;
	private double SOC = 0;
	private double Al = 0;
	private double Mg = 0;
	
	public double getCa() {
		return Ca;
	}
	public void setCa(double ca) {
		Ca = ca;
	}
	public double getP() {
		return P;
	}
	public void setP(double p) {
		P = p;
	}
	public double getPH() {
		return PH;
	}
	public void setPH(double pH) {
		PH = pH;
	}
	public double getSand() {
		return Sand;
	}
	public void setSand(double sand) {
		Sand = sand;
	}
	public double getSOC() {
		return SOC;
	}
	public void setSOC(double sOC) {
		SOC = sOC;
	}
	public double getAl() {
		return Al;
	}
	public void setAl(double al) {
		Al = al;
	}
	public double getMg() {
		return Mg;
	}
	public void setMg(double mg) {
		Mg = mg;
	}

	
}
