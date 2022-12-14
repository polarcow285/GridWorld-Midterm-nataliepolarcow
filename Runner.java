//import that contains the Point class
import java.awt.*;
import java.util.*;

class Runner{
  static void runGridworld(){
    //prints introductory text for this program
    printIntroText();

    //prompts user to set the height and width of the gridworld
    String question = "Choose the height (vertical length) of your gridworld (3-10)";
    int height = askUser(3, 10,question);
    
    question = "Choose the width (horizontal length) of your gridworld (3-10)";
    int width = askUser(3, 10,question);

    //initialize gridworld object
    Gridworld gridworld = new Gridworld(height,width);

  
    System.out.println("\nThis is what the grid world looks like!");
    //visually displays gridworld map to user
    Printer.printGrid(gridworld);


    //prompts user to set the starting position of the agent
    question = "\nChoose the starting X (horizontal) coordinate for Dr. Frewen (0-" + (width-1) + ")";
    int startX = askUser(0, width-1,question);

    question = "Choose the starting Y coordinate for Dr. Frewen (0-" + (height-1) + ")";
    int startY = askUser(0, height-1,question);
    Point start = new Point(startX, startY);

    gridworld.setStartLocation(start);

    System.out.println("\nThis is where Dr. Frewen starts!");
    
    //visually displays gridworld map with starting position to user
    Printer.printGrid(gridworld);
 

    //prompts user to set the ending location of the agent
    Point end = getEndingLocation(gridworld);
    gridworld.setEndLocation(end);

    System.out.println("\nThis is where Dr. Frewen should end!");
     //visually displays gridworld map with starting + ending position to user
    Printer.printGrid(gridworld);

    //prompts user to enter the amount of blackholes
    question = "\nHow many blackholes would you like in this environment? (0-" + ((width*height)-2) + ")";    
    int numBlackHoles = askUser(0, (width*height)-2,question);
    gridworld.setNumBlackHoles(numBlackHoles);
    
    manuallySetBlackHoles(gridworld);
      
    //solving the gridworld
    gridworld.solve();
    System.out.println("\n");

    //if the gridworld is solvable, display the path to take. Else, print out sad message
    if(gridworld.isPossible()){
      System.out.println("Here's the path that Dr. Frewen should take!");
      Printer.printAgent(gridworld);
    }
    else{
      System.out.println("I'm afraid this gridworld is not solvable! Dr. Frewen is stuck in this grid forever :(");
    }
  }

  static void randomlySetBlackHoles(Gridworld g){
    for(int i = 0; i<g.numOfBlackHoles; i++){
      boolean isSet = false;
      while(!isSet){
        int randomNumber = (int) (Math.random() * 10 + 1);
        
        int bhX = askUser(0, g.numOfColumns-1,question);
  
        int bhY = askUser(0, g.numOfRows-1,question);
          
        if(!g.setBlackHoleLocation(bhX, bhY)){
          System.out.println("Invalid. Please make sure the black hole is in an empty square!");
        }
        else{
          System.out.println("\nBlackhole set!");
          Printer.printGrid(g);
          System.out.println("\n");
          isSet = true;
        }
      }
    }
  }


  
  static void manuallySetBlackHoles(Gridworld g){
    for(int i = 0; i<g.numOfBlackHoles; i++){
      boolean isSet = false;
      while(!isSet){
        String question = "Choose the X (horizontal) coordinate for blackhole #" + (i+1) +  " (0-" + (g.numOfColumns-1) + ")";
        int bhX = askUser(0, g.numOfColumns-1,question);
  
        question = "Choose the Y (vertical) coordinate for blackhole #" + (i+1) +  " (0-" + (g.numOfRows-1) + ")";
        int bhY = askUser(0, g.numOfRows-1,question);
          
        if(!g.setBlackHoleLocation(bhX, bhY)){
          System.out.println("Invalid. Please make sure the black hole is in an empty square!");
        }
        else{
          System.out.println("\nBlackhole set!");
          Printer.printGrid(g);
          System.out.println("\n");
          isSet = true;
        }
      }
    }
  }

  static Point getEndingLocation(Gridworld g){
    boolean isValid = false;
    int endX = 0;
    int endY = 0;

    while(!isValid){
      String question = "Choose the ending X (horizontal) coordinate for Dr. Frewen (0-" + (g.numOfColumns-1) + ")";
      endX = askUser(0, g.numOfColumns-1,question);

      question = "Choose the ending Y (vertical) coordinate for Dr. Frewen (0-" + (g.numOfRows-1) + ")";
      endY = askUser(0, g.numOfRows-1,question);
      if(g.startPos.x == endX && g.startPos.y == endY){
        System.out.println("Invalid. Please make sure your ending coordinates are different from the starting coordinates.");
      }
      else{

        isValid = true;
      }
    }
    
    
    return new Point (endX, endY);
  }

  
  static void printIntroText(){
    System.out.println("Welcome to Gridworld Simulator!");
    System.out.println("\n");
    System.out.println("In gridworld, Dr. Frewen needs to figure out the best path from point A to point B on a grid. There might be blackholes in the grid that he needs to avoid.");
    System.out.println("Create your custom gridworld now!");
    System.out.println("\n");
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
      System.out.println(question);
    }
    

    return number;
    
  }
}