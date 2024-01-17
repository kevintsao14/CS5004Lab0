/**
 * This class represent a shoe. Each shoe has type, color, brand, and size.
 */
public class Shoe {
  private String type;
  private String color;
  private String brand;
  private double size;

  /**
   * Construct a Shoe object that has the provided type, color, brand, and size
   *
   * @param type  the type of the give shoe
   * @param color the color of the give shoe
   * @param brand the brand of the give shoe
   * @param size  the size of the give shoe
   */
  public Shoe(String type, String color, String brand, double size) {
    this.type = type;
    this.color = color;
    this.brand = brand;
    this.size = size;
  }
  /**
   * Return the type of the shoe
   *
   * @return the type of the shoe
   */
  public String getType() {
    return this.type;
  }

    /**
     * Return the color of the shoe
     * @return the color of the shoe
     */
    public String getColor () {
      return this.color;
    }

    /**
     * Return the brand of the shoe
     * @return the brand of the shoe
     */
    public String getBrand () {
      return this.brand;
    }

    /**
     * Return the size of the shoe
     * @return the size of the shoe
     */
    public double getSize () {
      return this.size;
    }
  }

