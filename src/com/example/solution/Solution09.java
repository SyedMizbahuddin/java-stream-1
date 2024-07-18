package com.example.solution;

import java.util.List;
import java.util.stream.Collectors;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

public class Solution09 {

	public static void main(String[] args) {

		WorldDao worldService = InMemoryWorldDao.getInstance();

		List<Country> countries = worldService.findAllCountries();

		List<CountryDensity> result = countries.stream()
				.filter(country -> country.getSurfaceArea() != 0)
				.map(country -> new CountryDensity(country.getName(), country.getPopulation() / country
						.getSurfaceArea()))
				.filter(pair -> pair.density() != 0)
				.sorted((a, b) -> Double.compare(b.density(), a.density()))
				.collect(Collectors.toList());

		System.out.println(result);

	}

	private static record CountryDensity(String country, double density) {
	}

}
