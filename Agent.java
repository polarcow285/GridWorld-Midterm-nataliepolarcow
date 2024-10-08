//import that contains the Point class
import java.awt.*;

//represents Dr. Frewen navigating the grid
class Agent{

  //represents agent's xy position in grid
  Point position; 

  
  //method that returns x,y coordinate from taking an action (up, down, right, or left) from starting in an xy coordinate
  Point move(Point state, Point action, int length, int width){
    int x = state.x + action.x;
    int y = state.y + action.y; 

    //if the agent moves out of the boundary, then it stays in the same place. The boundary is subracted by one so that the agent stays on the grid.
    if(x < 0 || x > (width-1)){
      x = state.x;
    }
    if(y<0 || y > (length-1)){
      y = state.y;
    }
    return new Point(x,y);
  }
}