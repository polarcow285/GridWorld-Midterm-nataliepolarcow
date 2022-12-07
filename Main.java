//import that contains the Point class
import java.awt.*;
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    Gridworld myGridworld = new Gridworld(4,4, new Point(0,0), new Point(1,2));
    //System.out.println(Arrays.deepToString(myGridworld.grid));
    
    //System.out.println(Arrays.toString(Gridworld.actionList()));
    myGridworld.printGridValues();
    myGridworld.solve();
    //myGridworld.printGridValues(); kjsd
    myGridworld.printActions();
    myGridworld.printGridValues();
    GridSpot myGridSpot = new GridSpot();
  

    /*
    Agent myAgent = new Agent();
    System.out.println(myAgent.move(new Point(2,3), new Point(0,1), 4, 4));
    Point nextState = myAgent.move(new Point(2,3), new Point(0,1), 4, 4);
    System.out.println(nextState);
    */
    //System.out.println(myGridworld.returnReward(new Point (3,2)));
    
  }
  
}