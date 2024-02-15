/**
 * This abstract class provides a skeletal implementation of the {@code Shape} interface to minimize the effort required to implement this interface.
 * It encapsulates common functionality and fields needed by all shapes, such as a reference point.
 * Created by ashesh on 1/26/2017.
 */
public abstract class AbstractShape implements Shape {
  /**
   * The reference point of the shape, often used as an anchor point or a point of origin for the shape.
   */
  protected Point2D reference;

  /**
   * Constructs an {@code AbstractShape} with the specified reference point.
   *
   * @param reference the reference point of the shape
   */
  public AbstractShape(Point2D reference) {
    this.reference = reference;
  }

  /**
   * Calculates and returns the distance from the shape's reference point to the origin of the coordinate system.
   *
   * @return the Euclidean distance from the reference point to the origin
   */
  @Override
  public double distanceFromOrigin() {
    return reference.distToOrigin();
  }

  /**
   * Compares this shape with the specified shape for order based on the area. Returns a negative integer,
   * zero, or a positive integer as this shape's area is less than, equal to, or greater than the specified shape's area.
   *
   * @param s the shape to be compared
   * @return a negative integer, zero, or a positive integer as this shape's area
   *         is less than, equal to, or greater than the specified shape's area
   */
  @Override
  public int compareTo(Shape s) {
    double areaThis = this.area();
    double areaOther = s.area();

    if (areaThis < areaOther) {
      return -1;
    } else if (areaOther < areaThis) {
      return 1;
    } else {
      return 0;
    }
  }
}
