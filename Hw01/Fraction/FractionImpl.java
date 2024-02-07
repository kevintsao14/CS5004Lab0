/**
 * This class represents a mathematical fraction with an integer numerator and a positive integer denominator.
 * It implements the Fraction interface and provides functionalities like simplification and addition to manipulate fractions.
 */
public class FractionImpl implements Fraction, Comparable<Fraction> {

  private int numerator;   // The numerator of the fraction
  private int denominator; // The denominator of the fraction

  /**
   * Constructs a FractionImpl instance with specified numerator and denominator.
   *
   * @param numerator The numerator of the fraction.
   * @param denominator The denominator of the fraction; must be positive and non-zero.
   * @throws IllegalArgumentException If the denominator is zero or negative.
   */
  public FractionImpl(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Denominator cannot be zero.");
    }
    if (denominator < 0) {
      numerator = -numerator; // Make the numerator negative if the denominator is negative
      denominator = -denominator; // Ensure the denominator is always positive
    }
    this.numerator = numerator;
    this.denominator = denominator;
    simplify();
  }

  /**
   * Calculates the greatest common divisor (GCD) of two integers.
   *
   * @param a The first integer.
   * @param b The second integer.
   * @return The GCD of a and b.
   */
  private static int gcd(int a, int b) {
    {
      if (b == 0) {
        return a;
      }
      return gcd(b, a % b);
    }
  }

  /**
   * Returns the numerator of the given fraction.
   *
   * @return The numerator.
   */
  @Override
  public int getNumerator() {
    return numerator;
  }

  /**
   * Sets the numerator of the given fraction and simplifies it.
   *
   * @param newNumerator The new numerator value.
   */
  @Override
  public void setNumerator(int newNumerator) {
    this.numerator = newNumerator;
    simplify();
  }

  /**
   * Returns the denominator of this fraction.
   *
   * @return The denominator.
   */
  @Override
  public int getDenominator() {
    return denominator;
  }

  /**
   * Sets the denominator of this fraction and simplifies the fraction.
   * The denominator cannot be zero or negative.
   *
   * @param newDenominator The new denominator value; must be positive.
   * @throws IllegalArgumentException If the new denominator is zero or negative.
   */
  @Override
  public void setDenominator(int newDenominator) {
    if (newDenominator <= 0) {
      throw new IllegalArgumentException("Denominator must be positive");
    }
    this.denominator = newDenominator;
    simplify();
  }

  /**
   * Returns the double representation of this fraction.
   *
   * @return The fraction as a double.
   */
  @Override
  public double toDouble() {
    return (double) numerator / denominator;
  }

  /**
   * Returns the reciprocal of this fraction.
   *
   * @return A new FractionImpl instance representing the reciprocal of this fraction.
   * @throws ArithmeticException If the numerator is zero, as the reciprocal would be undefined.
   */
  @Override
  public Fraction reciprocal() {
    if (numerator == 0) {
      throw new ArithmeticException("Cannot take reciprocal of a fraction with numerator 0");
    }
    return new FractionImpl(denominator, numerator); // Return new FractionImpl with inverted numerator and denominator
  }

  /**
   * Adds another fraction to this fraction and returns the result as a new fraction.
   *
   * @param other The fraction to add to this one.
   * @return A new FractionImpl instance representing the sum of this fraction and the other fraction.
   * @throws IllegalArgumentException If the other fraction is not an instance of FractionImpl.
   */
  @Override
  public Fraction add(Fraction other) {
    FractionImpl otherFrac = (FractionImpl) other;
    int commonDenominator = this.denominator * otherFrac.denominator;
    int numeratorThis = this.numerator * otherFrac.denominator;
    int numeratorOther = otherFrac.numerator * this.denominator;
    int sumNumerator = numeratorThis + numeratorOther;
    return new FractionImpl(sumNumerator, commonDenominator);
  }

  /**
   * Compares this fraction with another fraction.
   *
   * @param other The fraction to compare with this one.
   * @return A negative integer, zero, or a positive integer as this fraction is less than, equal to, or greater than the specified fraction.
   * @throws IllegalArgumentException If the other fraction is not an instance of FractionImpl.
   */
  @Override
  public int compareTo(Fraction other) {
    FractionImpl otherFrac = (FractionImpl) other;
    // Cross multiply to compare fractions without converting them to decimals
    int lhs = this.numerator * otherFrac.denominator;
    int rhs = otherFrac.numerator * this.denominator;
    return Integer.compare(lhs, rhs);
  }

  /**
   * Simplifies the fraction to its simplest form.
   */
  private void simplify() {
    int gcd = gcd(Math.abs(numerator), denominator);
    numerator /= gcd;
    denominator /= gcd;
  }

  /**
   * Returns a string representation of this fraction in its simplest form.
   *
   * @return The string representation of the fraction.
   */
  @Override
  public String toString() {
    if (denominator == 1) {
      return String.valueOf(numerator); // Return just the numerator if denominator is 1
    }
    return numerator + " / " + denominator;
  }
}
