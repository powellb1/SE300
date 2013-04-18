import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;

/*
 * Credit should be given to Ben O'Brien and Max Dewees for their help in steering us on the right path
 * 
 * This class creates nodes and edges that will be used for searching. The nodes are created from the Airports and
 * the edges are the Routes. The methods in this class will automatically generate Nodes and Edges based on the input
 * file. This class implements the depth first tree traversal searching ideology. The class also has an inner class of
 * Path. 
 * 
 */


public class Graph {

	LinkedList <Path> p = new LinkedList<Path>();
	LinkedList <Node> path;
	LinkedList <Node> allAirports;
	static Stack <Node> nodeStack = new Stack<Node>();
	static LinkedList <Node> routePath = new LinkedList<Node>();
	static LinkedList<Node>routePaths = new LinkedList<Node>();
	static LinkedList<Route> associatedRoutes = new LinkedList<Route>();

	Director d;

	public Graph(Director d){

		this.d=d;

	}

	/*
	 * This class is used to create a "path" of nodes. These are the nodes that need to be visited in order to make
	 * a valid route. The order of the stack which is passed to the constructor is the order in which the nodes need
	 * to be visited. This class contains an inner class of routePath with is a list of paths and the order in which 
	 * they need to be visited to complete the chain
	 *
	 */
	 class Path{

		 //initalize variables
		private  Stack<Node> pathStack;
		double cost=0;
		private Stack<Route> routeStack = new Stack<Route>();
		LinkedList<routePath>rP = new LinkedList<routePath>();

		//constructor
		//clone the stack so that as we continue to recurse we don't
		//mess with the copy of the stack for this class
		@SuppressWarnings("unchecked")
		public Path(Stack <Node> nodeStack){ 
			this.pathStack=(Stack <Node>)nodeStack.clone();

			getRoutes(routeStack, rP);
		}

		//override the toString method
		public String toString() {
			// TODO Auto-generated method stub
			return nodeStack.toString();
		}

		public LinkedList<routePath> getroutePath(){
			return rP;

		}

		//this method generates the routes in the order that we must visit them
		public void getRoutes(Stack<Route> routeStack,LinkedList<routePath> routePath){

			//if the size of our stack of paths is less than one, but not empty
			if(pathStack.size()<=1&&!pathStack.isEmpty()){
				//we make a new object of routePath with our current stack of routes
				routePath.add(new routePath(routeStack));
				//go back to the calling method
				return;


			}else{
				//we'll pop off the first node so that we can see what lies beneath
				Node n =pathStack.pop();
				Node next = pathStack.peek();
				//loop for all the incoming edges
				for(int i=0;i<n.getInEdges().size();i++){
					//if there is only one route on the stack
					if(routeStack.size()<1){
						//make sure that where we are coming from matches where we are and that the route is valid (the airport isn't closed)
						if(n.getInEdges().get(i).getFrom().toString().matches(next.toString())&&n.getInEdges().get(i).getRoute().isValid()){
							//we'll push this route onto our stack, recurse and once we recurse out, we'll pop the route off our stack
							routeStack.push(n.getInEdges().get(i).getRoute());
							getRoutes(routeStack,routePath);
							routeStack.pop();
						}
					}
					else{
						//make sure that where we are coming from matches where we are and that the duration between flights is atleast 30 minutes and the route is valid
						if(n.getInEdges().get(i).getFrom().toString().matches(next.toString())&&routeStack.peek().getDepTime()-n.getInEdges().get(i).getRoute().getArrivalTime()>30&&n.getInEdges().get(i).getRoute().isValid()){
							//push the route onto the stack, recurse and pop off once recursing is done
							routeStack.push(n.getInEdges().get(i).getRoute());
							getRoutes(routeStack,routePath);
							routeStack.pop();
						}
					}
				}
				//push the node we popped off back onto the stack
				pathStack.push(n);
			}
		}

		/*
		 * This is the red-headed step child class. I'm not happy with how this was implemented, but this is the way that it is done.
		 * This class holds the LinkedList of routes that service our airport, as well as the order in which they must be visited.
		 * This also contains the methods to return total time and cost as well as what airline most services this airport. 
		 * 
		 */
		
		 class routePath{

			private Stack<Route> routeStack;
			private LinkedList<Route> routeList = new LinkedList<Route>();

			@SuppressWarnings("unchecked")
			//we'll once again only clone the stack so that we don't mess up our copy as we make changes
			public routePath(Stack<Route> nodeStack){
				this.routeStack=(Stack <Route>)nodeStack.clone();
				//turn it into a linked list, in reverse order just for easier reading
				for(int i=0;i<routeStack.size();i++){
					routeList.addFirst(routeStack.get(i));	
				}

			}

			public String toString() {
				// TODO Auto-generated method stub
				return routeList.toString();
			}
			
			//return our routeList
			public LinkedList<Route> getPath(){
				
				return routeList;
				
			}

			//get the total cost
			public double getCost(){
				double cost =0;
				for(int i=0;i<routeList.size();i++){
					cost+=routeList.get(i).getCost();

				}
				return cost;
			}

