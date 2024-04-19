import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TexasHoldemHandEvaluator extends AbstractHandEvaluator {

  public TexasHoldemHandEvaluator(List<CardF> cards) {
    super(cards);
  }

  @Override
  public String getHandCombination() {
    CardF card1 = cards.get(0);
    CardF card2 = cards.get(1);

    boolean isSuited = card1.getSuit().equals(card2.getSuit());
    int differenceInValue = Math.abs(card1.getNumericValue() - card2.getNumericValue());

    // Pair
    if (card1.getValue().equals(card2.getValue())) {return "Pair";}

    // Suited Connector
    if (isSuited && differenceInValue == 1) {return "Suited Connector";}

    // Suited Cards
    if (isSuited) {return "Suited Cards";}

    // Connected Cards
    if (differenceInValue == 1) {return "Connected Cards";}

    // Two High Cards
    if (card1.getNumericValue() >= 10 && card2.getNumericValue() >= 10) {return "Two High Cards";}

    // Small Ax cards
    if (card1.getNumericValue() == 14 || card2.getNumericValue() ==14){return "Small Ax";}

    return "Throw Away";
  }

  @Override
  public int calculateScore() {
    CardF card1 = cards.get(0);
    CardF card2 = cards.get(1);

    int score = (card1.getNumericValue() + card2.getNumericValue()) / 2;
    boolean isSuited = card1.getSuit().equals(card2.getSuit());
    int differenceInValue = Math.abs(card1.getNumericValue() - card2.getNumericValue());

    // Check for pairs
    if (card1.getValue().equals(card2.getValue())) {score += 15;}

    // Check for suited cards
    if (isSuited) {score += 7;}

    // Check for two high cards
    if (card1.getNumericValue() >= 10 && card2.getNumericValue() >= 10) {score += 7;}

    // Check for connected cards
    if (differenceInValue == 1) {score += 3;}

    return score;
  }

  @Override
  public Map<String, String> getPositionBasedComments() {
    Map<String, String> adviceMap = new HashMap<>();
    int score = calculateScore();

    // Define the thresholds for a strong hand in each position
    int earlyPositionThreshold = 20;
    int middlePositionThreshold = 15;
    int latePositionThreshold = 12;

    // Provide advice for each position and add detailed comments
    String earlyDecision = score > earlyPositionThreshold ? "Bet" : "Fold";
    String middleDecision = score > middlePositionThreshold ? "Bet" : "Fold";
    String lateDecision = score > latePositionThreshold ? "Bet" : "Fold";

    String handCombination = getHandCombination();  // Get the hand combination once for all positions

    adviceMap.put("Early", earlyDecision + " - " + generateComment("Early", earlyDecision, handCombination));
    adviceMap.put("Middle", middleDecision + " - " + generateComment("Middle", middleDecision, handCombination));
    adviceMap.put("Late", lateDecision + " - " + generateComment("Late", lateDecision, handCombination));

    return adviceMap;
  }


  // Generates a comment based on the hand type, position, and betting decision
  private String generateComment(String position, String decision, String handCombination) {
    StringBuilder comment = new StringBuilder(handCombination);
    comment.append(" in ").append(position).append(" position - ");

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
        comment.append(generateThrowAwayComment(position, decision));
        break;
      default:
        logUnexpectedHandType(handCombination); // This method needs to be implemented to log errors
        comment.append("Unexpected hand type. Consider playing cautiously.");
    }

    return comment.toString();
  }

  private String generateGeneralComment(String position, String decision, String rationale) {
    String fold = "at this position, the disadvantage outweigh the strength of the hand.";
    String lateposition = "Late position gives you the advantages to observe other player's action";

    switch (position) {
      case "Early":
        return decision.equals("Fold")
            ? rationale + "However, " + fold
            : "Betting early since " + rationale;
      case "Middle":
        return decision.equals("Fold")
            ? rationale + "But, " + fold
            : rationale + "Middle position gives you some edge aginst players at early position.";
      case "Late":
        return decision.equals("Fold")
            ? lateposition + rationale + "However, the risk is not manageble in this scenario."
            : lateposition + "So you can play wider range of hands";
      default:
        logUnexpectedPosition(position);
        return "Unexpected position. Play cautiously.";
    }
  }

  // Example usage inside a specific hand type method
  private String generatePairComment(String position, String decision) {
    String rationale = "A pair is a very strong starting hand because it is a made hand already.";
    return generateGeneralComment(position, decision, rationale);
  }

  private String generateSuitedConnectorComment(String position, String decision) {
    String rationale =
        "A suited connector has many potential after flop, thus making it a very playable hand. ";
    return generateGeneralComment(position, decision, rationale);
  }

  private String generateTwoHighCardsComment(String position, String decision) {
    String rationale =
        "Two high cards have limited potential but still have decent value when hit a pair after flop. ";
    return generateGeneralComment(position, decision, rationale);
  }

  private String generateConnectedCardsComment(String position, String decision) {
    String rationale =
        "Connected cards can be vulnerable if it miss the flop, but it has the potential to hit a straight. ";
    return generateGeneralComment(position, decision, rationale);
  }

  private String generateSmallAxComment(String position, String decision) {
    String rationale = "Ax cards have limited potential, however, one still could hit a top pair. ";
    return generateGeneralComment(position, decision, rationale);
  }

  private String generateSuitedCardsComment(String position, String decision) {
    String rationale = "Suited cards can be very strong after flop if one hit a flush. ";
    return generateGeneralComment(position, decision, rationale);
  }

  private String generateThrowAwayComment(String position, String decision) {
    String rationale =
        "This combination is trash, don't play it. One still could hit backdoor two pair tho. ";
    return generateGeneralComment(position, decision, rationale);
  }

  // Utility method to log unexpected hand types
  private void logUnexpectedHandType(String handType) {
    System.err.println("Unexpected hand type encountered: " + handType);
  }
  // Utility method to log unexpected hand types
  private void logUnexpectedPosition(String position) {
    System.err.println("Unexpected position encountered: " + position);
  }
}


