import java.util.*;

//import that contains the Point class
import java.awt.*;
class Printer{
  
  static void printAgent(Gridworld g){
   

    //https://stackoverflow.com/questions/26475775/how-to-display-row-and-column-number-in-2d-array
    
    /*for(int i = 0; i< vertical; i++){
      for(int j = 0; j < horizontal; j++){      
        grid[i][j] = "' '";
      }
    }*/
    boolean hasReachedGoal = false;
    g.agent.position = g.startPos;
    while(!hasReachedGoal){
      g.grid[g.agent.position.y][g.agent.position.x].entity = g.grid[g.agent.position.y][g.agent.position.x].actionDisplay;
      Point nextState = g.agent.move(g.agent.position, g.grid[g.agent.position.y][g.agent.position.x].action, g.numOfRows, g.numOfColumns);
      g.agent.position = nextState;
      if(g.agent.position.equals(g.goal)){
        hasReachedGoal = true;
      }
    }
    
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
  static void printGrid(Gridworld g){

    //https://stackoverflow.com/questions/26475775/how-to-display-row-and-column-number-in-2d-array
    
    /*for(int i = 0; i< vertical; i++){
      for(int j = 0; j < horizontal; j++){      
        grid[i][j] = "' '";
      }
    }*/

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
  static void printActions(Gridworld g){

    //https://stackoverflow.com/questions/26475775/how-to-display-row-and-column-number-in-2d-array
    
    /*for(int i = 0; i< vertical; i++){
      for(int j = 0; j < horizontal; j++){      
        grid[i][j] = "' '";
      }
    }*/

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
  static void printValues(Gridworld g){

    //https://stackoverflow.com/questions/26475775/how-to-display-row-and-column-number-in-2d-array
    
    /*for(int i = 0; i< vertical; i++){
      for(int j = 0; j < horizontal; j++){      
        grid[i][j] = "' '";
      }
    }*/

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