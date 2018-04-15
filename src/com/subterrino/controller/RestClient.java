package com.subterrino.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient<T> {

	public List<T> request(String restUrl, String method, T classObject, Class<T> clazz){
		try {
			URL url = new URL(restUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();			
			conn.setDoInput(true);
			conn.setRequestMethod(method);
			conn.setRequestProperty("Accept", "application/json");
			
			if (classObject != null) {
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setDoOutput(true);
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(classObject);
				
				OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(json);
				wr.flush();
			}

			/*if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("HTTP error code: "+ conn.getResponseCode());
			}*/
			
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));	
				
				StringBuilder text = new StringBuilder();
				String line;			
				while ((line = br.readLine()) != null) {
					text.append(line + "\n");
				}
				br.close();
				conn.disconnect();
				
				List<T> objectList = new ArrayList<T>();
				JSONArray jsonArr = new JSONArray(text.toString());
				for (int i = 0; i < jsonArr.length(); i++) {
					ObjectMapper mapper = new ObjectMapper();				
					T object = (T) mapper.readValue(jsonArr.optJSONObject(i).toString(), clazz);
					objectList.add(object);
				}
							
				return objectList;
			} else {
				return null;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
}
