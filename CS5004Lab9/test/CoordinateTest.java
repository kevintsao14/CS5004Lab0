import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

  private Coordinate coordinate1;
  private Coordinate coordinate2;
  private Coordinate coordinate3;
  private Object differentObject;

  @Before
  public void setUp() {
    coordinate1 = new Coordinate(1, 1);
    coordinate2 = new Coordinate(1, 1);
    coordinate3 = new Coordinate(2, 2);
    differentObject = new Object();
  }

  @Test
  public void testEquals_Self() {
    assertTrue(coordinate1.equals(coordinate1));
    assertTrue(coordinate2.equals(coordinate2));
  }

  @Test
  public void testEquals_SameValues() {
    assertTrue(coordinate1.equals(coordinate2));
    assertTrue("Reversing arguments should still result in equality",
        coordinate2.equals(coordinate1));
  }

  @Test
  public void testEquals_DifferentValues() {
    assertFalse(coordinate1.equals(coordinate3));
    assertFalse("Reversing arguments should still result in inequality",
        coordinate3.equals(coordinate1));
  }

  @Test
  public void testEquals_DifferentType() {
    assertFalse("A coordinate should not be equal to an object of a different type",
        coordinate1.equals(differentObject));
    // Demonstrating the method does not throw an exception when types are different
    try {
      boolean result = coordinate1.equals(differentObject);
      assertFalse("Should not be equal.",result);
    } catch (Exception e) {
      fail("Different object type should not throw an exception.");
    }
  }
}
