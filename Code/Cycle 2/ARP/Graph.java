import java.io.IOException;
import java.util.ArrayList;
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
	//static LinkedList <Route> allRoutes;
	Director d;

	public Graph(Director d){

		this.d=d;

	}


	static class Path{

		private  Stack<Node> pathStack;
		private  LinkedList<Route> route = new LinkedList<Route>();
		private LinkedList<Route> cheapest = new LinkedList<Route>();
		double cost=0;
		private Stack<Route> routeStack;

		@SuppressWarnings("unchecked")
		public Path(Stack <Node> nodeStack){ 
			this.pathStack=(Stack <Node>)nodeStack.clone();
			//System.out.println("Path's nodeStack: " + nodeStack.toString());
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return nodeStack.toString();
		}


		public void getRoutes(Stack<Route> routeStack,LinkedList<routePath> routePath){

			if(pathStack.size()<=1){
				routePath.add(new routePath(routeStack));
				//System.out.println(routeStack.toString());
				return;


			}else{
				Node n =pathStack.pop();
				Node next = pathStack.peek();



				for(int i=0;i<n.getInEdges().size();i++){

					if(n.getInEdges().get(i).getFrom().toString().matches(next.toString())){
						routeStack.push(n.getInEdges().get(i).getRoute());
						getRoutes(routeStack,routePath);
						routeStack.pop();

					}

				}

				
			}

		}
		
		
		
		
		static class routePath{
			
			private Stack<Route> routeStack;
			private LinkedList<Route> routeList = new LinkedList<Route>();
			
			@SuppressWarnings("unchecked")
			public routePath(Stack<Route> nodeStack){
				this.routeStack=(Stack <Route>)nodeStack.clone();
				for(int i=0;i<routeStack.size();i++){
					routeList.addLast(routeStack.get(i));
					
				}
				System.out.println(routeList.toString());
				
			}
			
			public String toString() {
				// TODO Auto-generated method stub
				return routeList.toString();
			}
			
		}
	}
	/*
		public LinkedList<Route> getRoutes(){

			Node n = pathStack.pop();
			Node next = pathStack.peek();

			while(!pathStack.isEmpty()){

				for(int k=0;k<n.getInEdges().size();k++){

					if(n.getInEdges().get(k).getFrom().toString().matches(next.toString())){
							//System.out.println(n.getInEdges().get(k).getRoute().toString());
							//System.out.println(next.toString());
						if(route.size()>=1){
							//System.out.println(n.getInEdges().get(k).getRoute().toString());
							//if(!route.getFirst().getOrigin().toString().matches(n.getInEdges().get(k).getRoute().getOrigin().toString())){


								if((route.getFirst().getDepTime()-n.getInEdges().get(k).getRoute().getArrivalTime())>30){
									//System.out.println(n.getInEdges().get(k).getRoute().toString());
									route.addFirst(n.getInEdges().get(k).getRoute());

								}
							//}else{

								//route.addFirst(n.getInEdges().get(k).getRoute());

							//}

						}else{
							//System.out.println(n.getInEdges().get(k).getRoute().toString());
							route.addFirst(n.getInEdges().get(k).getRoute());

						}

					}

				}

				if(pathStack.size()<=1){
					break;
				}else{
					n=pathStack.pop();
					next=pathStack.peek();

				}
			}


			/*
			for(i=0;i<route.size();i++){

				if(i+1<route.size()){
					if(route.get(i+1).getArrivalTime()-route.get(i).getDepTime()<30&&!route.get(i+1).getOrigin().toString().matches()){
						System.out.println(route.get(i+1).getArrivalTime()-route.get(i).getDepTime());
						route.remove(i);
						i--;
					}

				}

			}





			return route;
		}


	}
	
	
	public LinkedList <Route> getCost(){

			for(int k=0;k<route.size();k++){
				if(k+1<route.size()){
					if(route.get(k).getOrigin().toString().matches(route.get(k+1).getOrigin().toString())&&route.get(k).getDestination().toString().matches(route.get(k+1).getDestination().toString())&&route.get(k).getCost()<cost){

						cost=route.get(k).getCost();
						cheapest.add(route.get(k));

					}
					else{
						cost+=route.get(k).getCost();
						cheapest.add(route.get(k));

					}

				}else if(route.size()>1){
					if(route.get(k).getOrigin().toString().matches(route.get(k-1).getOrigin().toString())&&route.get(k).getDestination().toString().matches(route.get(k-1).getDestination().toString())&&route.get(k).getCost()<cost){

						cost=route.get(k).getCost();
						cheapest.remove(route.get(k-1));
						cheapest.add(route.get(k));

					}
					/*else{
						cost+=route.get(k).getCost();
						cheapest.add(route.get(k));

					}
					 
				}

			}
			return cheapest;
		}

	 */


	public static void main(String[] args) {

		LinkedList<Route> allRoutes;
		LinkedList<Airport> airports;
		FileInput f = null;
		//LinkedList<Route> a;
		//LinkedList <Path> path = new LinkedList<Path>();

		//LinkedList<Route> r;


		try {
			f = new FileInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allRoutes = f.getRoutes();
		airports = f.getAirports();

		LinkedList<Node> allAirports = new LinkedList<Node>();

		//System.out.println(allRoutes.get(18).getAirline());
		allAirports = createNodes(airports);
		addRoutes(allAirports,allRoutes);
		getCheapest(allAirports.get(0), allAirports.get(3));



		//System.out.println(r.toString());




	}

	public static LinkedList<Route> getCheapest(Node Origin, Node Destination){
		Stack <Node> cheapestNodesStack = new Stack <Node>();
		LinkedList <Path> cheapestPaths = new LinkedList<Path>();
		//LinkedList<Route> associatedRoutes = new LinkedList<Route>();
		Stack <Route> routeStack = new Stack<Route>();
		LinkedList <Route> r = new LinkedList<Route>();
		LinkedList<Graph.Path.routePath> rP = new LinkedList<Graph.Path.routePath>();
		pathFind(Origin,Destination,cheapestNodesStack, cheapestPaths);

		
		for(int i=0;i<cheapestPaths.size();i++){
		
			System.out.println(cheapestPaths.get(i).toString());
			cheapestPaths.get(i).getRoutes(routeStack,rP);
			
		
		
		}
		
		/*

		LinkedList<Route> rTemp = new LinkedList<Route>();
		double cost=999999999;
		double tempCost=0;
		int tempI;
		int index=0;

		for(int i=0;i<cheapestPaths.size();i++){

			//System.out.println(cheapestPaths.get(i).getRoutes().toString());
			rTemp=cheapestPaths.get(i).getCost();

			if(!rTemp.isEmpty()){	
				for(int k=0;k<rTemp.size();k++){

					tempCost+=rTemp.get(k).getCost();

				}
				System.out.println(tempCost+"\n");
				if(tempCost<cost){
					cost=tempCost;
					r=rTemp;
				}
				tempCost=0;
			}
		}
		cheapestNodesStack.clear();
		if(r.isEmpty()){
			System.out.println("No available route was found!");

		}
		System.out.println(r.toString());
		 */
		return r;

	}

	public void draw(){

		allAirports=createNodes(d.getAirports());
		addRoutes(allAirports,d.getAllRoutes());

	}


	//this method will automatically generate any route associated with each node. 
	public static void addRoutes(LinkedList<Node> airport, LinkedList<Route> routes){

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
	public static LinkedList <Route> getArrivingRoutes(Node airport){

		LinkedList<Route> Arrivals = new LinkedList<Route>();

		//loops for every incoming route associated with that airport	
		for(int i=0;i<airport.inEdges.size();i++){

			//add to a linked list of all the routes associated with particular node
			Arrivals.add(airport.inEdges.get(i).getRoute());

		} 
		return Arrivals;
	}

	//this method will return any route departing from a given node
	public static LinkedList<Route> getDepartingRoutes(Node airport){

		LinkedList<Route> Departures = new LinkedList<Route>();

		//loop for all the outgoing edges associated with a node
		for(int i=0;i<airport.outEdges.size();i++){

			//return a linked list of all the routes associated
			Departures.add(airport.outEdges.get(i).getRoute());

		} 
		return Departures;
	}

	//this method will automatically create nodes for all the airports in the system
	public static LinkedList<Node> createNodes(LinkedList<Airport> airports){

		LinkedList<Node> nodes = new LinkedList<Node>();

		//loop for all airports in the system
		for(int i =0; i<airports.size();i++){

			//create a new node with the given code and add it to the list of nodes
			nodes.add(new Node(airports.get(i).getCode()));
		}

		return nodes;

	}


	public static void pathFind(Node Origin, Node Destination, Stack<Node> nodeStack, LinkedList <Path> path){

		//System.out.println(associatedRoutes.toString());
		LinkedList <Node> visibleNodes = new LinkedList<Node>();
		//Node n;
		nodeStack.push(Origin);

		if(nodeStack.size()==0){
			return;
		}

		if(Origin.toString().matches(Destination.toString())){
			path.add(new Path(nodeStack));

			//System.out.println("Nodestack when the destination is equal: "+nodeStack.toString());

		}else{
			visibleNodes=getVisibleNodes(Origin,nodeStack);

			for(int i=0;i<visibleNodes.size();i++){
				//System.out.println(nodeStack.toString());
				pathFind(visibleNodes.get(i),Destination,nodeStack,path);

			}
		}
		nodeStack.pop();

	}

	public static LinkedList <Node> getVisibleNodes(Node n,Stack<Node> nodeStack){

		LinkedList<Node> visibleNodesunSorted = new LinkedList<Node>();
		//LinkedList<Route> temp = new LinkedList<Route>();
		for(int i=0;i<n.getOutEdges().size();i++){

			if(!nodeStack.contains(n.getOutEdges().get(i).getTo())){

				visibleNodesunSorted.add(n.getOutEdges().get(i).getTo());



			}

		}
		//temp = new LinkedList<Route>(new HashSet<Route>(associatedRoutes));
		//associatedRoutes=temp;

		//System.out.println("Associated routes: "+associatedRoutes.toString());
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