import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Test class for {@code TexasHoldemHandEvaluator}. This class conducts unit tests to verify the correct functionality
 * of hand evaluation, including scoring, decision making, and hand type determination specific to Texas Hold'em poker.
 */
public class TexasHoldemHandEvaluatorTest {
  private TexasHoldemHandEvaluator evaluator;
  private List<CardF> hand;
  private List<CardF> hand2;
  private TexasHoldemHandEvaluator pair;
  private TexasHoldemHandEvaluator suitedCard;
  private TexasHoldemHandEvaluator suitedConnector;
  private TexasHoldemHandEvaluator twoHighCard;
  private TexasHoldemHandEvaluator connectors;
  private TexasHoldemHandEvaluator smallAx;
  private TexasHoldemHandEvaluator badHand;
  private TexasHoldemHandEvaluator insufficientHand;

  /**
   * Sets up the initial conditions before each test case. Initializes the evaluator with a predefined hand
   * consisting of Ace of Hearts and King of Hearts, representing a suited connector.
   */
  @Before
  public void setUp() {
    hand = new ArrayList<>();
    hand.add(new CardF("A", "H")); // Ace of Hearts
    hand.add(new CardF("K", "H")); // King of Hearts (Suited Connector)
    evaluator = new TexasHoldemHandEvaluator(hand);

    hand2 = new ArrayList<>();
    hand2.add(new CardF("2", "H"));
    hand2.add(new CardF("8", "S"));
    badHand = new TexasHoldemHandEvaluator(hand2);
  }

  /**
   * Tests the hand combination identification method of the evaluator. Verifies that the evaluator correctly identifies
   * a suited connector when the hand consists of suited high cards in sequence.
   */
  @Test
  public void testGetHandCombination() {
    assertEquals("Hand should be identified as Suited Connector", "Suited Connector",
        evaluator.getHandCombination());
    assertEquals("Bad Hand hand tpye","Bad Hand", badHand.getHandCombination());
  }

  /**
   * Tests the hand combination method with insufficient cards to ensure that it throws an IllegalArgumentException as expected.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetHandCombinationWithInsufficientCards() {
    List<CardF> hand = new ArrayList<>();
    hand.add(new CardF("A", "H"));

    TexasHoldemHandEvaluator insufficientHand = new TexasHoldemHandEvaluator(hand);
    insufficientHand.getHandCombination();  // This should throw IllegalArgumentException
  }

  /**
   * Tests the score calculation functionality of the evaluator. Verifies that the score is calculated as expected
   * based on the predefined rules.
   */
  @Test
  public void testCalculateScore() {
    int score = evaluator.calculateScore();
    int score2 = badHand.calculateScore();
    assertEquals("Calculated score should match expected", 30, score);
    assertEquals("Calculated score should match expected for bad hand", 5, score2);
  }

  /**
   * Tests the decision-making logic of the evaluator based on a score and a given threshold.
   */
  @Test
  public void testDecideAction() {
    String action = evaluator.decideAction(30, 20);
    String action1 = badHand.decideAction(5,15);
    assertEquals("Should decide to Bet with score 30 and threshold 20", "Bet", action);
    assertEquals("Should decide to Bet with score 30 and threshold 15", "Fold", action1);
  }

  /**
   * Tests the generation of position-based decisions, ensuring that the evaluator provides correct betting advice
   * for different positions based on a given hand score.
   */
  @Test
  public void testGetPositionDecisions() {
    Map<String, String> decisions = evaluator.getPositionDecisions(30);
    assertEquals("Should Bet in Early Position with high score", "Bet", decisions.get("Early Position(Bet > 20)"));
    assertEquals("Should Bet in Middle Position with high score", "Bet", decisions.get("Middle Position(Bet > 15)"));
    assertEquals("Should Bet in Late Position with high score", "Bet", decisions.get("Late Position(Bet > 12)"));
  }

  /**
   * Tests the generation of position-based comments from the evaluator. Ensures that comments are generated
   * that align with the strategic advice based on hand evaluation.
   */
  @Test
  public void testGetPositionBasedComments() {
    Map<String, String> comments = evaluator.getPositionBasedComments();
    assertTrue("Early position advice should include strong play rationale", comments.get("Early Position(Bet > 20)").contains("Betting early since"));
    assertTrue("Middle position advice should emphasize positional advantage", comments.get("Middle Position(Bet > 15)").contains("Middle position gives you some edge"));
    assertTrue("Late position advice should highlight observational benefits", comments.get("Late Position(Bet > 12)").contains("Late position gives you the advantages"));
  }

