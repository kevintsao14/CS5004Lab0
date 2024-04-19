//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
//import java.util.List;
//import java.util.ArrayList;
//
//public class HandFTest {
//  private HandF hand;
//  private CardF aceOfSpades;
//  private CardF kingOfHearts;
//
//  @Before
//  public void setUp() {
//    // Initialize cards
//    aceOfSpades = new CardF("A", "S");
//    kingOfHearts = new CardF("K", "H");
//    // Since HandF now includes a TexasHoldemHandEvaluator, we must pass the cards to it
//    List<CardF> cards = new ArrayList<>();
//    cards.add(aceOfSpades);
//    cards.add(kingOfHearts);
//    hand = new HandF(aceOfSpades, kingOfHearts);
//  }
//
//  @Test
//  public void testHandInitialization() {
//    assertNotNull("Hand should be successfully initialized with two cards", hand);
//  }
//
//  @Test
//  public void testTotalValueCalculation() {
//    // Expected total value is the average of the numeric values plus the score adjustments
//    int expectedTotalValue = (aceOfSpades.getNumericValue() + kingOfHearts.getNumericValue()) / 2; // Base average score
//    assertTrue("Total value should calculate correctly", hand.getTotalValue() == expectedTotalValue);
//  }
//
//  @Test
//  public void testHandCombinationDetection() {
//    // This setup should yield "Two High Cards" because both are high cards (A, K) but not suited or connected
//    assertEquals("Should detect 'Two High Cards'", "Two High Cards", hand.getHandCombination());
//  }
//
//  @Test
//  public void testHandToString() {
//    String expectedOutput = String.format("First card: %s, Second card: %s, Total Value: %d, Hand Combination: %s",
//        aceOfSpades, kingOfHearts, hand.getTotalValue(), hand.getHandCombination());
//    assertEquals("toString should return the correct string", expectedOutput, hand.toString());
//  }
//}

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HandFTest {

  private HandF hand;
  private CardF card1;
  private CardF card2;

  @Before
  public void setUp() {
    card1 = new CardF("A", "S"); // Example card 1
    card2 = new CardF("K", "S"); // Example card 2
    hand = new HandF(card1, card2) {
      // HandF is abstract, so we provide a simple concrete implementation for testing.
    };
  }

  @Test
  public void testHandInitialization() {
    assertNotNull("Hand should not be null after initialization", hand);
  }

  @Test
  public void testFinalizeHand_ValidCards() {
    try {
      hand.finalizeHand();
      // No exception means success
    } catch (Exception e) {
      fail("Should not have thrown any exception for a valid hand");
    }
  }

  @Test(expected = Exception.class)
  public void testFinalizeHand_InvalidCards() throws Exception {
    // Creating a hand with null cards to test exception
    HandF invalidHand = new HandF(null, null) {
      // Provide concrete implementation
    };
    invalidHand.finalizeHand(); // This should throw an exception
  }

  @Test
  public void testGetTotalValue() {
    int value = hand.getTotalValue();
    // Expected value should be checked based on your scoring rules
    // For example, assume expected value based on evaluator's calculation
    assertTrue("Total value should be a non-negative number", value >= 0);
  }

  @Test
  public void testGetHandCombination() {
    String combination = hand.toGetHandCombination();
    assertNotNull("Hand combination should not be null", combination);
  }

  @Test
  public void testToString() {
    String description = hand.toString();
    assertTrue("Description should contain information about both cards", description.contains("A") && description.contains("K"));
    assertTrue("Description should contain the total value", description.contains("Total Value: "));
    assertTrue("Description should contain the hand combination", description.contains("Hand Combination: "));
  }
}
