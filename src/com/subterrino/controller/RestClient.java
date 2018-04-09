package com.subterrino.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient<T> {

	public List<T> restGET(String restUrl){
		try {
			URL url = new URL(restUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("HTTP error code: "+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));	
			
			StringBuilder text = new StringBuilder();
			String line;			
			while ((line = br.readLine()) != null) {
				text.append(line + "\n");
			}
			br.close();
			conn.disconnect();
			
			ObjectMapper mapper = new ObjectMapper();
			List<T> object = mapper.readValue(text.toString(), new TypeReference<List<T>>(){});

			return object;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void restPOST(String restUrl, List<T> objectList) {
		//https://stackoverflow.com/questions/21404252/post-request-send-json-data-java-httpurlconnection
	}
}
