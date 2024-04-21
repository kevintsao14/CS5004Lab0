import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Map;

public class TexasHoldemHandEvaluatorTest {

  private TexasHoldemHandEvaluator evaluator;
  private TexasHoldemHandEvaluator evaluator2;
  private TexasHoldemHandEvaluator evaluator3;
  private TexasHoldemHandEvaluator evaluator4;
  private TexasHoldemHandEvaluator evaluator5;
  private TexasHoldemHandEvaluator evaluator6;
  private TexasHoldemHandEvaluator evaluator7;
  private List<CardF> hand1;
  private List<CardF> hand2;
  private List<CardF> hand3;
  private List<CardF> hand4;
  private List<CardF> hand5;
  private List<CardF> hand6;
  private List<CardF> hand7;

  @Before
  public void setUp() {
    hand1 = new ArrayList<>();
    hand1.add(new CardF("A", "H")); // King of Hearts
    hand1.add(new CardF("K", "H")); // Queen of Hearts (Suited Connector)
    evaluator = new TexasHoldemHandEvaluator(hand1);

    hand2 = new ArrayList<>();
    hand2.add(new CardF("2", "D"));
    hand2.add(new CardF("2", "S"));
    evaluator2 = new TexasHoldemHandEvaluator(hand2);

    hand3 = new ArrayList<>();
    hand3.add(new CardF("A", "H"));
    hand3.add(new CardF("J", "S"));
    evaluator3 = new TexasHoldemHandEvaluator(hand3); //Two high Cards

    hand4 = new ArrayList<>();
    hand4.add(new CardF("4", "S"));
    hand4.add(new CardF("2", "S"));
    evaluator4 = new TexasHoldemHandEvaluator(hand4); // Suited Cards

    hand5 = new ArrayList<>();
    hand5.add(new CardF("6", "C"));
    hand5.add(new CardF("7", "S"));
    evaluator5 = new TexasHoldemHandEvaluator(hand5); // Connected Cards

    hand6 = new ArrayList<>();
    hand6.add(new CardF("A", "C"));
    hand6.add(new CardF("7", "S"));
    evaluator6 = new TexasHoldemHandEvaluator(hand6); // Small Ax

    hand7 = new ArrayList<>();
    hand7.add(new CardF("2", "H"));
    hand7.add(new CardF("7", "C"));
    evaluator7 = new TexasHoldemHandEvaluator(hand7); // Throw Away
  }

  @Test
  public void testCalculateScore() {
    int score = evaluator.calculateScore();
    // Expected score calculation:
    // Base score (A+K/2): (14 + 13)/2 = 13
    // Pair: +0
    // Suited: +7
    // Two high cards: +7
    // Connected: +3
    // Total score: 30
    assertEquals("Calculated score should be 30", 30, score);

    int score2 = evaluator2.calculateScore();
    // Expected score calculation:
    // Base score (2+2/2): (2 + 2)/2 = 2
    // Pair: +15
    // Suited: +0
    // Two high cards: +0
    // Connected: +0
    // Total score: 17
    assertEquals("Calculated score should be 17", 17, score2);
  }

  @Test
  public void testDecideAction() {
    assertEquals("Decision should be Bet", "Bet", evaluator.decideAction(30, 25));
    assertEquals("Decision should be Fold", "Fold", evaluator.decideAction(15, 20));
  }

  @Test
  public void testGetPositionDecisions() {
    // Test with a high score that should result in betting in all positions
    Map<String, String> decisions = evaluator.getPositionDecisions(25);
    assertEquals("Should bet in Early position with high score", "Bet", decisions.get("Early"));
    assertEquals("Should bet in Middle position with high score", "Bet", decisions.get("Middle"));
    assertEquals("Should bet in Late position with high score", "Bet", decisions.get("Late"));

    // Test with a lower score that should result in folding in early and middle but betting in late position
    decisions = evaluator.getPositionDecisions(14);
    assertEquals("Should fold in Early position with low score", "Fold", decisions.get("Early"));
    assertEquals("Should bet in Middle position with borderline score", "Fold", decisions.get("Middle"));
    assertEquals("Should bet in Late position with borderline score", "Bet", decisions.get("Late"));

    // Test with a very low score that should result in folding in all positions
    decisions = evaluator.getPositionDecisions(10);
    assertEquals("Should fold in Early position with very low score", "Fold", decisions.get("Early"));
    assertEquals("Should fold in Middle position with very low score", "Fold", decisions.get("Middle"));
    assertEquals("Should fold in Late position with very low score", "Fold", decisions.get("Late"));
  }

  @Test
  public void testgetHandCombination(){
    String type1 = evaluator.getHandCombination();
    String type2 = evaluator2.getHandCombination();
    String type3 = evaluator3.getHandCombination();
    String type4 = evaluator4.getHandCombination();
    String type5 = evaluator5.getHandCombination();
    String type6 = evaluator6.getHandCombination();
    String type7 = evaluator7.getHandCombination();

    assertEquals("Type should be Suited Connector", "Suited Connector", type1);
    assertEquals("Type should be Pair", "Pair", type2);
    assertEquals("Type should be Two High Cards", "Two High Cards", type3);
    assertEquals("Type should be Suited Cards", "Suited Cards", type4);
    assertEquals("Type should be Connected Cards", "Connected Cards", type5);
    assertEquals("Type should be Small Ax", "Small Ax", type6);
    assertEquals("Type should be Throw Away", "Bad Hand", type7);
  }

