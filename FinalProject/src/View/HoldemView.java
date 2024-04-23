//package view;
//
//import java.awt.BorderLayout;
//import java.util.Map;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//
//public class HoldemView extends JFrame{
//  private JTextField card1ValueTextField = new JTextField(3);
//  private JTextField card1SuitTextField = new JTextField(3);
//  private JTextField card2ValueTextField = new JTextField(3);
//  private JTextField card2SuitTextField = new JTextField(3);
//  private JButton submitButton = new JButton("Evaluate Hand");
//  private JTextArea resultArea = new JTextArea(20, 10);
//
//  public void PokerView() {
//    // Setup the GUI components
//    JFrame frame = new JFrame("Poker Hand Evaluator");
//    JPanel panel = new JPanel();
//    panel.add(new JLabel("Card 1 Value:"));
//    panel.add(card1ValueTextField);
//    panel.add(new JLabel("Card 1 Suit:"));
//    panel.add(card1SuitTextField);
//    panel.add(new JLabel("Card 2 Value:"));
//    panel.add(card2ValueTextField);
//    panel.add(new JLabel("Card 2 Suit:"));
//    panel.add(card2SuitTextField);
//    panel.add(submitButton);
//    frame.add(panel);
//    frame.add(resultArea, BorderLayout.SOUTH);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    frame.pack();
//    frame.setVisible(true);
//  }
//
//  public JButton getSubmitButton() {
//    return submitButton;
//  }
//
//  public String getFirstCardValue() {
//    return card1ValueTextField.getText().trim().toUpperCase();
//  }
//
//  public String getFirstCardSuit() {
//    return card1SuitTextField.getText().trim().toUpperCase();
//  }
//
//  public String getSecondCardValue() {
//    return card2ValueTextField.getText().trim().toUpperCase();
//  }
//
//  public String getSecondCardSuit() {
//    return card2SuitTextField.getText().trim().toUpperCase();
//  }
//
//  public void displayResults(String result) {
//    resultArea.setText(result);
//  }
//
//  public void displayError(String message) {
//    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
//  }
//
//  public void displayComments(Map<String, String> comments) {
//    StringBuilder commentsText = new StringBuilder();
//    for (Map.Entry<String, String> entry : comments.entrySet()) {
//      commentsText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
//    }
//    resultArea.setText(commentsText.toString());
//  }
//
//  public void displayDecisions(Map<String, String> decisions) {
//    StringBuilder decisionsText = new StringBuilder();
//    for (Map.Entry<String, String> entry : decisions.entrySet()) {
//      decisionsText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
//    }
//    resultArea.setText(decisionsText.toString());
//  }
//}

package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.Map;

public class HoldemView extends JFrame {
  private JTextField card1ValueTextField = new JTextField(3);
  private JTextField card1SuitTextField = new JTextField(3);
  private JTextField card2ValueTextField = new JTextField(3);
  private JTextField card2SuitTextField = new JTextField(3);
  private JButton submitButton = new JButton("Evaluate Hand");
  private JTextArea resultArea = new JTextArea(20, 100);  // Adjusted size for better visibility

  public HoldemView() {
    // Setup the GUI components
    setTitle("Poker Hand Evaluator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initUI();
    pack();
    setVisible(true);
  }

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

  public JButton getSubmitButton() {
    return submitButton;
  }

  public String getFirstCardValue() {
    return card1ValueTextField.getText().trim().toUpperCase();
  }

  public String getFirstCardSuit() {
    return card1SuitTextField.getText().trim().toUpperCase();
  }

  public String getSecondCardValue() {
    return card2ValueTextField.getText().trim().toUpperCase();
  }

  public String getSecondCardSuit() {
    return card2SuitTextField.getText().trim().toUpperCase();
  }

  public void displayError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  public void updateTextArea(String title, Map<String, String> dataMap) {
    StringBuilder stringBuilder = new StringBuilder(resultArea.getText());
    stringBuilder.append(title).append(":\n");
    for (Map.Entry<String, String> entry : dataMap.entrySet()) {
      stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
    }
    stringBuilder.append("\n");
    resultArea.setText(stringBuilder.toString());
  }

  public void formatHandInformation(String handInfo) {
    SwingUtilities.invokeLater(() -> resultArea.setText(handInfo));
  }

  // In HoldemView class

  public void displayHandInformation(String handInfo) {
    resultArea.append(handInfo + "\n\n");
  }

  public void displayDecisions(Map<String, String> decisions) {
    resultArea.append("Decisions:\n");
    for (Map.Entry<String, String> entry : decisions.entrySet()) {
      resultArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
    }
    resultArea.append("\n");
  }

  public void displayComments(Map<String, String> comments) {
    resultArea.append("Comments:\n");
    for (Map.Entry<String, String> entry : comments.entrySet()) {
      resultArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
    }
    resultArea.append("\n");
  }

}
