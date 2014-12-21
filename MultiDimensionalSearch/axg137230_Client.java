import java.util.Scanner;

/**
 * This class is the starting point. It read input from stdIn and process the
 * operations and displays the output.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */
public class axg137230_Client {
	public static Scanner scanner;
	private static axg137230_InputOperations operations;

	public static void main(String[] args) {
		// int lineno = 0;
		scanner = new Scanner(System.in);
		operations = new axg137230_InputOperations();
		while (scanner.hasNextLine()) {
			// lineno++;
			String line = scanner.nextLine();
			if (line.charAt(0) == '#')
				continue;
			// System.out.print(lineno+": ");
			operations.processOperation(line.replaceAll("\\s+", " "));
			// System.out.println(operations.getOutput());
		}
		System.out.printf("%.2f\n", operations.getOutput());
	}

}
