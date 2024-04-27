import java.util.Collections;
import java.util.Map;

//package controller;
public class HoldemController {

  private HoldemModel model;  // This would encapsulate HandF and evaluator logic
  private view.HoldemView view;
  private HandInfoDisplayFormat handInfo;

  public HoldemController(HoldemModel model, view.HoldemView view) {
    this.model = model;
    this.view = view;
    this.handInfo = new HandInfoDisplayFormat();
    initController();
  }

  private void initController() {
    view.getSubmitButton().addActionListener(e -> evaluateHand());
  }

  public void evaluateHand() {
    try {
      String card1Value = view.getFirstCardValue();
      String card1Suit = view.getFirstCardSuit();
      String card2Value = view.getSecondCardValue();
      String card2Suit = view.getSecondCardSuit();
      HandF hand = model.createHand(card1Value, card1Suit, card2Value, card2Suit);

      // Use the HandInfoDisplayFormat to format the display string
      String formattedHandInfo = handInfo.formatHandInformation(hand);

      // Display hand information, decisions, and comments
      view.displayHandInformation(formattedHandInfo);
      view.displayDecisions(model.getPositionDecisions());
      view.displayComments(model.getPositionBasedComments());

    } catch (IllegalArgumentException e) {
      view.displayError("Invalid Input: " + e.getMessage());
    }
  }

}

