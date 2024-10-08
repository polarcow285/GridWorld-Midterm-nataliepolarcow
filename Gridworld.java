import java.util.*;

//import that contains the Point class
import java.awt.*;
  

//The Gridworld class holds a blueprint for the gridworld environment that the agent navigates through.
class Gridworld{
  
  //height of gridworld
  final int numOfRows;
  
  //width of gridworld 
  final int numOfColumns;  

  //starting coordinate of the agent
  Point startPos; 

  //ending coordinate of the agent
  Point goal; 

  //number of blackholes in the gridworld
  int numOfBlackHoles; 

  //2d array that represents grid. This was something new that we did not cover in class. A 2d array is essentially an array of arrays. 
  GridSpot[][] grid; 

  //constant that represents how much the calculations should care about future rewards
  final static double gamma = 0.9; 

  //array of actions that represent down, up, right, left
  final static Point[] actionList = {new Point(0,1), new Point(0,-1), new Point(1,0), new Point(-1, 0)}; 
  Agent agent; //the agent is navigating through this gridworld

  //The constructor initializes a 2d array of Gridspots, the width and height of the gridworld, and an agent.
  public Gridworld(int height, int width){   
    numOfRows = height;
    numOfColumns = width;
    
    grid = new GridSpot[height][width];
    //initialize each element in 2d array grid to be a gridspot
    for(int i = 0; i< height; i++){
      for(int j = 0; j < width; j++){
        grid[i][j] = new GridSpot();
      }
    }
 
    
    
    agent = new Agent();
  }
  

  //METHODS

  //sets the starting location of the gridworld
  public void setStartLocation(Point start){
    startPos = start;
    grid[start.y][start.x].entity = "s |";
  }

  //sets the ending location of the gridworld
  public void setEndLocation(Point end){
    goal = end;
    grid[end.y][end.x].entity = "e |";
  }

  //sets the number of blackholes in the gridworld
  public void setNumBlackHoles(int num){
    numOfBlackHoles = num;
  }

  
  //checks whether the gridspot can be filled with a blackhole. if it can, the spot becomes a blackhole
  boolean setBlackHoleLocation(int x, int y){
    if(grid[y][x].entity.equals("__|")){
      //set the location of the blackhole
      grid[y][x].entity = "b |";
      return true;
    }
    else{
      return false;
    }
  }
  
  
  

  //calculates the reward for hypothetically being in a certain state
  int returnReward(Point nextState){
    GridSpot nextStateSpot = grid[nextState.y][nextState.x];
    //if next state == ending position, then reward is 10
    if(nextStateSpot.entity.equals("e |")){
      return 10;
    }
    //if next state == black hole, then reward is -10
    else if(nextStateSpot.entity.equals("b |")){
      return -10;
    }
    else{
      return -1;
    }
    
  }

  //checks whether a state is 'terminal' - whether it is a black hole or ending position
  boolean isTerminal(GridSpot state){
    if(state.entity.equals("b |") || state.entity.equals("e |")){
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
    //calculates best actions to take at each square
    calculateBestActions();

    //references from:
    //https://hub.gke2.mybinder.org/user/whatithinkabout-babyrobot-y3zhu5py/lab/tree/Reinforcement_Learning/Part%203%20-%20Policy%20and%20Value%20Iteration.ipynb
    //https://web.mit.edu/1.041/www/lectures/L15-value-iteration-2021fa-pre.pdf
    //https://github.com/mbodenham/gridworld-value-iteration/blob/master/deterministic.py

    
    
  }

  //calculates each square's value (number that represents how good the square is). returns delta, a value that determines whether calculations have converged
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

          //calculate the possibles value of 'state' by iterating through each action
          for(Point a : actionList){

            //calculate nextState, which is the xy coordinate from taking action a in 'state'
            Point nextState = new Point();
            nextState = agent.move(new Point(x,y), a, numOfRows, numOfColumns);
            
            //reward for being in nextState
            int reward = returnReward(nextState);

            //calculate the value
            //value = reward + gamma * nextState.value
            double v = reward + gamma * grid[nextState.y][nextState.x].value;

            //store possible values into a list
            possible_v.add(v);
                        
          }

          //store the largest value of the list and set it as the value of the gridspot
          double max_v = Collections.max(possible_v);
          state.value = max_v;

          //calculates delta (change in calculations in each iteration)
          if(delta < (state.value - old_v)){
            delta = state.value - old_v;
          }
          
        }
      }
    }
    return delta;
  }

  //calculates the best actions to take for each square
  void calculateBestActions(){
    //←↑→↓
    //char[] actionCharArr = {'D', 'U', 'R','L'};
    //array that holds the display for each action
    String[] actionDisplayArr = {"↓ |", "↑ |", "→ |","← |"};

    //iterates through each gridspot
    for(int y = 0; y< numOfRows; y++){      
      for(int x = 0; x < numOfColumns; x++){
        
        GridSpot state = grid[y][x];
        Point statePoint = new Point(x,y);
        
        if(!isTerminal(state)){
           //initialize possible v list
          ArrayList<Double> possible_v = new ArrayList<>();

          //calculate the possibles value of 'state' by iterating through each action
          for(Point a : actionList){

            //calculate nextState, which is the xy coordinate from taking action a in 'state'
            Point nextState = new Point();
            nextState = agent.move(statePoint, a, numOfRows, numOfColumns);          

            //reward for being in nextState
            int reward = returnReward(nextState);
           
            //calculate the value
            //value = reward + gamma * nextState.value
            double v = reward + gamma * grid[nextState.y][nextState.x].value;
            
            //store possible values into a list
            possible_v.add(v);
            
          }

          //store the largest value of the list and set it as the value of the gridspot
          double max_v = Collections.max(possible_v);
          //find the action that corresponds to the largest value in the list 
          int index = possible_v.indexOf(max_v);
          state.action = actionList[index];
          state.actionDisplay = actionDisplayArr[index];
        }
      }
       
    }
  }
  //Checks whether the gridworld is solvable or not
  boolean isPossible(){
    boolean solvingMaze = true;
    boolean isPossible = false;
    agent.position = startPos;
    int count = 0;
    
    while(solvingMaze){
      
      count++;
      //moves the agent according to the already-calculated actions in the gridspots
      Point action = grid[agent.position.y][agent.position.x].action;
      agent.position = agent.move(agent.position, action, numOfRows, numOfColumns);

      //if the agent has reached the ending position, then the gridworld is solvable
      if(agent.position.equals(goal)){
        isPossible = true;
        break;
      }
      //if the agent has surpassed 100 moves, the gridworld is deemed unsolvable
      else if(count > 100){
        isPossible = false;
        solvingMaze = false;
      }
     
    }
    return isPossible;
  }

  
  
  
}