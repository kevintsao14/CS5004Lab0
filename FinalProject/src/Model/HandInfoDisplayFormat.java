/**
 * This class provides functionality to format the information of a {@code HandF} object into a string for display.
 * It extracts the hand's score and combination and formats them into a human-readable string.
 */
public class HandInfoDisplayFormat {

  /**
   * Formats the score and hand combination of a given {@code HandF} object into a readable string.
   *
   * @param hand The {@code HandF} object whose information is to be formatted. Must not be null.
   * @return A formatted string containing the score and hand combination of the hand.
   * @throws NullPointerException if the {@code hand} is null.
   */
  public String formatHandInformation(HandF hand) {
    if (hand == null) {
      throw new NullPointerException("Hand cannot be null");
    }
    return String.format("Score: %s\nHand Combination: %s",
        hand.getHandScore(),
        hand.getHandCombination());
  }
}
