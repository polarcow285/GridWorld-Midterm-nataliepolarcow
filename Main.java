//import that contains the Point class
import java.awt.*;
import java.util.*;


class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    //vertical, horizontal
    Gridworld myGridworld = new Gridworld(4,5, new Point(4,0), new Point(0,3), 1);
    //System.out.println(Arrays.deepToString(myGridworld.grid));
    
    //System.out.println(Arrays.toString(Gridworld.actionList()));
    myGridworld.printGridValues();
    myGridworld.solve();
    //myGridworld.printGridValues();
    myGridworld.printActions();
    myGridworld.printGridValues();
    Printer.printGridworld(myGridworld);
    System.out.println(myGridworld.isPossible());
    //GridSpot myGridSpot = new GridSpot();
    String question = "Choose the height (vertical length) of your gridworld (3-10)";
    int height = askUser(3, 10,question);
    
    question = "Choose the width (horizontal length) of your gridworld (3-10)";
    int width = askUser(3, 10,question);
    //display gridworld size
    question = "Choose the starting X coordinate for your agent (0-" + (width-1) + ")";
    int startX = askUser(0, width-1,question);

    question = "Choose the starting Y coordinate for your agent (0-" + (height-1) + ")";
    int startY = askUser(0, height-1,question);
  

    /*
    Agent myAgent = new Agent();
    System.out.println(myAgent.move(new Point(2,3), new Point(0,1), 4, 4));
    Point nextState = myAgent.move(new Point(2,3), new Point(0,1), 4, 4);
    System.out.println(nextState);
    */
    //System.out.println(myGridworld.returnReward(new Point (3,2)));
    
  }
  static int askUser(int lowerLimit, int upperLimit, String question){
    System.out.println(question);
    Scanner consoleInput = new Scanner(System.in);
    boolean isValidNumber = false;
    int number = -1;

    while(!isValidNumber){
      if(consoleInput.hasNextInt()){
        number = consoleInput.nextInt();
        if(number >= lowerLimit && number <= upperLimit){
          break;
        }
      }
      else{
        consoleInput.nextLine();
      }
      System.out.println("That's not a valid input, please try again!");
    }
    

    return number;
    
  }
  
  
}