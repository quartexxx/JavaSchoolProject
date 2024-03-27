/*
 * Test class for Fraction operations
 * 
 * Author: Remigiusz Kocialski
 * Version: 1.0
 */
package com.remigiusz.basicfractions.model;

import model.InvalidValueException;
import model.Fraction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

/**
 * Test class for Fraction operations
 *
 * @author Remigiusz Kocialski
 * @version 1.0
 */
public class FractionsTests {

    double FLOAT_PRECISION = 0.000001;

    /**
     * Test addition operation for Fraction class
     *
     * @param frac1Str first fraction
     * @param frac2Str second fraction it means the test data is invalid)
     */
    @ParameterizedTest
    @CsvSource({"1/5, 1/7", "1/2, 1/3", "0/13, 4/3", "17/23, 1/2", "2/3, 13/4"})
    void testFractionAddition(String frac1Str, String frac2Str) {
        // Create two fractions
        Fraction frac1;
        Fraction frac2;
        try {
            frac1 = new Fraction(frac1Str);
            frac2 = new Fraction(frac2Str);
        } catch (InvalidValueException e) {
            fail("Invalid input values:  " + e.getMessage());
            return;
        }

        // Perform addition and check the result
        Fraction result = frac1.add(frac2);
        float actual = result.toFloat();
        float expected = (float) frac1.getNumerator() / frac1.getDenominator() + (float) frac2.getNumerator() / frac2.getDenominator();
        assertEquals(expected, actual, FLOAT_PRECISION, "Addition failed");
    }

    // Test subtraction operation for Fraction class
    @ParameterizedTest
    @CsvSource({"1/5, 1/7", "1/2, 1/3", "0/13, 4/3", "17/23, 1/2", "2/3, 13/4"})
    void testFractionSubtraction(String frac1Str, String frac2Str) {
        // Create two fractions
        Fraction frac1;
        Fraction frac2;
        try {
            frac1 = new Fraction(frac1Str);
            frac2 = new Fraction(frac2Str);
        } catch (InvalidValueException e) {
            fail("Invalid input values:  " + e.getMessage());
            return;
        }

        // Perform subtraction and check the result
        Fraction result = frac1.sub(frac2);
        float actual = result.toFloat();
        float expected = (float) frac1.getNumerator() / frac1.getDenominator() - (float) frac2.getNumerator() / frac2.getDenominator();
        assertEquals(expected, actual, FLOAT_PRECISION, "Subtraction failed");
    }

    // Test multiplication operation for Fraction class
    @ParameterizedTest
    @CsvSource({"1/5, 1/7", "1/2, 1/3", "0/13, 4/3", "17/23, 1/2", "2/3, 13/4"})
    void testFractionMultiplication(String frac1Str, String frac2Str) {
        // Create two fractions
        Fraction frac1;
        Fraction frac2;
        try {
            frac1 = new Fraction(frac1Str);
            frac2 = new Fraction(frac2Str);
        } catch (InvalidValueException e) {
            fail("Invalid input values:  " + e.getMessage());
            return;
        }

        // Perform multiplication and check the result
        Fraction result = frac1.mul(frac2);
        float actual = result.toFloat();
        float expected = ((float) frac1.getNumerator() / frac1.getDenominator()) * ((float) frac2.getNumerator() / frac2.getDenominator());
        assertEquals(expected, actual, FLOAT_PRECISION, "Multiplication failed");
    }

    // Test division operation for Fraction class
    @ParameterizedTest
    @CsvSource({"1/5, 1/7", "1/2, 1/3", "0/13, 4/3", "17/23, 1/2", "2/3, 13/4"})
    void testFractionDivision(String frac1Str, String frac2Str) {
        // Create two fractions
        Fraction frac1;
        Fraction frac2;
        try {
            frac1 = new Fraction(frac1Str);
            frac2 = new Fraction(frac2Str);
        } catch (InvalidValueException e) {
            fail("Invalid input values:  " + e.getMessage());
            return;
        }

        // Perform division and check the result
        Fraction result;
        try {
            result = frac1.div(frac2);
        } catch (InvalidValueException ex) {
            fail("Division by 0 attepted: " + ex.getMessage());
            return;
        }
        float actual = result.toFloat();
        float expected = ((float) frac1.getNumerator() / frac1.getDenominator()) / ((float) frac2.getNumerator() / frac2.getDenominator());
        assertEquals(expected, actual, FLOAT_PRECISION, "Division failed");
    }

    // Test handling of invalid division by zero
    @ParameterizedTest
    @CsvSource({"1/1, 1/0", "1/1, 0/1"})
    void testInvalidDivisionByZero(String frac1Str, String frac2Str) {
        assertThrows(InvalidValueException.class, () -> {
            // Create a fraction and attempt division by zero
            Fraction frac1 = new Fraction(frac1Str);
            Fraction frac2 = new Fraction(frac2Str);
            frac1.div(frac2);
        }, "Division by zero should throw InvalidValueException");
    }

    // Test handling of InvalidValueException for parsing invalid input
    @ParameterizedTest
    @CsvSource({"abc", "1/abc", "abc/1", "abc/abc", "1/2/3", "1/2/3/4"})
    void testInvalidValueException(String invalidInput) {
        // Check that parsing invalid input throws InvalidValueException
        assertThrows(InvalidValueException.class, () -> {
            new Fraction(invalidInput);
        }, "Parsing invalid input should throw InvalidValueException");
    }

    // Test handling of InvalidValueException for parsing null input
    @ParameterizedTest
    @NullSource
    void testNullValue(String invalidInput) {
        // Check that parsing invalid input throws InvalidValueException
        assertThrows(InvalidValueException.class, () -> {
            new Fraction(invalidInput);
        }, "Parsing invalid input should throw InvalidValueException");
    }

    // Test handling of InvalidValueException for parsing empty input
    @ParameterizedTest
    @EmptySource
    void testEmptyValue(String invalidInput) {
        // Check that parsing invalid input throws InvalidValueException
        assertThrows(InvalidValueException.class, () -> {
            new Fraction(invalidInput);
        }, "Parsing invalid input should throw InvalidValueException");
    }
}
