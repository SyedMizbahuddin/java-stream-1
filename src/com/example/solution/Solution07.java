package com.example.solution;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

import com.example.domain.Genre;
import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

public class Solution07 {

	public static void main(String[] args) {
		MovieService movieService = InMemoryMovieService.getInstance();

		Collection<Movie> movies = movieService.findAllMovies();

		BiFunction<List<Genre>, String, Boolean> exist = (a, b) -> a.stream().anyMatch(g -> g.getName().equals(b));

		List<Movie> result = movies.stream()
				.filter(movie -> movie.getGenres().size() == 2)
				.filter(movie -> exist.apply(movie.getGenres(), "Drama"))
				.filter(movie -> exist.apply(movie.getGenres(), "Comedy"))
				.toList();

		System.out.print(result);

	}
}
