import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * JUnit test class for books
 */
public class ShoeTest {

  private Shoe Shoe1;

  @Before
  public void setUp() {
    Shoe1 = new Shoe("Running", "Blue", "Nike", 9.5);
  }

  @Test
  public void testShoeType() {
    String expected = "Running";
    assertEquals(expected, Shoe1.getType());
  }

  @Test
  public void testBrand() {
    String expectedBrand = "Nike";
    assertEquals(expectedBrand, Shoe1.getBrand());
  }

  @Test
  public void testShoeColor() {
    String expectedColor = "Blue";
    assertEquals(expectedColor, Shoe1.getColor());
  }
}
