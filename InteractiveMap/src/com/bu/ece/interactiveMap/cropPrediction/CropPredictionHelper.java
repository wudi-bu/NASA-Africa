package com.bu.ece.interactiveMap.cropPrediction;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.bu.ece.interactiveMap.soilgrid.SoilGridIntegration;
import com.bu.ece.interactiveMap.utils.ESoilUtils;

public class CropPredictionHelper {

	public ArrayList<PredictionResultBean> predictCrops(String latitude, String longitude) {
		
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
		soilPropertyBean.DataSource = ((magnesium == null)?"":"REST");
		
		CropPrediction cropPrediction = new CropPrediction(soilPropertyBean);
		return cropPrediction.Predict();
	}
}
