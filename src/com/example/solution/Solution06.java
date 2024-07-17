package com.example.solution;

import java.util.List;
import java.util.stream.Collectors;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

public class Solution06 {

	public static void main(String[] args) {

		WorldDao worldService = InMemoryWorldDao.getInstance();

		List<Country> countries = worldService.findAllCountries();

		/*
		 * Sort the countries by number of their cities in descending order
		 * 
		 */

		List<Object> result = countries.stream()
				.sorted((a, b) -> b.getCities().size() - a.getCities().size())
				.collect(Collectors.mapping(a -> a.getName(), Collectors.toList()));

		System.out.println(result);
	}

}
