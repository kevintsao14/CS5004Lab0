public class ConcreteModel implements Model{

  @Override
  public void moveSnake(int direction) {
    //Updtae snake position
  }

  @Override
  public boolean checkCollision() {
    return false;
  }

  @Override
  public void generateApple() {
    //generate apple's position
  }

  @Override
  public List<Coordinate> getSnakeCoordinates() {
    //get snake's coordinates
  }

  @Override
  public Coordinate getAppleCoordinate() {
    //get apple's coordinate
  }
}
