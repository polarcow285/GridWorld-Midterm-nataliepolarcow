import java.util.*;

//import that contains the Point class
import java.awt.*;

class Runner{
  static void runGridworld(){
    boolean isDone = false;
    
    //prints introductory text for this program
    printIntroText();

    //keeps prompting the user to make simulations until the user says that they do not want to run another simulation
    while(!isDone){
      
  
      //prompts user to set the height and width of the gridworld
      String question = "\nChoose the height (vertical length) of your gridworld (3-10)";
      int height = askUser(3, 10,question);
      
      question = "\nChoose the width (horizontal length) of your gridworld (3-10)";
      int width = askUser(3, 10,question);
  
      //initialize gridworld object
      Gridworld gridworld = new Gridworld(height,width);
  
    
      System.out.println("\nThis is what the grid world looks like!");
      //visually displays gridworld map to user
      Printer.printGrid(gridworld);
  
  
      //prompts user to set the starting position of the agent
      question = "\nChoose the starting X (horizontal) coordinate for Dr. Frewen (0-" + (width-1) + ")";
      int startX = askUser(0, width-1,question);
  
      question = "\nChoose the starting Y coordinate for Dr. Frewen (0-" + (height-1) + ")";
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
  
      
      //prompts the user to decide whether they want to manually input the location of blackholes or randomly generate them 
      if(numBlackHoles != 0){
        question = ("\n Would you like to manually input the locations of the blackholes on the grid or randomly generate them? \n1: manual input \n2: randomly generate");
      int response = askUser(1, 2,question);
      
      if(response == 1){
        manuallySetBlackHoles(gridworld);
      }
      else{
        randomlySetBlackHoles(gridworld);
      }
    }
      
      
        
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
  
      System.out.println("\nSimulation complete!");


      //prompts the user to decide whether they want to simulate another gridworld
      question = ("\nWould you like to simulate another gridworld? \n1: Yes \n2: No");
      int response = askUser(1, 2,question);

      //if the user responds no, the program breaks out of the loop and the program stops
      if(response == 2){
        System.out.println("\nThanks for trying out this simulator!");
        isDone = true;
      }
    }
  }
  
  //METHODS

  //randomly sets the locations of blackholes
  static void randomlySetBlackHoles(Gridworld g){
    for(int i = 0; i<g.numOfBlackHoles; i++){
      boolean isSet = false;
      while(!isSet){
        int randomX = (int) (Math.random() * (g.numOfColumns));
        int randomY = (int) (Math.random() * (g.numOfRows));
          
        if(g.setBlackHoleLocation(randomX, randomY)){
          isSet = true;
        }
       
      }
    }
    System.out.println("\nBlackholes randomly generated!");
    Printer.printGrid(g);
  }


  //prompts the user to manually set the locations of the blackholes
  static void manuallySetBlackHoles(Gridworld g){
    for(int i = 0; i<g.numOfBlackHoles; i++){
      boolean isSet = false;
      while(!isSet){
        String question = "Choose the X (horizontal) coordinate for blackhole #" + (i+1) +  " (0-" + (g.numOfColumns-1) + ")";
        int bhX = askUser(0, g.numOfColumns-1,question);
  
        question = "Choose the Y (vertical) coordinate for blackhole #" + (i+1) +  " (0-" + (g.numOfRows-1) + ")";
        int bhY = askUser(0, g.numOfRows-1,question);
        
        //checks whether the blackhole location is in an empty square
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

  //sets the ending location of the gridworld
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

  //prints the introduction text
  static void printIntroText(){
    System.out.println("\nWelcome to Gridworld Simulator!");
    System.out.println("\n");
    System.out.println("In gridworld, Dr. Frewen needs to figure out the best path from point A to point B on a grid. There might be blackholes in the grid that he needs to avoid.");
    System.out.println("Create your custom gridworld now!");
  }

  //method that asks the user a question and the input has to be an integer between an upper and lower limit
  static int askUser(int lowerLimit, int upperLimit, String question){
    System.out.println(question);
    Scanner consoleInput = new Scanner(System.in);
    boolean isValidNumber = false;
    int number = -1;

    while(!isValidNumber){
      //ensures that input is an integer
      if(consoleInput.hasNextInt()){
        number = consoleInput.nextInt();
        //ensures that input is between bounds
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