/**
 * This class provides an abstract data type for an Edge between any two
 * vertices of any type.
 * 
 * @author Abhishek Gupta (axg137230) and Manuj Singh (mxs135630)
 *
 * @param <T>
 *            Type of objects between which there is an edge.
 */
public class axg137230_Edge {
	/**
	 * This holds the source of this edge.
	 */
	private axg137230_Node u;
	
	/**
	 * This holds the destination of this edge.
	 */
	private axg137230_Node v;
	
	/**
	 * This holds the weight of the edge.
	 */
	private int w;

	/**
	 * This constructor initializes an Edge.
	 * @param from
	 * @param to
	 * @param w
	 */
	public axg137230_Edge(axg137230_Node from, axg137230_Node to, int w) {
		this.u = from;
		this.v = to;
		this.w = w;
	}

	/**
	 * This method returns the start object of the edge.
	 * @return
	 */
	public axg137230_Node from() {
		return u;
	}

	/**
	 * This method returns the end object of the edge.
	 * @return
	 */
	public axg137230_Node to() {
		return v;
	}

	/**
	 * This method returns the weight of the edge.
	 * @return
	 */
	public int weight() {
		return w;
	}
	/**
	 * This method sets the weight of the edge.
	 * @return
	 */
	public void setWeight(int w) {
		this.w = w;
	}

}
