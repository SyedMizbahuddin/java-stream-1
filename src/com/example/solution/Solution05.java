package com.example.solution;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

public class Solution05 {

	public static void main(String[] args) {

		WorldDao worldService = InMemoryWorldDao.getInstance();

		List<Country> countries = worldService.findAllCountries();

		// Find the highest populated capital city of each continent
		/*
		 * From country - capital - then assosciated city map country to capital
		 */

		Map<String, Optional<Integer>> result = countries.stream()
				.map(country -> new ContinentCapitalPopulation(country.getContinent(), country.getCities()
						.stream()
						.filter(city -> city.getId() == country.getCapital())
						.map(city -> city.getPopulation())
						.findFirst()
						.orElse(0)))
				.collect(Collectors.groupingBy(ContinentCapitalPopulation::continent,
						Collectors.mapping(
								x -> x.population(), Collectors.maxBy(Integer::compareTo)

						)));

		System.out.println(result);

	}

	static record ContinentCapitalPopulation(String continent, int population) {
	}

}
