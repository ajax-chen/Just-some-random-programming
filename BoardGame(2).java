/**
 * Name   : Yizhao
 * Matric No.  :
 * PLab Acct.  :
 */

import java.util.*;

public class BoardGame {
  
  private ArrayList<Cell> cells;
  private ArrayList<Integer> maxCount;
  private int numCells;
  
  public BoardGame() {
    cells = new ArrayList<Cell>();
    maxCount = new ArrayList<Integer>();
  }
  
  private void run() {
    // treat this as your "main" method
    Scanner sc = new Scanner(System.in);
    
    numCells = sc.nextInt();
    
    int startingDiceSide = sc.nextInt();
    
    for (int i = 0; i < numCells; i++) {
      cells.add(new Cell());
    }
    
    int numOfBlocked = sc.nextInt();
    
    for (int j = 0; j < numOfBlocked; j++) {
      cells.get(sc.nextInt()-1).block();
    }
    
    int numOfDices = sc.nextInt();
    
    for (int k = 0; k < numOfDices; k++) {
      cells.get(sc.nextInt()-1).addDice(sc.nextInt());
    }
    
    // Add the starting dice to cell 0
    cells.get(0).addDice(startingDiceSide);
    int idx = 0;
    int minDice = 0;
    int maxDice = 0;
    getDice(idx, minDice, maxDice);
    
    System.out.println("Maximum Score: " + Collections.max(maxCount));
  }
  
  private void getDice(int idx, int minDice, int maxDice) {
    
    if (idx == numCells - 1) {     //NumCells is a global variable (total number of cells)
      maxCount.add(minDice);       //Add the result from every successful function to maxCount
    } else if (idx >= numCells || cells.get(idx).isBlocked()) {
      maxCount.add(0);             //Add 0 to the maxCount if exceed or isblocked
    } else {
      
      int collectDice = minDice + cells.get(idx).getMin();
      int collectMax = maxDice + cells.get(idx).getMax();
      
      for (int i = idx + collectDice; i < idx + collectMax + 1; i++) {
        getDice(i, collectDice, collectMax);
      }
    }
  }
  
  public static void main(String[] args) {
    BoardGame myBoardGame = new BoardGame();
    myBoardGame.run();
  }
}

class Cell {
  private int _maxDice;
  private int _minDice;
  private boolean _isBlocked;
  
  public Cell() {
    this._isBlocked = false;
  }
  
  public void addDice(int dice) {
    this._maxDice += dice;
    this._minDice++;
  }
  
  public int getMax() {
    return this._maxDice;
  }
  
  public int getMin() {
    return this._minDice;
  }
  
  public void block() {
    this._isBlocked = true;
  }
  
  public boolean isBlocked() {
    return this._isBlocked;
  }
}