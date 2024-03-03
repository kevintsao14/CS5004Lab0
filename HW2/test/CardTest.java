import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/*
Test for Card class
 */
public class CardTest {
  private Card cardHeart5;
  private Card cardSpade10;
  private Card cardDiamondAce;
  private Card cardClub3;

  @Before
  public void setUp() {
    cardHeart5 = new Card(Suit.HEARTS, 5);
    cardSpade10 = new Card(Suit.SPADES, 10);
    cardDiamondAce = new Card(Suit.DIAMONDS, 1); // Assuming Ace is represented as 1
    cardClub3 = new Card(Suit.CLUBS, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCardInvalidRankLower() {
    new Card(Suit.SPADES, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCardInvalidRankHigher() {
    new Card(Suit.HEARTS, 14);
  }

  @Test
  public void testGetSuit() {
    assertEquals(Suit.HEARTS, cardHeart5.getSuit());
    assertEquals(Suit.SPADES, cardSpade10.getSuit());
  }

  @Test
  public void testGetRank() {
    assertEquals(5, cardHeart5.getRank());
    assertEquals(10, cardSpade10.getRank());
  }

  @Test
  public void testGetColor() {
    assertEquals("Red", cardHeart5.getColor());
    assertEquals("Black", cardSpade10.getColor());
  }

  @Test
  public void testCompareTo() {
    assertTrue(cardHeart5.compareTo(cardSpade10) < 0);
    assertTrue(cardSpade10.compareTo(cardHeart5) > 0);
    assertEquals(0, cardHeart5.compareTo(new Card(Suit.HEARTS, 5)));
  }

  @Test
  public void testEquals() {
    assertTrue(cardHeart5.equals(new Card(Suit.HEARTS, 5)));
    assertFalse(cardHeart5.equals(cardSpade10));
  }

  @Test
  public void testHashCode() {
    assertEquals(cardHeart5.hashCode(), new Card(Suit.HEARTS, 5).hashCode());
    assertNotEquals(cardHeart5.hashCode(), cardSpade10.hashCode());
  }

  @Test
  public void testToString() {
    Card card = new Card(Suit.HEARTS, 12);
    assertEquals("toString should return correct format",
        "Card{suit=HEARTS, rank=12}", card.toString());
    assertNotNull("toString result should not be null", card.toString());
    assertEquals("Card{suit=HEARTS, rank=5}", cardHeart5.toString());
    assertEquals("Card{suit=SPADES, rank=10}", cardSpade10.toString());
  }
}
