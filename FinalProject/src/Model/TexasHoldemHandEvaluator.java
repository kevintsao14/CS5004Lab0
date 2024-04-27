import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Evaluates Texas Hold'em poker hands by analyzing the properties of the cards involved.
 * Extends {@code AbstractHandEvaluator} to implement specific poker hand evaluation logic
 * tailored for Texas Hold'em. This class provides methods to determine hand combinations,
 * calculate hand scores, and make strategic decisions based on the game's rules.
 */
public class TexasHoldemHandEvaluator extends AbstractHandEvaluator {

  /**
   * Initializes a new hand evaluator with a list of cards.
   *
   * @param cards the list of cards to evaluate. Must contain at least two cards to form a valid hand.
   */
  public TexasHoldemHandEvaluator(List<CardF> cards) {
    super(cards);
  }

  /**
   * Determines the combination type of the hand using the properties of the cards such as suits and values.
   * It categorizes the hand into types like Pair, Suited Connectors, and others based on common poker rules.
   *
   * @return A string representing the type of poker hand, such as "Pair" or "Suited Connector".
   * @throws IllegalArgumentException If there are insufficient cards to determine a hand combination.
   */
  @Override
  public String getHandCombination() {
    if (cards.size() < 2) {
      throw new IllegalArgumentException("Insufficient cards to determine hand combination.");
    }

    CardF card1 = cards.get(0);
    CardF card2 = cards.get(1);

    boolean isSuited = card1.getSuit().equals(card2.getSuit());
    int differenceInValue = Math.abs(card1.getNumericValue() - card2.getNumericValue());

    // Pair
    if (card1.getValue().equals(card2.getValue())) {
      return "Pair";
    }

    // Suited Connector
    if (isSuited && differenceInValue == 1) {
      return "Suited Connector";
    }

    // Suited Cards
    if (isSuited) {
      return "Suited Cards";
    }

    // Connected Cards
    if (differenceInValue == 1) {
      return "Connected Cards";
    }

    // Two High Cards
    if (card1.getNumericValue() >= 10 && card2.getNumericValue() >= 10) {
      return "Two High Cards";
    }

    // Small Ax cards
    if (card1.getNumericValue() == 14 || card2.getNumericValue() ==14){
      return "Small Ax";
    }

    return "Bad Hand";
  }

  /**
   * Calculates the score of the hand based on a combination of the card values, suits, and their relative positions.
   * This score helps in making strategic decisions during the game.
   *
   * @return The calculated numerical score representing the strength of the hand.
   */
  @Override
  public int calculateScore() {
    CardF card1 = cards.get(0);
    CardF card2 = cards.get(1);

    int score = (card1.getNumericValue() + card2.getNumericValue()) / 2;
    boolean isSuited = card1.getSuit().equals(card2.getSuit());
    int differenceInValue = Math.abs(card1.getNumericValue() - card2.getNumericValue());

    // Check for pairs
    if (card1.getValue().equals(card2.getValue())) {
      score += 15;
    }

    // Check for suited cards
    if (isSuited) {
      score += 7;
    }

    // Check for two high cards
    if (card1.getNumericValue() >= 10 && card2.getNumericValue() >= 10) {
      score += 7;
    }

    // Check for connected cards
    if (differenceInValue == 1) {
      score += 3;
    }

    return score;
  }

  /**
   * Decides the betting action ("Bet" or "Fold") based on the hand's score compared to a specified threshold.
   * This method aids players in making strategic decisions during betting rounds.
   *
   * @param score The score of the hand to evaluate.
   * @param threshold The threshold score that determines whether to "Bet" or "Fold".
   * @return The decision to either "Bet" if the score is above the threshold, or "Fold" if below.
   */
  public String decideAction(int score, int threshold) {
    return score > threshold ? "Bet" : "Fold";
  }

