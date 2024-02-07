import java.util.function.Predicate;

/**
 * Represents a node containing an integer element in a list. This class is part of a recursive
 * data structure for integer lists, where each node holds a single integer value and a reference
 * to the next node in the list.
 */
public class ElementNode implements IListOfInts {
  private int data;           // The integer data stored in this node
  private IListOfInts next;   // The reference to the next node in the list

  /**
   * Constructs a new ElementNode with the given integer data and the next node in the list.
   *
   * @param data The integer data to be stored in this node.
   * @param next The next node in the list.
   */
  public ElementNode(int data, IListOfInts next) {
    this.data = data;
    this.next = next;
  }

  /**
   * Prepends a given integer to the list, creating a new node for the integer
   * and setting it as the predecessor of this node.
   *
   * @param data The integer data to be added at the beginning of the list.
   * @return A new ElementNode containing the given data, linked to the current list.
   */
  @Override
  public IListOfInts prepend(int data) {
    return new ElementNode(data, this);
  }

  /**
   * Appends a given integer to the end of the list. This method recursively
   * traverses the list and adds the new element at the end.
   *
   * @param data The integer data to be added to the end of the list.
   * @return The head of the list with the new data appended.
   */
  @Override
  public IListOfInts append(int data) {
    this.next = this.next.append(data);
    return this;
  }

  /**
   * Inserts a given integer at a specified index in the list. If the index is 0,
   * the new element is inserted before this node.
   *
   * @param data  The integer data to be inserted into the list.
   * @param index The position at which to insert the new data.
   * @return The head of the list with the new data inserted.
   * @throws IndexOutOfBoundsException If the index is out of range.
   */
  @Override
  public IListOfInts insertAtIndex(int data, int index) throws IndexOutOfBoundsException {
    if (index == 0) {
      return new ElementNode(data, this);
    } else {
      this.next = this.next.insertAtIndex(data, index - 1);
      return this;
    }
  }

  /**
   * Retrieves the integer data at a specified index in the list. If the index is 0,
   * returns the data of this node.
   *
   * @param index The index of the data to retrieve.
   * @return The integer data at the specified index.
   * @throws IndexOutOfBoundsException If the index is out of range.
   */
  @Override
  public int getDataAtIndex(int index) throws IndexOutOfBoundsException {
    if (index == 0) {
      return this.data;
    } else {
      return this.next.getDataAtIndex(index - 1);
    }
  }

  /**
   * Returns the list of elements after this node, effectively removing the first element.
   *
   * @return The list starting from the node following this one.
   */
  @Override
  public IListOfInts getRest() {
    return this.next;
  }

  /**
   * Returns the count of elements in the list starting from this node.
   *
   * @return The number of elements in the list starting from this node.
   */
  @Override
  public int getCount() {
    return 1 + this.next.getCount();
  }

  /**
   * Returns the sum of all integer elements in the list starting from this node.
   *
   * @return The sum of all integer elements in the list starting from this node.
   */
  @Override
  public int getSum() {
    return this.data + this.next.getSum();
  }
}
