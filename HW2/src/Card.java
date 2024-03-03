import java.util.Objects;

/**
 * Represents a playing card with a suit and a rank.
 */
public class Card implements Comparable<Card> {
  private Suit suit;
  private int rank;

  /**
   * Constructs a card with the specified suit and rank.
   *
   * @param suit The suit of the card, not null.
   * @param rank The rank of the card, must be between 1 and 13.
   * @throws IllegalArgumentException if the rank is not between 1 and 13.
   */
  public Card(Suit suit, int rank) {
    if (rank < 1 || rank > 13) {
      throw new IllegalArgumentException("Rank must be between 1 and 13.");
    }
    this.suit = suit;
    this.rank = rank;
  }

  /**
   * Returns the suit of this card.
   *
   * @return The suit of this card.
   */
  public Suit getSuit() {
    return suit;
  }

  /**
   * Returns the rank of this card.
   *
   * @return The rank of this card.
   */
  public int getRank() {
    return rank;
  }

  /**
   * Returns the color of the card based on its suit.
   * Hearts and Diamonds are Red, Clubs and Spades are Black.
   *
   * @return String representing the color of the card, either "Red" or "Black".
   * @throws IllegalArgumentException if the suit is not recognized.
   */
  public String getColor() {
    switch (suit) {
      case HEARTS:
      case DIAMONDS:
        return "Red";
      case CLUBS:
      case SPADES:
        return "Black";
    }
    return null; //This line does not need a test because the property
                // of the enum type (I searched up on the internet).
  }

  /**
   * Compares this card with another card for order based on rank.
   *
   * @param other The card to be compared.
   * @return a negative integer, zero, or a positive integer as this card is less than, equal to, or greater than the specified card.
   */
  @Override
  public int compareTo(Card other) {
    return Integer.compare(this.rank, other.rank);
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * Two cards are equal if they have the same suit and rank.
   *
   * @param obj the reference object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Card)) return false;
    Card other = (Card) obj;
    return this.rank == other.rank && this.suit == other.suit;
  }

  /**
   * Returns a hash code value for this card.
   *
   * @return a hash code value for this card.
   */
  @Override
  public int hashCode(){
    return Objects.hash(suit,rank);
  }

  /**
   * Returns a string representation of the card, including its suit and rank.
   *
   * @return a string representation of the card.
   */
  @Override
  public String toString() {
    return "Card{" + "suit=" + suit + ", rank=" + rank + '}';
  }
}
