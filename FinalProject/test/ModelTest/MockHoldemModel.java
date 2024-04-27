/**
 * A mock version of the {@code HoldemModel} class used for testing purposes.
 * This class allows for the simulation of various behaviors by providing
 * predefined responses for the hand, decisions, and comments associated with
 * a game of Hold'em poker.
 *
 * @author The user (theoretical)
 */
import java.util.Map;

class MockHoldemModel extends HoldemModel {
  /**
   * The predefined {@code HandF} object representing the hand to be returned
   * when {@code createHand} is called.
   */
  private HandF hand;

  /**
   * A map storing the predefined decisions for player positions.
   * Key is the position identifier, and the value is the decision.
   */
  private Map<String, String> decisions;

  /**
   * A map storing comments based on player positions.
   * Key is the position identifier, and the value is the corresponding comment.
   */
  private Map<String, String> comments;

  /**
   * Constructs a new {@code MockHoldemModel} with predefined hand, decisions,
   * and comments.
   *
   * @param hand       the predefined {@code HandF} object to use for all {@code createHand} calls.
   * @param decisions  a map of player position identifiers to decisions.
   * @param comments   a map of player position identifiers to comments.
   */
  public MockHoldemModel(HandF hand, Map<String, String> decisions, Map<String, String> comments) {
    this.hand = hand;
    this.decisions = decisions;
    this.comments = comments;
  }

  /**
   * Creates and returns the predefined hand.
   * This method ignores the input parameters and returns the predefined {@code HandF} object.
   *
   * @param card1Value the value of the first card (ignored).
   * @param card1Suit  the suit of the first card (ignored).
   * @param card2Value the value of the second card (ignored).
   * @param card2Suit  the suit of the second card (ignored).
   * @return the predefined {@code HandF} object.
   */
  @Override
  public HandF createHand(String card1Value, String card1Suit, String card2Value, String card2Suit) {
    return hand;
  }

  /**
   * Retrieves the map of predefined position decisions.
   *
   * @return the map of decisions.
   */
  @Override
  public Map<String, String> getPositionDecisions() {
    return decisions;
  }

  /**
   * Retrieves the map of predefined position-based comments.
   *
   * @return the map of comments.
   */
  @Override
  public Map<String, String> getPositionBasedComments() {
    return comments;
  }
}
