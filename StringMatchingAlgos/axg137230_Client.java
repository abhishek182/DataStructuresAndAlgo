import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the starting point of the program. It expects two command line
 * arguments.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */
public class axg137230_Client {
	/**
	 * This holds the instance of the scanner used to read the file.
	 */
	private static Scanner scanner;

	/**
	 * The main method reads the input file and search the given pattern in the
	 * input text.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// If command line argument given is less than two.
		if (args.length < 2)
			System.out.println("Not enough arguments..");
		else {
			String pattern = args[0];
			String inputFileName = args[1];
			File inputFile = new File(inputFileName);
			try {
				scanner = new Scanner(inputFile);
				StringBuilder text = new StringBuilder();
				// Constructing a single string of the content in the input
				// file, removing the new line.
				while (scanner.hasNext())
					text.append(scanner.next());
				// System.out.println(text);
				axg137230_StringMatching matching = new axg137230_StringMatching(pattern,
						text.toString());

				long startTime = System.currentTimeMillis();
				// Find shifts using naive algorithm.
				ArrayList<Integer> validShifts = (ArrayList<Integer>) matching
						.naive();
				long endTime = System.currentTimeMillis();
				// Print the number of shift and time taken by the naive
				// algorithm.
				System.out.print(validShifts.size() + " "
						+ (endTime - startTime));

				startTime = System.currentTimeMillis();
				// Find shifts using Rabin Karp algorithm.
				validShifts = (ArrayList<Integer>) matching.rabinKarp();
				endTime = System.currentTimeMillis();
				// Print the time taken by the Rabin Karp algorithm.
				System.out.print(" " + (endTime - startTime));
				startTime = System.currentTimeMillis();
				// Find shifts using KMP algorithm.
				validShifts = (ArrayList<Integer>) matching.kmp();
				endTime = System.currentTimeMillis();
				// Print the time taken by the KMP algorithm.
				System.out.print(" " + (endTime - startTime));
				startTime = System.currentTimeMillis();
				// Find shifts using Boyer Moore algorithm.
				validShifts = (ArrayList<Integer>) matching.boyerMoore();
				endTime = System.currentTimeMillis();
				// Print the time taken by the Boyer Moore algorithm.
				System.out.print(" " + (endTime - startTime));
				System.out.println();
				// Printing the shifts only if the number of shifts is >= 20
				if (validShifts.size() <= 20)
					for (Integer i : validShifts) {
						System.out.print(i + " ");
					}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

}
