import java.util.List;
import java.util.Map;

/**
 * Abstract base class for evaluating hands in card games. This class provides a framework for defining the logic
 * for hand evaluation, including determining hand combinations, calculating scores, and making gameplay decisions
 * based on the score and game rules. Specific implementations must define the methods tailored to the particular
 * rules of the card game being implemented.
 */
public abstract class AbstractHandEvaluator {
  /**
   * List of cards that make up the hand to be evaluated.
   */
  protected List<CardF> cards;

  /**
   * Constructs an AbstractHandEvaluator with a given list of cards.
   *
   * @param cards The list of cards to be evaluated.
   */
  public AbstractHandEvaluator(List<CardF> cards) {
    this.cards = cards;
  }

  /**
   * Determines the combination type of the hand based on the specific rules of the card game.
   *
   * @return A string representing the type of hand combination.
   */
  public abstract String getHandCombination();

  /**
   * Calculates the score of the hand based on specific game rules.
   *
   * @return The numerical score of the hand.
   */
  public abstract int calculateScore();

  /**
   * Generates and returns a map of comments based on the position within the game, providing insights
   * and strategy tips based on the hand score and game dynamics.
   *
   * @return A map containing strategic comments for various game positions.
   */
  public abstract Map<String, String> getPositionBasedComments();

  /**
   * Decides the action to take (e.g., bet or fold) based on the hand's score compared to a specified threshold.
   *
   * @param score The score of the hand to be evaluated.
   * @param threshold The score threshold above which the action is to bet, and below which is to fold.
   * @return The decision of whether to bet or fold.
   */
  public abstract String decideAction(int score, int threshold);

  /**
   * Provides a map of decisions based on the score and the position in the game. This method is critical for
   * implementing strategic decision-making logic in the card game.
   *
   * @param score The score of the hand.
   * @return A map of decisions for various positions (e.g., early, middle, late) in the game.
   */
  public abstract Map<String, String> getPositionDecisions(int score);

  /**
   * Generates a comment based on the hand type, position, and decision. This method is used for generating
   * detailed feedback and strategy tips for players.
   *
   * @param position The position of the hand in the game.
   * @param decision The decision taken (e.g., bet or fold).
   * @param handCombination The type of hand combination identified.
   * @return A string containing the generated comment.
   */
  public abstract String generateComment(String position, String decision, String handCombination);

  /**
   * Generates a general comment based on the position, decision, and a provided rationale. This method is used
   * to elaborate on the reasoning behind certain decisions in the game.
   *
   * @param position The game position related to the comment.
   * @param decision The decision made (e.g., bet or fold).
   * @param rationale The rationale behind the decision, explaining why such a decision is recommended.
   * @return A string containing the tailored advice based on position and decision.
   */
  public abstract String generateGeneralComment(String position, String decision, String rationale);
}
