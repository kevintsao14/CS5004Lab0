public interface Model {
  void moveSnake(int direction);
  boolean checkCollision();
  private void regenApple()
  List<Coordinate> getSnakeCoordinates();
  Coordinate getAppleCoordinate();
}
