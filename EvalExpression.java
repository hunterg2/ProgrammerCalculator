 import java.util.Stack;

public class EvalExpression {
	
  public static long runExpression(String expr) {

    try {
      //System.out.println("Expression: " + expr);
      return evaluateExpression(expr);
    }
    catch (Exception ex) {
      System.out.println("Wrong expression: " + expr);
      return 0;
    }
  }

  // Evaluate an expression !!
  public static long evaluateExpression(String expression) {
    // Create operandStack to store operands
    Stack<Long> operandStack = new Stack<>();
  
    // Create operatorStack to store operators
    Stack<Character> operatorStack = new Stack<>();
  
    // Insert blanks around (, ), +, -, /, and * and %
    expression = insertBlanks(expression);

    // Extract operands and operators
    String[] tokens = expression.split(" ");

    // Phase 1: Scan tokens[i]s
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].length() == 0) // Blank space
        continue; // Back to the while loop to extract the next tokens[i]
      else if (tokens[i].equals("+") || tokens[i].equals("-")) {
        // Process all +, -, *, / in the top of the operator stack 
        while (!operatorStack.isEmpty() &&
          (operatorStack.peek() == '+' || 
           operatorStack.peek() == '-' ||
           operatorStack.peek() == '*' ||
           operatorStack.peek() == '/' ||
           operatorStack.peek() == '%')) {
          processAnOperator(operandStack, operatorStack);
        }

        // Push the + or - operator into the operator stack
        operatorStack.push(tokens[i].charAt(0));
      }
      else if (tokens[i].equals("*") || tokens[i].equals("/") || tokens[i].equals("%")) {
        // Process all *, / in the top of the operator stack 
        while (!operatorStack.isEmpty() &&
          (operatorStack.peek() == '*' ||
          operatorStack.peek() == '/' ||
          operatorStack.peek() == '%')) {
          processAnOperator(operandStack, operatorStack);
        }

        // Push the * or / operator into the operator stack
        operatorStack.push(tokens[i].charAt(0));
      }
      else if (tokens[i].trim().charAt(0) == '(') {
        operatorStack.push('('); // Push '(' to stack
      }
      else if (tokens[i].trim().charAt(0) == ')') {
        // Process all the operators in the stack until seeing '('
        while (operatorStack.peek() != '(') {
          processAnOperator(operandStack, operatorStack);
        }
        
        operatorStack.pop(); // Pop the '(' symbol from the stack
      }
      else { // An operand scanned
        // Push an operand to the stack
        operandStack.push(new Long(tokens[i]));
      }
    }

    // Phase 2: process all the remaining operators in the stack 
    while (!operatorStack.isEmpty()) {
      processAnOperator(operandStack, operatorStack);
    }

    // Return the result
    return operandStack.pop();
  }

  /** Process one operator: Take an operator from operatorStack and
   *  apply it on the operands in the operandStack */
  public static void processAnOperator(
      Stack<Long> operandStack, Stack<Character> operatorStack) {
    char op = operatorStack.pop();
    long op1 = operandStack.pop();
    long op2 = operandStack.pop();
    if (op == '+') 
      operandStack.push(op2 + op1);
    else if (op == '-') 
      operandStack.push(op2 - op1);
    else if (op == '*') 
      operandStack.push(op2 * op1);
    else if (op == '/') 
      operandStack.push(op2 / op1);
    else if (op == '%')
    	operandStack.push(op2 % op1);
  }
  
  public static String insertBlanks(String s) {
    String result = "";
    boolean opLastUsed = true;
    
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(' || s.charAt(i) == ')' || 
          s.charAt(i) == '+' || s.charAt(i) == '-' ||
          s.charAt(i) == '*' || s.charAt(i) == '/' ||
          s.charAt(i) == '%') {
    	  
    	  if (s.charAt(i) == '-' && opLastUsed) {
    		  result += s.charAt(i);
    	  }
    	  else {
    		  result += " " + s.charAt(i) + " ";
    	  }
    	opLastUsed = true;
      }
      else {
    	opLastUsed = false;
        result += s.charAt(i);
      }
    }

    return result;
  }
}