public class Main {
  public static void main(String[] args) {
    Model model = new ConcreteModel();
    View view = new ConcreteView();
    Controller controller = new GameController(model, view);

  }
}
