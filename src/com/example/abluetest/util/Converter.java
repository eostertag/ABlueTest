package com.example.abluetest.util;

public class Converter {

	public static double FahrenheitToCelsius(double value) {
		return (value - 32) * 5 / 9;
	}

	public static double CelsiusToFahrenheit(double value) {
		return (value * 9 / 5) + 32;
	}
}
