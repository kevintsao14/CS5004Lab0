import org.junit.Test;
import static org.junit.Assert.*;

public class IListOfIntsTest {

  @Test
  public void testEmptyNodeBehavior() {
    IListOfInts emptyNode = new EmptyNode();
    assertEquals("Empty node should have a count of 0", 0, emptyNode.getCount());
    assertEquals("Sum of an empty node should be 0", 0, emptyNode.getSum());

    IListOfInts singleElementList = emptyNode.append(10);
    assertTrue("Appending to an empty node should yield an ElementNode", singleElementList instanceof ElementNode);
    assertEquals("Element added to an empty node should have the correct value", 10, singleElementList.getDataAtIndex(0));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testEmptyNodeExceptionOnGetData() {
    IListOfInts emptyNode = new EmptyNode();
    emptyNode.getDataAtIndex(0); // This should throw an IndexOutOfBoundsException
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testEmptyNodeExceptionOnGetRest() {
    IListOfInts emptyNode = new EmptyNode();
    emptyNode.getRest(); // This should throw an UnsupportedOperationException
  }

  @Test
  public void testElementNodeBasicOperations() {
    IListOfInts list = new ElementNode(1, new EmptyNode());
    list = list.append(2).append(3); // Creates a list [1, 2, 3]

    assertEquals("List should correctly report its size", 3, list.getCount());
    assertEquals("Sum of the list elements should be correct", 6, list.getSum());
    assertEquals("Data at index 1 should be 2", 2, list.getDataAtIndex(1));

    IListOfInts rest = list.getRest();
    assertEquals("Rest of the list should exclude the first element", 2, rest.getCount());
  }

  @Test
  public void testElementNodeInsertion() {
    IListOfInts list = new ElementNode(1, new EmptyNode()).append(3); // Starts with [1, 3]
    list = list.insertAtIndex(2, 1); // Inserts 2 at index 1 to make the list [1, 2, 3]

    assertEquals("ElementNode should allow insertion at specific index", 2, list.getDataAtIndex(1));
    assertEquals("List should update its size after insertion", 3, list.getCount());
  }

  @Test
  public void testPrependToEmptyNode() {
    IListOfInts emptyList = new EmptyNode();
    IListOfInts result = emptyList.prepend(10);

    // Check that the result is not an empty node anymore
    assertFalse("Result should not be an instance of EmptyNode", result instanceof EmptyNode);

    // Check that the result is an instance of ElementNode
    assertTrue("Result should be an instance of ElementNode", result instanceof ElementNode);

    // Verify the data of the newly prepended element
    assertEquals("Newly prepended element should have the correct value", 10, result.getDataAtIndex(0));

    // Verify the count of elements in the list
    assertEquals("List should have 1 element after prepending", 1, result.getCount());
  }

  @Test
  public void testPrependToElementNode() {
    // Start with a single-element list
    IListOfInts list = new ElementNode(20, new EmptyNode());

    // Prepend a new element
    IListOfInts result = list.prepend(10);

    // Verify the data of the newly prepended element
    assertEquals("First element should be the newly prepended one", 10, result.getDataAtIndex(0));

    // Verify the data of the second element, which was the original first element
    assertEquals("Second element should be the original first one", 20, result.getDataAtIndex(1));

    // Verify the count of elements in the list
    assertEquals("List should have 2 elements after prepending", 2, result.getCount());
  }

  @Test
  public void testInsertAtIndexZeroInEmptyNode() {
    IListOfInts emptyList = new EmptyNode();
    IListOfInts resultList = emptyList.insertAtIndex(10, 0);

    // Check that the result is an instance of ElementNode
    assertTrue("Resulting list should be an instance of ElementNode", resultList instanceof ElementNode);

    // Verify the data of the inserted element
    assertEquals("Inserted element should have the correct value", 10, resultList.getDataAtIndex(0));

    // Verify the count of elements in the list
    assertEquals("List should have 1 element after insertion", 1, resultList.getCount());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInsertAtIndexGreaterThanZeroInEmptyNode() {
    IListOfInts emptyList = new EmptyNode();
    // Attempt to insert at an index greater than 0, which should throw an exception
    emptyList.insertAtIndex(20, 1);
  }
}
