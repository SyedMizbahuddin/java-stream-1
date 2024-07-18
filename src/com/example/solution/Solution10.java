package com.example.solution;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

public class Solution10 {

	public static void main(String[] args) {

		WorldDao worldService = InMemoryWorldDao.getInstance();

		List<Country> countries = worldService.findAllCountries();

		Map<Object, Optional<ContinentGNP>> result = countries.stream()
				.map(country -> new ContinentGNP(country.getContinent(), country.getGnp()))
				.collect(Collectors.groupingBy(pair -> pair.continent(), Collectors.maxBy((a, b) -> Double.compare(
						a.gnp, b.gnp))));

		System.out.println(result);
	}

	private static record ContinentGNP(String continent, double gnp) {
	};
}
