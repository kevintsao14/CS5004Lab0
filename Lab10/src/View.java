import java.awt.Graphics;

public interface View {
  void renderGame(List<Coordinate> snakeCoordinates, Coordinate appleCoordinate);
  void setInputStream(InputStream inputStream);
  void setOutputStream(OutputStream outputStream);
  protected void paintComponent(Graphics g) {}

}
