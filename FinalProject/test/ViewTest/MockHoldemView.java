import java.util.Map;
import javax.swing.JButton;
import view.HoldemView;

class MockHoldemView extends HoldemView {
  public String displayedHandInfo;
  public Map<String, String> displayedDecisions;
  public Map<String, String> displayedComments;
  public String errorMessage;
  public boolean submitButtonClicked = false;

  @Override
  public String getFirstCardValue() {
    return "A";
  }

  @Override
  public String getFirstCardSuit() {
    return "H";
  }

  @Override
  public String getSecondCardValue() { return "K"; }

  @Override
  public String getSecondCardSuit() { return "D"; }

  @Override
  public void displayHandInformation(String info) {
    displayedHandInfo = info;
  }

  @Override
  public void displayDecisions(Map<String, String> decisions) {
    displayedDecisions = decisions;
  }

  @Override
  public void displayComments(Map<String, String> comments) {
    displayedComments = comments;
  }

  @Override
  public void displayError(String message) {
    errorMessage = message;
  }

  @Override
  public JButton getSubmitButton() {
    JButton button = new JButton();
    button.addActionListener(e -> submitButtonClicked = true);
    return button;
  }
}
