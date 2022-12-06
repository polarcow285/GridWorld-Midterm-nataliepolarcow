//import that contains the Point class
import java.awt.*;
import java.util.Arrays;

class Gridworld{

  final int numOfColumns;
  final int numOfRows;
  final Point startPos;
  final Point goal;
  //final int numOfBlackHoles;
  GridSpot[][] grid;
  final static double gamma = 0.9;
  //agent

  public Gridworld(int length, int width, Point start, Point end){
    numOfColumns = length;
    numOfRows = width;
    startPos = start;
    goal = end;
    grid = new GridSpot[length][width];
    //for each 
    for(int i = 0; i< length; i++){
      for(int j = 0; j < width; j++){
        grid[i][j] = new GridSpot();
      }
    }
    
  }

  //METHODS
  //temp
  void printGridValues(){
    double[][] gridValues;
    gridValues = new double[numOfColumns][numOfRows];
    for(int i = 0; i< numOfColumns; i++){
      for(int j = 0; j < numOfRows; j++){
        gridValues[i][j] = grid[i][j].value;
      }
    }

    System.out.println(Arrays.deepToString(gridValues));
  }

  static Point[] actionList(){
    Point[] actions = {new Point(0,1), new Point(0,-1), new Point(1,0), new Point(-1, 0)};
    return actions;
  }
  
  int returnReward(Point nextState){
    
    return 0;
  }

  boolean isTerminal(GridSpot state){
    return false;
  }

  void solve(){
    calculateValues();
    
  }
  double calculateValues(){
     for(int i = 0; i< numOfColumns; i++){
      for(int j = 0; j < numOfRows; j++){
        GridSpot state = grid[i][j];
        if(!isTerminal(state)){
          
        }
      }
    }
    return 0.0; //wooo
  }

  
  
  
}