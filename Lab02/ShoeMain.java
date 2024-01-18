public class ShoeMain {

  public static void main(String[] args) {
    // Creating a Shoe object
    Shoe runningShoe = new Shoe("Basketball", "Blue", "Nike", 9.5);

    // Accessing information using getter methods
    String type = runningShoe.getType();
    String color = runningShoe.getColor();
    String brand = runningShoe.getBrand();
    double size = runningShoe.getSize();

    // Displaying information
    System.out.println("Type: " + type);
    System.out.println("Color: " + color);
    System.out.println("Brand: " + brand);
    System.out.println("Size: " + size);
  }
}
