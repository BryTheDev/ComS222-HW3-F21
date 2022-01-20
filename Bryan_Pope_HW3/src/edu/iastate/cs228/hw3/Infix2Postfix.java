package edu.iastate.cs228.hw3;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Infix2Postfix {
	private static String INPUT_FILE_NAME = "input.txt";
	private static String OUTPUT_FILE_NAME = "output.txt";

	public static void main(String args[]) throws FileNotFoundException {
		// Declares our arraylist and other needed objects
		ArrayList<String> expressions = new ArrayList<>();
		ExpressionExtractor extractor = new ExpressionExtractor(INPUT_FILE_NAME, OUTPUT_FILE_NAME, expressions);
		ExpressionConverter converter = new ExpressionConverter();
		// We don't handle the file not found, we let that exit the program
		extractor.scanFromFile();
		for (int i = 0; i < expressions.size(); i++) {
			// ugly nested statement, but essentially this takes the string at
			// index i, converts it to postfix (or it's error) and puts it back into
			// the expressions arraylist
			expressions.set(i, converter.InfixToPostfix(expressions.get(i)));
		}
		extractor.writeToFile();

	}

}
