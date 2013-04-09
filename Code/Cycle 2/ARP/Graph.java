import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/*
 * Credit should be given to Ben O'Brien and Max Dewees for their help in steering us on the right path
 * 
 * This class creates nodes and edges that will be used for searching. The nodes are created from the Airports and
 * the edges are the Routes. The methods in this class will automatically generate Nodes and Edges based on the input
 * file. This class implements the depth first tree traversal searching ideology. 
 * 
 */


public class Graph {

	static LinkedList <Path> p = new LinkedList<Path>();
	static LinkedList <Node> path;
	static LinkedList <Node> allAirports;
	static Stack <Node> nodeStack = new Stack<Node>();
	static LinkedList <Node> routePath = new LinkedList<Node>();
	static LinkedList<Node>routePaths = new LinkedList<Node>();
	static LinkedList<Route> associatedRoutes = new LinkedList<Route>();

	Director d;

	public Graph(Director d){

		this.d=d;

	}


	 class Path{

		private  Stack<Node> pathStack;
		double cost=0;
		private Stack<Route> routeStack = new Stack<Route>();
		LinkedList<routePath>rP = new LinkedList<routePath>();

		@SuppressWarnings("unchecked")
		public Path(Stack <Node> nodeStack){ 
			this.pathStack=(Stack <Node>)nodeStack.clone();

			getRoutes(routeStack, rP);
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return nodeStack.toString();
		}

		public LinkedList<routePath> getroutePath(){
			return rP;

		}


		public void getRoutes(Stack<Route> routeStack,LinkedList<routePath> routePath){

			if(pathStack.size()<=1&&!pathStack.isEmpty()){
				routePath.add(new routePath(routeStack));

				return;


			}else{
				Node n =pathStack.pop();
				Node next = pathStack.peek();



				for(int i=0;i<n.getInEdges().size();i++){
					if(routeStack.size()<1){
						if(n.getInEdges().get(i).getFrom().toString().matches(next.toString())){
							routeStack.push(n.getInEdges().get(i).getRoute());
							getRoutes(routeStack,routePath);
							routeStack.pop();
						}
					}
					else{
						if(n.getInEdges().get(i).getFrom().toString().matches(next.toString())&&routeStack.peek().getDepTime()-n.getInEdges().get(i).getRoute().getArrivalTime()>30){
							routeStack.push(n.getInEdges().get(i).getRoute());
							getRoutes(routeStack,routePath);
							routeStack.pop();
						}
					}
				}
				pathStack.push(n);
			}
		}

		 class routePath{

			private Stack<Route> routeStack;
			private LinkedList<Route> routeList = new LinkedList<Route>();

			@SuppressWarnings("unchecked")
			public routePath(Stack<Route> nodeStack){
				this.routeStack=(Stack <Route>)nodeStack.clone();
				for(int i=0;i<routeStack.size();i++){
					routeList.addFirst(routeStack.get(i));

				}
			}

			public String toString() {
				// TODO Auto-generated method stub
				return routeList.toString();
			}
			
			public LinkedList<Route> getPath(){
				
				return routeList;
				
			}

			public double getCost(){
				double cost =0;
				for(int i=0;i<routeList.size();i++){
					cost+=routeList.get(i).getCost();

				}
				return cost;
			}

			public int getTime(){
				int time=0;
				for(int i=0;i<routeList.size();i++){
					if(routeList.size()>i+1){
						time+=(routeList.get(i).getDepTime()-routeList.get(i).getArrivalTime())+(routeList.get(i).getArrivalTime()-routeList.get(i+1).getDepTime());

					}else{

						time+=(routeList.get(i).getDepTime()-routeList.get(i).getArrivalTime());

					}

				}
				return time;
			}

			public String getAirlines(){

				HashSet <String> uniqueAirLines = new HashSet <String>();
				LinkedList <String> airlines = new LinkedList<String>();

				for(int i=0;i<routeList.size();i++){

					if(!uniqueAirLines.contains(routeList.get(i).getAirline())){

						uniqueAirLines.add(routeList.get(i).getAirline());

					}
					airlines.add(routeList.get(i).getAirline());
				}

				return "s";

			}


		}
	}
	 
