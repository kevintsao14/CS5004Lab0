import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Implementation of a generic Hand interface managing a collection of cards.
 *
 * @param <C> the card type this hand manages
 */
public class ImplementHand<C> implements Hand<C> {

  private ArrayList<C> cards;

  /**
   * Constructs an empty hand of cards.
   */
  public ImplementHand() {
    this.cards = new ArrayList<>();
  }

  /**
   * Creates and returns a new, empty hand.
   *
   * @return a new instance of {@code ImplementHand} that is empty.
   */
  @Override
  public Hand<C> emptyHand() {
    return new ImplementHand<>();
  }

  /**
   * Adds a card to the beginning of the hand.
   *
   * @param card The card to be added to the hand.
   */
  @Override
  public void add(C card) {
    cards.add(0, card);
  }

  /**
   * Discards a card at the specified index from the hand.
   *
   * @param index The index of the card to be discarded.
   * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
   */
  @Override
  public void discardCard(int index) {
    if (index >= 0 && index < cards.size()) {
      cards.remove(index);
    } else {
      throw new IndexOutOfBoundsException("Invalid card index.");
    }
  }

  /**
   * Retrieves the card at the specified index.
   *
   * @param index The index of the card to retrieve.
   * @return The card at the specified index.
   * @throws IllegalArgumentException if the index is out of range (index < 0 || index >= size()).
   */
  @Override
  public C get(int index) {
    if (index >= 0 && index < cards.size()) {
      return cards.get(index);
    } else {
      throw new IllegalArgumentException("Index out of range");
    }
  }

  /**
   * Returns the size of the hand.
   *
   * @return The number of cards in the hand.
   */
  @Override
  public int getSize() {
    return cards.size();
  }

  /**
   * Checks if the hand is empty.
   *
   * @return true if the hand is empty, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    return cards.isEmpty();
  }

  /**
   * Finds the index of the specified card in the hand.
   *
   * @param card The card to find.
   * @return The index of the specified card, or -1 if not found.
   */
  @Override
  public int find(C card) {
    return cards.indexOf(card);
  }

  /**
   * Sorts the hand using the specified comparator.
   *
   * @param comparator The comparator to determine the order of the cards.
   */
  @Override
  public void sortHand(Comparator<C> comparator) {
    Collections.sort(cards, comparator);
  }

  /**
   * Retrieves a new hand consisting of cards that match the given predicate.
   *
   * @param predicate The predicate to apply to each card.
   * @return A new hand containing only the cards that match the predicate.
   */
  @Override
  public Hand<C> getHand(Predicate<C> predicate) {
    Hand<C> result = new ImplementHand<>();
    for (C card : cards) {
      if (predicate.test(card)) {
        result.add(card);
      }
    }
    return result;
  }

  /**
   * Calculates the sum of ranks for the cards in the hand.
   *
   * @return The sum of ranks for all cards in the hand.
   */
  @Override
  public int rankSum() {
    int sum = 0;
    for (C card : cards) {
      sum += ((Card) card).getRank();
    }
    return sum;
  }

  /**
   * Creates a new hand where each card has been mapped to a new type using the provided
   * converter function. This method accounts for the reversed order of card addition
   * by reversing the iteration order of the cards during mapping to ensure that the
   * mapped hand retains the original order of card addition.
   *
   * @param <S> the type of the mapped values
   * @param converter a function that converts a card of type {C} to type {S}
   * @return a new {Hand<S>} containing the mapped values, preserving the original order of addition
   */
  @Override
  public <S> Hand<S> getMap(Function<C, S> converter) {
    Hand<S> mappedHand = new ImplementHand<>();
    List<C> reversedCards = new ArrayList<>(cards);
    Collections.reverse(reversedCards); // Reverse the order of reversedCards to match the original addition order

    for (C card : reversedCards) {
      S mappedValue = converter.apply(card);
      mappedHand.add(mappedValue);
    }
    return mappedHand;
  }
}
