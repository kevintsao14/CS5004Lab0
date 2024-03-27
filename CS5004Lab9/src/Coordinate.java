/**
 * Represents a 2D coordinates on a plane.
 */
public class Coordinate {

  /**
   * Construct a new coordinates objects with x and y coordinates.
   *
   * @param x x coordinates of the point
   * @param y y coordinates of the point
   */
  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * The x Coordinate point of the 2D space
   */
  int x;
  /**
   * The y Coordinate point of the 2D space
   */
  int y;

  /**
   * Compare this Coordinates object with other Coordinates objects for equality.
   * The two object will be consider equal if both x and y coordinates are equal.
   *
   * @param obj The object to compare this Coordinate against.
   * @return True if the size of the object is identical, otherwise false.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Coordinate) {
      Coordinate other = (Coordinate) obj;
      return other.x == this.x && other.y == this.y;
    }

    return false;
  }
}

//Todo 1: A hash code method can be implement to better compare the two objects.