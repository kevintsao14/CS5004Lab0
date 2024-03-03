import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents a hand of cards or other elements. This interface allows for
 * operations such as adding, discarding, and accessing elements.
 *
 * @param <C> the type of elements in this hand, e.g., Card.
 */
public interface Hand<C> {

  /**
   * Creates an empty hand.
   *
   * @return an empty hand
   */
  Hand<C> emptyHand();

  /**
   * Adds an element to the hand.
   *
   * @param c the element to add
   */
  void add(C c);

  /**
   * Discards an element at a specified index.
   *
   * @param index the index of the element to discard
   * @throws IndexOutOfBoundsException if the index is invalid
   */
  void discardCard(int index) throws IndexOutOfBoundsException;

  /**
   * Retrieves an element by its index.
   *
   * @param index the index of the element to retrieve
   * @return the element at the specified index
   * @throws IndexOutOfBoundsException if the index is invalid
   */
  C get(int index) throws IndexOutOfBoundsException;

  /**
   * Returns the number of elements in the hand.
   *
   * @return the size of the hand
   */
  int getSize();

  /**
   * Checks if the hand is empty.
   *
   * @return true if the hand contains no elements, false otherwise
   */
  boolean isEmpty();

  /**
   * Finds an element that matches the given element.
   *
   * @param c the element to find in the hand
   * @return the matching element, or null if not found
   */
  int find(C c);

  /**
   * Sorts the elements in the hand using the provided comparator.
   *
   * @param comparator the comparator to determine the order of the elements
   */
  void sortHand(Comparator<C> comparator);

  /**
   * Filters the elements in the hand using the provided predicate.
   *
   * @param filter the predicate to apply to each element
   * @return a list of elements that satisfy the predicate
   */
  Hand<C> getHand(Predicate<C> filter);

  /**
   * Calculates and returns the sum of ranks of the elements in the hand.
   *
   * @return the sum of ranks
   */
  int rankSum();

  /**
   * Converts the cards in the hand to a new type using the provided converter function.
   *
   * @param <S> the type of the result after conversion
   * @param converter the function to convert cards from type C to type S
   * @return a new hand containing the converted cards
   */
  <S> Hand<S> getMap(Function<C,S> converter);
}