package com.example.api.service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.json.WeatherResponse;

@Service
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
	public Integer callWeather() {

		WeatherResponse weather = restTemplate.getForObject(weatherUrl, WeatherResponse.class,
				location, key);
		Integer temperature = Double.valueOf(weather.getWeather().getTemperature()).intValue() - 271;

		return temperature;
	}

}
