import java.util.Map;

class MockHoldemModel extends HoldemModel {
  private HandF hand;
  private Map<String, String> decisions;
  private Map<String, String> comments;

  public MockHoldemModel(HandF hand, Map<String, String> decisions, Map<String, String> comments) {
    this.hand = hand;
    this.decisions = decisions;
    this.comments = comments;
  }

  @Override
  public HandF createHand(String card1Value, String card1Suit, String card2Value, String card2Suit) {
    return hand; // Return a predefined hand
  }

  @Override
  public Map<String, String> getPositionDecisions() {
    return decisions;
  }

  @Override
  public Map<String, String> getPositionBasedComments() {
    return comments;
  }
}
