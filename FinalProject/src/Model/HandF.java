import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a poker hand consisting of two cards in Texas Hold'em.
 * This class ensures the hand is properly initialized with two non-null cards
 * and uses an evaluator to assess the hand's score and combination. It implements
 * the {@code IHand} interface, providing methods to get scores, combinations,
 * positional decisions, and detailed descriptions of the hand.
 */
public abstract class HandF implements IHand {
  private CardF firstCard;
  private CardF secondCard;
  private TexasHoldemHandEvaluator evaluator;  // Reference to the evaluator

  /**
   * Constructs a {@code HandF} object with two cards.
   * This constructor initializes the hand with specified cards and sets up an evaluator
   * to assess the properties of the hand such as score and combination.
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
   * Provides strategic decisions based on the position at the table and the hand's score.
   * This method utilizes the evaluator to decide the best course of action for a given hand.
   *
   * @return a map of decision recommendations based on the hand's score.
   */
  @Override
  public Map<String, String> getHandPositionDecisions(){
    int score = getHandScore();
    return evaluator.getPositionDecisions(score);
  }

  /**
   * Provides comments based on the position at the table and the hand's evaluation.
   * These comments are useful for strategic insights and understanding hand strength in various scenarios.
   *
   * @return a map of position-based comments.
   */
  @Override
  public Map<String, String> getHandPositionBasedComments(){
    return evaluator.getPositionBasedComments();
  }

  /**
   * Provides a full description of the hand including the details of each card,
   * the total hand score, and the hand combination.
   *
   * @return a string describing the hand in detail, encompassing card details, total score, and combination.
   */
  @Override
  public String describeHand() {
    String firstCardDescription = firstCard != null ? firstCard.toString() : "None";
    String secondCardDescription = secondCard != null ? secondCard.toString() : "None";
    int totalValue = getHandScore();
    String handCombination = getHandCombination();

    return String.format("First card: %s, Second card: %s, Total Value: %d, Hand Combination: %s",
        firstCardDescription, secondCardDescription, totalValue, handCombination);
  }
}
