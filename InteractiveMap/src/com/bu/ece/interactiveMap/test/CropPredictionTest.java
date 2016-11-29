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
	public void testInEgypt() {
		
		ArrayList<PredictionResultBean> egyptResults = cropPredictionHelper.predictCrops("26.12", "29.18");
		assertFalse(isCultivatable(egyptResults));
	}
	
	@Test
	public void testInAlgeria() {
		
		ArrayList<PredictionResultBean> algeriaResults = cropPredictionHelper.predictCrops("27.37", "5.98");
		assertFalse(isCultivatable(algeriaResults));
	}
	
	@Test
	public void testInSiberia() {
		
		ArrayList<PredictionResultBean> siberiaResults = cropPredictionHelper.predictCrops("70.00", "90.00");
		assertFalse(isCultivatable(siberiaResults));
	}
	
	@Test
	public void testInGreenland() {
		
		ArrayList<PredictionResultBean> greenlandResults = cropPredictionHelper.predictCrops("71.00", "-42.00");
		assertFalse(isCultivatable(greenlandResults));
	}
	
	@Test
	public void testInAntarctic() {
		
		ArrayList<PredictionResultBean> antarcticResults = cropPredictionHelper.predictCrops("-78.00", "72.00");
		assertFalse(isCultivatable(antarcticResults));
	}
	
	@Test
	public void testInCongo() {
		
		ArrayList<PredictionResultBean> congoResults = cropPredictionHelper.predictCrops("0.00", "22.15");
		assertTrue(isCultivatable(congoResults));
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
	
	@Test
	public void testInSouthAfrica() {
		
		ArrayList<PredictionResultBean> southAfricaResults = cropPredictionHelper.predictCrops("-30.15", "24.43");
		assertTrue(isCultivatable(southAfricaResults));
		if(southAfricaResults != null) {
			for(PredictionResultBean prediction : southAfricaResults) {
				if(prediction != null) {
					if("Wheat".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.5");
					}
				}
			}
		}
	}
	
	@Test
	public void testInSudan() {
		
		ArrayList<PredictionResultBean> sudanResults = cropPredictionHelper.predictCrops("11.52", "29.88");
		assertTrue(isCultivatable(sudanResults));
		if(sudanResults != null) {
			for(PredictionResultBean prediction : sudanResults) {
				if(prediction != null) {
					if("Wheat".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.5");
					}
				}
			}
		}
	}
	
	@Test
	public void testInNigeria() {
		
		ArrayList<PredictionResultBean> nigeriaResults = cropPredictionHelper.predictCrops("6.50", "6.50");
		assertTrue(isCultivatable(nigeriaResults));
		if(nigeriaResults != null) {
			for(PredictionResultBean prediction : nigeriaResults) {
				if(prediction != null) {
					if("Corn".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.5");
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.9");
					}
				}
			}
		}
	}
	
	@Test
	public void testInAngola() {
		
		ArrayList<PredictionResultBean> angolaResults = cropPredictionHelper.predictCrops("-12.64", "16.44");
		assertTrue(isCultivatable(angolaResults));
		if(angolaResults != null) {
			for(PredictionResultBean prediction : angolaResults) {
				if(prediction != null) {
					if("Rice".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.5");
					} else if("Soybean".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.7");
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.9");
					}
				}
			}
		}
	}
	
	@Test
	public void testInZimbabwe() {
		
		ArrayList<PredictionResultBean> zimbabweResults = cropPredictionHelper.predictCrops("-19.47", "30.06");
		assertTrue(isCultivatable(zimbabweResults));
		if(zimbabweResults != null) {
			for(PredictionResultBean prediction : zimbabweResults) {
				if(prediction != null) {
					if("Rice".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.5");
					} else if("Soybean".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.9");
					}
				}
			}
		}
	}
	
	@Test
	public void testInBrazil() {
		
		ArrayList<PredictionResultBean> brazilResults = cropPredictionHelper.predictCrops("-16.80", "42.01");
		assertTrue(isCultivatable(brazilResults));
		if(brazilResults != null) {
			for(PredictionResultBean prediction : brazilResults) {
				if(prediction != null) {
					if("Corn".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.7");
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.9");
					}
				}
			}
		}
	}
	
	@Test
	public void testInUS() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("31.05", "-89.47");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Soybean".equals(prediction.getCropName())) {
						assertEquals(String.valueOf(prediction.getAccuracy()), "0.5");
					} else if("Wheat".equals(prediction.getCropName())) {
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