			//get the total time
			public double getTime(){
				double time=0;
				for(int i=0;i<routeList.size();i++){
					
					//this nonsense in terms of "piTime" is a result of me not realizing that mod 60 is a much better way to implement.
					//piTime is derived from the notion that an hour is 2pi revolutions around the clock, and a minute is pi/30 revolutions
					//around that same clock. This implementation was left for its novelty. 
					
					double arrivalMinutes = routeList.get(i).getArrivalTime()%10.0;
					double arrivalHours = (routeList.get(i).getArrivalTime()-arrivalMinutes)/100.0;
					double piArrivalHours = arrivalHours*Math.PI*2.0;
					double piArrivalMinutes = arrivalMinutes*Math.PI/30.0;
					
					double departureMinutes = routeList.get(i).getDepTime()%10.0;
					double departureHours = (routeList.get(i).getDepTime()-departureMinutes)/100.0;
					double piDepartureHours = departureHours*Math.PI*2.0;
					double piDepartureMinutes = departureMinutes*Math.PI/30.0;
					
					if(routeList.size()>i+1){
						
					
						double nextdepartureMinutes = routeList.get(i+1).getDepTime()%10.0;
						double nextdepartureHours = (routeList.get(i+1).getDepTime()-nextdepartureMinutes)/100.0;
						double nextpiDepartureHours = nextdepartureHours*Math.PI*2.0;
						double nextpiDepartureMinutes = nextdepartureMinutes*Math.PI/30.0;
						
						
						
						time+=(((piArrivalHours-piDepartureHours)+(piArrivalMinutes-piDepartureMinutes))+((nextpiDepartureHours -piArrivalHours)+(nextpiDepartureMinutes -piArrivalMinutes)))/(2.0*Math.PI);

					}else{

						time+=(((piArrivalHours+piArrivalMinutes)-(piDepartureHours+piDepartureMinutes))/(2.0*Math.PI));

					}

				}
				return time;
			}

			//return what airline the route uses the most
			public TreeMap<String,Integer> getAirlines(){

				TreeMap <String,Integer> uniqueAirlines = new TreeMap <String,Integer>();
				LinkedList <String> airlines = new LinkedList<String>();

				for(int i=0;i<routeList.size();i++){

					if(!uniqueAirlines.containsKey(routeList.get(i).getAirline())){

						uniqueAirlines.put(routeList.get(i).getAirline(),Integer.valueOf(0));

					}
					airlines.add(routeList.get(i).getAirline());
				}
				
				Integer count;
				
				for(int i=0;i<airlines.size();i++){
					
					count = uniqueAirlines.get(airlines.get(i));
					count++;
					uniqueAirlines.put(airlines.get(i), count);

					count=0;
					
					
					
				}
				//System.out.println(uniqueAirlines.toString()+"\n");
				return uniqueAirlines;
			}


		}
	}
	 
	 //get the path between the Origin and Destination
	public LinkedList<Path> getPath(Node Origin, Node Destination){
		Stack <Node> cheapestNodesStack = new Stack <Node>();
		LinkedList <Path> Path = new LinkedList<Path>();
		pathFind(Origin,Destination,cheapestNodesStack, Path);
		return Path;
	}

	//this method recreates the graph. Its here since the user can make changes to the system
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
		for(int i=0;i<airport.getInEdges().size();i++){

			//add to a linked list of all the routes associated with particular node
			Arrivals.add(airport.getInEdges().get(i).getRoute());

		} 
		return Arrivals;
	}

	//this method will return any route departing from a given node
	public LinkedList<Route> getDepartingRoutes(Node airport){

		LinkedList<Route> Departures = new LinkedList<Route>();

		//loop for all the outgoing edges associated with a node
		for(int i=0;i<airport.getOutEdges().size();i++){

			//return a linked list of all the routes associated
			Departures.add(airport.getOutEdges().get(i).getRoute());

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

//this method will find a path of nodes between two nodes, in the order in which they must be visited
	public void pathFind(Node Origin, Node Destination, Stack<Node> nodeStack, LinkedList <Path> path){


		LinkedList <Node> visibleNodes = new LinkedList<Node>();
		
		nodeStack.push(Origin);

		//if we have nothing in our node, we need to get out of the method
		if(nodeStack.size()==0){
			return;
		}
		//if our origin is the same as our destination, we know we have arrived, meaning we can create a new Path
		if(Origin.toString().matches(Destination.toString())){
			path.add(new Path(nodeStack));

		}else{
			//otherwise we need to get where we can go from where we are
			visibleNodes=getVisibleNodes(Origin,nodeStack);
			//for all the possibilities of visible nodes
			for(int i=0;i<visibleNodes.size();i++){
				//recurse
				pathFind(visibleNodes.get(i),Destination,nodeStack,path);

			}
		}
		//pop off the node
		nodeStack.pop();

	}

	//this method will return to us all nodes we can reach going forward from where we are
	public LinkedList <Node> getVisibleNodes(Node n,Stack<Node> nodeStack){

		LinkedList<Node> visibleNodesunSorted = new LinkedList<Node>();

		//for all the edges that come out of this node
		for(int i=0;i<n.getOutEdges().size();i++){
			//provided its not already in the stack
			if(!nodeStack.contains(n.getOutEdges().get(i).getTo())){
				//we'll add it to the unsorted list
				visibleNodesunSorted.add(n.getOutEdges().get(i).getTo());



			}

		}
		//cast it to a hashset and back to a linkedlist to remove duplicates
		return new LinkedList<Node>(new HashSet<Node>(visibleNodesunSorted));
	}

//returns a node. This is used to get the appropriate node in the GUI.
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