import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test class for {@code HandInfoDisplayFormat}.
 * This class tests the functionality of formatting hand information into a readable string
 * using a minimal implementation of {@code HandF}.
 */
public class HandInfoDisplayFormatTest {

  /**
   * A minimal concrete implementation of {@code HandF} for testing purposes.
   */
  private static class TestableHandF extends HandF {
    public TestableHandF(CardF firstCard, CardF secondCard) {
      super(firstCard, secondCard);
    }

    @Override
    public int getHandScore() {
      return 40;  // Providing a stub score
    }

    @Override
    public String getHandCombination() {
      return "HandComb";  // Providing a stub hand combination
    }
  }

  /**
   * Tests the correct formatting of hand information using an instance of {@code TestableHandF}.
   */
  @Test
  public void testFormatHandInformation() {
    CardF firstCard = new CardF("A", "H");  // Ace of Hearts
    CardF secondCard = new CardF("K", "H"); // King of Hearts

    HandF testHand = new TestableHandF(firstCard, secondCard);
    HandInfoDisplayFormat formatter = new HandInfoDisplayFormat();

    // Expected format based on the hardcoded values in TestableHandF
    String expectedOutput = "Score: 40\nHand Combination: HandComb";
    String result = formatter.formatHandInformation(testHand);

    assertEquals("Formatted hand information should match expected output", expectedOutput, result);
  }

  /**
   * Tests the behavior of formatHandInformation when null is passed.
   * Expected to throw a NullPointerException with a specific message.
   */
  @Test
  public void testFormatHandInformationWithNullHand() {
    HandInfoDisplayFormat formatter = new HandInfoDisplayFormat();
    try {
      formatter.formatHandInformation(null);  // Expected to throw NullPointerException
      fail("Expected a NullPointerException to be thrown");
    } catch (NullPointerException e) {
      assertEquals("Hand cannot be null", e.getMessage());
    }
  }
}
