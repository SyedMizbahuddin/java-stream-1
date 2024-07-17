package com.example.solution;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

public class Solution01 {

	public static void main(String[] args) {
		MovieService movieService = InMemoryMovieService.getInstance();

		Collection<Movie> movies = movieService.findAllMovies();

		Map<String, Long> result = movies.stream()
				.flatMap(movie -> movie.getDirectors()
						.stream())
				.collect(Collectors.groupingBy(x -> x.getName(), Collectors.counting()));

		System.out.println(result);

		Map<Object, List<Object>> result2 = movies.stream()
				.flatMap(movie -> movie.getDirectors()
						.stream()
						.map(director -> new AbstractMap.SimpleEntry<String, String>(director.getName(), movie
								.getTitle())))
				.collect(Collectors.groupingBy(x -> x.getKey(), Collectors.mapping(x -> x.getValue(), Collectors
						.toList())));

		System.out.println(result2);

	}
}
