package edu.iastate.cs228.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Bryan Pope
 *
 */
public class ExpressionExtractor {
	
protected ArrayList<String> ExpressionList;
private String inputFileName;
private String outputFileName;
	
	/**
	 * The default contructor for a Expression Extractor. Copies the pointer to the native arraylist
	 * and the name of the file (Which will be constant in this assignment, but in case they ever want to change it up)
	 * 
	 * @param fileName
	 * @param s
	 */
	public ExpressionExtractor(String inputfileName, String outputFileName, ArrayList<String> s) {
		ExpressionList = s;
		this.inputFileName = inputfileName;
		this.outputFileName = outputFileName;
	}
	
	/**
	 * This method scans all the lines of the input and copies them to strings. These make up the individual expressions
	 * @throws FileNotFoundException
	 */
	public void scanFromFile() throws FileNotFoundException {
		Scanner scnr = new Scanner(new FileReader(inputFileName));
		String inputString = "";
		while(scnr.hasNextLine()) {
			inputString = scnr.nextLine();
			this.ExpressionList.add(inputString);
		}
	}
	
	public void writeToFile() throws FileNotFoundException {
		try {
			File f = new File(outputFileName);
			f.createNewFile();
			FileWriter myWriter = new FileWriter(outputFileName);
			for(int i = 0; i < ExpressionList.size(); i++) {
				myWriter.write(ExpressionList.get(i));
				if(i != ExpressionList.size() - 1) {
					myWriter.write("\n");
				}
			}
			myWriter.close();
		} catch (IOException e) {
			throw new FileNotFoundException();
		}

	}

}
