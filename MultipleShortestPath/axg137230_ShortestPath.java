import java.util.LinkedList;
import java.util.Queue;

/**
 * This class provides abstract data type for holding the graph information and
 * find out the number shortest paths from the source to all other nodes.The
 * class uses the improved version of bellman ford algorithm for findind
 * shortest paths.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 */
public class axg137230_ShortestPath {
	/**
	 * This holds a very large value.
	 */
	private static final long INFINITY = Long.MAX_VALUE;
	/**
	 * This holds the array of nodes or vertices in the graph.
	 */
	private axg137230_Node[] nodes;

	/**
	 * This holds the number of vertices in the graph.
	 */
	private int V;

	/**
	 * This holds the index of the source node.
	 */
	private int source;

	/**
	 * This holds the index of the destination node.
	 */
	private int destination;

	/**
	 * This holds the start time of the program.
	 */
	private long start;

	/**
	 * This holds the end time of the program.
	 */
	private long end;

	/**
	 * The constructor initialize all the nodes and source and destination
	 * nodes.
	 * 
	 * @param V
	 *            The number of vertices.
	 * @param source
	 *            The index of the source node.
	 * @param destination
	 *            The index of the destination node.
	 */
	public axg137230_ShortestPath(int V, int source, int destination) {
		start = System.currentTimeMillis();
		this.V = V;
		this.source = source;
		this.destination = destination;
		this.nodes = new axg137230_Node[this.V + 1];
		for (int i = 1; i < this.V + 1; i++) {
			nodes[i] = new axg137230_Node(i);
		}
		nodes[source].dist = 0;
		nodes[source].noOfPaths = 1;

	}

	/**
	 * This method adds an edge with weight w between nodes with index u and v.
	 * 
	 * @param u
	 *            Index of starting node of the edge.
	 * @param v
	 *            Index of ending node of the edge.
	 * @param w
	 *            Weight of the edge.
	 */
	public void addEdge(int u, int v, int w) {
		axg137230_Edge e = new axg137230_Edge(nodes[u], nodes[v], w);
		this.nodes[u].adj.add(e);

	}

	/**
	 * This method relaxes the edge and updating the destination node if needed.
	 * 
	 * @param e
	 * @return
	 */
	private boolean relax(axg137230_Edge e) {
		axg137230_Node u = e.from();
		axg137230_Node v = e.to();
		if (v.dist > u.dist + e.weight()) {
			v.dist = u.dist + e.weight();
			v.prev = u;
			v.noOfPaths = u.noOfPaths;
			return true; // if distance to v has changed.
		}
		if (v.dist == u.dist + e.weight())
			if (!u.equals(v.prev))
				v.noOfPaths += u.noOfPaths;
		return false; // if distance to v has not changed.
	}

	/**
	 * This method finds out the shortest paths from source node to all other
	 * nodes.
	 * 
	 * @throws Exception
	 *             If found a non-positive cycle in the graph.
	 */
	public void findShortestPaths() throws Exception {
		// Queue to store nodes with updated distance from the source node.
		Queue<axg137230_Node> queue = new LinkedList<>();
		queue.add(nodes[source]); // add source node the queue.
		while (!queue.isEmpty()) {
			axg137230_Node u = queue.remove();
			// If single node has been visited for more than number of vertices
			// times.
			if (u.count++ > V) {
				throw new Exception(
						"Non-positive cycle in graph. DAC is not applicable");

			}
			// relax each outgoing edge of node u.
			for (axg137230_Edge e : u.adj)
				if (relax(e) && !queue.contains(e.to()))
					queue.add(e.to());
		}
		end = System.currentTimeMillis();
		// display output.
		System.out.println(nodes[destination].dist + " "
				+ nodes[destination].noOfPaths + " " + (end - start) + " msec");
		if (V <= 100) {
			for (int i = 1; i < V + 1; i++) {
				if (nodes[i].dist == INFINITY)
					System.out.print(i + " INF ");
				else
					System.out.print(i + " " + nodes[i].dist + " ");
				if (nodes[i].prev != null)
					System.out.print(nodes[i].prev.val + " ");
				else
					System.out.print("- ");
				System.out.print(nodes[i].noOfPaths + "\n");
			}
		}
	}

}
