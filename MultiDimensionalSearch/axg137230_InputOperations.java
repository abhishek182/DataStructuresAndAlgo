/**
 * This class implement methods to process operations provided by the client.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */

public class axg137230_InputOperations {
	/**
	 * This variable holds the data structure storing the item information.
	 */
	private axg137230_ParallelDataStructure pds = new axg137230_ParallelDataStructure();

	/**
	 * This variable holds the sum of output of the all the operations
	 */
	private double output = 0.0;

	/**
	 * This method accepts the operation string and decides what to do with it.
	 * 
	 * @param operation
	 */
	public void processOperation(String operation) {
		String[] parts = operation.split(" ");
		if (parts[0].equalsIgnoreCase("Insert")) {
			long id = Long.parseLong(parts[1]);
			double price = (double) Double.parseDouble(parts[2]);
			// Remove the command, price and last 0 from the operation.
			int namesLength = parts.length - 4;
			long[] names = new long[namesLength];
			if (namesLength != 0) {
				for (int i = 3, j = 0; i < parts.length - 1; i++, j++) {
					names[j] = Long.parseLong(parts[i]);
				}
			}
			double temp = (double) pds.insert(id, names, price);
			// System.out.println("Insert: " + temp);
			output = (double) output + (double) temp;
		} else if (parts[0].equalsIgnoreCase("Find")) {
			long id = Long.parseLong(parts[1]);
			double temp = (double) pds.find(id);
			// System.out.println("Find: " + temp);
			output = (double) output + (double) temp;
		} else if (parts[0].equalsIgnoreCase("Delete")) {
			long id = Long.parseLong(parts[1]);
			double temp = (double) pds.delete(id);
			// System.out.println("Delete: " + temp);
			output = (double) output + (double) temp;
		} else if (parts[0].equalsIgnoreCase("PriceHike")) {
			long lowId = Long.parseLong(parts[1]);
			long highId = Long.parseLong(parts[2]);
			int rate = Integer.parseInt(parts[3]);
			double temp = (double) pds.priceHike(lowId, highId, rate);
			// System.out.println("PriceHike: " + temp);
			output = (double) output + (double) temp;
		} else if (parts[0].equalsIgnoreCase("FindMaxPrice")) {
			long name = Long.parseLong(parts[1]);
			double temp = (double) pds.findMaxPrice(name);
			// System.out.println("FindMaxPrice: " + temp);
			output = (double) output + (double) temp;
		} else if (parts[0].equalsIgnoreCase("FindMinPrice")) {
			long name = Long.parseLong(parts[1]);
			double temp = (double) pds.findMinPrice(name);
			// System.out.println("FindMinPrice: " + temp);
			output = (double) output + (double) temp;
		} else if (parts[0].equalsIgnoreCase("FindPriceRange")) {
			long name = Long.parseLong(parts[1]);
			double lowPrice = (double) Double.parseDouble(parts[2]);
			double highPrice = (double) Double.parseDouble(parts[3]);
			double temp = (double) pds
					.findPriceRange(name, lowPrice, highPrice);
			// System.out.println("FindPriceRange: " + temp);
			output = (double) output + (double) temp;
		}
	}

	/**
	 * This method returns the output.
	 * 
	 * @return double
	 */
	public double getOutput() {
		return (double) output;
	}

}
