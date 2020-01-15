package com.example.api.service.client;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class WeatherClient {
	
	@Value("${openweathermap.api.url}")
	private String weatherUrl;
	
	@Value("${openweathermap.api.location}")
	private String location;
	
	@Value("${openweathermap.api.key}")
	private String key;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * 
	 * Example of API call weather Api: Example URl
	 * "http://api.openweathermap.org/data/2.5/weather?id=3467865&appid=8037b88948d6c798757e2399d27c0c5f"
	 * Method return weather by locationId and convert Kelvin to Celsius
	 * 
	 * @param
	 * @return int
	 * 
	 */
	public int callWeather() throws JSONException {

		String weather = restTemplate.getForObject(weatherUrl, String.class,
				location, key);

		JSONObject json = new JSONObject(weather);
		String jsonTemp = json.getJSONObject("main").getString("temp");

		double doubleValue = Double.parseDouble(jsonTemp);
		int jsonTempInt = (int) doubleValue;
		jsonTempInt = (jsonTempInt - 271);

		return jsonTempInt;
	}

}
