//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public abstract class HandF implements IHand {
//  private CardF firstCard;
//  private CardF secondCard;
//  private TexasHoldemHandEvaluator evaluator;  // Reference to the evaluator
//
//  // Constructor
//  public HandF(CardF firstCard, CardF secondCard) {
//    this.firstCard = firstCard;
//    this.secondCard = secondCard;
//    // Initialize the evaluator with a list of cards
//    List<CardF> cards = new ArrayList<>();
//    cards.add(firstCard);
//    cards.add(secondCard);
//    this.evaluator = new TexasHoldemHandEvaluator(cards);
//  }
//
//  // Method to ensure the hand is properly initialized
//  public void finalizeHand() throws Exception {
//    if (firstCard == null || secondCard == null) {
//      throw new Exception("Hand must contain exactly two cards");
//    }
//  }
//
//  // Implementing getTotalValue method using the evaluator
//  @Override
//  public int toGetHandSocre() {
//    return evaluator.calculateScore();  // Delegate to evaluator
//  }
//
//  // Implementing getHandCombination method using the evaluator
//  @Override
//  public String toGetHandCombination() {
//    return evaluator.getHandCombination();  // Delegate to evaluator
//  }
//
//  // Implementing describeHand method
//  @Override
//  public String describeHand() {
//    // Prepare components to include in the string
//    String firstCardDescription = firstCard != null ? firstCard.toString() : "None";
//    String secondCardDescription = secondCard != null ? secondCard.toString() : "None";
//    int totalValue = toGetHandSocre();  // Get the total value of the hand
//    String handCombination = toGetHandCombination();  // Get the hand combination description
//
//
//    // Construct the final string
//    return String.format("First card: %s, Second card: %s, Total Value: %d, Hand Combination: %s",
//        firstCardDescription, secondCardDescription, totalValue, handCombination);
//  }
//}

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a poker hand consisting of two cards in Texas Hold'em.
 * This class ensures the hand is properly initialized with two non-null cards
 * and uses an evaluator to assess the hand's score and combination.
 */
public abstract class HandF implements IHand {
  private CardF firstCard;
  private CardF secondCard;
  private TexasHoldemHandEvaluator evaluator;  // Reference to the evaluator

  /**
   * Constructs a HandF object with two cards.
   * Initializes an evaluator for these cards.
   *
   * @param firstCard The first card of the hand; must not be null.
   * @param secondCard The second card of the hand; must not be null.
   * @throws IllegalArgumentException if either card is null.
   */
  public HandF(CardF firstCard, CardF secondCard) {
    if (firstCard == null || secondCard == null) {
      throw new IllegalArgumentException("Both cards must be non-null");
    }
    this.firstCard = firstCard;
    this.secondCard = secondCard;

    List<CardF> cards = new ArrayList<>();
    cards.add(firstCard);
    cards.add(secondCard);
    this.evaluator = new TexasHoldemHandEvaluator(cards);
  }

  /**
   * Returns the total score of the hand as evaluated by the hand evaluator.
   *
   * @return the score of the hand.
   */
  @Override
  public int getHandScore() {
    return evaluator.calculateScore();
  }

  /**
   * Returns the hand combination type as evaluated by the hand evaluator.
   *
   * @return the description of the hand combination.
   */
  @Override
  public String getHandCombination() {
    return evaluator.getHandCombination();
  }

  /**
   * Provides a full description of the hand including the details of each card,
   * the total hand score, and the hand combination.
   *
   * @return a string describing the hand in detail.
   */
  @Override
  public String describeHand() {
    String firstCardDescription = firstCard != null ? firstCard.toString() : "None";
    String secondCardDescription = secondCard != null ? secondCard.toString() : "None";
    int totalValue = getHandScore();  // Use corrected method name
    String handCombination = getHandCombination();  // Use corrected method name

    return String.format("First card: %s, Second card: %s, Total Value: %d, Hand Combination: %s",
        firstCardDescription, secondCardDescription, totalValue, handCombination);
  }
}
