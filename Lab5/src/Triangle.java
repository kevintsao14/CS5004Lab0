/**
 * Represents a triangle defined by three points in a 2D space.
 * This class extends {@code AbstractShape} and implements methods to calculate the triangle's area and perimeter.
 */
public class Triangle extends AbstractShape {
  private Point2D point2;
  private Point2D point3;

  /**
   * Constructs a triangle with the specified reference point and two additional points.
   * Throws IllegalArgumentException if the points are not unique.
   *
   * @param reference the reference point of the triangle
   * @param point2 the second point of the triangle
   * @param point3 the third point of the triangle
   * @throws IllegalArgumentException if any of the points are not unique
   */
  public Triangle(Point2D reference, Point2D point2, Point2D point3) {
    super(reference);
    if (reference.equals(point2) || reference.equals(point3) || point2.equals(point3)) {
      throw new IllegalArgumentException("Points must be unique");
    }
    this.point2 = point2;
    this.point3 = point3;
  }

  /**
   * Calculates the distance between two {@code Point2D} objects.
   *
   * @param p1 the first point
   * @param p2 the second point
   * @return the Euclidean distance between the two points
   */
  private double distance(Point2D p1, Point2D p2) {
    return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
  }

  /**
   * Calculates the area of the triangle using Heron's formula.
   * Returns 0 for degenerate triangles (where points are collinear).
   *
   * @return the area of the triangle
   */
  @Override
  public double area() {
    double a = distance(reference, point2);
    double b = distance(reference, point3);
    double c = distance(point2, point3);
    double s = (a + b + c) / 2;
    double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
    return area > 0 ? area : 0;
  }

  /**
   * Calculates the perimeter of the triangle as the sum of the lengths of its sides.
   *
   * @return the perimeter of the triangle
   */
  @Override
  public double perimeter() {
    return distance(reference, point2) + distance(reference, point3) + distance(point2, point3);
  }

  /**
   * Resizes the triangle by a given factor. The method scales the distances from the reference point
   * to the other two points, effectively resizing the triangle while maintaining its shape.
   *
   * @param factor the factor by which the triangle is to be resized. The actual distances from the reference
   *               point to the other points are scaled by the square root of this factor.
   * @return a new {@code Triangle} instance that represents the resized triangle.
   */
  @Override
  public Triangle resize(double factor) {
    // Calculate scaling factor for distances. Since area changes by the factor,
    // distances should change by the square root of the factor to maintain the shape.
    double scaleFactor = Math.sqrt(factor);

    // Calculate new positions for point2 and point3 scaled from the reference point
    Point2D newPoint2 = new Point2D(
        reference.getX() + (point2.getX() - reference.getX()) * scaleFactor,
        reference.getY() + (point2.getY() - reference.getY()) * scaleFactor);

    Point2D newPoint3 = new Point2D(
        reference.getX() + (point3.getX() - reference.getX()) * scaleFactor,
        reference.getY() + (point3.getY() - reference.getY()) * scaleFactor);

    // Return a new Triangle with the resized dimensions
    return new Triangle(reference, newPoint2, newPoint3);
  }

  /**
   * Provides a string representation of the triangle, including the coordinates of its three points.
   *
   * @return a string describing the triangle
   */
  @Override
  public String toString() {
    return String.format("Triangle: Reference (%.2f,%.2f), Point2 (%.2f,%.2f), Point3 (%.2f,%.2f)",
        reference.getX(), reference.getY(),
        point2.getX(), point2.getY(),
        point3.getX(), point3.getY());
  }
}

// Answers to Question 3:
// To change from area to perimeter, the part that need to be change is the compareTo methd.
// If no AbstracShape was created, all Triangle, Circle, and Rectangle files need to be modified,
// however, in our case only the AbstracShape.java need to be chagne.
