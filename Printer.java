import java.util.*;

//import that contains the Point class
import java.awt.*;
class Printer{
  //prints the agent's final path to the destination
  static void printAgent(Gridworld g){
   
    
    boolean hasReachedGoal = false;
    
    g.agent.position = g.startPos;
    while(!hasReachedGoal){
      //set the display to the action to take (arrows)
      g.grid[g.agent.position.y][g.agent.position.x].entity = g.grid[g.agent.position.y][g.agent.position.x].actionDisplay;
      //calculate the next square that the agent should move to
      Point nextState = g.agent.move(g.agent.position, g.grid[g.agent.position.y][g.agent.position.x].action, g.numOfRows, g.numOfColumns);
      //set the agent's position to the next state
      g.agent.position = nextState;

      //if the agent has reached the goal, break
      if(g.agent.position.equals(g.goal)){
        hasReachedGoal = true;
      }
    }

    //printing the grid + agent's path with the rows and columns labeled
     //credit from https://stackoverflow.com/questions/26475775/how-to-display-row-and-column-number-in-2d-array
    System.out.printf("%-3s", "");
    for (int i = 0; i < g.numOfColumns; i++) {
      System.out.printf("%-3d", i);
    }
    System.out.println();
    for (int i = 0; i < g.numOfRows; i++) {
        System.out.printf("%-3d", i);
        for (int j = 0; j < g.numOfColumns; j++) {
            System.out.printf("%-3s", g.grid[i][j].entity);
        }
        System.out.println();
    
    }
  
  }
  //prints the grid/visually displays gridworld
  static void printGrid(Gridworld g){

    //https://stackoverflow.com/questions/26475775/how-to-display-row-and-column-number-in-2d-array
    

    //prints the grid with headers and rows labeled
    //credit from https://stackoverflow.com/questions/26475775/how-to-display-row-and-column-number-in-2d-array
    System.out.printf("%-3s", "");
    for (int i = 0; i < g.numOfColumns; i++) {
      System.out.printf("%-3d", i);
    }
    System.out.println();
    for (int i = 0; i < g.numOfRows; i++) {
        System.out.printf("%-3d", i);
        for (int j = 0; j < g.numOfColumns; j++) {
            System.out.printf("%-3s", g.grid[i][j].entity);
        }
        System.out.println();
    }
  
  }
  //prints the actions in all the squares, used for testing only
  static void printActions(Gridworld g){

    //prints the grid with headers and rows labeled
    //credit from https://stackoverflow.com/questions/26475775/how-to-display-row-and-column-number-in-2d-array
    System.out.printf("%-3s", "");
    for (int i = 0; i < g.numOfColumns; i++) {
      System.out.printf("%-3d", i);
    }
    System.out.println();
    for (int i = 0; i < g.numOfRows; i++) {
        System.out.printf("%-3d", i);
        for (int j = 0; j < g.numOfColumns; j++) {
            System.out.printf("%-3s", g.grid[i][j].actionDisplay);
        }
        System.out.println();
    }
  
  }

  //prints all the values in all the squares, used for testing only
  static void printValues(Gridworld g){  
    //prints the grid with headers and rows labeled
    //credit from https://stackoverflow.com/questions/26475775/how-to-display-row-and-column-number-in-2d-array
    double[][] gridValues;
    gridValues = new double[g.numOfRows][g.numOfColumns];
    for(int i = 0; i< g.numOfRows; i++){
      for(int j = 0; j < g.numOfColumns; j++){
        gridValues[i][j] = g.grid[i][j].value;
      }
    }
    String strWithOuterBrackets = Arrays.deepToString(gridValues).replace("], ", "]\n");    
    System.out.println(strWithOuterBrackets.substring(1, strWithOuterBrackets.length()-1));
  
  }
}