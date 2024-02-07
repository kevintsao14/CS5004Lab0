import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionTest {
  private Fraction testSimple;
  private Fraction negativeNum;
  private Fraction numIs0;

  @Before
  public void setUp() throws Exception {
    testSimple = new FractionImpl(2,4); // Simplified to 1/2
    negativeNum = new FractionImpl(-2,4); // Simplified to -1/2
    numIs0 = new FractionImpl(0,4); // Simplified to 0/1
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorZeroDenominatorThrowsException() {
    new FractionImpl(1, 0); // Attempting to create a fraction with a zero denominator
  }

  @Test
  public void testConstructorWithNegativeDenominator() {
    // Create a fraction with a negative denominator
    Fraction fraction = new FractionImpl(1, -2);

    // Verify the numerator is now negative, reflecting the negation of the denominator
    assertEquals("Numerator should be negated", -1, fraction.getNumerator());

    // Verify the denominator is now positive
    assertEquals("Denominator should be made positive", 2, fraction.getDenominator());
  }

  @Test
  public void testConstructorWithBothNegative() {
    // Create a fraction with both negative numerator and denominator
    Fraction fraction = new FractionImpl(-1, -2);

    // Verify the numerator is now positive, as two negatives make a positive
    assertEquals("Numerator should be positive when both numerator and denominator are negative", 1, fraction.getNumerator());

    // Verify the denominator is now positive
    assertEquals("Denominator should be made positive", 2, fraction.getDenominator());
  }

  @Test
  public void testGetNumerator() {
    assertEquals(1, testSimple.getNumerator());
    assertEquals(-1, negativeNum.getNumerator());
    assertEquals(0, numIs0.getNumerator());
  }

  @Test
  public void testSetNumerator() {
    testSimple.setNumerator(5);
    assertEquals(5, testSimple.getNumerator());
  }

  @Test
  public void testGetDenominator() {
    assertEquals(2, testSimple.getDenominator());
  }

  @Test
  public void testSetDenominator() {
    testSimple.setDenominator(5);
    assertEquals(5, testSimple.getDenominator());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetDenominatorMustBePositiveThrowsException() {
    testSimple.setDenominator(0);
  }

  @Test
  public void testToDouble() {
    assertEquals(0.5, testSimple.toDouble(), 0.00001);
    assertEquals(-0.5, negativeNum.toDouble(), 0.00001);
    assertEquals(0.0, numIs0.toDouble(), 0.00001);
  }

  @Test
  public void testToString() {
    assertEquals("1 / 2", testSimple.toString());
    assertEquals("-1 / 2", negativeNum.toString());
    assertEquals("0", numIs0.toString());
  }

  @Test
  public void testReciprocal() {
    Fraction reciprocal = testSimple.reciprocal();
    assertEquals(2, reciprocal.getNumerator());
    assertEquals(1, reciprocal.getDenominator());

    try {
      numIs0.reciprocal();
      fail("Expected ArithmeticException, but no exception was thrown.");
    } catch (ArithmeticException e) {
      // Expected exception
    }
  }

  @Test
  public void testAddSameDenominator() {
    Fraction result = testSimple.add(testSimple); // 1/2 + 1/2 = 2/2 = 1
    assertEquals("Numerator: 1", 1, result.getNumerator());
    assertEquals("Denominator: 1", 1, result.getDenominator());
  }

  @Test
  public void testAddDifferentDenominator() {
    Fraction fraction3 = new FractionImpl(1, 2); // 1/2
    Fraction result = testSimple.add(fraction3); // 1/2 + 1/2 = 2/2 = 1
    assertEquals("Numerator: 1", 1, result.getNumerator());
    assertEquals("Denominator: 1", 1, result.getDenominator());
  }

  @Test
  public void testAddNegativeFraction() {
    Fraction result = testSimple.add(negativeNum); // 1/2 + (-1/2) = 0
    assertEquals("Numerator should be 0", 0, result.getNumerator());
    assertEquals("Denominator should be 1", 1, result.getDenominator());
  }

  @Test
  public void testAddResultingInWholeNumber() {
    Fraction result = testSimple.add(testSimple); // 1/2 + 1/2 = 2/2 = 1
    assertEquals("Numerator should be 1", 1, result.getNumerator());
    assertEquals("Denominator should be 1", 1, result.getDenominator());
  }

  @Test
  public void testCompareTo() {
    Fraction larger = new FractionImpl(3, 4);
    assertTrue(testSimple.compareTo(larger) < 0);
    assertTrue(larger.compareTo(testSimple) > 0);
    assertEquals(0, testSimple.compareTo(new FractionImpl(2, 4)));
  }
}
