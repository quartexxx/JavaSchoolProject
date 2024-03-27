package model;

import java.util.List;

/**
 * The Fraction class represents a mathematical fraction with a numerator and
 * denominator.
 *
 * @author Remigiusz Kocialski
 * @version 1.0
 */
public class Fraction {

    // Private fields representing the numerator and denominator of the fraction
    private int numerator;
    private int denominator;

    /**
     * Initializes a fraction with a default value of 1/1.
     */
    public Fraction() {
        this.denominator = this.numerator = 1;
    }

    /**
     * Constructor to initialize a Fraction object with given numerator and
     * denominator.
     *
     * @param numerator Numerator of the fraction.
     * @param denominator Denominator of the fraction.
     * @throws InvalidValueException Thrown if denominator == 0.
     */
    public Fraction(int numerator, int denominator) throws InvalidValueException {
        if (denominator == 0) {
            throw new InvalidValueException("Denominator can't be 0");
        }
        this.denominator = denominator;
        this.numerator = numerator;
    }

    /**
     * Constructor for parsing fractions from strings.
     *
     * @param data A string representation of a fraction (as returned by
     * `toString`).
     * @throws InvalidValueException Thrown if the fraction representation is
     * invalid or denominator == 0.
     */
    public Fraction(String data) throws InvalidValueException {
        if (data == null) {
            throw new InvalidValueException("Fraction cannot be made from null");
        }
        List<String> parts = List.of(data.split("/"));

        if (parts.size() != 2) {
            throw new InvalidValueException("Fraction must be in the form of a/b");
        }
        if (!parts.get(0).matches("-?\\d+") || !parts.get(1).matches("-?\\d+")) {
            throw new InvalidValueException("Numerator and denominator must be integers");
        }
        int numerator = Integer.parseInt(parts.get(0));
        int denominator = Integer.parseInt(parts.get(1));
        if (denominator == 0) {
            throw new InvalidValueException("Denominator can't be 0");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Calculates the least common multiple (LCM) of two numbers.
     *
     * @param a First number.
     * @param b Second number.
     * @return LCM of the two numbers.
     */
    private int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    /**
     * Calculates the greatest common divisor (GCD) of two numbers.
     *
     * @param a First number.
     * @param b Second number.
     * @return GCD of the two numbers.
     */
    private int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    /**
     * Returns the denominator of the fraction.
     *
     * @return Denominator of the fraction.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Returns the numerator of the fraction.
     *
     * @return Numerator of the fraction.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Adds the current fraction to another fraction and returns the result.
     *
     * @param other Another fraction to add.
     * @return Resultant fraction after addition.
     */
    public Fraction add(Fraction other) {
        int lcm = lcm(this.denominator, other.denominator);
        int aMul = lcm / this.denominator;
        int bMul = lcm / other.denominator;
        Fraction out = new Fraction();
        out.numerator = this.numerator * aMul + other.numerator * bMul;
        out.denominator = lcm;
        out.reduce();
        return out;
    }

    /**
     * Subtracts another fraction from the current fraction and returns the
     * result.
     *
     * @param other Another fraction to subtract.
     * @return Resultant fraction after subtraction.
     */
    public Fraction sub(Fraction other) {
        int lcm = lcm(this.denominator, other.denominator);
        int aMul = lcm / this.denominator;
        int bMul = lcm / other.denominator;
        Fraction out = new Fraction();
        out.numerator = this.numerator * aMul - other.numerator * bMul;
        out.denominator = lcm;
        out.reduce();
        return out;
    }

    /**
     * Multiplies the current fraction by another fraction and returns the
     * result.
     *
     * @param other Another fraction to multiply.
     * @return Resultant fraction after multiplication.
     */
    public Fraction mul(Fraction other) {
        Fraction out = new Fraction();
        out.numerator = this.numerator * other.numerator;
        out.denominator = this.denominator * other.denominator;
        out.reduce();
        return out;
    }

    /**
     * Divides the current fraction by another fraction and returns the result.
     *
     * @param other Another fraction to divide by.
     * @return Resultant fraction after division.
     * @throws InvalidValueException If the other fraction's numerator is 0.
     */
    public Fraction div(Fraction other) throws InvalidValueException {
        Fraction out = new Fraction();
        if (other.numerator == 0) {
            throw new InvalidValueException("Division by 0");
        }
        out.numerator = this.numerator * other.denominator;
        out.denominator = this.denominator * other.numerator;
        out.reduce();
        return out;
    }

    /**
     * Returns a string representation of the fraction in the form
     * "numerator/denominator".
     *
     * @return String representation of the fraction.
     */
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    /**
     * Reduces the fraction.
     */
    private void reduce() {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    /**
     * Returns a float representation of the fraction.
     *
     * @return Float representation of the fraction.
     */
    public float toFloat() {
        return (float) numerator / (float) denominator;
    }
}
