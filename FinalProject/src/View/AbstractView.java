public abstract class AbstractView implements IUserInterface {
  // Implement common methods here, like displayErrorMessage or handleUserInteraction
  public abstract void displayHand(Hand hand);
  public abstract void displayScore(int score);
  public abstract void displayAdvice(String advice);
  public abstract Hand getHandInput();
}
