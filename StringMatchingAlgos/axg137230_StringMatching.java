import java.util.ArrayList;
import java.util.List;

/**
 * This class implements various algorithms of string matching.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */
public class axg137230_StringMatching {
	/**
	 * This holds the pattern to be searched in a given string.
	 */
	private String pattern;

	/**
	 * This holds the text within which pattern is to be searched.
	 */
	private String text;

	/**
	 * This holds the number of distinct character the given string can have.
	 */
	private final int CHAR_SIZE = 2;

	/**
	 * This constructor initializes the text and pattern with the given input.
	 * 
	 * @param pattern
	 * @param text
	 */
	public axg137230_StringMatching(String pattern, String text) {
		this.pattern = pattern;
		this.text = text;
	}

	/**
	 * This method implements the naive algorithm of string matching.
	 * 
	 * @return List of all possible shifts.
	 */
	public List<Integer> naive() {
		List<Integer> validShifts = new ArrayList<Integer>();
		long textLength = this.text.length();
		int patternLength = this.pattern.length();
		for (int i = 0; i < textLength - patternLength + 1; i++) {
			String subString = this.text.substring(i, i + patternLength);
			if (this.pattern.compareTo(subString) == 0)
				validShifts.add(i);
		}
		return validShifts;
	}

	/**
	 * This method implements Rabin Karp algorithm of string matching.
	 * 
	 * @return List of all possible shifts.
	 */
	public List<Integer> rabinKarp() {
		List<Integer> validShifts = new ArrayList<Integer>();
		long textLength = this.text.length();
		int patternLength = this.pattern.length();
		int prime = 101; // prime number for taking mod in hashing.
		int mult = (int) (Math.pow(2, patternLength - 1) % prime);
		int hashPattern = hash(this.pattern, prime);
		int hashSubString = hash(this.text.substring(0, patternLength), prime);
		for (int i = 0; i < textLength - patternLength + 1; i++) {
			if (hashPattern == hashSubString) {
				// Compare string only if hash values of the pattern and the
				// substring is same.
				String subString = this.text.substring(i, i + patternLength);
				if (this.pattern.compareTo(subString) == 0)
					validShifts.add(i);
			}
			if (i < textLength - patternLength) {
				hashSubString = (2 * (hashSubString - this.text.charAt(i)
						* mult) + this.text.charAt(i + patternLength))
						% prime; // subtracting hash of first character and
									// adding the next one.
				if (hashSubString < 0) {
					hashSubString += prime; // in case the hash has gone
											// negative.
				}
			}
		}
		return validShifts;
	}

	/**
	 * This method calculates hash value of a string with given prime number.
	 * 
	 * @param str
	 * @param prime
	 * @return
	 */
	private int hash(String str, int prime) {
		int hash = 0;
		for (int i = 0; i < str.length(); i++)
			hash = (hash * 2 + str.charAt(i)) % prime;
		return hash;
	}

	/**
	 * This method implement the Boyer Moore algorithm of string matching.
	 * 
	 * @return List of all possible shifts.
	 */
	public List<Integer> boyerMoore() {
		List<Integer> validShifts = new ArrayList<Integer>();
		int patternLength = this.pattern.length();
		long textLength = this.text.length();
		int[] bad = new int[CHAR_SIZE];
		// initialize position of all characters in the pattern.
		for (int k = 0; k < CHAR_SIZE; k++)
			bad[k] = -1;
		// find the right most occurrence of the each character in the pattern.
		badCharPreProcessing(bad);
		int i = 0, j;
		while (i <= textLength - patternLength) {
			j = patternLength - 1;
			// keep moving towards left till it matches with the text.
			while (j >= 0 && this.pattern.charAt(j) == this.text.charAt(i + j))
				j--;
			if (j < 0) {
				// pattern found here since all characters must have matched.
				validShifts.add(i);
				// shift, such that next character in the text aligns with its
				// last occurrence in the pattern.
				i += (i + patternLength < textLength) ? patternLength
						- bad[this.text.charAt(i + patternLength) - 48] : 1;
			} else {
				// shift, so that bad character in the text aligns
				// with its last occurrence in the pattern.
				i += Math.max(1, j - bad[this.text.charAt(i + j) - 48]);
			}
		}
		return validShifts;
	}

	/**
	 * This method finds the right most occurrence of the each character in the
	 * pattern also called as bad character pre-processing in the algorithm.
	 * 
	 * @param bad
	 */
	private void badCharPreProcessing(int[] bad) {
		for (int i = 0; i < this.pattern.length(); i++)
			bad[pattern.charAt(i) - 48] = i;
	}

	/**
	 * This method implements the KMP algorithm of string matching.
	 * 
	 * @return
	 */
	public List<Integer> kmp() {
		ArrayList<Integer> shift = new ArrayList<>();
		long noOfCharinText = this.text.length();
		int noOfCharinPattern = this.pattern.length();
		int[] border = calPrefix();
		int j = -1;
		for (int i = 0; i < noOfCharinText; i++) {
			while (j >= 0
					&& (this.pattern.charAt(j + 1) != this.text.charAt(i)))
				j = border[j] - 1;
			if (this.pattern.charAt(j + 1) == this.text.charAt(i))
				j = j + 1;
			if (j == noOfCharinPattern - 1) {
				shift.add(i - j);
				j = border[j] - 1;
			}
		}
		return shift;
	}

	/**
	 * This method calculates the prefix array in the pattern.
	 * 
	 * @return
	 */
	private int[] calPrefix() {
		int noOfCharinPattern = this.pattern.length();
		int[] prefixArray = new int[noOfCharinPattern];
		prefixArray[0] = 0;
		int j = -1;
		for (int i = 1; i < noOfCharinPattern; i++) {
			while (j >= 0
					&& (this.pattern.charAt(j + 1) != this.pattern.charAt(i)))
				j = prefixArray[j] - 1;
			if (this.pattern.charAt(j + 1) == this.pattern.charAt(i))
				j = j + 1;
			prefixArray[i] = j + 1;
		}
		return prefixArray;
	}
}
