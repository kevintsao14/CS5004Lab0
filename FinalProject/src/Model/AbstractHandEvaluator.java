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
}
