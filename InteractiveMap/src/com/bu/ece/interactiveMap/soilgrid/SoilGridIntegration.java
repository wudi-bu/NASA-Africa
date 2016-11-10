package com.bu.ece.interactiveMap.soilgrid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SoilGridIntegration {

	private static final String SOIL_GRID_QUERY = "https://rest.soilgrids.org/query?lon=%s&lat=%s"; 
	
	public static JSONObject getSoilProperties(String latitude, String longitude) {
		
		URL url = null;
		JSONObject jsonObject = null;
	    try {
	    	url = new URL(String.format(SOIL_GRID_QUERY, longitude, latitude));
	        HttpURLConnection servletConnection = (HttpURLConnection) url.openConnection();
	        servletConnection.setRequestMethod("GET");
	        servletConnection.setRequestProperty("Content-type","application/x-www-form-urlencoded");
	        servletConnection.setDoOutput(true);
	        servletConnection.setUseCaches(false);
	        
	        InputStream stream = servletConnection.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	        StringBuilder response = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	response.append(line);
	        }
	        
	        reader.close();
	        String responseAsString = response.toString();
			
	        JSONParser parser = new JSONParser();
	        jsonObject = (JSONObject) parser.parse(responseAsString);

	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    return jsonObject;
	}
}
