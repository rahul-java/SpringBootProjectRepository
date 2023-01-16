package com.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbRestApiProject2WithDbApplicationTests {

	private Calculator calculator=new Calculator();
	
	@Test
	void contextLoads() {
	}

	@Test
	void testSum()
	{
		//expected result
		int expectedResult=30;
		
		//actual result
		int actualResult = calculator.doSum(12, 4, 14);
		
		assertThat(actualResult).isEqualTo(expectedResult);
	}
	
	@Test
	void testMultiply()
	{
		//expected result
		int expectedResult=300;
		
		//actual result
		int actualResult = calculator.doMultiply(50, 6);
		
		assertThat(actualResult).isEqualTo(expectedResult);
	}
	
	@Test
	void testCompare()
	{	
		//actual result
		Boolean actualResult = calculator.compareTwoNums(5, 5);
		
		assertThat(actualResult).isTrue();
	}
}
