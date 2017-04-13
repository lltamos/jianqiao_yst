package com.alqsoft.utils;

import java.math.BigDecimal;

public class DoubleTest {
	
	public static void main(String[] args) {
		double a = 15.8000;
		double b = 0.001;
		BigDecimal n1 = new BigDecimal(Double.toString(a));
		BigDecimal n2 = new BigDecimal(Double.toString(b));
		BigDecimal  result = n1.multiply(n2);
		System.out.println(result);
	}
}
