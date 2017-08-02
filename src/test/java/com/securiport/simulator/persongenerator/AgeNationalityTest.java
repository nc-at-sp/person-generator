package com.securiport.simulator.persongenerator;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class AgeNationalityTest {
	
	private AgeNationalityGenerator _ang;
	private RandomSeed _R = new RandomSeed();
	private String _ageData;
//	private int seed = 1514271723;
	
	@Before
	public void before() {
		_ang = new AgeNationalityGenerator();
		_ageData = "C:/Users/Alexander/csv/ageData.json";
	}
	
	@Test
	public void getNextStepTest() {
		int trials = 5;
		for (int i=0; i<trials; i++) {
			try {
				World world = _ang.parseJson(_ageData);
				try {
					Continent continent = _ang.nextContinent(world, _R);
//					System.out.println(continent.getContinentName());
					try { 
						Regions region = _ang.nextRegion(continent, _R);
//						System.out.println(region.getRegionName());
						try {
							Countries country = _ang.nextCountry(region, _R);
							System.out.println(country.getCountryName());
							try { 
								Map<String, Double> ageDist = country.getAges();
								int age = _ang.nextAge(ageDist, _R);
								System.out.println(age);
								LocalDate birthdate = _ang.getDOB(age, _R);
								System.out.println(birthdate);
							} catch (AssertionError e) {
								e.printStackTrace();
							}
						} catch (AssertionError e) {
							e.printStackTrace();
						}
					} catch (AssertionError e) {
						e.printStackTrace();
					}
				} catch (AssertionError e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void nextCountryTest() throws FileNotFoundException {
		// a test to see if we can get to the next country:
		World world = _ang.parseJson(_ageData);
		Continent continent = _ang.nextContinent(world, _R);
		Regions region = _ang.nextRegion(continent, _R);
		Countries country = _ang.nextCountry(region, _R);
		System.out.println(country.getCountryName());
	}
}