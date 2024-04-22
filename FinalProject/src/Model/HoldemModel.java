//package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HoldemModel {
  private HandF currentHand;
  private List<CardF> cards;
  private TexasHoldemHandEvaluator evaluator;

  public HoldemModel() {
    this.cards = new ArrayList<>();
  }

  public HandF createHand(String card1Value, String card1Suit, String card2Value, String card2Suit) throws IllegalArgumentException {
    CardF card1 = new CardF(card1Value, card1Suit);
    System.out.println("Card 1: " + card1Value + " of " + card1Suit);
    CardF card2 = new CardF(card2Value, card2Suit);
    System.out.println("Card 2: " + card2Value + " of " + card2Suit);
    cards.clear();
    cards.add(card1);
    cards.add(card2);
    currentHand = new ConcreteHandF(card1, card2); // ConcreteHandF must be a concrete implementation of HandF
    evaluator = new TexasHoldemHandEvaluator(Arrays.asList(card1, card2));
    return currentHand;
  }

  public int calculateScore() {
    if (currentHand == null) {
      throw new IllegalStateException("No hand initialized.");
    }
    return currentHand.getHandScore();
  }

  public String getHandCombination() {
    if (currentHand == null) {
      throw new IllegalStateException("No hand initialized.");
    }
    return currentHand.getHandCombination();
  }

  public String getHandDescription() {
    if (currentHand == null) {
      throw new IllegalStateException("No hand initialized.");
    }
    return currentHand.describeHand();
  }

  public Map<String, String> getPositionDecisions() {
    int score = calculateScore(); // Use the evaluator to get the current hand's score.
    return evaluator.getPositionDecisions(score);
  }

  public Map<String, String> getPositionBasedComments(){
    return evaluator.getPositionBasedComments();
  }
}
