

import java.util.Map;

public interface IHand {
  int getHandScore(); // Returns the sum of the numeric values of the cards in the hand
//  public void finalizeHand();
  String describeHand(); // Returns a string description of the hand
  String getHandCombination();
  Map<String, String> getHandPositionDecisions();
  Map<String, String> getHandPositionBasedComments();
}
