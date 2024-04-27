/**
 * Concrete implementation of the {@code HandF} abstract class. This class provides a specific implementation
 * for initializing a poker hand with two cards, typically used in games like Texas Hold'em.
 */
public class ConcreteHandF extends HandF {

  /**
   * Constructs a {@code ConcreteHandF} object with two specified cards.
   * This constructor simply passes the provided card objects to the superclass constructor
   * of {@code HandF}, leveraging its functionality to initialize the card fields.
   *
   * @param firstCard The first card of the hand, must not be null.
   * @param secondCard The second card of the hand, must not be null.
   * @throws IllegalArgumentException if either {@code firstCard} or {@code secondCard} is null,
   *                                  which is checked in the {@code HandF} constructor.
   */
  public ConcreteHandF(CardF firstCard, CardF secondCard) {
    super(firstCard, secondCard);
  }
}
