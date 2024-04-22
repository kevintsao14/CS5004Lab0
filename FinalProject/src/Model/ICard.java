
public interface ICard {
  String getValue();
  String getSuit();
  int getNumericValue();
  boolean equals(Object obj);
  int hashCode();
}