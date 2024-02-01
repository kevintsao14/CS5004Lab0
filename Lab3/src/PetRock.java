public class PetRock {
  private String name;
  private boolean happy = false;

  public PetRock(String name) {
    this.name = name;
    if (name.isEmpty()){
      throw new IllegalArgumentException();
    }
  }
  public String getName() {
    return name;
  }

  public boolean isHappy(){
    return happy;
  }

  public void playWithRock() {
    happy = true;
  }

  public String getHappyMessage(){
    if (!happy){
      throw new IllegalStateException();
    }
    return "I'm happy!";
  }

  public int getFavNumber(){
    return 42;
  }

  public void waitTillHappy(){
    while (!happy){
      //do nothing!
    }
  }
  public double getMass(){
    double expectedMass = 10.0;
    return expectedMass;
  }

  @Override
  public String toString() {
    return "PetRock{" +
        "name='" + name + '\'' +
        ", happy=" + happy +
        ", Fav Num=" + getFavNumber() +'}';
  }
}
