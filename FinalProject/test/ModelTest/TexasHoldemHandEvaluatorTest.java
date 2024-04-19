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
    // Total score: 29
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
    assertEquals("Type should be Throw Away", "Throw Away", type7);
  }

  @Test
  public void testGetPositionBasedComments() {
    Map<String, String> comments = evaluator.getPositionBasedComments();
    assertEquals("Early position advice should be 'Bet'", "Bet", comments.get("Early"));
    assertEquals("Middle position advice should be 'Bet'", "Bet", comments.get("Middle"));
    assertEquals("Late position advice should be 'Bet'", "Bet", comments.get("Late"));

    Map<String, String> comments2 = evaluator2.getPositionBasedComments();
    assertEquals("Early position advice should be 'Fold'", "Fold", comments2.get("Early"));
    assertEquals("Middle position advice should be 'Bet'", "Bet", comments2.get("Middle"));
    assertEquals("Late position advice should be 'Bet'", "Bet", comments2.get("Late"));
  }
}
