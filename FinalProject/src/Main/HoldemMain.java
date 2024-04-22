

import view.HoldemView;
import javax.swing.JFrame;

public class HoldemMain {
  public static void main(String[] args) {
    HoldemModel model = new HoldemModel();
    HoldemView view = new HoldemView();
    new HoldemController(model, view);
  }
}
