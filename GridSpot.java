//import that contains the Point class
import java.awt.*;


//class representing a square in the grid
class GridSpot{

  //the display of the gridspot when it gets printed out
  String entity;

  //the value that represents how good the gridspot is
  double value; 

  //the best action to take in this gridspot
  Point action; 

  //the display of the action when it gets printed out
  String actionDisplay; 

  //the constructor initializes the value at 0, the entity as a blank square, and the action as blank
  public GridSpot(){
    value = 0;
    entity = "__|";
    actionDisplay = "  |";
  }
  
 

}