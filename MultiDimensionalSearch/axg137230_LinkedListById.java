/*************************************************************************
 *  Compilation:  javac SequentialSearchST.java
 *  Execution:    java SequentialSearchST
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/31elementary/tinyST.txt  
 *  
 *  Symbol table implementation with sequential search in an
 *  unordered linked list of id-value pairs.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *
 *  % java SequentialSearchST < tiny.txt 
 *  L 11
 *  P 10
 *  M 9
 *  X 7
 *  H 5
 *  C 4
 *  R 3
 *  A 8
 *  E 12
 *  S 0
 *
 *************************************************************************/

/**
 * The <tt>SequentialSearchST</tt> class represents an (unordered) symbol table
 * of generic id-value pairs. It supports the usual <em>put</em>, <em>get</em>,
 * <em>contains</em>, <em>delete</em>, <em>size</em>, and <em>is-empty</em>
 * methods. It also provides a <em>ids</em> method for iterating over all of
 * the ids. A symbol table implements the <em>associative array</em>
 * abstraction: when associating a value with a id that is already in the
 * symbol table, the convention is to replace the old value with the new value.
 * The class also uses the convention that values cannot be <tt>null</tt>.
 * Setting the value associated with a id to <tt>null</tt> is equivalent to
 * deleting the id from the symbol table.
 * <p>
 * This implementation uses a singly-linked list and sequential search. It
 * relies on the <tt>equals()</tt> method to test whether two ids are equal. It
 * does not call either the <tt>compareTo()</tt> or <tt>hashCode()</tt> method.
 * The <em>put</em> and <em>delete</em> operations take linear time; the
 * <em>get</em> and <em>contains</em> operations takes linear time in the worst
 * case. The <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 * <p>
 * For additional documentation, see <a
 * href="http://algs4.cs.princeton.edu/31elementary">Section 3.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class axg137230_LinkedListById {
	private int N; // number of id-value pairs
	private Node first; // the linked list of id-value pairs

	// a helper linked list data type
	private class Node {
		private Long id;
		private axg137230_Item val;
		private Node next;

		public Node(Long id, axg137230_Item val, Node next) {
			this.id = id;
			this.val = val;
			this.next = next;
		}
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public axg137230_LinkedListById() {
	}

	/**
	 * Returns the number of id-value pairs in this symbol table.
	 * 
	 * @return the number of id-value pairs in this symbol table
	 */
	public int size() {
		return N;
	}

	/**
	 * Is this symbol table empty?
	 * 
	 * @return <tt>true</tt> if this symbol table is empty and <tt>false</tt>
	 *         otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Does this symbol table contain the given id?
	 * 
	 * @param id
	 *            the id
	 * @return <tt>true</tt> if this symbol table contains <tt>id</tt> and
	 *         <tt>false</tt> otherwise
	 */
	public boolean contains(Long id) {
		return get(id) != null;
	}

	/**
	 * Returns the value associated with the given id.
	 * 
	 * @param id
	 *            the id
	 * @return the value associated with the given id if the id is in the
	 *         symbol table and <tt>null</tt> if the id is not in the symbol
	 *         table
	 */
	public axg137230_Item get(Long id) {
		for (Node x = first; x != null; x = x.next) {
			if (id.equals(x.id))
				return x.val;
		}
		return null;
	}

	/**
	 * Inserts the id-value pair into the symbol table, overwriting the old
	 * value with the new value if the id is already in the symbol table. If
	 * the value is <tt>null</tt>, this effectively deletes the id from the
	 * symbol table.
	 * 
	 * @param id
	 *            the id
	 * @param val
	 *            the value
	 */
	public void put(Long id, axg137230_Item val) {
		if (val == null) {
			delete(id);
			return;
		}
		for (Node x = first; x != null; x = x.next)
			if (id.equals(x.id)) {
				x.val = val;
				return;
			}
		first = new Node(id, val, first);
		N++;
	}

	/**
	 * Removes the id and associated value from the symbol table (if the id is
	 * in the symbol table).
	 * 
	 * @param id
	 *            the id
	 */
	public void delete(Long id) {
		first = delete(first, id);
	}

	// delete id in linked list beginning at Node x
	// warning: function call stack too large if table is large
	private Node delete(Node x, Long id) {
		if (x == null)
			return null;
		if (id.equals(x.id)) {
			N--;
			return x.next;
		}
		x.next = delete(x.next, id);
		return x;
	}

	/**
	 * Returns all ids in the symbol table as an <tt>Iterable</tt>. To iterate
	 * over all of the ids in the symbol table named <tt>st</tt>, use the
	 * foreach notation: <tt>for (id id : st.ids())</tt>.
	 * 
	 * @return all ids in the sybol table as an <tt>Iterable</tt>
	 */
	public Iterable<Long> ids() {
		axg137230_Queue<Long> queue = new axg137230_Queue<Long>();
		for (Node x = first; x != null; x = x.next)
			queue.enqueue(x.id);
		return queue;
	}

}