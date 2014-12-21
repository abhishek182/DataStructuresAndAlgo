import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * This class implements the parallel data structure to store items. object of
 * item is stored only once but its reference is stored in three different data
 * structures.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */
public class axg137230_ParallelDataStructure {
	/**
	 * This hashmap store item reference hashed by its id.
	 */
	private axg137230_HashMapById idSearch;

	/**
	 * This hashmap stores items in a RedBlack Tree (key as price) hashed by
	 * name.
	 */
	private axg137230_HashMapByName nameSearch;

	/**
	 * This RedBlack tree stores id of items in order.
	 */
	private axg137230_RedBlackKeyTree<Long> keyTree;

	public axg137230_ParallelDataStructure() {
		this.idSearch = new axg137230_HashMapById();
		this.nameSearch = new axg137230_HashMapByName();
		this.keyTree = new axg137230_RedBlackKeyTree<Long>();
	}

	/**
	 * This method insert a new item or update the older one into all three data
	 * structures.
	 * 
	 * @param id
	 *            long
	 * @param names
	 *            long[]
	 * @param price
	 *            double
	 * @return int
	 */
	public int insert(long id, long[] names, double price) {
		// Checking if item already exists.
		axg137230_Item oldItem = this.idSearch.get(id);
		if (oldItem == null) {
			// Creating new item only once. Using its reference else where.
			axg137230_Item item = new axg137230_Item(id, names, (double) price);
			// Inserting item in idSearch map.
			this.idSearch.put(id, item);
			// For each name inserting item in nameSearch map.
			for (long name : item.getNames()) {
				this.nameSearch.insert(name, item);
			}
			// Inserting id in keyTree.
			keyTree.put(id);
			return 1;
		} else {
			// Updating old item
			long[] oldnames = oldItem.getNames(); // get old names.
			// Overriding names if new names are given.
			if (names != null && names.length != 0) {
				oldItem.setNames(names);
			}
			// Deleting its references from nameSearch map for older names.
			for (long name : oldnames) {
				this.nameSearch.delete(name, oldItem.getPrice(), oldItem);
			}
			// Updating price of the item.
			oldItem.setPrice((double) price);
			// Inserting the item again into nameSearch with updated names or
			// older.
			for (long name : oldItem.getNames()) {
				this.nameSearch.insert(name, oldItem);
			}
			return 0;
		}
	}

	/**
	 * This method returns the price of item if found. It uses only idSearch map
	 * to get the reference of the item with given id.
	 * 
	 * @param id
	 * @return
	 */
	public double find(long id) {
		axg137230_Item item = this.idSearch.get(id);
		if (item != null)
			return (double) item.getPrice();
		return 0.0d;
	}

	/**
	 * This method delete all the references of item with given id from all data
	 * structure and making it available for garbage collection.
	 * 
	 * @param id
	 * @return
	 */
	public long delete(long id) {
		// get item from idSearch Map
		axg137230_Item item = this.idSearch.get(id);
		long sum = 0L;
		if (item != null) {
			// get its names[] if found else return 0.
			// for each name get the tree in nameSearch and delete the item
			// reference from the tree using its price
			for (long name : item.getNames()) {
				this.nameSearch.delete(name, item.getPrice(), item);
				sum += name;
			}
			// delete the key from key tree.
			this.keyTree.delete(id);
			// delete the key from the idSearch.
			this.idSearch.delete(id);
		}
		return sum;
	}

	/**
	 * This method returns minimum price of item for a given name.
	 * 
	 * @param name
	 * @return
	 */
	public double findMinPrice(long name) {
		// get the tree of items using name. Find the min() in the tree. return
		// it.
		axg137230_RedBlackBST treeByName = this.nameSearch.get(name);
		if (treeByName != null)
			return (double) treeByName.min();
		else
			return 0.0d;
	}

	/**
	 * This method returns maximum price of item for a given name.
	 * 
	 * @param name
	 * @return
	 */
	public double findMaxPrice(long name) {
		// get the tree of items using name. Find the max() in the tree. return
		// it.
		axg137230_RedBlackBST treeByName = this.nameSearch.get(name);
		if (treeByName != null)
			return (double) treeByName.max();
		else
			return 0.0d;
	}

	/**
	 * This method finds number of item between low and high price for a given
	 * name.
	 * 
	 * @param name
	 * @param low
	 * @param high
	 * @return
	 */
	public int findPriceRange(long name, double low, double high) {
		// get the tree of items using name. Find the keys using keys(lo,hi) and
		// return the count.
		axg137230_RedBlackBST treeByName = this.nameSearch.get(name);
		Iterable<Double> keys = treeByName.keys((double) low, (double) high);
		int count = 0;
		for (Double d : keys) {
			LinkedList<axg137230_Item> itemList = treeByName.get((double) d);
			count += itemList.size();
		}
		return count;
	}

	/**
	 * This method hike the price of all items between ids, lowId and highId by
	 * rate% and return the sum of increase.
	 * 
	 * @param lowId
	 * @param highId
	 * @param rate
	 * @return
	 */
	public double priceHike(long lowId, long highId, int rate) {
		if (rate > 0) {
			// get all ids within range [lowId,highId] using keyTree.
			Iterable<Long> ids = this.keyTree.keys(lowId, highId);
			// Iterate over them and get the corresponding item.
			double increaseBy = (double) (1.0d + (double) rate / 100.0d);
			double netIncrease = 0.0d;
			for (Long id : ids) {
				// Get the item by id
				axg137230_Item item = this.idSearch.get(id);
				// Delete its references from all the tree hashed by its names.
				for (long name : item.getNames()) {
					this.nameSearch.delete(name, item.getPrice(), item);
				}
				// Get the old price.
				double oldPrice = (double) item.getPrice();
				// Calculate new Price.
				double newPrice = (double) truncateDecimal((double) oldPrice
						* (double) increaseBy, 2);
				// Calculate the net increase in the price and add it to the net
				// total.
				netIncrease = (double) netIncrease
						+ (double) ((double) newPrice - (double) oldPrice);
				// Set new price to the item.
				item.setPrice(newPrice);
				// Insert again item to the map hashed by name.
				for (long name : item.getNames()) {
					this.nameSearch.insert(name, item);
				}
				// Insert again the item into map hashed by id.
				this.idSearch.put(id, item);
			}
			return truncateDecimal(netIncrease, 2);
		} else
			return 0.0d;
	}

	/**
	 * This method truncates the double value after given number of decimal
	 * places.
	 * 
	 * @param unroundedNumber
	 * @param decimalPlaces
	 * @return
	 */
	private double truncateDecimal(double unroundedNumber, int decimalPlaces) {
		if (unroundedNumber > 0) {
			return new BigDecimal(String.valueOf(unroundedNumber)).setScale(
					decimalPlaces, BigDecimal.ROUND_FLOOR).doubleValue();
		} else {
			return new BigDecimal(String.valueOf(unroundedNumber)).setScale(
					decimalPlaces, BigDecimal.ROUND_CEILING).doubleValue();
		}

	}

}