  /**
   * Provides a map of betting decisions for different positional contexts within a game.
   * Different positions on the table might require different strategies, and this method assists in
   * deciding the appropriate action based on the position.
   *
   * @param score The score of the hand, which influences decisions in various positions.
   * @return A map containing decisions for positions like "Early Position", "Middle Position", etc.
   */
  @Override
  public Map<String, String> getPositionDecisions(int score) {
    Map<String, String> decisions = new HashMap<>();
    int earlyPositionThreshold = 20;
    int middlePositionThreshold = 15;
    int latePositionThreshold = 12;


    decisions.put("Late Position(Bet > 12)", decideAction(score, latePositionThreshold));
    decisions.put("Early Position(Bet > 20)", decideAction(score, earlyPositionThreshold));
    decisions.put("Middle Position(Bet > 15)", decideAction(score, middlePositionThreshold));

    return decisions;
  }

  /**
   * Generates and returns position-based comments that provide insight into why certain decisions
   * are recommended. These comments are useful for players to understand the rationale behind suggested
   * betting strategies.
   *
   * @return A map of position-based strategic comments.
   */
  @Override
  public Map<String, String> getPositionBasedComments() {
    int score = calculateScore();
    Map<String, String> decisions = getPositionDecisions(score);
    Map<String, String> adviceMap = new HashMap<>();
    String handCombination = getHandCombination(); // Get the hand combination once for all positions

    // Generate comments based on the decisions
    decisions.forEach((position, decision) -> {
      String comment = generateComment(position, decision, handCombination);
      adviceMap.put(position," \n" + comment + "\n");
    });

    return adviceMap;
  }

  /**
   * Constructs a detailed comment based on the hand type, position at the table, and the decision to bet or fold.
   * This method helps in providing specific advice that can guide players during gameplay.
   *
   * @param position The table position related to the comment.
   * @param decision The decision taken, such as "Bet" or "Fold".
   * @param handCombination The type of hand combination evaluated.
   * @return A string representing a detailed strategic comment.
   */
  @Override
  // Generates a comment based on the hand type, position, and betting decision
  public String generateComment(String position, String decision, String handCombination) {
    StringBuilder comment = new StringBuilder();

    // Determine the comment based on the hand combination
    switch (handCombination) {
      case "Pair":
        comment.append(generatePairComment(position, decision));
        break;
      case "Suited Connector":
        comment.append(generateSuitedConnectorComment(position, decision));
        break;
      case "Connected Cards":
        comment.append(generateConnectedCardsComment(position, decision));
        break;
      case "Two High Cards":
        comment.append(generateTwoHighCardsComment(position, decision));
        break;
      case "Small Ax":
        comment.append(generateSmallAxComment(position, decision));
        break;
      case "Suited Cards":
        comment.append(generateSuitedCardsComment(position, decision));
        break;
      case "Bad Hand":
        comment.append(generateBadHandComment(position, decision));
        break;
      default:
        logUnexpectedHandType(handCombination); // This method needs to be implemented to log errors
        comment.append("Unexpected hand type. Consider playing cautiously.");
    }

    return comment.toString();
  }

  /**
   * Generates a general comment based on the table position, the decision made (Bet or Fold), and a provided rationale.
   * This method adapts its output based on the position and decision, offering strategic advice that aligns with
   * the situation's specifics.
   *
   * @param position The table position for which the comment is being generated.
   * @param decision The decision taken at this position ("Bet" or "Fold").
   * @param rationale The rationale behind the decision, explaining why such a decision is recommended.
   * @return A string containing the tailored advice based on position and decision.
   */
  @Override
  public String generateGeneralComment(String position, String decision, String rationale) {
    String fold = "at this position, the disadvantage outweigh the strength of the hand.";
    String lateposition = "Late position gives you the advantages to observe other player's action. ";

    switch (position) {
      case "Early Position(Bet > 20)":
        return decision.equals("Fold")
            ? rationale + "However, " + fold
            : "Betting early since " + rationale;
      case "Middle Position(Bet > 15)":
        return decision.equals("Fold")
            ? rationale + "But, " + fold
            : rationale + "Middle position gives you some edge aginst players at early position.";
      case "Late Position(Bet > 12)":
        return decision.equals("Fold")
            ? lateposition + rationale + "However, the risk is not manageble in this scenario."
            : lateposition + " So you can play wider range of hands.";
      default:
        logUnexpectedPosition(position);
        return "Unexpected position. Play cautiously.";
    }
  }

