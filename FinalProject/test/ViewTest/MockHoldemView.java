import java.util.Map;
import javax.swing.JButton;
import view.HoldemView;

/**
 * A mock version of the {@code HoldemView} class used primarily for testing
 * the user interface without interacting with the actual game logic.
 * This class simulates user interface behaviors by storing state changes
 * internally and providing predefined responses for card values and suits.
 */
class MockHoldemView extends HoldemView {
  /**
   * Stores the last displayed hand information as a string.
   */
  public String displayedHandInfo;

  /**
   * Stores the last set of decisions displayed in the view.
   * Each entry maps a player's position to their decision as a string.
   */
  public Map<String, String> displayedDecisions;

  /**
   * Stores the last set of comments displayed in the view.
   * Each entry maps a player's position to their comment as a string.
   */
  public Map<String, String> displayedComments;

  /**
   * Stores the last error message displayed in the view.
   */
  public String errorMessage;

  /**
   * Indicates whether the submit button has been clicked.
   */
  public boolean submitButtonClicked = false;

  /**
   * Provides a predefined first card value, simulating user input.
   *
   * @return "A" as the value of the first card.
   */
  @Override
  public String getFirstCardValue() {
    return "A";
  }

  /**
   * Provides a predefined first card suit, simulating user input.
   *
   * @return "H" as the suit of the first card.
   */
  @Override
  public String getFirstCardSuit() {
    return "H";
  }

  /**
   * Provides a predefined second card value, simulating user input.
   *
   * @return "K" as the value of the second card.
   */
  @Override
  public String getSecondCardValue() {
    return "K";
  }

  /**
   * Provides a predefined second card suit, simulating user input.
   *
   * @return "D" as the suit of the second card.
   */
  @Override
  public String getSecondCardSuit() {
    return "D";
  }

  /**
   * Simulates the display of hand information in the user interface.
   *
   * @param info Information about the hand to be displayed.
   */
  @Override
  public void displayHandInformation(String info) {
    displayedHandInfo = info;
  }

  /**
   * Simulates the display of decisions in the user interface.
   *
   * @param decisions A map of player positions to their decisions.
   */
  @Override
  public void displayDecisions(Map<String, String> decisions) {
    displayedDecisions = decisions;
  }

  /**
   * Simulates the display of comments in the user interface.
   *
   * @param comments A map of player positions to their comments.
   */
  @Override
  public void displayComments(Map<String, String> comments) {
    displayedComments = comments;
  }

  /**
   * Simulates the display of an error message in the user interface.
   *
   * @param message The error message to be displayed.
   */
  @Override
  public void displayError(String message) {
    errorMessage = message;
  }

  /**
   * Creates and returns a submit button with an action listener that
   * changes {@code submitButtonClicked} to true when the button is clicked.
   *
   * @return A {@code JButton} object representing the submit button.
   */
  @Override
  public JButton getSubmitButton() {
    JButton button = new JButton();
    button.addActionListener(e -> submitButtonClicked = true);
    return button;
  }
}