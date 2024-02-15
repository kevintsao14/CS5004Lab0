import java.util.Comparator;

/**
 * This class provides a comparison mechanism for Shape objects based on their perimeter.
 * It implements the Comparator interface, allowing it to be used wherever a custom comparator is needed,
 * such as in sorting algorithms. The natural ordering of Shape objects (based on area) remains unaffected,
 * enabling the use of this comparator to sort Shape objects by their perimeter without altering their inherent comparison logic.
 */
public class PerimeterComparator implements Comparator<Shape> {

  /**
   * Compares two Shape objects based on their perimeter.
   *
   * @param s1 the first Shape object to be compared.
   * @param s2 the second Shape object to be compared.
   * @return a negative integer, zero, or a positive integer as the perimeter of the first Shape
   *         is less than, equal to, or greater than the perimeter of the second Shape.
   */
  @Override
  public int compare(Shape s1, Shape s2) {
    // Retrieve the perimeters of the two shapes
    double perimeter1 = s1.perimeter();
    double perimeter2 = s2.perimeter();

    // Compare the perimeters and return the result
    return Double.compare(perimeter1, perimeter2);
  }
}
