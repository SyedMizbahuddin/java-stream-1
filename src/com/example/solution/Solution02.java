package com.example.solution;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

public class Solution02 {

	public static void main(String[] args) {

		WorldDao worldService = InMemoryWorldDao.getInstance();

		List<Country> countries = worldService.findAllCountries();

		Map<Object, List<Object>> result = countries.stream()
				.flatMap(country -> country.getCities()
						.stream()
						.map(city -> new AbstractMap.SimpleEntry<>(country.getName(), city.getName())))
				.collect(Collectors.groupingBy(entry -> entry.getKey(), Collectors.mapping(entry -> entry.getValue(),
						Collectors.toList())));

		System.out.println(result);

		List<SimpleEntry<String, Integer>> result2 = countries.stream()
				.map(country -> new AbstractMap.SimpleEntry<>(country.getName(), country.getCities()
						.stream().map(city -> city.getPopulation()).max((a, b) -> (a - b)).orElse(0))).collect(
								Collectors.toList());

		System.out.println(result2);
		// this is country to city not continent

		Map<String, Optional<Integer>> result3 = countries.stream()
				.map(country -> country.getCities()
						.stream()
						.map(city -> new ContinentCityPair(country.getContinent(), city.getPopulation()))
						.toList())
				.flatMap(Collection::stream)
				.collect(Collectors
						.groupingBy(ContinentCityPair::getContinent, Collectors.mapping(
								ContinentCityPair::getPopulation, Collectors.maxBy(Integer::compareTo))

						));

		System.out.println(result3);

	}

	static class ContinentCityPair {
		String continent;
		int population;

		public ContinentCityPair(String continent, int population) {
			super();
			this.continent = continent;
			this.population = population;
		}

		public String getContinent() {
			return continent;
		}

		public int getPopulation() {
			return population;
		}

	}
}
