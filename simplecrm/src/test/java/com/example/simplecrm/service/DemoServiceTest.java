package com.example.simplecrm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DemoServiceTest {

    private DemoService demoService;

    @BeforeEach
    public void init() {
        demoService = new DemoService();
    }

    @Test
    public void testAdd() {
        // 1. SETUP / ARRANGE
        // Create the instance of the class that we want to test
        // DemoService demoService = new DemoService();

        // Define the expected result
        int expectedResult = 8;

        // 2. EXECUTE / ACT
        // Call the method that we want to test
        int actualResult = demoService.add(3, 5);

        // 3. ASSERT
        // Compare the actual result with the expected result
        assertEquals(expectedResult, actualResult, "3 + 5 should be 8");
    }

    @Test
    public void testSubtract() {
        // 1. SETUP
        // Create the instance of the class that we want to test
        // DemoService demoService = new DemoService();

        // Define the expected result
        int expectedResult = 2;

        // 2. EXECUTE
        // Call the method that we want to test
        int actualResult = demoService.subtract(5, 3);

        // 3. ASSERT
        // Compare the actual result with the expected result
        assertEquals(expectedResult, actualResult, "5 - 3 should be 2");
    }

    @Test
    public void testMultiply() {
        // 1. SETUP / ARRANGE
        int expectedResult = 15;

        // ACT
        int actualResult = demoService.multiply(3, 5);

        // ASSERT
        assertEquals(expectedResult, actualResult, "3 * 5 should be 15");
    }

    @Test
    public void testDivide() {
        // 1. SETUP / ARRANGE
        int expectedResult = 2;

        // ACT
        int actualResult = demoService.divide(10, 5);

        // ASSERT
        assertEquals(expectedResult, actualResult, "10 / 5 should be 2");
    }

    // To test a method throws an exception, use assertThrows
    @Test
    public void divide_byZero_throwsArithmeticException() {

        assertThrows(ArithmeticException.class, () -> demoService.divide(10, 0));
    }

    // @Test
    // public void testIsEven() {
    // // 1. SETUP / ARRANGE
    // boolean expectedResult = true;

    // // ACT
    // boolean actualResult = demoService.isEven(10, 2);

    // // ASSERT
    // assertEquals(expectedResult, actualResult, "10 is even when divided by 2");
    // }

    // @Test
    // public void testIsEven() {
    // // 1. SETUP / ARRANGE

    // // ACT
    // boolean actualResult1 = demoService.isEven(2);
    // boolean actualResult2 = demoService.isEven(3);
    // // ASSERT
    // assertEquals(true, actualResult1, "2 is an even number");
    // assertEquals(false, actualResult2, "3 is not an even number");
    // }
    // Unit test does not mean 1 test for 1 method
    // refers to testing a unit of functionality
    // a method may have multiple behaviours
    // seperate test, each test should check a single behaviour

    // How to design test cases
    /*
     * 1. What is the purpose of the method?
     * to test whether an int is an even number
     * 2. What are the valid input ranges and equivalent partitions (groups)?
     * inputs: Integer.MIN_VALUE to Integer.MAX_VALUE
     * group of even numbers : .., -2, 0, 2, 4, ...
     * group of odd numbers: ..., -1, 1, 3, 5, ...
     * 3. Boundary values / Edge cases
     * Integer.MIN_VALUE and Integer.MAX_VALUE
     * 
     * Test cases
     * 1. test a number is even returns true
     * 2. test a number is odd returns false
     * 3. test Integer.MIN_VALUE returns true (..8)
     * 4. test Integer.MAX_VALUE returns false (...7)
     * 
     * methodName_scenario_expectedOutcome
     * isEven_evenNumber_returnsTrue()
     * isEven_oddNumber_returnsFalse()
     * isEven_minInt_returnsTrue()
     * isEven_maxInt_returnsFalse()
     * 
     */

    @Test
    public void isEven_evenNumber_returnsTrue() {
        int number = 4;

        boolean result = demoService.isEven(number);

        assertEquals(true, result, number + " should be even");
    }

    @Test
    public void isEven_oddNumber_returnsFalse() {
        int number = 5;
        boolean result = demoService.isEven(number);
        assertEquals(false, result, number + " should not be even");
    }

    @Test
    public void isEven_minInt_returnsTrue() {
        int number = Integer.MIN_VALUE;
        boolean result = demoService.isEven(number);
        assertEquals(true, result, number + " should be even");
    }

    @Test
    public void isEven_maxInt_returnsFalse() {
        int number = Integer.MAX_VALUE;
        boolean result = demoService.isEven(number);
        assertEquals(false, result, number + " should not be even");
    }

}
