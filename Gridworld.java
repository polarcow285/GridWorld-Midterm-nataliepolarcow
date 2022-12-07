//import that contains the Point class
import java.awt.*;
import java.util.*;  


class Gridworld{

  final int numOfColumns;
  final int numOfRows;
  final Point startPos;
  final Point goal;
  //final int numOfBlackHoles;
  GridSpot[][] grid;
  final static double gamma = 0.9;
  //down, up, right, left
  final static Point[] actionList = {new Point(0,1), new Point(0,-1), new Point(1,0), new Point(-1, 0)};
  Agent agent;

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
        if(i == end.y && j == end.x){
          grid[i][j].entity = 'e';
        }
        else if(i == start.y && j == start.x){
          grid[i][j].entity = 's';
        }
      }
    }
    agent = new Agent();
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
    System.out.println(Arrays.deepToString(gridValues).replace("], ", "]\n"));

  }
  void printActions(){
    char[][] gridActions;
    
    gridActions = new char[numOfColumns][numOfRows];
    for(int i = 0; i< numOfColumns; i++){
      for(int j = 0; j < numOfRows; j++){
       
        gridActions[i][j] = grid[i][j].actionChar;
      }
    }
    System.out.println(Arrays.deepToString(gridActions).replace("], ", "]\n"));

  }

  /*static Point[] actionList(){
    Point[] actions = {new Point(0,1), new Point(0,-1), new Point(1,0), new Point(-1, 0)};
    return actions;
  }*/
  
  int returnReward(Point nextState){
    GridSpot nextStateSpot = grid[nextState.y][nextState.x];
    //if next state == ending position, then reward is 10
    if(nextStateSpot.entity == 'e'){
      return 10;
    }
    //if next state == black hole, then reward is -10
    else if(nextStateSpot.entity == 'b'){
      return -10;
    }
    else{
      return -1;
    }
    
  }

  boolean isTerminal(GridSpot state){
    if(state.entity == 's' || state.entity == 'e'){
      return true;
    }
    else{
      return false;
    }
    
  }

  void solve(){

    boolean converged = false;
    while(!converged){
      double delta = calculateValues();
      if (delta < 0.0001){
        converged = true;
      }
      
    }
    printGridValues();
    System.out.println("made it!");
    calculateBestActions();
    
    
  }
  double calculateValues(){
    double delta = 0.0;
     for(int i = 0; i< numOfColumns; i++){
      for(int j = 0; j < numOfRows; j++){
        GridSpot state = grid[i][j];
        if(!isTerminal(state)){
          //initialize possible v list
          ArrayList<Double> possible_v = new ArrayList<>();

          //store the old value of the state
          double old_v = state.value;
          
          for(Point a : actionList){
            Point nextState = agent.move(new Point(i,j), a, numOfColumns, numOfRows);
            
            
            int reward = returnReward(nextState);

            double v = reward + gamma * grid[nextState.y][nextState.x].value;
            possible_v.add(v);
                        
          }
          
          double max_v = Collections.max(possible_v);
          state.value = max_v;
          
          if(delta < (state.value - old_v)){
            delta = state.value - old_v;
          }
          
        }
      }
    }
    return delta;
  }

  void calculateBestActions(){
    char[] actionCharArr = {'D', 'U', 'R','L'};
    for(int i = 0; i< numOfColumns; i++){      
      for(int j = 0; j < numOfRows; j++){
        //int i = 0;
        //int j = 2;
        GridSpot state = grid[i][j];
        if(!isTerminal(state)){
           //initialize possible v list
          ArrayList<Double> possible_v = new ArrayList<>();

          for(Point a : actionList){
            System.out.print("action: ");
            System.out.println(a);
            
            Point nextState = agent.move(new Point(i,j), a, numOfColumns, numOfRows);
            System.out.print("next state: ");
            System.out.println(nextState);
            int reward = returnReward(nextState);
            System.out.print("reward: ");
            System.out.println(reward);

            
            double v = reward + gamma * grid[nextState.y][nextState.x].value;
            System.out.print("value: ");
            System.out.println(v);

            possible_v.add(v);
            
          }
          double max_v = Collections.max(possible_v);
          int index = possible_v.indexOf(max_v);
          System.out.println(possible_v);
          System.out.println(max_v);
          state.action = actionList[index];
          state.actionChar = actionCharArr[index];
        }
       
      }
     }
  }

  
  
  
}