package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.Map;

/**
 * Provides the graphical user interface for the poker hand evaluator application.
 * This class extends {@link JFrame} and implements {@code IUserInterface}, handling all user interactions
 * including card input, button actions, and displaying results and errors.
 */
public class HoldemView extends JFrame implements view.IUserInterface {
  public JTextField card1ValueTextField = new JTextField(3);
  public JTextField card1SuitTextField = new JTextField(3);
  public JTextField card2ValueTextField = new JTextField(3);
  public JTextField card2SuitTextField = new JTextField(3);
  public JButton submitButton = new JButton("Evaluate Hand");
  public JTextArea resultArea = new JTextArea(20, 100);  // Adjusted size for better visibility

  /**
   * Constructs a HoldemView and initializes its UI components.
   */
  public HoldemView() {
    setTitle("Poker Hand Evaluator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initUI();
    pack();
    setVisible(true);
  }

  /**
   * Initializes the user interface, arranging components for card input and results display.
   */
  private void initUI() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Card 1 Value:"));
    panel.add(card1ValueTextField);
    panel.add(new JLabel("Card 1 Suit:"));
    panel.add(card1SuitTextField);
    panel.add(new JLabel("Card 2 Value:"));
    panel.add(card2ValueTextField);
    panel.add(new JLabel("Card 2 Suit:"));
    panel.add(card2SuitTextField);
    panel.add(submitButton);

    resultArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(resultArea);
    add(panel, BorderLayout.CENTER);
    add(scrollPane, BorderLayout.SOUTH);
  }

  /**
   * Returns the submit button for event handling.
   *
   * @return the submit button.
   */
  public JButton getSubmitButton() {
    return submitButton;
  }

  /**
   * Retrieves the value of the first card from the text field.
   *
   * @return the trimmed and uppercased value of the first card.
   */
  public String getFirstCardValue() {
    return card1ValueTextField.getText().trim().toUpperCase();
  }

  /**
   * Retrieves the suit of the first card from the text field.
   *
   * @return the trimmed and uppercased suit of the first card.
   */
  public String getFirstCardSuit() {
    return card1SuitTextField.getText().trim().toUpperCase();
  }

  /**
   * Retrieves the value of the second card from the text field.
   *
   * @return the trimmed and uppercased value of the second card.
   */
  public String getSecondCardValue() {
    return card2ValueTextField.getText().trim().toUpperCase();
  }

  /**
   * Retrieves the suit of the second card from the text field.
   *
   * @return the trimmed and uppercased suit of the second card.
   */
  public String getSecondCardSuit() {
    return card2SuitTextField.getText().trim().toUpperCase();
  }

  /**
   * Displays an error message in a dialog box.
   *
   * @param message the error message to display.
   */
  public void displayError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Clears the results area text.
   */
  public void clearResultsArea() {
    resultArea.setText("");
  }

  /**
   * Updates the text area with title and data from a map in a formatted way.
   *
   * @param title the title for the section of data.
   * @param dataMap a map containing strings to display in key-value pairs.
   */
  public void updateTextArea(String title, Map<String, String> dataMap) {
    StringBuilder stringBuilder = new StringBuilder(resultArea.getText());
    stringBuilder.append(title).append(":\n");
    for (Map.Entry<String, String> entry : dataMap.entrySet()) {
      stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
    }
    stringBuilder.append("\n");
    resultArea.setText(stringBuilder.toString());
  }

  /**
   * Formats and displays detailed information about a hand in the text area.
   *
   * @param handInfo a string containing formatted information about a hand.
   */
  public void displayHandInformation(String handInfo) {
    clearResultsArea();
    resultArea.append(handInfo + "\n\n");
  }

  /**
   * Displays strategic decisions based on the hand evaluation in the results area.
   *
   * @param decisions a map of decisions where keys are position descriptions and values are the recommended actions.
   */
  public void displayDecisions(Map<String, String> decisions) {
    resultArea.append("Decisions:\n");
    for (Map.Entry<String, String> entry : decisions.entrySet()) {
      resultArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
    }
    resultArea.append("\n");
  }

  /**
   * Displays comments based on the hand's evaluation and game position.
   *
   * @param comments a map containing comments for various game positions.
   */
  public void displayComments(Map<String, String> comments) {
    resultArea.append("Comments:\n");
    for (Map.Entry<String, String> entry : comments.entrySet()) {
      resultArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
    }
    resultArea.append("\n");
  }
}
