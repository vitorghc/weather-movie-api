package com.example.api.service;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.enums.EnumGenre;
import com.example.api.json.MovieResponse;
import com.example.api.service.client.MovieClient;
import com.example.api.service.client.WeatherClient;

@Service
public class WeatherMovieService {

	@Autowired
	private WeatherClient weatherClient;
	
	@Autowired
	private MovieClient movieClient;

	
	/**
	 * 
	 * Method return category Movies
	 * 
	 * @param Integer
	 *            weather
	 * @return String
	 */
	public MovieResponse getGenreMovies(Integer weather) {
		MovieResponse movies = null;

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
	 * Method for mount movies
	 * 
	 * @param
	 * @return String
	 */
	public MovieResponse mountMovieByGenre() throws JSONException {
		return getGenreMovies(weatherClient.callWeather());
	}

}
