import java.util.*;
class Printer{
  static void printGridworld(Gridworld g){
    g.agent.position = g.startPos;
    g.grid[g.agent.position.y][g.agent.position.x].entity = 'a';
    //g.grid.agent.
    g.printEntities();
  }
  static void printBlankGrid(){
    
  }
}