/**
 * This class implements the structure of and item.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */

public class axg137230_Item {
	/**
	 * This variable holds the id of an item
	 */
	private long id;

	/**
	 * This variable holds the names associated with an item.
	 */
	private long[] names;

	/**
	 * This variable holds the price of an item.
	 */
	private double price;

	public axg137230_Item(long id, long[] names, double price) {
		this.id = id;
		this.names = names;
		this.price = (double) price;
	}

	// Getters and setters.
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long[] getNames() {
		return names;
	}

	public void setNames(long[] names) {
		this.names = names;
	}

	public double getPrice() {
		return (double) price;
	}

	public void setPrice(double price) {
		this.price = (double) price;
	}

}
