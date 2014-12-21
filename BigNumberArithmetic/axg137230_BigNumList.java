import java.util.Iterator;

/**
 * This class is the implementation Linked list of integer numbers. It also
 * implements Iterable interface so that the list can be iterated.
 * 
 * @implements Iterable
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */
public class axg137230_BigNumList implements Iterable<Integer> {
	/**
	 * This is the head of the linked list.
	 */
	private Node head;

	/**
	 * This stores the number of the nodes in the linked list.
	 */
	private int N;

	/**
	 * This class defines the structure of a node. A node contains and integer
	 * variable and reference to other node.
	 * 
	 * @author Abhishek Gupta
	 *
	 */
	private static class Node {
		private Integer num;
		private Node next;
	}

	/**
	 * This is constructor that initialize the linked list.
	 */
	public axg137230_BigNumList() {
		head = null;
		N = 0;
	}

	/**
	 * This method checks whether linked list is empty or not.
	 * 
	 * @return a boolean true/false value.
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * This method returns the number of nodes in the linked list.
	 * 
	 * @return an integer value.
	 */
	public int size() {
		return N;
	}

	/**
	 * This method inserts a new node at the head of the linked list.
	 * 
	 * @param num
	 *            an integer value to be stored in the new node.
	 */
	public void insert(int num) {
		Node oldHead = head;
		head = new Node(); // new node becomes the head.
		head.num = num;
		head.next = oldHead; // old head comes next to the new head.
		N++;
	}

	/**
	 * This method reverses the linked list and returns this list with updated
	 * head.
	 * 
	 * @return BigNumList type.
	 */
	public axg137230_BigNumList reverse() {
		if (!isEmpty()) {
			Node current, prevNode = null;
			current = head;
			while (current != null) {
				Node temp = current.next;
				current.next = prevNode; // reversing the pointer assignment.
				prevNode = current;
				current = temp;
			}
			head = prevNode; // making last node as the head.
			return this;
		}
		return null;
	}

	/**
	 * This methods sets value of all the nodes of the linked list to zero.
	 */
	public void setAllToZero() {
		if (!isEmpty()) {
			Node current = head;
			while (current != null) {
				current.num = 0;
				current = current.next;
			}
		}
	}

	/**
	 * This method cuts down the linked list until the head becomes non-zero.
	 */
	public void cutZerosAtHead() {
		if (head != null)
			while (head.num == 0 & head.next != null) {
				head = head.next;
				N--; // reducing the number of nodes.
			}
	}

	/**
	 * This method returns the iterator to the list.
	 * 
	 * @return a ListIterator.
	 */
	@Override
	public Iterator<Integer> iterator() {
		return new ListIterator<Integer>(head);
	}

	/**
	 * This class implements the iterator for the list.
	 * 
	 * @implements Iterator
	 * @author Abhishek Gupta
	 *
	 */
	@SuppressWarnings("hiding")
	private class ListIterator<Integer> implements Iterator<Integer> {
		/**
		 * This points to he current node.
		 */
		private Node current;

		/**
		 * This is the constructor initializing the list iterator.
		 * 
		 * @param head
		 *            start iteration from the head of the list.
		 */
		public ListIterator(Node head) {
			current = head;
		}

		/**
		 * This method checks whether there is a node at iterator or not.
		 * 
		 * @return a boolean true/false value.
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * This method returns the integer value stored in the node on to which
		 * iterator is pointing currently.
		 * 
		 * @return an integer value.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Integer next() {
			if (!hasNext())
				return null;
			Integer item = (Integer) current.num;
			current = current.next;
			return item;
		}

	}

}
