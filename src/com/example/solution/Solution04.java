package com.example.solution;

import java.util.List;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

public class Solution04 {

	public static void main(String[] args) {

		WorldDao worldService = InMemoryWorldDao.getInstance();

		List<Country> countries = worldService.findAllCountries();

		// Find the highest populated capital city
		/*
		 * From country - capital - then assosciated city map country to capital
		 */

		List<Integer> populations = countries.stream()
				.map(country -> country.getCities()
						.stream()
						.filter(city -> city.getId() == country.getCapital())
						.map(city -> city.getPopulation())
						.findFirst()
						.orElse(0))
				.toList();

		System.out.println(populations);

		int max = countries.stream()
				.map(country -> country.getCities()
						.stream()
						.filter(city -> city.getId() == country.getCapital())
						.map(city -> city.getPopulation())
						.findFirst()
						.orElse(0))
				.max(Integer::compare)
				.get();

		System.out.println(max);

	}

}