  @Test
  public void testGetPositionBasedComments() {
    // Generate position based comments for a high-scoring hand
    Map<String, String> comments = evaluator.getPositionBasedComments();
    assertEquals("Early position advice should include Bet", "Bet - Early position. "
        + "Betting early since A suited connector has many potential after flop, "
        + "thus making it a very playable hand. ", comments.get("Early"));
    assertEquals("Middle position advice should include Bet", "Bet - Middle position. "
        + "A suited connector has many potential after flop, thus making "
        + "it a very playable hand. Middle position gives you some edge aginst players at early position.",
        comments.get("Middle"));
    assertEquals("Late position advice should include Bet", "Bet - Late position. "
        + "Late position gives you the advantages to observe other "
        + "player's action So you can play wider range of hands.", comments.get("Late"));

    Map<String, String> comments2 = evaluator3.getPositionBasedComments();
    assertEquals("Early position advice should include Fold","Fold - Early position. "
        + "Two high cards have limited potential but still have decent "
        + "value when hit a pair after flop. However, at this position, the disadvantage outweigh "
        + "the strength of the hand.", comments2.get("Early"));
    assertEquals("Middle position advice should include Bet", "Bet - Middle position. "
        + "Two high cards have limited potential but still have decent "
        + "value when hit a pair after flop. Middle position gives you some edge aginst players at "
        + "early position.", comments2.get("Middle"));
    assertEquals("Late position advice should include Bet","Bet - Late position. "
        + "Late position gives you the advantages to observe other "
        + "player's action So you can play wider range of hands.", comments2.get("Late"));

    // Changing to a low-scoring hand (7-2 offsuit)
    Map<String, String> comments3 = evaluator7.getPositionBasedComments();
    assertEquals("Early position advice should include Fold", "Fold - Early position. "
        + "This combination is trash, don't play it. One still could hit backdoor "
        + "two pair tho. However, at this position, the disadvantage outweigh the strength of the hand.",
        comments3.get("Early"));
  }

  @Test
  public void testGenerateCommentPair() {
    String result = evaluator2.generateComment("Early", "Bet", "Pair");
    assertTrue("Comment for a Pair should include specific advice", result.contains("A pair is a very strong starting hand because it is a made hand already."));
  }

  @Test
  public void testGenerateCommentSuitedConnector() {
    // Suited Connector already set in setUp()
    String result = evaluator.generateComment("Middle", "Bet", "Suited Connector");
    assertTrue("Comment for Suited Connector should include potential benefits", result.contains("A suited connector has many potential after flop, thus making it a very playable hand."));
  }

  @Test
  public void testGenerateCommentConnectedCards() {
    String result = evaluator5.generateComment("Late", "Fold", "Connected Cards");
    assertTrue("Comment for Connected Cards should include risk and potential", result.contains("Connected cards can be vulnerable if it miss the flop, but it has the potential to hit a straight."));
  }

  @Test
  public void testGenerateCommentTwoHighCards() {
    String result = evaluator3.generateComment("Early", "Bet", "Two High Cards");
    assertTrue("Comment for Two High Cards should include limited potential", result.contains("Two high cards have limited potential but still have decent value when hit a pair after flop."));
  }

  @Test
  public void testGenerateCommentSmallAx() {
    String result = evaluator6.generateComment("Middle", "Fold", "Small Ax");
    assertTrue("Comment for Small Ax should include potential for hitting a top pair", result.contains("Ax cards have limited potential, however, one still could hit a top pair."));
  }

  @Test
  public void testGenerateCommentSuitedCards() {
    String result = evaluator4.generateComment("Late", "Fold", "Suited Cards");
    assertTrue("Comment for Suited Cards should include flush potential", result.contains("Suited cards can be very strong after flop if one hit a flush."));
  }

  @Test
  public void testGenerateCommentBadHand() {
    String result = evaluator7.generateComment("Early", "Fold", "Bad Hand");
    assertTrue("Comment for Bad Hand should include a cautionary advice", result.contains("This combination is trash, don't play it."));
  }

  @Test
  public void testGenerateCommentUnexpectedHandType() {
    String result = evaluator.generateComment("Middle", "Bet", "Unknown Hand Type");
    assertTrue("Comment for an unexpected hand type should include a default advice",
        result.contains("Unexpected hand type. Consider playing cautiously."));
  }

  @Test
  public void testGenerateCommentUnexpectedPosition() {
    String result = evaluator7.generateComment("Fake", "Fold", "Bad Hand");
    assertTrue("Comment for an unexpected hand type should include a default advice",
        result.contains("Unexpected position. Play cautiously."));
  }
}
