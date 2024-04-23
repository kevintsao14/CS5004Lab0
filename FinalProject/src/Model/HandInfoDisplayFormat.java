public class HandInfoDisplayFormat {
  public String formatHandInformation(HandF hand) {
    return String.format("Score: %s\nHand Combination: %s",
        hand.getHandScore(),
        hand.getHandCombination());
  }
}