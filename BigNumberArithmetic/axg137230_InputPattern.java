import java.util.HashMap;

/**
 * This class stores the input pattern and implements the method to evaluate the
 * pattern.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */
public class axg137230_InputPattern {
	/**
	 * This constant defines the initial capacity of the input array.
	 */
	private final static int INIT_CAPACITY = 10;

	/**
	 * This array of strings stores the patterns. Single element stores one
	 * pattern.
	 */
	private String[] input;

	/**
	 * Number of patterns in the array.
	 */
	private int N;

	/**
	 * This hash map keep records of the variables and there value.
	 */
	private HashMap<Character, axg137230_BigNumList> variables;

	/**
	 * This constructor initializes the class variables to the default value.
	 */
	public axg137230_InputPattern() {
		input = new String[INIT_CAPACITY + 1];
		N = 1;
		variables = new HashMap<Character, axg137230_BigNumList>();
	}

	/**
	 * This method inserts the string pattern at a given index.
	 * 
	 * @param index
	 * @param pattern
	 */
	public void insertPattern(int index, String pattern) {
		if (N == input.length - 1)
			resize(2 * N); // double the array size when it is about to fill.
		input[index] = pattern;
		N++;
	}

	/**
	 * This method is to resize the array in case of overflow.
	 * 
	 * @param capacity
	 */
	private void resize(int capacity) {
		String[] temp = new String[capacity];
		for (int i = 1; i <= N; i++)
			temp[i] = input[i];
		input = temp;
	}

	/**
	 * This method parses the input array and evaluate each pattern and work
	 * accordingly.
	 */
	public void parseInput() {
		int i = 1;
		while (i < N) {
			String pattern = input[i];
			if (pattern.length() == 1) {
				// case where variable is to be displayed.
				System.out.println(axg137230_BigNumOpn.numToStr(variables.get(pattern
						.charAt(0))));
			} else if (pattern.charAt(1) == '?') {
				// case where conditional jump is to be decided.
				String var = axg137230_BigNumOpn
						.numToStr(variables.get(pattern.charAt(0)));
				if (!var.equals("0")) {
					i = pattern.charAt(2) - '0'; // jump to i index.
					continue;
				}
			} else if (!pattern.contains("+") && !pattern.contains("-")
					&& !pattern.contains("*") && !pattern.contains("^")) {
				// case where a fresh variable is assigned a value.
				variables.put(pattern.charAt(0),
						axg137230_BigNumOpn.strToNum(pattern.substring(2)));
			} else if (pattern.charAt(3) == '+') {
				// case where two variables are added and result is stored in
				// some variable.
				variables.put(pattern.charAt(0), axg137230_BigNumOpn.add(
						variables.get(pattern.charAt(2)),
						variables.get(pattern.charAt(4))));
			} else if (pattern.charAt(3) == '-') {
				// case where second variable is subtracted from the first one
				// and result is stored in some variable.
				variables.put(pattern.charAt(0), axg137230_BigNumOpn.subtract(
						variables.get(pattern.charAt(2)),
						variables.get(pattern.charAt(4))));
			} else if (pattern.charAt(3) == '*') {
				// case where two variables are multiplied and result is stored
				// in some variable.
				variables.put(pattern.charAt(0), axg137230_BigNumOpn.multiply(
						variables.get(pattern.charAt(2)),
						variables.get(pattern.charAt(4))));
			} else if (pattern.charAt(3) == '^') {
				// case where first variable to the power of second variable is
				// calculated and stored in some variable.
				variables.put(pattern.charAt(0), axg137230_BigNumOpn.power(
						variables.get(pattern.charAt(2)),
						variables.get(pattern.charAt(4))));
			}
			i++;
		}
	}

	// Temporary method to print out all the patterns.
	public void printInput() {
		for (int i = 1; i < N; i++) {
			System.out.println(i + " " + input[i]);
		}
	}

}
