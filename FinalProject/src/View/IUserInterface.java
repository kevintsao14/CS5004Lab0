package view;

import java.util.Map;

public interface IUserInterface {
  // Method to get the value of the first card
  String getFirstCardValue();

  // Method to get the suit of the first card
  String getFirstCardSuit();

  // Method to get the value of the second card
  String getSecondCardValue();

  // Method to get the suit of the second card
  String getSecondCardSuit();

  // Method to display errors to the user
  void displayError(String message);

  // Method to clear the results area
  void clearResultsArea();

  // Method to update the text area with new data
  void updateTextArea(String title, Map<String, String> dataMap);

  // Method to format and display hand information
  void displayHandInformation(String handInfo);

  // Method to display decisions
  void displayDecisions(Map<String, String> decisions);

  // Method to display additional comments
  void displayComments(Map<String, String> comments);
}
