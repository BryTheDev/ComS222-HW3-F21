package edu.iastate.cs228.hw3;
import static org.junit.Assert.assertEquals;

import org.junit.*;
public class ExpressionConverterTest {

	@Test
	public void testAddition() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 + 3";
		assertEquals("1 3 +",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testAdditionParenthesis() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "( 1 + 3 )";
		assertEquals("1 3 +",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testLongAddition() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 + 3 + 4 + 5 + 6";
		assertEquals("1 3 + 4 + 5 + 6 +",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testSubtraction() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 - 3";
		assertEquals("1 3 -",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testLongSubtraction() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 - 3 - -4 - 5 - 6";
		assertEquals("1 3 - -4 - 5 - 6 -",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testAdditionAndSubtraction() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 - 3 + 4 - 5 + 6";
		assertEquals("1 3 - 4 + 5 - 6 +",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testMultiplication() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 * 3";
		assertEquals("1 3 *",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testLongMultiplication() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 * 3 * 4 * 5 * 6";
		assertEquals("1 3 * 4 * 5 * 6 *",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testDivision() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 / 3";
		assertEquals("1 3 /",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testLongDivision() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 / 3 / 4 / 5 / 6";
		assertEquals("1 3 / 4 / 5 / 6 /",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testMod() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 % 3";
		assertEquals("1 3 %",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testLongMod() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 % 3 % 4 % 5 % 6";
		assertEquals("1 3 % 4 % 5 % 6 %",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testExponent() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 ^ 3";
		assertEquals("1 3 ^",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testLongExponent() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 ^ 3 ^ 4 ^ 5 ^ 6";
		assertEquals("1 3 4 5 6 ^ ^ ^ ^",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testAddSubstractMultipy() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 + 3 * 4 + 5 * 6";
		assertEquals("1 3 4 * + 5 6 * +",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testExampleExpression() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "x ^ y / ( 5 * z ) + 10";
		assertEquals("x y ^ 5 z * / 10 +",e.InfixToPostfix(testString));
	}

	@Test
	public void testExample2Expression() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "12 + ( ( 23 * ( 4 ^ ( 3 - ( 7 / ( 11 ^ 2 ) ) ) ) ) % 25 )";
		assertEquals("12 23 4 3 7 11 2 ^ / - ^ * 25 % +",e.InfixToPostfix(testString));
	}
	@Test
	public void testTooManyOperands() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 1 + 1";
		assertEquals("Error: too many operands (1)",e.InfixToPostfix(testString));
	}
	@Test
	public void testTooManyOperators() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "1 + + 1";
		assertEquals("Error: too many operators (+)",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testNoSubExpression() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "( ) 1 + 1";
		assertEquals("Error: no subexpression detected",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testMissingRightBracketExpression() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = "( 1 + 1";
		assertEquals("Error: no closing parenthesis detected",e.InfixToPostfix(testString));
	}
	@Test
	public void testMissingleftBracketExpression() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = ") 1 + 1";
		assertEquals("Error: no opening parenthesis detected",e.InfixToPostfix(testString));
	}
	
	@Test
	public void testNoSubexpressionBeforeTooManyOperands() {
		ExpressionConverter e = new ExpressionConverter();
		String testString = " 1 () 1 + 1";
		assertEquals("Error: no subexpression detected",e.InfixToPostfix(testString));
	}

}
