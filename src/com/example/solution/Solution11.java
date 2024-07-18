package com.example.solution;

import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.dao.InMemoryWorldDao;
import com.example.dao.WorldDao;
import com.example.domain.Country;

public class Solution11 {

	public static void main(String[] args) {

		WorldDao worldService = InMemoryWorldDao.getInstance();

		List<Country> countries = worldService.findAllCountries();

		// Find the minimum, the maximum and the average population of each continent.

		Map<String, LongSummaryStatistics> result = countries.stream().collect(Collectors.groupingBy(
				Country::getContinent, Collectors.summarizingLong(Country::getPopulation)));

		System.out.println(result);
	}

}
