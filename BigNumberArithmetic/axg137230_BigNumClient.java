import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is the staring point of the program. It provides the client which
 * takes the input pattern and parse it.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */
public class axg137230_BigNumClient {

	public static void main(String[] args) {
		Scanner scanner = null;
		scanner = new Scanner(System.in);
		axg137230_InputPattern input = new axg137230_InputPattern();
		while (scanner.hasNext()) {
			input.insertPattern(scanner.nextInt(), scanner.next());
		}
		input.parseInput();
		scanner.close();
	}

}
