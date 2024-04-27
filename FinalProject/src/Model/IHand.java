import java.util.Map;

/**
 * Defines the interface for a hand in a card game. This interface specifies the methods necessary
 * to obtain scores, descriptions, and strategic decisions related to a hand of cards.
 */
public interface IHand {

  /**
   * Calculates and returns the score of the hand based on the numeric values of the cards.
   * The specific scoring rules are defined by the implementing class.
   *
   * @return The total score of the hand as an integer.
   */
  int getHandScore();

  /**
   * Generates and returns a descriptive string of the hand. This might include the type of hand,
   * the cards in the hand, and other relevant information as defined by the implementing class.
   *
   * @return A string description of the hand.
   */
  String describeHand();

  /**
   * Identifies and returns the type of card combination represented by the hand.
   * Examples include "pair", "straight", or "flush", depending on the game rules.
   *
   * @return A string representing the type of hand combination.
   */
  String getHandCombination();

  /**
   * Provides a map of decisions based on the hand's position within the game context.
   * These decisions are typically strategic moves such as "bet", "hold", or "fold",
   * tailored to different positions like "early", "middle", or "late".
   *
   * @return A map where keys represent game positions and values represent the decisions.
   */
  Map<String, String> getHandPositionDecisions();

  /**
   * Provides a map of comments based on the hand's evaluation and position in the game.
   * These comments offer insights and recommendations tailored to specific game positions,
   * helping guide the player's strategy.
   *
   * @return A map where keys represent game positions and values contain descriptive comments.
   */
  Map<String, String> getHandPositionBasedComments();
}
