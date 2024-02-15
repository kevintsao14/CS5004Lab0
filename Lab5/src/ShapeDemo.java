import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Demonstrates the functionality of different shapes and their comparison based on area and perimeter.
 * This class uses a LinkedList to store shapes and Collections.sort to sort them.
 */
public class ShapeDemo {

  /**
   * The main method to demonstrate shape sorting by area and perimeter.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    List<Shape> shapes = new LinkedList<>();

    // Adding shapes to the list
    shapes.add(new Circle(5.0, 6.0, 15.0)); // Add a circle
    shapes.add(new Rectangle(4.0, 6.0, 3.0, 4.0)); // Add a rectangle
    shapes.add(new Triangle(new Point2D(1.0, 2.0), new Point2D(0.0, 3.0), new Point2D(-1.0, 0.0))); // Add a triangle

    // Sort shapes by area (natural ordering using Comparable)
    Collections.sort(shapes);

    // Print shapes sorted by area
    System.out.println("Sorted by area:");
    for (Shape shape : shapes) {
      System.out.println(shape + " - Area: " + shape.area());
    }

    // Sort shapes by perimeter (using Comparator)
    Collections.sort(shapes, new PerimeterComparator());

    // Print shapes sorted by perimeter
    System.out.println("\nSorted by perimeter:");
    for (Shape shape : shapes) {
      System.out.println(shape + " - Perimeter: " + shape.perimeter());
    }
  }
}
