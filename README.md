# Grid World Midterm Project
Source code for my high school midterm project that simulates Grid World, a grid environment where an entity has to reach its destination in the most efficient path while avoiding obstacles.

## **Introduction**
Grid World is a basic reinforcement learning problem where an entity (my programming teacher) moves on a grid to reach a goal. Using value iteration, the program finds the best path for the entity to take by calculating the most efficient moves, even with obstacles in the way. 

## **Description & Main Features**
I simulated a deterministic Grid World environment where users can change the grid size, starting and ending points, and add obstacles (black holes), making each run of the simulation different. 
This was done by using the Scanner class to receive user input and formatted print commands.

To find the most efficient path my programming teacher should take to reach his destination, I implemented value iteraion. Value iteration assigns a value to each grid spot/cell. The higher the value, the more advantageous it is to be on that cell in order to reach the destination.
For each grid spot (aka state), you calculate its value by considering all possible actions (eg move up, down, right, left) you can take from that state. For each action, you look at the expected reward of the next states you might end up in and then choose the action that gives you the highest expected reward.
You update the value of each state based on this maximum expected value, and you keep doing this repeatedly for all states until the values converge.
The mathematical calculations for the above process are defined by repeatedly applying the Bellman update eqaution.

After calculating the values for all the grid spots, the best path is determined by following the grid spots with the highest values, which correspond to the best actions you can take at each step.

## **Usage**
This project was originally done on Repl.it, but it is now on JDoodle since it is no longer free to deploy projects on Repl.it. 
I have noticed a random bugs with formatting and valid user inputs on JDoodle that were not apparent on Repl.it.

Try my project on Jdoodle [here](https://www.jdoodle.com/ia/1eG8)
1. Check the “Interactive” box on the Input/Output menu
2. Click the orange Execute (▶) button to run!
