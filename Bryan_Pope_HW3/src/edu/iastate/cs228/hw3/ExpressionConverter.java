package edu.iastate.cs228.hw3;

import java.util.Stack;

public class ExpressionConverter {
	private int rank;
	private int parenthesisCount;
	private String previous;

	public ExpressionConverter() {
	}

	/**
	 * @param infixString - The string in the infix format to be converted to  postfix
	 *             
	 * @return the converted string in postfix format if correct, or the appropriate error if there's an issue
	 * with the conversion
	 * 
	 * This method takes a string in infix notation with each operand and operator separated by spaces. 
	 */
	public String InfixToPostfix(String infixString) {
		Stack<Character> operatorStack = new Stack<Character>();
		String postfixString = ""; // Initializes the postfix string
		int i;
		this.rank = 0;
		this.parenthesisCount = 0;
		int elementPrecidence;
		// this splits the expression into an array of smaller strings. The delimiter
		// for this is a space
		String[] splitStr = infixString.trim().split(" ");
		this.previous = splitStr[0];
		for (i = 0; i < splitStr.length; i++) {
			// Updates the rank and Parenthesis counts based on the input
			updateRankAndParenthesis(splitStr[i]);
			// Exits and returns the string with the error if there's an error
			if (previous.equals("(") && splitStr[i].equals(")")) {
				return issueString(null, 3);
			}
			if (isRankIssue() > 0) {
				return issueString(splitStr[i], isRankIssue());
			}
			// This exits the program if parenthesis don't have an equation in there
			this.previous = splitStr[i];
			// This appends the string if the section is NOT an operator
			if (isOperator(splitStr[i]) == false) {
				postfixString = postfixString + splitStr[i] + " ";
				continue;
			}
			// if the stack is empty or if precedence of new operator is greater than the
			// previous element
			if (operatorStack.isEmpty() == true
					|| getInputPrecedence(splitStr[i].charAt(0)) > getStackPrecedence(operatorStack.peek())) {
				operatorStack.push(splitStr[i].charAt(0));
			}

			else {
				elementPrecidence = getInputPrecedence(splitStr[i].charAt(0));
				// Keeps popping the elements until an operator of equal precedence is met
				while (operatorStack.isEmpty() == false
						&& elementPrecidence <= getStackPrecedence(operatorStack.peek())) {
					if (splitStr[i].charAt(0) == ')' && operatorStack.peek() == '(') {
						operatorStack.pop();
						break;
					}
//					else if(splitStr[i].charAt(0) == ')' && operatorStack.size() == 1 && operatorStack.peek() != '('){
//						return issueString(null,4);
//					}
					// if it's not a parenthesis, we append the popped output to the string
					else {
						postfixString += operatorStack.pop() + " ";

					}
				}
				// if it isn't a parenthesis, we add the new character to the stack.
				if (splitStr[i].charAt(0) != ')') {
					operatorStack.push(splitStr[i].charAt(0));
				}
			}
		}
		// Cleans up old operators
		while (operatorStack.isEmpty() == false) {
			// Don't print parenthesis
			if (operatorStack.peek() == '(' || operatorStack.peek() == ')') {
				operatorStack.pop();
			} else {
				postfixString += operatorStack.pop() + " ";
			}
		}
		if (isParenthesisIssue() > 0) {
			return issueString(splitStr[i - 1], isParenthesisIssue());
		}

		return postfixString.trim();
	}

	/**
	 * @param c - The operator to be evaluated
	 * @return - The Input precedence of the operator, returns -5 if not found.
	 */
	private int getInputPrecedence(Character c) {
		if (c.equals('-') || c.equals('-') || c.equals('+')) {
			return 1;
		} else if (c.equals('*') || c.equals('/') || c.equals('%')) {
			return 2;
		} else if (c.equals('^')) {
			return 4;
		} else if (c.equals('(')) {
			return 5;
		}

		else if (c.equals(')')) {
			return -1;
		}
		return -5;
	}
	/**
	 * @param c - The operator to be evaluated
	 * @return - The Stack precedence of the operator, returns -5 if not found.
	 */
	private int getStackPrecedence(Character c) {
		if (c.equals('-') || c.equals('-') || c.equals('+')) {
			return 1;
		} else if (c.equals('*') || c.equals('/') || c.equals('%')) {
			return 2;
		} else if (c.equals('^')) {
			return 3;
		} else if (c.equals('(')) {
			return -1;
		}

		else if (c.equals(')')) {
			return 0;
		}
		return -5;// bad output
	}

	/**
	 * This method updates the rank instance variable based on the inputed character
	 * @param s - O
	 */
	private void updateRankAndParenthesis(String s) {
		if (isOperator(s) == false && s.equals("(") == false && s.equals(")") == false) {
			this.rank++;
		} else if (s.equals("(")) {
			this.parenthesisCount++;
		} else if (s.equals(")")) {
			this.parenthesisCount--;
		} else {
			this.rank--;
		}
	}

	/**
	 * @return -1 if there is no issue 1 if the expression has too many operands 2
	 *         if there are too many operators
	 */

	/**
	 * Checks if rank has gone below 0 or above one. Returns an integer accordingly
	 * @return 1 if there's too many operands, 2 if there's too many operators, and -1 if there's no issue at all.
	 */
	private int isRankIssue() {
		if (rank > 1) {
			return 1;
		}
		if (rank < 0) {
			return 2;
		}
		return -1;
	}

	/**
	 * @return 4 if no closing parenthesis, 5 if no opening parenthesis, -1 if there's no issue
	 */
	private int isParenthesisIssue() {
		if (this.parenthesisCount > 0) {
			return 4;
		}

		if (this.parenthesisCount < 0) {
			return 5;
		}
		return -1;
	}

	/**
	 * @param badElement - This is the offending element
	 * @param type       The types are as follows; 1. Too many operands 2. too many
	 *                   operators 3. no subexpression detected, 4 no closing parenthesis, 5 no opening parenthesis
	 * @return
	 */
	private String issueString(String badElement, int type) {
		if (type == 1) {
			return "Error: too many operands (" + badElement + ")";
		}
		if (type == 2) {
			return "Error: too many operators (" + badElement + ")";
		}
		if (type == 3) {
			return "Error: no subexpression detected";
		}
		if (type == 4) {
			return "Error: no closing parenthesis detected";
		}
		if (type == 5) {
			return "Error: no opening parenthesis detected";
		}
		return "";
	}

	/**
	 * A boolean function returning if the string object is numeric
	 * 
	 * @param str - The input string
	 * @return Whether the contents of a string are an operator
	 */
	private static boolean isOperator(String str) {
		if (str.equals("-") || str.equals("-") || str.equals("+") || str.equals("*") || str.equals("/")
				|| str.equals("%") || str.equals("^") || str.equals("(") || str.equals(")")) {
			return true;
		}
		return false;
	}
}
