import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * This class provides unit tests for the HoldemModel class, which is part of a Texas Hold'em poker game model.
 * It tests the class's ability to handle hand creation, score calculation, and game strategy recommendations,
 * ensuring that all components function as expected according to Texas Hold'em rules.
 */
public class HoldemModelTest {
  private HoldemModel holdemModel;

  /**
   * Sets up a new HoldemModel instance before each test to ensure a fresh state.
   */
  @Before
  public void setUp() {
    holdemModel = new HoldemModel();
  }

  /**
   * Tests the constructor of the HoldemModel to ensure the cards list is initialized and empty.
   */
  @Test
  public void testHoldemModelConstructor() {
    assertNotNull("Cards list should not be null", holdemModel.getCards());
    assertTrue("Cards list should be empty upon initialization", holdemModel.getCards().isEmpty());
  }

  /**
   * Tests the createHand method with valid inputs to ensure it correctly creates a hand and initializes necessary components.
   */
  @Test
  public void testCreateHand() {
    HandF createdHand = holdemModel.createHand("A", "H", "K", "D");
    assertNotNull("Created hand should not be null", createdHand);
    assertEquals("Hand should contain exactly two cards", 2, holdemModel.getCards().size());

    // Instead of checking the evaluator directly, verify the outcome that would require the evaluator's involvement.
    try {
      int score = holdemModel.calculateScore();  // This method uses the evaluator
      assertTrue("Score should be a non-negative integer", score >= 0);
    } catch (IllegalStateException e) {
      fail("Evaluator should be initialized and score calculation should succeed after creating a hand.");
    }
  }


  /**
   * Tests the createHand method to confirm that it throws an IllegalArgumentException when invalid card values or suits are provided.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateHandWithInvalidValues() {
    holdemModel.createHand("11", "H", "B", "D");
  }

  /**
   * Tests the calculateScore method to ensure it throws an IllegalStateException when no hand has been initialized.
   */
  @Test(expected = IllegalStateException.class)
  public void testCalculateScoreWithNoHand() {
    holdemModel.calculateScore();
  }

  /**
   * Tests the calculateScore method to confirm it returns the correct score after a hand is created.
   */
  @Test
  public void testCalculateScore() {
    holdemModel.createHand("10", "S", "J", "S");
    int score = holdemModel.calculateScore();
    assertEquals("Score should match the expected value based on the hand", holdemModel.getCurrentHand().getHandScore(), score);
  }

  /**
   * Tests the getHandCombination method to ensure it throws an IllegalStateException when no hand has been initialized.
   */
  @Test(expected = IllegalStateException.class)
  public void testGetHandCombinationWithNoHand() {
    holdemModel.getHandCombination();
  }

  /**
   * Tests the getHandCombination method to confirm it returns the correct description of the hand after it's been created.
   */
  @Test
  public void testGetHandCombination() {
    holdemModel.createHand("Q", "C", "Q", "D");
    String combination = holdemModel.getHandCombination();
    assertEquals("Hand combination description should match the current hand's description", holdemModel.getCurrentHand().getHandCombination(), combination);
  }

  /**
   * Tests the getPositionDecisions method to ensure it returns a valid map of decisions based on the current hand's score.
   */
  @Test
  public void testGetPositionDecisions() {
    holdemModel.createHand("5", "H", "5", "C");
    Map<String, String> decisions = holdemModel.getPositionDecisions();
    assertNotNull("Decision map should not be null", decisions);
    assertFalse("Decision map should contain entries", decisions.isEmpty());
  }

  /**
   * Tests the getPositionBasedComments method to ensure it provides a valid map containing position-based comments.
   */
  @Test
  public void testGetPositionBasedComments() {
    holdemModel.createHand("3", "S", "7", "H");
    Map<String, String> comments = holdemModel.getPositionBasedComments();
    assertNotNull("Comments map should not be null", comments);
    assertFalse("Comments map should contain entries", comments.isEmpty());
  }
}
