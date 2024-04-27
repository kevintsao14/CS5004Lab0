// package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A mock version of the HoldemModel to be used for testing controller and view interactions.
 */
public class MockHoldemModel extends HoldemModel {
  private HandF mockCurrentHand;
  private Map<String, String> mockPositionDecisions = new HashMap<>();
  private Map<String, String> mockPositionBasedComments = new HashMap<>();
  private int mockScore;
  private String mockHandCombination;

  @Override
  public HandF createHand(String card1Value, String card1Suit, String card2Value, String card2Suit) {
    // Return a predefined mock hand instead of creating a new one
    return mockCurrentHand;
  }

  @Override
  public int calculateScore() {
    // Return a mock score for testing
    return mockScore;
  }

  @Override
  public String getHandCombination() {
    // Return a mock hand combination for testing
    return mockHandCombination;
  }

  @Override
  public Map<String, String> getPositionDecisions() {
    // Return a mock map of position decisions for testing
    return mockPositionDecisions;
  }

  @Override
  public Map<String, String> getPositionBasedComments() {
    // Return a mock map of position-based comments for testing
    return mockPositionBasedComments;
  }

  // Setters for mock data to allow configuration in tests
  public void setMockCurrentHand(HandF mockCurrentHand) {
    this.mockCurrentHand = mockCurrentHand;
  }

  public void setMockScore(int mockScore) {
    this.mockScore = mockScore;
  }

  public void setMockHandCombination(String mockHandCombination) {
    this.mockHandCombination = mockHandCombination;
  }

  public void setMockPositionDecisions(Map<String, String> mockPositionDecisions) {
    this.mockPositionDecisions = mockPositionDecisions;
  }

  public void setMockPositionBasedComments(Map<String, String> mockPositionBasedComments) {
    this.mockPositionBasedComments = mockPositionBasedComments;
  }
}