  /**
   * Additional tests to verify the evaluator's functionality for specific hand types like pairs, suited cards, etc.
   * These tests ensure that the evaluator accurately identifies and provides feedback on various poker hand types.
   */
  @Test
  public void testPair() {
    hand = new ArrayList<>();
    hand.add(new CardF("K", "H"));
    hand.add(new CardF("K", "S"));
    pair = new TexasHoldemHandEvaluator(hand);
    Map<String, String> pairComments = pair.getPositionBasedComments();
    String pairResult = pair.generateComment("Early Position(Bet > 20)", "Bet", "Pair");

    assertEquals("This is a pair", "Pair", pair.getHandCombination());
    assertTrue("Strong hand", pairResult.contains(
        "A pair is a very strong starting hand because it is a made hand already. "));
  }

  @Test
  public void testSuited() {
    hand = new ArrayList<>();
    hand.add(new CardF("9", "H"));
    hand.add(new CardF("K", "H"));
    suitedCard = new TexasHoldemHandEvaluator(hand);
    Map<String, String> suitedCardComments = suitedCard.getPositionBasedComments();
    String suitedResult = suitedCard.generateComment("Early Position(Bet > 20)", "Bet", "Suited Cards");

    assertEquals("This is a suitedCard", "Suited Cards", suitedCard.getHandCombination());
    assertTrue("Strong hand",suitedResult .contains(
        "Suited cards can be very strong after flop if one hit a flush. "));
  }

  @Test
  public void testBadHand() {
    Map<String, String> badHandComments = badHand.getPositionBasedComments();
    String badHandResult = badHand.generateComment("Middle Position(Bet > 15)", "Fold",
        "Bad Hand");

    assertEquals("This is a suitedCard", "Bad Hand", badHand.getHandCombination());
    assertTrue("Bad hand comments",badHandResult .contains(
        "This combination is trash, don't play it. One still could hit backdoor two pair tho. "));
  }

  @Test
  public void testConnectedCards() {
    hand = new ArrayList<>();
    hand.add(new CardF("7", "D"));
    hand.add(new CardF("8", "S"));
    connectors = new TexasHoldemHandEvaluator(hand);
    Map<String, String> badHandComments = connectors.getPositionBasedComments();
    String badHandResult = connectors.generateComment("Middle Position(Bet > 15)", "Fold",
        "Connected Cards");

    assertEquals("This is a connectors", "Connected Cards", connectors.getHandCombination());
    assertTrue("Connected cards comments",badHandResult .contains(
        "Connected cards can be vulnerable if it miss the flop, but it has the potential to hit a straight. "));
  }

  @Test
  public void testSmallAx() {
    hand = new ArrayList<>();
    hand.add(new CardF("A", "H"));
    hand.add(new CardF("8", "C"));
    smallAx = new TexasHoldemHandEvaluator(hand);
    Map<String, String> badHandComments = smallAx.getPositionBasedComments();
    String smallAxResult = smallAx.generateComment("Middle Position(Bet > 15)", "Fold",
        "Small Ax");

    assertEquals("This is a smallAx", "Small Ax", smallAx.getHandCombination());
    assertTrue("Small Ax comments",smallAxResult .contains(
        "Ax cards have limited potential, however, one still could hit a top pair. "));
  }

  @Test
  public void testTwoHighCards() {
    hand = new ArrayList<>();
    hand.add(new CardF("K", "H"));
    hand.add(new CardF("10", "S"));
    twoHighCard = new TexasHoldemHandEvaluator(hand);
    Map<String, String> badHandComments = twoHighCard.getPositionBasedComments();
    String twoHighCardResult = twoHighCard.generateComment("Early Position(Bet > 20)", "Bet",
        "Two High Cards");

    assertEquals("This is a suitedCard", "Two High Cards", twoHighCard.getHandCombination());
    assertTrue("Two high cards comments",twoHighCardResult.contains(
        "Two high cards have limited potential but still have decent value when hit a pair after flop. "));
  }

  @Test
  public void testUnexpectedPosition() {
    String result = evaluator.generateComment("Unknown Position", "Fold", "Pair");
    assertTrue("Comment for an unexpected position should include default advice",
        result.contains("Unexpected position. Play cautiously."));
  }

  @Test
  public void testGenerateCommentUnexpectedHandType() {
    String result = evaluator.generateComment("Middle", "Bet", "Unknown Hand Type");
    assertTrue("Comment for an unexpected hand type should include a default advice",
        result.contains("Unexpected hand type. Consider playing cautiously."));
  }
}
