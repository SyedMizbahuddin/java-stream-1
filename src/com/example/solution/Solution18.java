package com.example.solution;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

public class Solution18 {

	public static void main(String[] args) {

		MovieService movieService = InMemoryMovieService.getInstance();

		Collection<Movie> movies = movieService.findAllMovies();

		// Find the year where the maximum number of movie is available

		int year = movies.stream()
				.collect(Collectors.groupingBy(Movie::getYear, Collectors.counting()))
				.entrySet()
				.stream()
				.max((a, b) -> Long.compare(a.getValue(), b.getValue())).get().getKey();

		System.out.println(year);
	}

}
