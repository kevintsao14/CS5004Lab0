//import java.util.Collections;
//import java.util.Map;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class HoldemControllerTest {
//  private MockHoldemModel mockModel;
//  private MockHoldemView mockView;
//  private HoldemController controller;
//
//  @Before
//  public void setUp() {
//    HandF mockHand = new MockHandF("Pair", 2); // Assume MockHandF extends HandF
//    Map<String, String> mockDecisions = Collections.singletonMap("Early", "Fold");
//    Map<String, String> mockComments = Collections.singletonMap("Early", "Not favorable");
//
//    mockModel = new MockHoldemModel(mockHand, mockDecisions, mockComments);
//    mockView = new MockHoldemView();
//
//    controller = new HoldemController(mockModel, mockView);
//  }
//
//  @Test
//  public void testEvaluateHandSuccessful() {
//    controller.evaluateHand();
//
//    assertNotNull("Info should be displayed", mockView.displayedInfo);
//    assertEquals("Decisions should be displayed", "Fold", mockView.displayedDecisions.get("Early"));
//    assertEquals("Comments should be displayed", "Not favorable", mockView.displayedComments.get("Early"));
//  }
//
//  @Test
//  public void testEvaluateHandInvalidInput() {
//    // Simulate invalid input
//    mockView = new MockHoldemView() {
//      @Override
//      public String getFirstCardValue() {
//        return ""; // Invalid value
//      }
//    };
//    controller = new HoldemController(mockModel, mockView);
//
//    controller.evaluateHand();
//
//    assertEquals("Invalid Input: Card value cannot be empty", mockView.displayedError);
//  }
//}
