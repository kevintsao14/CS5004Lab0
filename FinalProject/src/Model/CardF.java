/**
 * Represents a single playing card with a value and a suit. This class is suitable for games
 * involving both numbered cards and face cards like Jacks (J), Queens (Q), Kings (K), and Aces (A).
 */
public class CardF implements ICard {
  /**
   * The value of the card. This can be "2" to "10", "J" for Jack, "Q" for Queen,
   * "K" for King, or "A" for Ace.
   */
  public final String value;

  /**
   * The suit of the card. Valid suits are represented by "S" for Spades, "H" for Hearts,
   * "C" for Clubs, and "D" for Diamonds.
   */
  public final String suit;

  /**
   * Constructs a CardF object with specified value and suit.
   *
   * @param value The value of the card, which must be a valid card number or face ("2"-"10", "J", "Q", "K", "A").
   * @param suit The suit of the card, which must be one of "S" (Spades), "H" (Hearts), "C" (Clubs), or "D" (Diamonds).
   * @throws IllegalArgumentException if either the value or the suit are invalid according to standard card rules.
   */
  public CardF(String value, String suit) {
    // Validate the value
    if (!value.matches("[2-9]|10|[AJKQ]")) {
      throw new IllegalArgumentException("Invalid card value: " + value);
    }
    // Validate the suit
    if (!suit.matches("[SHCD]")) {
      throw new IllegalArgumentException("Invalid card suit: " + suit);
    }
    this.value = value;
    this.suit = suit;
  }

  /**
   * Returns the value of the card.
   *
   * @return The value of the card as a String.
   */
  public String getValue() {
    return value;
  }

  /**
   * Returns the suit of the card.
   *
   * @return The suit of the card as a String.
   */
  public String getSuit() {
    return suit;
  }

  /**
   * Converts face card values to their respective numerical values for easier computation in games.
   * 'A' (Ace) is considered the highest with a value of 14.
   *
   * @return The numerical value of the card, where Jacks are 11, Queens are 12, Kings are 13, and Aces are 14.
   */
  public int getNumericValue() {
    switch (value) {
      case "A":
        return 14;
      case "K":
        return 13;
      case "Q":
        return 12;
      case "J":
        return 11;
      default:
        return Integer.parseInt(value); // This will now safely assume value is a valid number between 2 and 10
    }
  }

  /**
   * Checks if this card is equal to another card based on value and suit.
   *
   * @param obj The object to compare this card against.
   * @return true if the given object represents a card equivalent to this card, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    CardF other = (CardF) obj;
    return value.equals(other.value) && suit.equals(other.suit);
  }

  /**
   * Returns a hash code for this card.
   *
   * @return a hash code value for this card.
   */
  @Override
  public int hashCode() {
    return 31 * value.hashCode() + suit.hashCode();
  }

  /**
   * Returns a string representation of the card.
   *
   * @return A string that textually represents this card.
   */
  @Override
  public String toString() {
    return String.format("Value: %s, Suit: %s", value, suit);
  }
}
