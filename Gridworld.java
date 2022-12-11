import java.util.*;

//import that contains the Point class
import java.awt.*;
  

//The Gridworld class holds a blueprint for the gridworld environment that the agent navigates through.
class Gridworld{
  
  final int numOfRows; //height  of gridworld
  final int numOfColumns; //width of gridworld  
  private final Point startPos; //starting coordinate of the agent
  final Point goal; //ending coordinate of the agent
  final int numOfBlackHoles; //number of black holes in the grid
  GridSpot[][] grid; //2d array that represents grid
  final static double gamma = 0.9; //constant that represents how much the calculations should care about future rewards
  
  final static Point[] actionList = {new Point(0,1), new Point(0,-1), new Point(1,0), new Point(-1, 0)}; //list of actions that represent down, up, right, left
  Agent agent; //the agent is navigating through this gridworld


  //The constructor initializes a 2d array with the starting position, ending position, and black hole locations in the grid
  public Gridworld(int height, int width, int numBlackHoles){   
    numOfRows = height;
    numOfColumns = width;
    
    grid = new GridSpot[height][width];
    //for each 
    /*for(int i = 0; i< height; i++){
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
    numOfBlackHoles = numBlackHoles;
    */
    
    agent = new Agent();
  }
  

  //METHODS
  public void setStartLocation(Point start){
    startPos = start;
    grid[start.y][start.x] = 's';
  }
  public void setEndLocation(Point end){
    goal = end;
    grid[start.y][start.x] = 'e';
  }
  public void setNumBlackHoles(int num){
    numOfBlackHoles = num;

  }
  
  //temp
  boolean setBlackHoleLocation(int x, int y){
    if(grid[y][x].entity == ' '){
      //set the location
      grid[y][x].entity = 'b';
      return true;
    }
    else{
      return false;
    }
  }
  void printGridValues(){
    double[][] gridValues;
    gridValues = new double[numOfRows][numOfColumns];
    for(int i = 0; i< numOfRows; i++){
      for(int j = 0; j < numOfColumns; j++){
        gridValues[i][j] = grid[i][j].value;
      }
    }
    String strWithOuterBrackets = Arrays.deepToString(gridValues).replace("], ", "]\n");    
    System.out.println(strWithOuterBrackets.substring(1, strWithOuterBrackets.length()-1));

  }
  void printActions(){
    char[][] gridActions;
    
    gridActions = new char[numOfRows][numOfColumns];
    for(int i = 0; i< numOfRows; i++){
      for(int j = 0; j < numOfColumns; j++){
       
        gridActions[i][j] = grid[i][j].actionChar;
      }
    }
    String strWithOuterBrackets = Arrays.deepToString(gridActions).replace("], ", "]\n");    
    System.out.println(strWithOuterBrackets.substring(1, strWithOuterBrackets.length()-1));

  }
  void printEntities(){
    char[][] gridEntities;
    
    gridEntities = new char[numOfRows][numOfColumns];
    for(int i = 0; i< numOfRows; i++){
      for(int j = 0; j < numOfColumns; j++){
       
        gridEntities[i][j] = grid[i][j].entity;
      }
    }
    String strWithOuterBrackets = Arrays.deepToString(gridEntities).replace("], ", "]\n");    
    System.out.println(strWithOuterBrackets.substring(1, strWithOuterBrackets.length()-1));

  }

  //calculates the reward for hypothetically being in a certain state
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

  //checks whether a state is a black hole the ending position
  boolean isTerminal(GridSpot state){
    if(state.entity == 'b' || state.entity == 'e'){
      return true;
    }
    else{
      return false;
    }
    
  }

  //method that calculates the values for all the squares and gets the best action to take when in those squares
  void solve(){

    boolean converged = false;

    //run calculateValues() until calculations have converged
    while(!converged){
      double delta = calculateValues();
      if (delta < 0.0001){
        converged = true;
      }
      
    }
    System.out.println("made it!");

    //calculates best actions to take at each square
    calculateBestActions();
    //test();
    
    
  }

  //calculates each square's value (numbe that represents how good the square is)
  double calculateValues(){
    double delta = 0.0;
    //iterate through each gridspot on the grid
     for(int y = 0; y< numOfRows; y++){
      for(int x = 0; x < numOfColumns; x++){
        GridSpot state = grid[y][x];
        
        if(!isTerminal(state)){
          //initialize possible v list
          ArrayList<Double> possible_v = new ArrayList<>();

          //store the old value of the state
          double old_v = state.value;
          
          for(Point a : actionList){
            Point nextState = new Point();
            nextState = agent.move(new Point(x,y), a, numOfRows, numOfColumns);
            
            
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
    for(int y = 0; y< numOfRows; y++){      
      for(int x = 0; x < numOfColumns; x++){
      
        //System.out.println("\n");
        //System.out.println("state x = " + x + ", y = " + y);
        
        GridSpot state = grid[y][x];
        Point statePoint = new Point(x,y);
        if(!isTerminal(state)){
           //initialize possible v list
          ArrayList<Double> possible_v = new ArrayList<>();

          for(Point a : actionList){
            //System.out.print("action: ");
            //System.out.println(a);
            Point nextState = new Point();
            nextState = agent.move(statePoint, a, numOfRows, numOfColumns);
            
            //System.out.print("next state: ");
            //System.out.println(nextState);
            
            int reward = returnReward(nextState);
            //System.out.print("reward: ");
            //System.out.println(reward);

            
            double v = reward + gamma * grid[nextState.y][nextState.x].value;
            //System.out.print("value: ");
            //System.out.println(v);

            possible_v.add(v);
            
          }
          double max_v = Collections.max(possible_v);
          int index = possible_v.indexOf(max_v);
          //System.out.println(possible_v);
          //System.out.println(max_v);
          state.action = actionList[index];
          state.actionChar = actionCharArr[index];
        }
      }
       
    }
  }

  boolean isPossible(){
    boolean solvingMaze = true;
    boolean isPossible = false;
    agent.position = startPos;
    int count = 0;
    while(solvingMaze){
      
      count++;
      
      Point action = grid[agent.position.y][agent.position.x].action;

      agent.position = agent.move(agent.position, action, numOfRows, numOfColumns);


      if(agent.position.equals(goal)){
        isPossible = true;
        break;
      }
      else if(count > 40){
        isPossible = false;
        solvingMaze = false;
      }
     
    }
    return isPossible;
  }

  
  
  
}