import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CardFTest {

  private CardF aceOfSpades;
  private CardF kingOfHearts;
  private CardF tenOfDiamonds;
  private CardF queenOfClubs;
  private CardF aceOfClubs;
  private CardF secondAceOfSpades;
  private CardF secondKingOfHearts;

  @Before
  public void setUp() {
    aceOfSpades = new CardF("A", "S");
    kingOfHearts = new CardF("K", "H");
    tenOfDiamonds = new CardF("10", "D");
    queenOfClubs = new CardF("Q", "C");
    secondAceOfSpades = new CardF("A", "S");
    secondKingOfHearts = new CardF("K", "H");
    aceOfClubs = new CardF("A", "C");
  }

  @Test
  public void whenCardIsValid_thenCorrectAttributesAreSet() {
    assertEquals("Expected value A for Ace of Spades", "A", aceOfSpades.getValue());
    assertEquals("Expected suit S for Ace of Spades", "S", aceOfSpades.getSuit());
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenValueIsInvalid_thenExceptionIsThrown() {
    new CardF("1", "S"); // "1" is not valid, valid values are [2-9, 10, J, Q, K, A]
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenSuitIsInvalid_thenExceptionIsThrown() {
    new CardF("K", "Hearts"); // Suit must be one of [S, H, C, D]
  }

  @Test
  public void whenCardIsFaceValue_thenNumericValueIsCorrect() {
    assertEquals("Numeric value for Ace should be 14", 14, aceOfSpades.getNumericValue());
    assertEquals("Numeric value for King should be 13", 13, kingOfHearts.getNumericValue());
    assertEquals("Numeric value for Queen should be 12", 12, queenOfClubs.getNumericValue());
    assertEquals("Numeric value for Jack should be 11", 11, new CardF("J", "D").getNumericValue());
  }

  @Test
  public void whenCardIsNumeric_thenNumericValueIsCorrect() {
    assertEquals("Numeric value for 10 should be 10", 10, tenOfDiamonds.getNumericValue());
    assertEquals("Numeric value for 2 should be 2", 2, new CardF("2", "H").getNumericValue());
  }

  @Test
  public void toStringReturnsExpectedFormat() {
    assertEquals("Expected format mismatch", "Value: A, Suit: S", aceOfSpades.toString());
    assertEquals("Expected format mismatch", "Value: 10, Suit: D", tenOfDiamonds.toString());
  }

  @Test
  public void testEquallWithSelf(){
    assertTrue("A card should equal to itself", aceOfSpades.equals(aceOfSpades));
    assertTrue("A card should equal to itself", tenOfDiamonds.equals(tenOfDiamonds));
  }

  @Test
  public void testNonEquality() {
    assertFalse("Cards with different suits should not be equal", aceOfSpades.equals(aceOfClubs));
    assertFalse("Cards with different values should not be equal", aceOfClubs.equals(queenOfClubs));
  }

  @Test
  public void testEqualsWithDifferentClass() {
    Object fakeCard = new Object();
    assertFalse("A card should not be equal to an object of a different class", aceOfSpades.equals(fakeCard));
  }

  @Test
  public void testHashCodeConsistencyWithEquals() {
    assertTrue("Equal objects must have equal hash codes", aceOfSpades.hashCode() == secondAceOfSpades.hashCode());
    assertTrue("Equal objects must have equal hash codes", secondKingOfHearts.hashCode() == kingOfHearts.hashCode());
  }

  @Test
  public void testDifferentHashCodes() {
    assertFalse("Non-equal objects should generally have different hash codes", kingOfHearts.hashCode() == tenOfDiamonds.hashCode());
    assertFalse("Non-equal objects should generally have different hash codes", queenOfClubs.hashCode() == secondAceOfSpades.hashCode());
  }
}

