package com.example.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;

import org.json.JSONException;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.example.api.json.MovieResponse;
import com.example.api.json.Result;
import com.example.api.service.WeatherMovieService;

public class WeatherMovieControllerTest extends AbstractControllerTest {
	
	private static final String GET_MOVIES = "/movies";
	
	@MockBean
    private WeatherMovieService weatherMovieService;
	
	@Test
	public void getMoviesSuccess() throws Exception {
		givenCountTypesServiceReturnSuccess();
		whenCallGetMovies();
		thenExpectSuccessStatus();
	}
	
	
	//Given
	private void givenCountTypesServiceReturnSuccess() throws JSONException{
        when(weatherMovieService.mountMovieByGenre())
        		.thenReturn(MovieResponse.builder() 
        							.results(Arrays.asList(new Result("Filme 1", "9.2")))
        							.build());
    }
	private void whenCallGetMovies() throws Exception {
        response = mockMvc.perform(get(GET_MOVIES))
                .andReturn()
                .getResponse();
	}
	
	private void thenExpectSuccessStatus() {
		assertThat(HttpStatus.OK.value()).isEqualTo(response.getStatus());
	}
}
