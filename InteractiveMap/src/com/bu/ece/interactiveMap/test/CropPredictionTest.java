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
						assertEquals("0.65", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInSouthAfrica() {
		
		ArrayList<PredictionResultBean> southAfricaResults = cropPredictionHelper.predictCrops("-34.52466147177173", "20.0830078125");
		assertTrue(isCultivatable(southAfricaResults));
		if(southAfricaResults != null) {
			for(PredictionResultBean prediction : southAfricaResults) {
				if(prediction != null) {
					if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.65", String.valueOf(prediction.getAccuracy()));
					} else if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInSudan() {
		
		ArrayList<PredictionResultBean> sudanResults = cropPredictionHelper.predictCrops("11.005904459659464", "28.828125");
		assertTrue(isCultivatable(sudanResults));
		if(sudanResults != null) {
			for(PredictionResultBean prediction : sudanResults) {
				if(prediction != null) {
					if("Soybean".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.65", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
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
						assertEquals("0.8", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.65", String.valueOf(prediction.getAccuracy()));
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
					if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInZimbabwe() {
		
		ArrayList<PredictionResultBean> zimbabweResults = cropPredictionHelper.predictCrops("-20.0559313", "29.597168");
		assertTrue(isCultivatable(zimbabweResults));
		if(zimbabweResults != null) {
			for(PredictionResultBean prediction : zimbabweResults) {
				if(prediction != null) {
					if("Soybean".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.65", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInMadagascar() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("-19.445874298215937", "48.636474609375");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Soybean".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.65", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInMozambique() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("-16.8886597873816", "38.4521484375");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.8", String.valueOf(prediction.getAccuracy()));
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.65", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInZambia() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("-15.47485740268724", "30.179443359375");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.65", String.valueOf(prediction.getAccuracy()));
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInNamibia() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("-27.186242185608737", "16.907958984375");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInTanzania() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("-8.211490323420682", "38.73779296875");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Soybean".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInKenya() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("-1.889305962837306", "40.0341796875");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Soybean".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.65", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInSomalia() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("2.986927393334876", "46.2744140625");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Soybean".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInCameroon() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("3.162455530237848", "10.986328125");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Soybean".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInUganda() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("0.7909904981540058", "31.728515625");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Soybean".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInGhana() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("6.402648405963896", "-1.0546875");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Soybean".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.65", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.8", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInSenegal() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("14.774882506516272", "-16.083984375");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInMorocco() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("34.95799531086792", "-5.625");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Soybean".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInTunisia() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("36.527294814546245", "9.66796875");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.65", String.valueOf(prediction.getAccuracy()));
					} else if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.5", String.valueOf(prediction.getAccuracy()));
					} else if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInIndia() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("12.382928338487396", "79.365234375");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Wheat".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Rice".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInBrazil() {
		
		ArrayList<PredictionResultBean> brazilResults = cropPredictionHelper.predictCrops("-16.29509667958126", "-40.595855712890625");
		assertTrue(isCultivatable(brazilResults));
		if(brazilResults != null) {
			for(PredictionResultBean prediction : brazilResults) {
				if(prediction != null) {
					if("Barly".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					}
				}
			}
		}
	}
	
	@Test
	public void testInUS() {
		
		ArrayList<PredictionResultBean> usResults = cropPredictionHelper.predictCrops("36.65189429289056", "-103.040771484375");
		assertTrue(isCultivatable(usResults));
		if(usResults != null) {
			for(PredictionResultBean prediction : usResults) {
				if(prediction != null) {
					if("Corn".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
					} else if("Soybean".equals(prediction.getCropName())) {
						assertEquals("0.3", String.valueOf(prediction.getAccuracy()));
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
