//import java.util.Map;
//import java.util.HashMap;
//
//public class MockHandF extends HandF {
//  private int mockScore;
//  private String mockCombination;
//  private Map<String, String> mockPositionDecisions;
//  private Map<String, String> mockPositionComments;
//
//  public MockHandF(CardF firstCard, CardF secondCard, int score, String combination,
//      Map<String, String> positionDecisions, Map<String, String> positionComments) {
//    super(firstCard, secondCard); // Call to super to initialize cards
//    this.mockScore = score;
//    this.mockCombination = combination;
//    this.mockPositionDecisions = positionDecisions;
//    this.mockPositionComments = positionComments;
//  }
//
//  @Override
//  public int getHandScore() {
//    return mockScore;
//  }
//
//  @Override
//  public String getHandCombination() {
//    return mockCombination;
//  }
//
//  @Override
//  public Map<String, String> getHandPositionDecisions(){
//    return mockPositionDecisions;
//  }
//
//  @Override
//  public Map<String, String> getHandPositionBasedComments(){
//    return mockPositionComments;
//  }
//
//  @Override
//  public String describeHand() {
//    String firstCardDescription = getFirstCard() != null ? getFirstCard().toString() : "None";
//    String secondCardDescription = getSecondCard() != null ? getSecondCard().toString() : "None";
//
//    return String.format("First card: %s, Second card: %s, Total Value: %d, Hand Combination: %s",
//        firstCardDescription, secondCardDescription, mockScore, mockCombination);
//  }
//}
