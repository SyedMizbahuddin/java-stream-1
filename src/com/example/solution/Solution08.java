package com.example.solution;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

public class Solution08 {

	public static void main(String[] args) {
		MovieService movieService = InMemoryMovieService.getInstance();

		Collection<Movie> movies = movieService.findAllMovies();

		Map<Integer, List<String>> result = movies.stream()
				.collect(Collectors.groupingBy(Movie::getYear, Collectors.mapping(Movie::getTitle, Collectors
						.toList())));

		System.out.println(result);

	}
}
