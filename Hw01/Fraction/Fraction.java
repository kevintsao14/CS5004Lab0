/**
 * Represents the protocol for operations that can be performed on fractions.
 */
public interface Fraction extends Comparable<Fraction> {

  /**
   * Gets the numerator of the fraction.
   *
   * @return The numerator of the fraction.
   */
  int getNumerator();

  /**
   * Sets the numerator of the fraction.
   *
   * @param numerator The new numerator value.
   */
  void setNumerator(int numerator);

  /**
   * Gets the denominator of the fraction.
   *
   * @return The denominator of the fraction.
   */
  int getDenominator();

  /**
   * Sets the denominator of the fraction ensuring it's positive.
   *
   * @param denominator The new denominator value.
   * @throws IllegalArgumentException If the denominator is not positive.
   */
  void setDenominator(int denominator);

  /**
   * Converts the fraction to its double equivalent.
   *
   * @return The decimal representation of the fraction.
   */
  double toDouble();

  /**
   * Returns the reciprocal of the fraction.
   *
   * @return A new fraction instance representing the reciprocal.
   * @throws ArithmeticException If the fraction's numerator is zero.
   */
  Fraction reciprocal();

  /**
   * Adds another fraction to this fraction and returns the result.
   *
   * @param other The fraction to add to this one.
   * @return A new fraction representing the sum.
   */
  Fraction add(Fraction other);

  /**
   * Generates a simplified string representation of the fraction.
   *
   * @return The string representation of the fraction in its simplest form.
   */
  @Override
  String toString();
}
