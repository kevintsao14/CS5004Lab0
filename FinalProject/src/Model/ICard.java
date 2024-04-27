/**
 * Defines the interface for a card in a card game. This interface specifies the basic methods that
 * a card class must implement, providing a consistent way to retrieve card properties and manage card identity.
 */
public interface ICard {

  /**
   * Retrieves the value of the card. Depending on the game, this could be a number or a face value (e.g., "A" for Ace).
   *
   * @return The value of the card as a String.
   */
  String getValue();

  /**
   * Retrieves the suit of the card. Typical suits include "Hearts", "Diamonds", "Clubs", and "Spades".
   *
   * @return The suit of the card as a String.
   */
  String getSuit();

  /**
   * Converts the card's value to its numeric equivalent for easier computation in game logic.
   * For example, face cards like Jacks, Queens, and Kings may be converted to specific numerical values.
   *
   * @return The numeric value of the card.
   */
  int getNumericValue();

  /**
   * Determines whether this card is equal to another object. Typically used to compare another card
   * to see if they have the same value and suit.
   *
   * @param obj The object to compare this card against.
   * @return true if the given object represents a card equivalent to this one, false otherwise.
   */
  boolean equals(Object obj);

  /**
   * Computes the hash code for this card. Essential for using cards in collections that depend on hash codes,
   * such as HashMaps and HashSets.
   *
   * @return A hash code value for this card.
   */
  int hashCode();
}
