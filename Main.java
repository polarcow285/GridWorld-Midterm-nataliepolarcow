//import that contains the Point class
import java.awt.*;
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    Gridworld myGridworld = new Gridworld(4,4, new Point(2,2), new Point(3,2));
    //System.out.println(Arrays.deepToString(myGridworld.grid));
    
    //System.out.println(Arrays.toString(Gridworld.actionList()));
    myGridworld.printGridValues();
    myGridworld.solve();
  }
}