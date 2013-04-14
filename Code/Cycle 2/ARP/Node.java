import java.util.LinkedList;

/*
 * This class is used to build the foundation for the graph.
 * It contains an inner class of Edge which is used to tell 
 * the node what is coming and going from it.
 * 
 */

class Node{


	private final String AirportCode;
	private final LinkedList<Edge> inEdges;
	private final LinkedList<Edge> outEdges;

	//constructor for the nodes. The identifier for each node is the Airport code imported from the file 
	public Node(String AirportCode) {
		this.AirportCode = AirportCode;
		inEdges = new LinkedList<Edge>();
		outEdges = new LinkedList<Edge>();


	}

	//this method allows the program to add edges (routes) to each airport (node)
	public Node addEdge(Node node,Route r){
		Edge e = new Edge(this, node,r);
		outEdges.add(e);
		node.inEdges.add(e);
		return this;
	}

	//returns what edges are coming in to the node
	public LinkedList<Edge> getInEdges(){

		return inEdges;

	}

	//returns what edges are going out of the node
	public LinkedList<Edge> getOutEdges(){

		return outEdges;

	}

	//returns the airport code
	public String toString() {
		return AirportCode;
	}

	/*
	 * This class tells the system what nodes are connected. Every node has a list
	 * of edges to tell the sytem what is going where. 
	 */
	static class Edge{
		private final Node from;
		private final Node to;
		private final Route r;

		//the constructor requires a to and from node as well as an identifier in the means of a route 
		public Edge(Node from, Node to, Route r) {
			this.from = from;
			this.to = to;
			this.r = r;
		}
		@Override
		public boolean equals(Object obj) {
			Edge e = (Edge)obj;
			return e.from == from && e.to == to;
		}

		//return what route this edge represents
		public Route getRoute(){

			return r;

		}
		//return where the edge is going to
		public Node getTo(){

			return to;

		}
		//return where the node is coming from
		public Node getFrom(){

			return from;

		}

	}


}