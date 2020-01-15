package com.example.api.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("vote_average")
	private String voteAverage;
	

}
