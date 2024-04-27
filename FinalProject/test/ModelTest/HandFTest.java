import static org.junit.Assert.*;

import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides unit tests for the {@code HandF} class, which represents a hand of cards.
 * This test class specifically tests the functionality of the {@code HandF} class including
 * initialization, exception handling, and core behaviors like scoring and describing hands.
 */
public class HandFTest {

  private HandF hand;
  private CardF card1;
  private CardF card2;

  /**
   * Sets up the test environment before each test method.
   * Initializes two cards and a hand containing these cards.
   * This method ensures that each test starts with a well-defined state of cards and hand.
   */
  @Before
  public void setUp() {
    card1 = new CardF("A", "S"); // Ace of Spades
    card2 = new CardF("K", "H"); // King of Hearts
    hand = new HandF(card1, card2) {};
  }

  /**
   * Tests the {@code HandF} constructor for handling null as the first card.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_NullFirstCard_ThrowsException() {
    new HandF(null, card2) {};
  }

  /**
   * Tests the {@code HandF} constructor for handling null as the second card.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_NullSecondCard_ThrowsException() {
    new HandF(card1, null) {};
  }

  /**
   * Tests the calculation of the hand's score.
   * Verifies that the score is non-negative and matches the expected value.
   */
  @Test
  public void testGetHandScore() {
    int score = hand.getHandScore();
    assertTrue("Score should be a positive integer", score >= 0);
    assertEquals("Score should be 23", 23, score);
  }

  /**
   * Tests retrieval of the hand's combination description.
   * Ensures the description is neither null nor empty, and matches the expected description.
   */
  @Test
  public void testGetHandCombination() {
    String combination = hand.getHandCombination();
    assertNotNull("Combination should not be null", combination);
    assertFalse("Combination should not be empty", combination.isEmpty());
    assertEquals("Combination should be 'Connected Cards'", "Connected Cards", combination);
  }

  /**
   * Tests the retrieval of position-based decisions for the hand.
   * Verifies that the returned map is neither null nor empty.
   */
  @Test
  public void testGetHandPositionDecisions() {
    Map<String, String> decisions = hand.getHandPositionDecisions();
    assertNotNull("Decisions map should not be null", decisions);
    assertFalse("Decisions map should not be empty", decisions.isEmpty());
  }

  /**
   * Tests the retrieval of position-based comments for the hand.
   * Verifies that the returned map is neither null nor empty.
   */
  @Test
  public void testGetHandPositionBasedComments() {
    Map<String, String> comments = hand.getHandPositionBasedComments();
    assertNotNull("Comments map should not be null", comments);
    assertFalse("Comments map should not be empty", comments.isEmpty());
  }

  /**
   * Tests the description of the hand.
   * Verifies that the description includes details of both cards, the total value, and the hand combination.
   */
  @Test
  public void testDescribeHand() {
    String description = hand.describeHand();
    assertNotNull("Description should not be null", description);
    assertTrue("Description should include the first card details", description.contains("A"));
    assertTrue("Description should include the second card details", description.contains("K"));
    assertTrue("Description should mention Total Value", description.matches(".*Total Value: \\d+.*"));
    assertTrue("Description should mention Hand Combination", description.contains("Hand Combination"));
  }
}