  /**
   * Generates a comment specifically tailored for a poker hand consisting of a pair.
   * Utilizes the general comment generating method to include additional strategic advice specific to pairs.
   *
   * @param position The table position related to the comment.
   * @param decision The decision made for this hand type.
   * @return A string containing the advice for handling a pair at the specified position.
   */
  private String generatePairComment(String position, String decision) {
    String rationale = "A pair is a very strong starting hand because it is a made hand already. ";
    return generateGeneralComment(position, decision, rationale);
  }

  /**
   * Generates a comment specifically tailored for a poker hand consisting of a suited connectors.
   * Utilizes the general comment generating method to include additional strategic advice specific to
   * suited connectors.
   *
   * @param position The table position related to the comment.
   * @param decision The decision made for this hand type.
   * @return A string containing the advice for handling a suited connectors at the specified position.
   */
  private String generateSuitedConnectorComment(String position, String decision) {
    String rationale =
        "A suited connector has many potential after flop, thus making it a very playable hand. ";
    return generateGeneralComment(position, decision, rationale);
  }

  /**
   * Generates a comment specifically tailored for a poker hand consisting of a two high cards.
   * Utilizes the general comment generating method to include additional strategic advice specific
   * to two high cards.
   *
   * @param position The table position related to the comment.
   * @param decision The decision made for this hand type.
   * @return A string containing the advice for handling a two high cards at the specified position.
   */
  private String generateTwoHighCardsComment(String position, String decision) {
    String rationale =
        "Two high cards have limited potential but still have decent value when hit a pair after flop. ";
    return generateGeneralComment(position, decision, rationale);
  }

  /**
   * Generates a comment specifically tailored for a poker hand consisting of a connected cards.
   * Utilizes the general comment generating method to include additional strategic advice specific
   * to connected cards.
   *
   * @param position The table position related to the comment.
   * @param decision The decision made for this hand type.
   * @return A string containing the advice for handling a connected cards at the specified position.
   */
  private String generateConnectedCardsComment(String position, String decision) {
    String rationale =
        "Connected cards can be vulnerable if it miss the flop, but it has the potential to hit a straight. ";
    return generateGeneralComment(position, decision, rationale);
  }

  /**
   * Generates a comment specifically tailored for a poker hand consisting of a small Ax.
   * Utilizes the general comment generating method to include additional strategic advice specific
   * to small Ax.
   *
   * @param position The table position related to the comment.
   * @param decision The decision made for this hand type.
   * @return A string containing the advice for handling a small Ax at the specified position.
   */
  private String generateSmallAxComment(String position, String decision) {
    String rationale = "Ax cards have limited potential, however, one still could hit a top pair. ";
    return generateGeneralComment(position, decision, rationale);
  }

  /**
   * Generates a comment specifically tailored for a poker hand consisting of a suited cards.
   * Utilizes the general comment generating method to include additional strategic advice specific
   * to suited cards.
   *
   * @param position The table position related to the comment.
   * @param decision The decision made for this hand type.
   * @return A string containing the advice for handling a suited cards at the specified position.
   */
  private String generateSuitedCardsComment(String position, String decision) {
    String rationale = "Suited cards can be very strong after flop if one hit a flush. ";
    return generateGeneralComment(position, decision, rationale);
  }

  /**
   * Generates a comment specifically tailored for a poker hand consisting of a bad hand.
   * Utilizes the general comment generating method to include additional strategic advice specific
   * to bad hand.
   *
   * @param position The table position related to the comment.
   * @param decision The decision made for this hand type.
   * @return A string containing the advice for handling a bad hand at the specified position.
   */
  private String generateBadHandComment(String position, String decision) {
    String rationale =
        "This combination is trash, don't play it. One still could hit backdoor two pair tho. ";
    return generateGeneralComment(position, decision, rationale);
  }

  /**
   * Utility method to log unexpected hand types.
   * This method assists in debugging by logging unexpected hand types encountered during the evaluation.
   *
   * @param handType The unexpected hand type that was encountered.
   */
  private void logUnexpectedHandType(String handType) {
    System.err.println("Unexpected hand type encountered: " + handType);
  }

  /**
   * Utility method to log unexpected positions.
   * This method assists in debugging by logging unexpected positions encountered during strategic decision-making.
   *
   * @param position The unexpected position that was encountered.
   */
  private void logUnexpectedPosition(String position) {
    System.err.println("Unexpected position encountered: " + position);
  }
}


