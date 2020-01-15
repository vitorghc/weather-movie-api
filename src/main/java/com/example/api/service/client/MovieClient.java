package com.example.api.service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.json.MovieResponse;

@Service	
public class MovieClient {

	@Value("${themoviedb.api.key}")
	private String movieKey;

	@Value("${themoviedb.api.url}")
	private String movieUrl;
	
	public final RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * Example Url: https://api.themoviedb.org/3/movie/550?api_key=
	 * c04496000528dacd99fdfdac3cd27c0d Method return endpoint genre movies
	 * 
	 * @param int genreId
	 */
	public MovieResponse getGenreMovie(int genreId) {
		MovieResponse movie = restTemplate.getForObject(movieUrl, MovieResponse.class,
				genreId, movieKey);
		return movie;
	}
}
