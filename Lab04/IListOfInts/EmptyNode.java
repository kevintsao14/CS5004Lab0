import java.util.function.Predicate;
/**
 * Represents an empty node in a list of integers. This class is a part of a recursive data
 * structure for integer lists, where it signifies the end of the list.
 */
public class EmptyNode implements IListOfInts {

  /**
   * Prepends a given integer to this empty node, effectively creating a new list with
   * a single element.
   *
   * @param data The integer data to be added at the beginning of the list.
   * @return A new {@link ElementNode} containing the given data and pointing to this empty node.
   */
  @Override
  public IListOfInts prepend(int data) {
    return new ElementNode(data, this);
  }

  /**
   * Appends a given integer to this empty node, effectively creating a new list with
   * a single element.
   *
   * @param data The integer data to be added to the list.
   * @return A new {@link ElementNode} containing the given data.
   */
  @Override
  public IListOfInts append(int data) {
    return new ElementNode(data, this);
  }

   /**
   * Attempts to insert a given integer at a specified index in this empty list. Since
   * the list is empty, the only valid index is 0.
   *
   * @param data  The integer data to insert.
   * @param index The target index for the data to be inserted at.
   * @return A new {@link ElementNode} containing the given data if the index is 0.
   * @throws IndexOutOfBoundsException If the provided index is not 0, indicating the index is out of bounds.
   */
  @Override
  public IListOfInts insertAtIndex(int data, int index) throws IndexOutOfBoundsException {
    if (index == 0) {
      return new ElementNode(data, this);
    } else {
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for insertion into an empty list.");
    }
  }

  /**
   * Retrieves the integer data at a specified index in this empty list. Since the list
   * is empty, any index is invalid.
   *
   * @param index The index of the data to retrieve.
   * @return Does not return normally.
   * @throws IndexOutOfBoundsException Always thrown since there are no elements in the list.
   */
  @Override
  public int getDataAtIndex(int index) throws IndexOutOfBoundsException {
    throw new IndexOutOfBoundsException("Cannot get data at index " + index + " from an empty list.");
  }

  /**
   * Returns the rest of the list after this node. For an empty node, this operation is
   * not supported since there are no elements.
   *
   * @return Does not return normally.
   * @throws UnsupportedOperationException Always thrown since there is no 'rest' of an empty list.
   */
  @Override
  public IListOfInts getRest() {
    throw new UnsupportedOperationException("Cannot get the rest of an empty list.");
  }

  /**
   * Returns the count of elements in this list. For an empty node, this is always 0.
   *
   * @return The count of elements in the list, which is 0 for an empty node.
   */
  @Override
  public int getCount() {
    return 0;
  }

  /**
   * Returns the sum of all integers in this list. For an empty node, the sum is 0.
   *
   * @return The sum of elements in the list, which is 0 for an empty node.
   */
  @Override
  public int getSum() {
    return 0;
  }
}
