import java.util.Comparator;
/**
 * This class is used to compare two Card objects based on the ordinal value of their suits.
 * It implements the comparator interface, providing a custom comparison
 * to sort cards by their suits. The suits are compared based on their ordinal values,
 * which are defined in the Suit enumeration.
 * <p>
 * This comparator can be used to sort a collection of Card objects in ascending order
 * of their suits (CLUBS -> DIAMONDS -> HEARTs -> SPADES).
 */

public class SuitComparator implements Comparator<Card> {

  /**
   * Compare the suits between two card object.
   * @param card1 the first card to compare.
   * @param card1 the second card to compare.
   * @return integer zero, positive one, or negative one depending on the first card object is
   * equal or greater than, or smaller than the second card object.
   */
  @Override
  public int compare(Card card1, Card card2) {
    int card1int = card1.getSuit().ordinal();
    int card2int = card2.getSuit().ordinal();
    if ( card1int < card2int ) {
      return -1;
    }
    if (card1int > card2int) {
      return 1;
    }
    return 0;
  }
}
