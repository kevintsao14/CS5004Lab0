public interface Fraction {
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
   * @throws IllegalArgumentException If the fraction becomes invalid.
   */
  void setNumerator(int numerator);

  /**
   * Gets the denominator of the fraction.
   *
   * @return The denominator of the fraction.
   */
  int getDenominator();

  /**
   * Sets the denominator of the fraction.
   *
   * @param denominator The new denominator value.
   * @throws IllegalArgumentException If the denominator is not positive.
   */
  void setDenominator(int denominator);

  /**
   * Returns the scientific value (decimal) of the fraction.
   *
   * @return The decimal value of the fraction.
   */
  double toDouble();

  /**
   * Returns a simplified String representation of the fraction.
   *
   * @return The fraction as a simplified String.
   */
  String toString();

  /**
   * Returns the reciprocal of the fraction.
   *
   * @return The reciprocal of the fraction.
   * @throws ArithmeticException If the numerator is zero.
   */
  Fraction reciprocal();

  /**
   * Adds another fraction to this fraction.
   *
   * @param other The fraction to be added.
   * @return The result of adding the two fractions.
   */
  Fraction add(Fraction other);

  /**
   * Compares this fraction to another fraction.
   *
   * @param other The fraction to compare with.
   * @return A negative integer if (this < other), a positive integer if (this > other), and 0 otherwise.
   */
  int compareTo(Fraction other);
}
