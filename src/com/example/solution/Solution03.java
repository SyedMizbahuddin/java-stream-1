package com.example.solution;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

public class Solution03 {

	public static void main(String[] args) {

		MovieService movieService = InMemoryMovieService.getInstance();

		Collection<Movie> movies = movieService.findAllMovies();

		// Find the number of genres of each director's movies

		List<DirectorGenrePair> result = movies.stream()
				.map(movie -> movie.getDirectors().stream()
						.map(director -> movie.getGenres()
								.stream()
								.map(genre -> new DirectorGenrePair(director.getName(), genre.getName())).toList())
						.toList())
				.flatMap(Collection::stream)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());

		// director , genre
		System.out.println(result);

		Map<String, List<DirectorGenrePair>> result2 = movies.stream()
				.map(movie -> movie.getDirectors().stream()
						.map(director -> movie.getGenres()
								.stream()
								.map(genre -> new DirectorGenrePair(director.getName(), genre.getName())).toList())
						.toList())
				.flatMap(Collection::stream)
				.flatMap(Collection::stream)
				.distinct()
				.collect(Collectors.groupingBy(DirectorGenrePair::getDirector));

		System.out.println(result2);

		// director , genreCount
	}

	static class DirectorGenrePair {
		String director;
		String genre;

		public DirectorGenrePair(String director, String genre) {
			super();
			this.director = director;
			this.genre = genre;
		}

		public String getDirector() {
			return director;
		}

		public String getGenre() {
			return genre;
		}

		@Override
		public String toString() {
			return "DirectorGenrePair [director=" + director + ", genre=" + genre + "]";
		}

	}

}
