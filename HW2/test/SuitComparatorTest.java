import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SuitComparatorTest {
  private Card cardClub2;
  private Card cardDiamond3;
  private Card cardHeart4;
  private Card cardSpade5;
  private SuitComparator comparator;

  @Before
  public void setUp() {
    cardClub2 = new Card(Suit.CLUBS, 2);
    cardDiamond3 = new Card(Suit.DIAMONDS, 3);
    cardHeart4 = new Card(Suit.HEARTS, 4);
    cardSpade5 = new Card(Suit.SPADES, 5);
    comparator = new SuitComparator();
  }

  @Test
  public void testCompareSameSuit() {
    assertEquals(0, comparator.compare(cardClub2, new Card(Suit.CLUBS, 10)));
  }

  @Test
  public void testCompareDifferentSuits() {
    int comparisonResult = comparator.compare(cardSpade5, cardHeart4);
    assertTrue("Expected cardSpade5 to be greater than cardHeart4", comparisonResult > 0);
    assertTrue(comparator.compare(cardClub2, cardDiamond3) < 0);
    assertTrue(comparator.compare(cardDiamond3, cardHeart4) < 0);
    assertTrue(comparator.compare(cardHeart4, cardSpade5) < 0);
  }

  @Test
  public void testSortCardsBySuit() {
    List<Card> cards = Arrays.asList(cardSpade5, cardHeart4, cardDiamond3, cardClub2);
    Collections.sort(cards, comparator);
    assertEquals(Arrays.asList(cardClub2, cardDiamond3, cardHeart4, cardSpade5), cards);
  }
}
