/*************************************************************************
 * Compilation: javac SeparateChainingHashST.java Execution: java
 * SeparateChainingHashST
 *
 * A symbol table implemented with a separate-chaining hash table.
 * 
 * % java SeparateChainingHashST
 *
 *************************************************************************/
/**
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class axg137230_HashMapById {
	private static final int INIT_CAPACITY = 10000;

	// largest prime <= 2^i for i = 3 to 31
	// not currently used for doubling and shrinking
	// private static final int[] PRIMES = {
	// 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
	// 32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
	// 8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
	// 536870909, 1073741789, 2147483647
	// };

	private int N; // number of key-value pairs
	private int M; // hash table size
	private axg137230_LinkedListById[] st; // array of linked-list symbol
												// tables

	// create separate chaining hash table
	public axg137230_HashMapById() {
		this(INIT_CAPACITY);
	}

	// create separate chaining hash table with M lists
	public axg137230_HashMapById(int M) {
		this.M = M;
		st = (axg137230_LinkedListById[]) new axg137230_LinkedListById[M];
		for (int i = 0; i < M; i++)
			st[i] = new axg137230_LinkedListById();
	}

	// resize the hash table to have the given number of chains b rehashing all
	// of the keys
	private void resize(int chains) {
		axg137230_HashMapById temp = new axg137230_HashMapById(
				chains);
		for (int i = 0; i < M; i++) {
			for (long key : st[i].ids()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.M = temp.M;
		this.N = temp.N;
		this.st = temp.st;
	}

	// hash value between 0 and M-1
	private int hash(Long key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	// return number of key-value pairs in symbol table
	public int size() {
		return N;
	}

	// is the symbol table empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// is the key in the symbol table?
	public boolean contains(long key) {
		return get(key) != null;
	}

	// return value associated with key, null if no such key
	public axg137230_Item get(long key) {
		int i = hash(key);
		return st[i].get(key);
	}

	// insert key-value pair into the table
	public void put(long key, axg137230_Item val) {
		if (val == null) {
			delete(key);
			return;
		}

		// double table size if average length of list >= 10
		if (N >= 10 * M)
			resize(2 * M);

		int i = hash(key);
		if (!st[i].contains(key))
			N++;
		st[i].put(key, val);
	}

	// delete key (and associated value) if key is in the table
	public void delete(long key) {
		int i = hash(key);
		if (st[i].contains(key))
			N--;
		st[i].delete(key);

		// halve table size if average length of list <= 2
		if (M > INIT_CAPACITY && N <= 2 * M)
			resize(M / 2);
	}

	// return keys in symbol table as an Iterable
	public Iterable<Long> keys() {
		axg137230_Queue<Long> queue = new axg137230_Queue<Long>();
		for (int i = 0; i < M; i++) {
			for (long key : st[i].ids())
				queue.enqueue(key);
		}
		return queue;
	}

}