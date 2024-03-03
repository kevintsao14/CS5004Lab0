import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;

/**
 * Tests for hand interface.
 */
public class HandTest {
  private Hand<Card> hand;
  private Card card1, card2, card3;

  @Before
  public void setUp() {
    hand = new ImplementHand().emptyHand();
    card1 = new Card(Suit.CLUBS, 3);
    card2 = new Card(Suit.HEARTS, 5);
    card3 = new Card(Suit.SPADES, 7);
    // Add these cards to the hand for most tests
    hand.add(card1);
    hand.add(card2);
    hand.add(card3);
  }

  @Test
  public void testIsEmptyInitially() {
    assertFalse(hand.isEmpty());
    hand = new ImplementHand().emptyHand(); // Override setup for this specific scenario
    assertTrue("Hand should be initially empty", hand.isEmpty());
    assertEquals("Hand size should be 0 initially", 0, hand.getSize());
  }

  @Test
  public void testAddCard() {
    Card newCard = new Card(Suit.DIAMONDS, 2);
    hand.add(newCard);
    assertEquals("Hand size should be 4 after adding a card", 4, hand.getSize());
    assertTrue(hand.find(newCard) != -1);
  }

  @Test
  public void testDiscardCard() {
    hand.discardCard(0); // Discard the first card
    assertEquals("Hand size should decrease by 1", 2, hand.getSize());
    assertTrue("Discarded card should not be in the hand", hand.find(card3) == -1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testDiscardCardFromEmptyHand() {
    hand = new ImplementHand().emptyHand();
    hand.discardCard(0); // Expecting an exception
  }

  @Test
  public void testGetCard() {
    assertEquals("Should retrieve the first card correctly", card3, hand.get(0));
    assertEquals("Should retrieve the second card correctly", card2, hand.get(1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardInvalidIndex() {
    hand.get(5);
  }

  @Test
  public void testFindCard() {
    int index1 = hand.find(card1);
    int index2 = hand.find(card3);
    assertTrue("Card1 should be found in the hand", index1 != -1);
    assertTrue("Card3 should be found in the hand", index2 != -1);
  }

  @Test
  public void testSortHand() {
    hand.sortHand(Comparator.comparing(Card::getRank));
    assertTrue(hand.get(0).getRank() <= hand.get(1).getRank()
        && hand.get(1).getRank() <= hand.get(2).getRank());
  }

  @Test
  public void testGetHandWithFilter() {
    Hand<Card> heartsHand = hand.getHand(c -> c.getSuit() == Suit.HEARTS);
    assertEquals("Filtered hand should contain only hearts", 1, heartsHand.getSize());
    assertTrue("Filtered hand should contain card2", heartsHand.find(card2) != -1);
  }

  @Test
  public void testRankSum() {
    assertEquals(card1.getRank() + card2.getRank() + card3.getRank(), hand.rankSum());
    hand.add(new Card(Suit.CLUBS, 2)); // Add another card to change the sum
    assertEquals(card1.getRank() + card2.getRank() + card3.getRank() + 2, hand.rankSum());
  }

  @Test
  public void testGetMap() {
    //I dont know how to test this
  }
  }