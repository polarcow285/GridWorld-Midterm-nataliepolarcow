//import that contains the Point class
import java.awt.*;


//class representing a square in the grid
class GridSpot{
  String entity; //the display of the gridspot when it gets printed out
  double value; //the value that represents how good the gridspot is
  Point action; //the best action to take in this gridspot
  String actionDisplay; //the display of the action when it gets printed out

  //the constructor initializes the value at 0, the entity as a blank square, and the action as blank
  public GridSpot(){
    value = 0;
    entity = "__|";
    actionDisplay = "  |";
  }
  
 

}