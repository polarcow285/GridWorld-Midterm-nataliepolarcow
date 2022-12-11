//import that contains the Point class
import java.awt.*;
import java.util.*;


class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    //vertical, horizontal
    /*Gridworld myGridworld = new Gridworld(4,5, new Point(4,0), new Point(0,3), 1);
    //System.out.println(Arrays.deepToString(myGridworld.grid));
    
    //System.out.println(Arrays.toString(Gridworld.actionList()));
    myGridworld.printGridValues();
    myGridworld.solve();
    //myGridworld.printGridValues();
    myGridworld.printActions();
    myGridworld.printGridValues();
    Printer.printGridworld(myGridworld);
    System.out.println(myGridworld.isPossible());*/
    //GridSpot myGridSpot = new GridSpot();
    String question = "Choose the height (vertical length) of your gridworld (3-10)";
    int height = askUser(3, 10,question);
    
    question = "Choose the width (horizontal length) of your gridworld (3-10)";
    int width = askUser(3, 10,question);
    //display gridworld size

    System.out.println("\n");
    System.out.println("This is what the grid world looks like!");
    Printer.printBlankGrid(height, width);
    
    System.out.println("\n");
    question = "Choose the starting X coordinate for your agent (0-" + (width-1) + ")";
    int startX = askUser(0, width-1,question);

    question = "Choose the starting Y coordinate for your agent (0-" + (height-1) + ")";
    int startY = askUser(0, height-1,question);
    Point start = new Point(startX, startY);

    System.out.println("\n");
    System.out.println("This is where the agent is!");
    
    Printer.printAgent(height, width, start);
    
    
    
    
   
    boolean isValid = false;
    int endX = 0;
    int endY = 0;

    while(!isValid){
      question = "Choose the ending X coordinate for your agent (0-" + (width-1) + ")";
      endX = askUser(0, width-1,question);

      question = "Choose the ending Y coordinate for your agent (0-" + (height-1) + ")";
      endY = askUser(0, height-1,question);
      if(startX == endX && startY == endY){
        System.out.println("Invalid. Please make sure your ending coordinates are different from the starting coordinates.");
      }
      else{
        System.out.println("WOOO");
        isValid = true;
      }
    }
    
    
    Point end = new Point (endX, endY);

    //ask for number of blackholes
    question = "How many blackholes would you like in this environment? (0-" + ((width*height)-2) + ")";
    int numBlackHoles = askUser(0, (width*height)-2,question);

    Gridworld gridworld = new Gridworld(height,width, start, end, numBlackHoles);
    
    for(int i = 0; i<numBlackHoles; i++){
      boolean isSet = false;
      while(!isSet){
        question = "Choose the X coordinate for blackhole #" + (i+1) +  " (0-" + (width-1) + ")";
        int bhX = askUser(0, width-1,question);
  
        question = "Choose the Y coordinate for blackhole #" + (i+1) +  " (0-" + (height-1) + ")";
        int bhY = askUser(0, height-1,question);
          
        if(!gridworld.setBlackHoleLocation(bhX, bhY)){
          System.out.println("Invalid. Please make sure the black hole isn't in a starting or ending coordinate!");
        }
        else{
          System.out.println("Blackhole set!");
          isSet = true;
        }
      }
    }

    gridworld.solve();
    if(gridworld.isPossible()){
      gridworld.printActions();
      gridworld.printGridValues();
    }
    else{
      System.out.println("I'm afraid this gridworld is not solvable!");
    }
    
    Printer.printGridworld(gridworld);
    
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