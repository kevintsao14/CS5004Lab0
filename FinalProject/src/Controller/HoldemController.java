import java.util.Map;

//package controller;
public class HoldemController {
  private HoldemModel model;  // This would encapsulate HandF and evaluator logic
  private view.HoldemView view;

  public HoldemController(HoldemModel model, view.HoldemView view) {
    this.model = model;
    this.view = view;
    initController();
  }

  private void initController() {
    view.getSubmitButton().addActionListener(e -> evaluateHand());
  }

  private void evaluateHand() {
    try {
      String card1Value = view.getFirstCardValue();
      String card1Suit = view.getFirstCardSuit();
      String card2Value = view.getSecondCardValue();
      String card2Suit = view.getSecondCardSuit();
      HandF hand = model.createHand(card1Value, card1Suit, card2Value, card2Suit); // Model handles creation and evaluation
      String results = hand.describeHand();
      view.displayResults(results);

      // Get position-based decisions and comments
      Map<String, String> decisions = model.getPositionDecisions();
      Map<String, String> comments = model.getPositionBasedComments();

      // Assuming you have methods in your view to display these maps
      view.displayDecisions(decisions);
      view.displayComments(comments);
    } catch (IllegalArgumentException e) {
      view.displayError("Invalid Input: " + e.getMessage());
    }
  }
}