	public LinkedList<Path> getCheapest(Node Origin, Node Destination){
		Stack <Node> cheapestNodesStack = new Stack <Node>();
		LinkedList <Path> cheapestPaths = new LinkedList<Path>();
		pathFind(Origin,Destination,cheapestNodesStack, cheapestPaths);
		
		return cheapestPaths;
	}

	public void draw(){

		allAirports=createNodes(d.getAirports());
		addRoutes(allAirports,d.getAllRoutes());

	}


	//this method will automatically generate any route associated with each node. 
	public void addRoutes(LinkedList<Node> airport, LinkedList<Route> routes){

		Node n = null;

		//this loops for every element in the node linked list
		for(int k=0;k<airport.size();k++){

			//this loops for every element of the route linked list
			for(int i=0;i<routes.size();i++){

				//this will only go into this portion if the node is the same as the destination node
				if(airport.get(k).toString().matches(routes.get(i).getDestination().toString())){

					//this iterates for every element in the node linked list
					for(int u=0;u<airport.size();u++){

						//this will only execute this portion if the node is the same as the origin
						if(airport.get(u).toString().matches(routes.get(i).getOrigin().toString())){

							//we then assign the current node we're at to n
							n=airport.get(u);

						}
					}
					//code then adds a route between origin and the destination
					n.addEdge(airport.get(k), routes.get(i));

				}

			}

		}
	}

	//this method will return any arriving routes into a given node
	public LinkedList <Route> getArrivingRoutes(Node airport){

		LinkedList<Route> Arrivals = new LinkedList<Route>();

		//loops for every incoming route associated with that airport	
		for(int i=0;i<airport.inEdges.size();i++){

			//add to a linked list of all the routes associated with particular node
			Arrivals.add(airport.inEdges.get(i).getRoute());

		} 
		return Arrivals;
	}

	//this method will return any route departing from a given node
	public LinkedList<Route> getDepartingRoutes(Node airport){

		LinkedList<Route> Departures = new LinkedList<Route>();

		//loop for all the outgoing edges associated with a node
		for(int i=0;i<airport.outEdges.size();i++){

			//return a linked list of all the routes associated
			Departures.add(airport.outEdges.get(i).getRoute());

		} 
		return Departures;
	}

	//this method will automatically create nodes for all the airports in the system
	public LinkedList<Node> createNodes(LinkedList<Airport> airports){

		LinkedList<Node> nodes = new LinkedList<Node>();

		//loop for all airports in the system
		for(int i =0; i<airports.size();i++){

			//create a new node with the given code and add it to the list of nodes
			nodes.add(new Node(airports.get(i).getCode()));
		}

		return nodes;

	}


	public void pathFind(Node Origin, Node Destination, Stack<Node> nodeStack, LinkedList <Path> path){


		LinkedList <Node> visibleNodes = new LinkedList<Node>();

		nodeStack.push(Origin);

		if(nodeStack.size()==0){
			return;
		}

		if(Origin.toString().matches(Destination.toString())){
			path.add(new Path(nodeStack));

		}else{
			visibleNodes=getVisibleNodes(Origin,nodeStack);

			for(int i=0;i<visibleNodes.size();i++){

				pathFind(visibleNodes.get(i),Destination,nodeStack,path);

			}
		}
		nodeStack.pop();

	}

	public LinkedList <Node> getVisibleNodes(Node n,Stack<Node> nodeStack){

		LinkedList<Node> visibleNodesunSorted = new LinkedList<Node>();

		for(int i=0;i<n.getOutEdges().size();i++){

			if(!nodeStack.contains(n.getOutEdges().get(i).getTo())){

				visibleNodesunSorted.add(n.getOutEdges().get(i).getTo());



			}

		}

		return new LinkedList<Node>(new HashSet<Node>(visibleNodesunSorted));
	}


	public Node getNode(Airport a){

		Node n = null;

		for(int i=0;i<allAirports.size();i++){
			if(a.toString().matches(allAirports.get(i).toString())){

				n=allAirports.get(i);
			}

		}

		return n;
	}

}