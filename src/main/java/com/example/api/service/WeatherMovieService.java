package com.example.api.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.enums.EnumGenre;
import com.example.api.service.client.MovieClient;
import com.example.api.service.client.WeatherClient;

@Service
public class WeatherMovieService {

	@Value("${themoviedb.api.key}")
	private String movieKey;

	@Value("${themoviedb.api.url}")
	private String movieUrl;

	@Value("${openweathermap.api.url}")
	private String weatherUrl;
	
	@Value("${openweathermap.api.location}")
	private String location;
	
	@Value("${openweathermap.api.key}")
	private String key;

	@Autowired
	private WeatherClient weatherClient;
	
	@Autowired
	private MovieClient movieClient;

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

		RestTemplate restTemplate = new RestTemplate();
		String weather = restTemplate.getForObject(weatherUrl, String.class,
				location, key);

		JSONObject json = new JSONObject(weather);
		String jsonTemp = json.getJSONObject("main").getString("temp");

		double doubleValue = Double.parseDouble(jsonTemp);
		int jsonTempInt = (int) doubleValue;
		jsonTempInt = (jsonTempInt - 271);

		return jsonTempInt;
	}

	/**
	 * 
	 * Method return category Movies
	 * 
	 * @param Integer
	 *            weather
	 * @return String
	 */
	public String callMovies(Integer weather) {
		String movies = null;

		if (weather == 40) {
			int genreId = EnumGenre.ACTION.getValue();
			movies = createJsonGenreMovie(genreId);
		} else if (weather >= 36 && weather <= 40) {
			int genreId = EnumGenre.COMEDY.getValue();
			movies = createJsonGenreMovie(genreId);
		} else if (weather >= 20 && weather <= 35) {
			int genreId = EnumGenre.ANIMATION.getValue();
			movies = createJsonGenreMovie(genreId);
		} else if (weather >= 0 && weather <= 20) {
			int genreId = EnumGenre.TRILLER.getValue();
			movies = createJsonGenreMovie(genreId);
		} else if (weather < 0) {
			int genreId = EnumGenre.DOCUMENT.getValue();
			movies = createJsonGenreMovie(genreId);
		}

		return movies;
	}
	
	/**
	 * 
	 * Method return category Movies
	 * 
	 * @param Integer
	 *            weather
	 * @return String
	 */
	public String callMovies1(Integer weather) {
		String movies = null;

		if (weather == 40) {
			int genreId = EnumGenre.ACTION.getValue();
			movies = movieClient.getGenreMovie(genreId);
		} else if (weather >= 36 && weather <= 40) {
			int genreId = EnumGenre.COMEDY.getValue();
			movies = movieClient.getGenreMovie(genreId);
		} else if (weather >= 20 && weather <= 35) {
			int genreId = EnumGenre.ANIMATION.getValue();
			movies = movieClient.getGenreMovie(genreId);
		} else if (weather >= 0 && weather <= 20) {
			int genreId = EnumGenre.TRILLER.getValue();
			movies = movieClient.getGenreMovie(genreId);
		} else if (weather < 0) {
			int genreId = EnumGenre.DOCUMENT.getValue();
			movies = movieClient.getGenreMovie(genreId);
		}

		return movies;
	}

	/**
	 * Example Url: https://api.themoviedb.org/3/movie/550?api_key=
	 * c04496000528dacd99fdfdac3cd27c0d Method return endpoint genre movies
	 * 
	 * @param int genreId
	 */
	private String createJsonGenreMovie(int genreId) {
		RestTemplate restTemplate = new RestTemplate();
		String movie = restTemplate.getForObject(movieUrl, String.class,
				genreId, movieKey);
		return movie;
	}

	/**
	 * Method for mount movies
	 * 
	 * @param
	 * @return String
	 */
	public List<String> mountMovieByGenre() throws JSONException {
		String movies = null;
		Integer weather = this.callWeather();

		if (weather != null) {
			movies = this.callMovies(weather);
		}

		// movies json modify
		JSONObject results = new JSONObject(movies);
		List<String> list = new ArrayList<String>();
		JSONArray array = results.getJSONArray("results");
		for (int i = 0; i < array.length(); i++) {
			list.add("Nota: "
					+ array.getJSONObject(i).getString("vote_average"));
			list.add("Filme: " + array.getJSONObject(i).getString("title"));
			list.add("-----------------------------------");
		}

		return list;
	}
	
	/**
	 * Method for mount movies
	 * 
	 * @param
	 * @return String
	 */
	public List<String> mountMovieByGenre1() throws JSONException {
		String movies = null;
		Integer weather = weatherClient.callWeather();

		if (weather != null) {
			movies = callMovies(weather);
		}

		// movies json modify
		JSONObject results = new JSONObject(movies);
		List<String> list = new ArrayList<String>();
		JSONArray array = results.getJSONArray("results");
		for (int i = 0; i < array.length(); i++) {
			list.add("Nota: "
					+ array.getJSONObject(i).getString("vote_average"));
			list.add("Filme: " + array.getJSONObject(i).getString("title"));
			list.add("-----------------------------------");
		}

		return list;
	}

}
