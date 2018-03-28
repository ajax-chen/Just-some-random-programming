/**
 * Name          : Chen Yizhao
 * Matric number : A0168759E
 */

import java.util.*;

public class Chemistry {
  private HashMap <Character, Integer> mass;
  private Stack <Integer> element;
  
  public Chemistry() {
    mass = new HashMap <Character, Integer> ();
    element = new Stack <Integer> ();
  }
  
  public void run() {
    
    Scanner sc = new Scanner(System.in);
    
    int n = sc.nextInt();
    sc.nextLine();
    
    initializeMassMapping(sc, n);
    
    String input = sc.nextLine(); // chemical formula
                       
    char[] formula = input.toCharArray();
    
    processInput(formula);
    
    int total = accumulate();
    
    System.out.println(total);
    
    sc.close();
  }
  
   private int accumulate() {
    
    int result = 0;
    int top = element.pop();
    
    while (top != -1 && !element.empty()) {
      result += top;
      top = element.pop();
    }
    
    return result;
  }
  
  private void processInput(char[] formula) {
    
    element.push(-1); // Assume the base of the stack is -1 (To end the addition)
    
    for (char c : formula) {
      if (c == '(') { // open bracket
        element.push(-1); // start a new "session"
      } else if (c == ')') { //close bracket
        int sum = accumulate(); // helper method
        element.push(sum);
      } else if (Character.isDigit(c)) {
        int multiplier = Character.getNumericValue(c);
        element.push(element.pop() * multiplier);
        // System.out.println("element " + element);
      } else { // then c must be an element
        element.push(mass.get(c));
      }
    }
  }
  
  private void initializeMassMapping(Scanner sc, int n) {
    
    while (n-- >0) {
      mass.put(sc.next().charAt(0), sc.nextInt());
    }
    sc.nextLine();
  }
  
  public static void main(String[] args) {
    Chemistry newChem = new Chemistry();
    newChem.run();
  }
}