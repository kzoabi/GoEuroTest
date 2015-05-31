package com.goeuro.position.suggestion.api.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationAPI implements Serializable {

   private static final long serialVersionUID = -9195365595709172547L;

   private static Logger logger = Logger.getLogger(LocationAPI.class.getName());
   
   static final String API_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

   public LocationAPI() {
   }


   public JsonParser getData(String queryString) throws Exception {
	   long startTime = System.currentTimeMillis();
	   String getUri = API_URL + queryString;

	   long startTimeHTTP = System.currentTimeMillis();
	   URL obj = new URL(getUri);
	   HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	   //add request header
	   con.setRequestMethod("GET");
	   con.setRequestProperty("charset", "utf-8");
	   con.setRequestProperty("Accept", "application/json");
	   BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
	   logger.info("LocationAPI::getData - Fetch HTTP time: " + (System.currentTimeMillis() - startTimeHTTP));

	   String inputLine;
	   StringBuffer response = new StringBuffer();
	   while ((inputLine = rd.readLine()) != null) {
		   response.append(inputLine);
	   }
	   rd.close();
	   if (con.getResponseCode() > 399) {
		   Exception exception;
		   try {
			   exception = new Exception("An exception occurred during getting data from GoEuro: \n" + getUri + "\nResponse:" + response.toString());
		   } catch(Exception e) {
			   String errorMsg = "An exception occurred during getting data from GoEuro: \n" + getUri + "\nErrorCode: " + con.getResponseCode() + ". Response: " + response.toString();
			   logger.log(Level.SEVERE,"error:: " + errorMsg, e);
			   throw new Exception(errorMsg);
		   }
		   throw exception;
	   }
	   logger.info("LocationAPI::getData - response: " + response.toString()); 
	   ObjectMapper mapper = new ObjectMapper();
	   JsonNode rootNode = mapper.readTree(response.toString());
	   JsonParser jp = mapper.treeAsTokens(rootNode);
	   logger.info("LocationAPI::getData - Fetch time: " + (System.currentTimeMillis() - startTime));
	   return jp;
   }

}
