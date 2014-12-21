import java.util.Iterator;

/**
 * This class contains methods that enable users to do various arithmetic
 * operations on the axg137230_BigNumList types.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */
public class axg137230_BigNumOpn {
	/**
	 * This method converts a string into a axg137230_BigNumList instance.
	 * 
	 * @param numberInString
	 * @return
	 */
	public static axg137230_BigNumList strToNum(String numberInString) {
		if (numberInString != null) {
			axg137230_BigNumList bigNum = new axg137230_BigNumList();
			for (int i = 0; i < numberInString.length(); i++) {
				bigNum.insert(numberInString.charAt(i) - '0');
			}
			return bigNum;
		}
		return null;
	}

	/**
	 * This method converts a axg137230_BigNumList instance into a String.
	 * 
	 * @param bigNum
	 * @return
	 */
	public static String numToStr(axg137230_BigNumList bigNum) {
		if (bigNum != null) {
			char[] numString = new char[bigNum.size()];
			int i = bigNum.size() - 1;
			for (Integer integer : bigNum) {
				numString[i--] = (char) (integer + 48); // reversing the order.
			}
			return String.valueOf(numString);
		}
		return null;
	}

	/**
	 * This methods adds two axg137230_BigNumList instances and returns the thier sum.
	 * 
	 * @param bigNum1
	 * @param bigNum2
	 * @return a axg137230_BigNumList type
	 */
	public static axg137230_BigNumList add(axg137230_BigNumList bigNum1, axg137230_BigNumList bigNum2) {
		if (bigNum1 == null || bigNum2 == null)
			return null;
		else {
			axg137230_BigNumList bigNumSum = new axg137230_BigNumList();
			int carry = 0;
			Iterator<Integer> i1 = bigNum1.iterator(), i2 = bigNum2.iterator();
			while (i1.hasNext() || i2.hasNext()) {
				int num1 = 0, num2 = 0, sum = 0;
				if (i1.hasNext())
					num1 = i1.next();
				if (i2.hasNext())
					num2 = i2.next();
				sum = num1 + num2 + carry;
				// For base 10
				bigNumSum.insert(sum % 10);
				carry = sum / 10;
			}
			if (carry != 0) {
				bigNumSum.insert(carry);
			}
			return bigNumSum.reverse(); // returning the reverse list as the
										// order in the big list should be
										// reverse of the original number.
		}
	}

	/**
	 * This method subtracts one axg137230_BigNumList from the other and return the
	 * resultant axg137230_BigNumList.
	 * 
	 * @param bigNum1
	 * @param bigNum2
	 * @return a axg137230_BigNumList type
	 */
	public static axg137230_BigNumList subtract(axg137230_BigNumList bigNum1, axg137230_BigNumList bigNum2) {
		if (bigNum1 == null || bigNum2 == null)
			return null;
		else if (bigNum1.size() < bigNum2.size()) {
			axg137230_BigNumList zeroList = new axg137230_BigNumList();
			zeroList.insert(0);
			return zeroList;
		} else {
			axg137230_BigNumList bigNumSubtract = new axg137230_BigNumList();
			boolean carry = false;
			Iterator<Integer> i1 = bigNum1.iterator(), i2 = bigNum2.iterator();
			while (i1.hasNext() || i2.hasNext()) {
				int num1 = 0, num2 = 0; // if any list is traversed till end set
										// its pointer value to 0.
				if (i1.hasNext())
					num1 = i1.next();
				if (i2.hasNext())
					num2 = i2.next();
				if (carry)
					num1 -= 1; // if carry is taken subtract 1 from next number.
				if (num1 < num2 && i1.hasNext()) {
					num1 += 10; // if num1 is smaller than num2 and bigNum1 is a
								// larger number than bigNum2 add 10 to num1
								// else bigNum2 is bigger and result will be
								// negative.
					carry = true;
				} else {
					carry = false;
				}
				/*
				 * if (!(!i1.hasNext() && num1 == num2) && !(!i1.hasNext() &&
				 * num1 < num2)) bigNumSubtract.insert(num1 - num2);
				 */
				if (num1 >= num2)
					bigNumSubtract.insert(num1 - num2);
				if (!i1.hasNext() && num1 < num2)
					bigNumSubtract.setAllToZero(); // if i1 reaches at end and
													// last num1 is less than
													// last num2 result would be
													// negative.

			}
			bigNumSubtract.cutZerosAtHead(); // Trimming zero at the head.
			return bigNumSubtract.reverse();// returning the reverse list as the
											// order in the big list should be
											// reverse of the original number.
		}
	}

