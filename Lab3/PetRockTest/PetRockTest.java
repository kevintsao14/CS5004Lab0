import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class PetRockTest {
  private PetRock rocky;

  @Before
  public void setUp() throws Exception {
    rocky = new PetRock("Rocky");
  }

  @Test
  public void testGetName() {
    assertEquals("Rocky", rocky.getName());
  }

  @Test
  public void testUnhappy() {
    assertFalse(rocky.isHappy());
  }

  @Test
  public void happyAfterPlay() {
    rocky.playWithRock();
    assertTrue(rocky.isHappy());
  }

  @Test (expected = IllegalStateException.class)
  public void nameFail() {
    rocky.getHappyMessage();
  }
  @Test
  public void name() {
    rocky.playWithRock();
    String msg = rocky.getHappyMessage();
    assertEquals("I'm happy!",msg);
  }

  @Test
  public void testFavNum() {
    assertEquals(42, rocky.getFavNumber());
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyNameFail() {
    PetRock woofy = new PetRock("");
  }

  @Test (timeout = 100)//in ms
  public void waitForHappyTimeOut() {
    rocky.waitTillHappy();
  }

  @Test
  public void testMass() {
    assertEquals(10.0,rocky.getMass());
  }

  @Test
  public void testToString() {
    assertEquals("PetRock{name='Rocky', happy=false, Fav Num=42}",
                  rocky.toString());
  }
}



