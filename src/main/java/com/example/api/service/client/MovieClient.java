package com.example.api.service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class MovieClient {

	@Value("${themoviedb.api.key}")
	private String movieKey;

	@Value("${themoviedb.api.url}")
	private String movieUrl;
	
	/**
	 * Example Url: https://api.themoviedb.org/3/movie/550?api_key=
	 * c04496000528dacd99fdfdac3cd27c0d Method return endpoint genre movies
	 * 
	 * @param int genreId
	 */
	public String getGenreMovie(int genreId) {
		RestTemplate restTemplate = new RestTemplate();
		String movie = restTemplate.getForObject(movieUrl, String.class,
				genreId, movieKey);
		return movie;
	}
}