	/**
	 * This method multiply two axg137230_BigNumList instance and return the result.
	 * 
	 * @param bigNum1
	 *            a axg137230_BigNumList type
	 * @param bigNum2
	 *            a axg137230_BigNumList type
	 * @return a axg137230_BigNumList type
	 */
	public static axg137230_BigNumList multiply(axg137230_BigNumList bigNum1, axg137230_BigNumList bigNum2) {
		if (bigNum1 == null || bigNum2 == null)
			return null;
		else {
			axg137230_BigNumList largeList, smallList, bigListMul = new axg137230_BigNumList();
			// multiplying smaller to the larger list.
			if (bigNum1.size() > bigNum2.size()) {
				largeList = bigNum1;
				smallList = bigNum2;
			} else {
				largeList = bigNum2;
				smallList = bigNum1;
			}
			int count = 0;
			Iterator<Integer> i2 = smallList.iterator(); // iterator to smaller
															// list.
			while (i2.hasNext()) {
				int carry = 0;
				axg137230_BigNumList tempBigList = new axg137230_BigNumList(); // this list will
															// store the final
															// result.
				int num2 = i2.next();
				// iterator to larger list initialized for single iteration of
				// smaller list.
				Iterator<Integer> i1 = largeList.iterator();
				// iterator to resultant list initialized for every single
				// iteration of smaller list and used with the tempBigList to
				// calculate the result.
				Iterator<Integer> i3 = bigListMul.iterator();
				// jumping the positions after multiplication.
				for (int i = 0; i < count; i++) {
					int oldNum = i3.next();
					tempBigList.insert(oldNum);
				}
				while (i1.hasNext()) {
					int num1 = i1.next();
					int mul = num2 * num1 + carry;
					if (count > 0) {
						int num3 = 0;
						if (i3.hasNext())
							num3 = i3.next();
						mul += num3; // adding result of previous
										// multiplication.
						carry = mul / 10;
						tempBigList.insert(mul % 10);
					} else {
						carry = mul / 10; // first multiplication.
						tempBigList.insert(mul % 10);
					}
				}
				if (carry != 0)
					tempBigList.insert(carry);
				bigListMul = tempBigList.reverse(); // swapping between
													// bigListMul and
													// tempBigList.
				count++;
			}
			return bigListMul;
		}
	}

	/**
	 * This method calculates the bigNum1 to the power of bigNum2 and returns
	 * the result.
	 * 
	 * @param bigNum1
	 * @param bigNum2
	 * @return a axg137230_BigNumList type
	 */
	public static axg137230_BigNumList power(axg137230_BigNumList bigNum1, axg137230_BigNumList bigNum2) {
		if (bigNum1 == null || bigNum2 == null)
			return null;
		else {
			axg137230_BigNumList bigListPower = bigNum1;
			axg137230_BigNumList sub = new axg137230_BigNumList();
			sub.insert(1);
			while (!numToStr(bigNum2).equalsIgnoreCase("1")) {
				bigListPower = multiply(bigListPower, bigNum1);
				bigNum2 = subtract(bigNum2, sub);
			}
			return bigListPower;
		}
	}

	// Temporary method to display the axg137230_BigNumList in the reverse order.
	public static void showNum(axg137230_BigNumList bigNum) {
		for (Integer integer : bigNum) {
			System.out.print(integer + " -> ");
		}
		System.out.println();
	}
}
