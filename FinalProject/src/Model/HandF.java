import java.util.ArrayList;
import java.util.List;

public abstract class HandF implements IHand {
  private CardF firstCard;
  private CardF secondCard;
  private TexasHoldemHandEvaluator evaluator;  // Reference to the evaluator

  // Constructor
  public HandF(CardF firstCard, CardF secondCard) {
    this.firstCard = firstCard;
    this.secondCard = secondCard;
    // Initialize the evaluator with a list of cards
    List<CardF> cards = new ArrayList<>();
    cards.add(firstCard);
    cards.add(secondCard);
    this.evaluator = new TexasHoldemHandEvaluator(cards);
  }

  // Method to ensure the hand is properly initialized
  public void finalizeHand() throws Exception {
    if (firstCard == null || secondCard == null) {
      throw new Exception("Hand must contain exactly two cards");
    }
  }

  // Implementing getTotalValue method using the evaluator
  @Override
  public int getTotalValue() {
    return evaluator.calculateScore();  // Delegate to evaluator
  }

  // Implementing getHandCombination method using the evaluator
  @Override
  public String toGetHandCombination() {
    return evaluator.getHandCombination();  // Delegate to evaluator
  }

  // Implementing describeHand method
  @Override
  public String describeHand() {
    // Prepare components to include in the string
    String firstCardDescription = firstCard != null ? firstCard.toString() : "None";
    String secondCardDescription = secondCard != null ? secondCard.toString() : "None";
    int totalValue = getTotalValue();  // Get the total value of the hand
    String handCombination = toGetHandCombination();  // Get the hand combination description

    // Construct the final string
    return String.format("First card: %s, Second card: %s, Total Value: %d, Hand Combination: %s",
        firstCardDescription, secondCardDescription, totalValue, handCombination);
  }
}