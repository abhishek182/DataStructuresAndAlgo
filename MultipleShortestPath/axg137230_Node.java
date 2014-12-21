import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the abstract data type for a vertex in the graph.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */
public class axg137230_Node {
	/**
	 * This holds a very large value.
	 */
	private static final long INFINITY = Long.MAX_VALUE;
	/**
	 * This stores the distance of this node from the source node.
	 */
	public long dist;

	/**
	 * This stores the previous node in the shortest path.
	 */
	public axg137230_Node prev;

	/**
	 * This stores the number of shortest paths to this node.
	 */
	public long noOfPaths;

	/**
	 * This stores the number of times the outgoing edges of this node has been
	 * relaxed.
	 */
	public int count;

	/**
	 * This stores the list of outgoing edges from this node.
	 */
	public List<axg137230_Edge> adj;

	/**
	 * This holds the value to be stored in the node.
	 */
	public int val;

	/**
	 * The constructor initialize the node to its default value.
	 * 
	 * @param val
	 *            The value to be stored in the val property,
	 */
	public axg137230_Node(int val) {
		this.dist = INFINITY;
		this.prev = null;
		this.noOfPaths = 0;
		this.count = 0;
		this.adj = new ArrayList<axg137230_Edge>();
		this.val = val;
	}

}
