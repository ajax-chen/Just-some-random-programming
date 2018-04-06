/**
 * Name   : Yizhao
 * Matric No.  :
 * PLab Acct.  :
 */

import java.util.*;

public class BoardGame {
  
  private void run() {
    
    Scanner sc = new Scanner(System.in);
    
    int n = sc.nextInt();
    int startingDiceSides = sc.nextInt();
    
    Cell[] cells = new Cell[n];
    for (int i = 0; i < n; i++) {
      cells[i] = new Cell(); // Initialize the cells
    }
    
    int b = sc.nextInt();
    
    for(int j = 0; j < b; j++){
      cells[sc.nextInt()-1].blockCell();
    }
    
    int t = sc.nextInt();
    
    for(int k = 0; k < t; k++) {
      cells[sc.nextInt()-1].addSides(sc.nextInt());
    }
    
    int max = 0;
    int currIdx = 0;
    int numDice = 1;
    int maxDice = startingDiceSides;
    
    max = getMax(cells, currIdx, numDice, maxDice, max);
    
    System.out.println("Maximum Score: " + max);
  }
  
  private int getMax(Cell[] cells, int position, int numDice, int maxDice, int max) {
    
    // Base case:
    if (position >= cells.length) { // If exceed N cells: return 0
      return 0;
    }
    if (cells[position].isBlocked()) { // If land on blocked cell, return 0
      return 0;
    }
    if (position == cells.length-1) { //Land on cell N: return numOfDices
      return numDice;
    }
    
    int collectDice = numDice + cells[position].getNumDice();
    int collectMax = maxDice + cells[position].getMaxDice();
    
    
    
    
    // Transition:
    for(int i = collectDice; i <= collectMax; i++) {
      int result = getMax(cells, position+i, collectDice, collectMax, max); // Move forward by all possible sum of dice rolls
      max = Math.max(result, max);
    }
    
    return max;
  }
  
  public static void main(String[] args) {
    BoardGame myBoardGame = new BoardGame();
    myBoardGame.run();
  }
}

class Cell {
  private boolean _isBlocked;
  private int _minDice;
  private int _maxDice;
  
  public Cell() {
    this._isBlocked = false;
    this._minDice = 0;
    this._maxDice = 0;
  }
  
  public void blockCell(){
    this._isBlocked = true;
  }
  
  public boolean isBlocked(){
    return this._isBlocked;
  }
  
  public void addSides(int highestRoll){
    this._minDice += 1; // number of dices in the cell
    this._maxDice += highestRoll; // sum of all sides of the dices
  }
  
  public int getNumDice() {
    return this._minDice;
  }
  
  public int getMaxDice() {
    return this._maxDice;
  }
}
