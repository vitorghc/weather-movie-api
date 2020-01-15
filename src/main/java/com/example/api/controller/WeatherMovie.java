package com.example.api.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.api.service.WeatherMovieService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/movies")
public class WeatherMovie {

	@Autowired
	private WeatherMovieService weatherMovieService;

	/**
	 * Endpoint from call weatherMovieApp
	 * http://localhost:8080/weatherMovie/movies
	 * 
	 */
	@GetMapping
	@ApiOperation(value = "Find Movies by weather temperature", response = String.class, responseContainer = "List")
	public List<String> getMovies() throws JSONException, NullPointerException {
		List<String> jsonMovie = weatherMovieService.mountMovieByGenre();
		return jsonMovie;
	}
	
	@GetMapping(value = "/new")
	@ApiOperation(value = "Find Movies by weather temperature", response = String.class, responseContainer = "List")
	public List<String> getMovies1() throws JSONException, NullPointerException {
		List<String> jsonMovie = weatherMovieService.mountMovieByGenre1();
		return jsonMovie;
	}

}
