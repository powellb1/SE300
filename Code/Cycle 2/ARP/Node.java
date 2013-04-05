import java.util.LinkedList;


class Node{


	public final String AirportCode;
	public final LinkedList<Edge> inEdges;
	public final LinkedList<Edge> outEdges;

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

	public LinkedList<Edge> getInEdges(){

		return inEdges;

	}

	public LinkedList<Edge> getOutEdges(){

		return outEdges;

	}

	//returns the airport code
	public String toString() {
		return AirportCode;
	}

	static class Edge{
		public final Node from;
		public final Node to;
		public final Route r;

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


		public Route getRoute(){

			return r;

		}

		public Node getTo(){

			return to;

		}

		public Node getFrom(){

			return from;

		}

	}


}