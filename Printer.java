import java.util.*;

//import that contains the Point class
import java.awt.*;
class Printer{
  static void printGridworld(Gridworld g){
    g.agent.position = g.startPos;
    g.grid[g.agent.position.y][g.agent.position.x].entity = 'a';
    //g.grid.agent.
    g.printEntities();
  }
  static void printAgent(int vertical, int horizontal, Point point){
    String[][] grid; 
    grid = new String[vertical][horizontal];

    //https://stackoverflow.com/questions/26475775/how-to-display-row-and-column-number-in-2d-array
    
    /*for(int i = 0; i< vertical; i++){
      for(int j = 0; j < horizontal; j++){      
        grid[i][j] = "' '";
      }
    }*/

    System.out.printf("%-3s", "");
    for (int i = 0; i < horizontal; i++) {
      System.out.printf("%-3d", i);
    }
    System.out.println();
    for (int i = 0; i < vertical; i++) {
        System.out.printf("%-3d", i);
        for (int j = 0; j < horizontal; j++) {
            if(i == point.y && j == point.x){
              System.out.printf("%-3s", grid[i][j] = "a |");
            }
          else{
            System.out.printf("%-3s", grid[i][j] = "__|");
          }
            
        }
        System.out.println();
    }
  
  }
  static void printBlankGrid(Gridworld g){
    String[][] grid; 
    grid = new String[g.numOfRows][g.numOfColumns];

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
            System.out.printf("%-3s", grid[i][j] = "__|");
        }
        System.out.println();
    }
  
  }
  /*
  static void printBlankGrid(int vertical, int horizontal){
    String[][] grid; 
    grid = new String[vertical][horizontal];

    //https://stackoverflow.com/questions/26475775/how-to-display-row-and-column-number-in-2d-array
    
   

    System.out.printf("%-3s", "");
    for (int i = 0; i < horizontal; i++) {
      System.out.printf("%-3d", i);
    }
    System.out.println();
    for (int i = 0; i < vertical; i++) {
        System.out.printf("%-3d", i);
        for (int j = 0; j < horizontal; j++) {
            System.out.printf("%-3s", grid[i][j] = "__|");
        }
        System.out.println();
    }
  
  }*/
}