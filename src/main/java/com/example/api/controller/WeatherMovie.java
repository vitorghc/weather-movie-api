package com.example.api.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.service.WeatherMovieService;

@RestController
@RequestMapping("/weatherMovie")
public class WeatherMovie {

	@Autowired
	private WeatherMovieService weatherMovieService;

	/**
	 * Endpoint from call weatherMovieApp
	 * http://localhost:8080/weatherMovie/movies
	 * 
	 */
	@GetMapping(value = "/movies")
	@ApiOperation(value = "Find Movies by weather temperature", response = String.class, responseContainer = "List")
	public List<String> getMovies() throws JSONException, NullPointerException {
		List<String> jsonMovie = weatherMovieService.mountMovieByGenre();
		return jsonMovie;
	}

}
