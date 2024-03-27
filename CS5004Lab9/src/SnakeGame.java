import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The {@code SnakeGame} class serves as the entry point for the snake game application.
 * It initializes and displays the main game window with a {@code MyPanel} instance where
 * the game is rendered.
 */
public class SnakeGame {

  /**
   * The main method that creates the game window and initializes the game.
   * It sets up a {@code JFrame} window, adds a {@code MyPanel} to it, and makes the window visible.
   *
   * @param args command line arguments (not used in this application).
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JPanel panel = new MyPanel();
    panel.setPreferredSize(new Dimension(400, 400));
    frame.add(panel);
    frame.pack();
    // by default, everything is invisible. Make it visible
    frame.setVisible(true);
  }
}
