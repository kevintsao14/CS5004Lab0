public class CardF implements ICard {
  public final String value; // Use a String to include numbers and face cards like "J", "Q", "K", "A"
  public final String suit; // "Spades", "Hearts", "Clubs", "Diamonds"

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

  // Getters
  public String getValue() {
    return value;
  }

  public String getSuit() {
    return suit;
  }

  // Method to convert face cards to numerical values
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

  @Override
  public int hashCode() {
    return 31 * value.hashCode() + suit.hashCode();
  }
  @Override
  public String toString() {
    return String.format("Value: %s, Suit: %s", value, suit);
  }
}
