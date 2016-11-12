package com.bu.ece.interactiveMap.utils;

public class ESoilUtils {

	public static boolean isEmpty(String inputString) {
		
		if(inputString == null || inputString.trim().length() == 0) {
			return true;
		}
		
		return false;
	}
		
	public static double convertStringToDouble(String stringToConvert, double defaultValueToReturn) {
		
		if(isEmpty(stringToConvert)) {
			return defaultValueToReturn;
		}
		
		try {
			return Double.parseDouble(stringToConvert);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		return defaultValueToReturn;
	}
}
