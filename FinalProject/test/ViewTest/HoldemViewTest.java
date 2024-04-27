import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class HoldemViewTest {
  private view.HoldemView view;

  @Before
  public void setUp() {
    // Ensure the GUI does not actually show during tests to avoid manual dismissal
    view = new view.HoldemView();
    view.setVisible(false);  // Hide GUI during automated test runs
  }

  @Test
  public void testComponentInitialization() {
    assertNotNull("Card1 Value Text Field should not be null", view.card1ValueTextField);
    assertNotNull("Card1 Suit Text Field should not be null", view.card1SuitTextField);
    assertNotNull("Card2 Value Text Field should not be null", view.card2ValueTextField);
    assertNotNull("Card2 Suit Text Field should not be null", view.card2SuitTextField);
    assertNotNull("Submit Button should not be null", view.submitButton);
    assertTrue("Result area should be non-editable", !view.resultArea.isEditable());
  }

  @Test
  public void testDisplayError() {
    String errorMessage = "Test Error";
    view.displayError(errorMessage);
    // Ideally, check if the error message dialog was displayed.
    // This may require adding a lastErrorMessage property to the view for testing purposes.
  }

  @Test
  public void testUpdateTextArea() {
    String title = "Results";
    Map<String, String> dataMap = new HashMap<>();
    dataMap.put("Hand", "Flush");
    dataMap.put("Best Move", "Raise");

    view.updateTextArea(title, dataMap);
    assertTrue("Result area should contain title", view.resultArea.getText().contains(title));
    assertTrue("Result area should contain hand information", view.resultArea.getText().contains("Flush"));
    assertTrue("Result area should contain move advice", view.resultArea.getText().contains("Raise"));
  }
}
