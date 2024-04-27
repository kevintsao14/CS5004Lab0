import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Represents the model for a Texas Hold'em poker game, managing the current hand,
 * cards, and the evaluation of the hand. This class interacts with several other classes
 * to provide a complete model of a poker hand according to Texas Hold'em rules.
 */
public class HoldemModel {
  private HandF currentHand;
  private List<CardF> cards;
  private TexasHoldemHandEvaluator evaluator;
  private HandInfoDisplayFormat handInfo;

  /**
   * Constructs a new HoldemModel with an empty list of cards.
   */
  public HoldemModel() {
    this.cards = new ArrayList<>();
  }

  /**
   * Creates a hand of two cards using the specified values and suits.
   *
   * @param card1Value the value of the first card (e.g., "A", "10", "K")
   * @param card1Suit the suit of the first card (e.g., "H" for hearts)
   * @param card2Value the value of the second card
   * @param card2Suit the suit of the second card
   * @return the created hand which is an instance of {@code HandF}.
   * @throws IllegalArgumentException if the card values or suits are invalid.
   */
  public HandF createHand(String card1Value, String card1Suit, String card2Value, String card2Suit) {
    CardF card1 = new CardF(card1Value, card1Suit);
    CardF card2 = new CardF(card2Value, card2Suit);
    cards.clear();
    cards.add(card1);
    cards.add(card2);
    currentHand = new ConcreteHandF(card1, card2);
    evaluator = new TexasHoldemHandEvaluator(Arrays.asList(card1, card2));
    return currentHand;
  }

  /**
   * Retrieves a list of {@code CardF} objects representing the current cards in the game.
   * These cards are used in the current poker hand.
   *
   * @return A list of {@code CardF} objects currently being used in the game.
   */
  public List<CardF> getCards() {
    return cards;
  }

  /**
   * Gets the current poker hand being played or evaluated in the game.
   * This method returns the active hand, which is an instance of {@code HandF}.
   *
   * @return The current {@code HandF} object representing the hand being played.
   */
  HandF getCurrentHand() {
    return currentHand;
  }

  /**
   * Calculates the score of the current hand using the Texas Holdem hand evaluator.
   *
   * @return the score of the current hand.
   * @throws IllegalStateException if no hand has been initialized yet.
   */
  public int calculateScore() {
    if (currentHand == null) {
      throw new IllegalStateException("No hand initialized.");
    }
    return currentHand.getHandScore();
  }

  /**
   * Retrieves the hand combination description of the current hand.
   *
   * @return a string representing the hand combination.
   * @throws IllegalStateException if no hand has been initialized yet.
   */
  public String getHandCombination() {
    if (currentHand == null) {
      throw new IllegalStateException("No hand initialized.");
    }
    return currentHand.getHandCombination();
  }

  /**
   * Provides strategic decisions based on the current score of the hand.
   *
   * @return a map of decisions for various positions in the game based on the current hand's score.
   */
  public Map<String, String> getPositionDecisions() {
    int score = calculateScore(); // Use the evaluator to get the current hand's score.
    return evaluator.getPositionDecisions(score);
  }

  /**
   * Provides comments based on the position at the table and the evaluation of the current hand.
   *
   * @return a map containing comments for various positions based on the current hand evaluation.
   */
  public Map<String, String> getPositionBasedComments() {
    return evaluator.getPositionBasedComments();
  }
}
