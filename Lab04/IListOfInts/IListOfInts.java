 /**
 * Represents a list of integers. This interface defines operations for manipulating and
 * accessing elements in the list.
 */
public interface IListOfInts {
  /**
   * Prepends a given integer to the list.
   *
   * @param data The integer data to add at the beginning of the list.
   * @return A new instance of IListOfInts with the data added.
   */
  IListOfInts prepend(int data);

  /**
   * Appends a given integer to the end of the list.
   *
   * @param data The integer data to add at the end of the list.
   * @return A new instance of IListOfInts with the data added.
   */
  IListOfInts append(int data);

   /**
   * Inserts a given integer at the specified index in the list. If the index is equal to
   * the size of the list, the data is appended.
   *
   * @param data The integer data to insert into the list.
   * @param index The position at which to insert the data.
   * @return A new instance of IListOfInts with the data inserted.
   * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index > size()).
   */
  IListOfInts insertAtIndex(int data, int index) throws IndexOutOfBoundsException;

   /**
   * Retrieves the integer at the specified index in the list.
   *
   * @param index The index of the integer to retrieve.
   * @return The integer at the specified index.
   * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
   */
  int getDataAtIndex(int index) throws IndexOutOfBoundsException;

   /**
   * Returns a new IListOfInts that represents all but the first element of this list.
   *
   * @return A new IListOfInts instance containing all elements except the first.
   * @throws UnsupportedOperationException If the list is empty.
   */
  IListOfInts getRest();

   /**
   * Returns the number of elements in the list.
   *
   * @return The count of elements in the list.
   */
  int getCount();

   /**
   * Calculates the sum of all integers in the list.
   *
   * @return The sum of all integers in the list.
   */
  int getSum();
}

