
import java.util.List;
import java.util.Map;

public abstract class AbstractHandEvaluator {
  protected List<CardF> cards;

  public AbstractHandEvaluator(List<CardF> cards) {
    this.cards = cards;
  }
  public abstract String getHandCombination();

  public abstract int calculateScore();

  public abstract Map<String, String> getPositionBasedComments();
  public abstract String decideAction(int score, int threshold);
  public abstract Map<String, String> getPositionDecisions(int score);
  public abstract String generateComment(String position, String decision, String handCombination);
  public abstract String generateGeneralComment(String position, String decision, String rationale);

}
