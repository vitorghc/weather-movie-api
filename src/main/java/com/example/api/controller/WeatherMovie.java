package com.example.api.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.json.MovieResponse;
import com.example.api.service.WeatherMovieService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/movies")
public class WeatherMovie {

	@Autowired
	private WeatherMovieService weatherMovieService;

	@GetMapping
	@ApiOperation(value = "Find Movies by weather temperature", response = MovieResponse.class, responseContainer = "List")
	public MovieResponse getMovies1() throws JSONException, NullPointerException {
		MovieResponse jsonMovie = weatherMovieService.mountMovieByGenre();
		return jsonMovie;
	}

}
