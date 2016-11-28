package com.bu.ece.interactiveMap.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.bu.ece.interactiveMap.cropPrediction.CropPredictionHelper;
import com.bu.ece.interactiveMap.cropPrediction.PredictionResultBean;

import junit.framework.TestResult;

public class CropPredictionTest implements junit.framework.Test {

	CropPredictionHelper cropPredictionHelper = new CropPredictionHelper();
	
	@Test
	public void testOverIndianOcean() {
		
		ArrayList<PredictionResultBean> indianOceanResults = cropPredictionHelper.predictCrops("0.00", "78.00");
		assertFalse(isCultivatable(indianOceanResults));
	}
	
	@Test
	public void testInCongo() {
		
		ArrayList<PredictionResultBean> congoResults = cropPredictionHelper.predictCrops("0.00", "22.15");
		if(congoResults != null) {
			for(PredictionResultBean prediction : congoResults) {
				if(prediction != null) {
					if("Corn".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.5");
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.7");
					}
				}
			}
		}
	}

	@Override
	public int countTestCases() {
		return 2;
	}

	@Override
	public void run(TestResult arg0) {
		
	}
	
	private boolean isCultivatable(ArrayList<PredictionResultBean> predictionResults) {
		
		boolean cropsPredicted = false;
		
		if(predictionResults != null) {
			for(PredictionResultBean prediction : predictionResults) {
				if(prediction != null) {
					if(prediction.getIs_Cultivatible()) {
						cropsPredicted = true;
					}
				}
			}
		}
		
		return cropsPredicted;
	}
}
