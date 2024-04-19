public interface IHand {
  int getTotalValue(); // Returns the sum of the numeric values of the cards in the hand
//  public void finalizeHand();
  String describeHand(); // Returns a string description of the hand
  String toGetHandCombination();
}
