import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Credit should be given to Ben O'Brien and Max Dewees for their help in steering us on the right path
 * 
 * This class creates nodes and edges that will be used for searching. The nodes are created from the Airports and
 * the edges are the Routes. The methods in this class will automatically generate Nodes and Edges based on the input
 * file. This class implements the depth first tree traversal searching ideology. 
 * 
 * 
 * 
 * 
 */


public class Graph {

	
//class for the nodes. 	
  static class Node{
	
	
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
    public Node addEdge(Node node,int RouteNumber){
      Edge e = new Edge(this, node,RouteNumber);
      outEdges.add(e);
      node.inEdges.add(e);
      return this;
    }
    
    //returns the airport code
    public String toString() {
        return AirportCode;
      }

    
  }
  
  //static class Path
  /*
   * In essence, this class should create a path between two airports. It can then filter based on cost and time, etc.
   * This is just a thought. Time will tell how well this is implemented. 
   * 
   */

  //this class generates the routes between each node
  static class Edge{
    public final Node from;
    public final Node to;
    public int RouteNumber;
    
    //the constructor requires a to and from node as well as an identifier in the means of a route number
    public Edge(Node from, Node to, int RouteNumber) {
      this.from = from;
      this.to = to;
      this.RouteNumber = RouteNumber;
    }
    @Override
    public boolean equals(Object obj) {
      Edge e = (Edge)obj;
      return e.from == from && e.to == to;
    }
    
    
    public int getRouteNumber(){
    	
    	return RouteNumber;
    	
    }
    
  }

  public static void main(String[] args) {
	
	  LinkedList<Route> allRoutes;
	  ArrayList<Airport> airports;
	  FileInput f = null;
	  Route cheapestCost;
	  
	  try {
			f = new FileInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      allRoutes = f.getRoutes();
	      airports = f.getAirports();
	  
	  
	LinkedList<Node> allAirports = new LinkedList<Node>();
	
	allAirports = createNodes(airports);
	addRoutes(allAirports,allRoutes);
    
    getArrivingRoutes(allAirports.getFirst(), allRoutes);
    /*
	cheapestCost = getCheapest(allAirports, allRoutes, allAirports.get(2), allAirports.get(1));
	if(cheapestCost.getNumber()==-1){
		
		System.out.println("No connections were found!");
	}
	else{
	System.out.println(cheapestCost.toString());
	}
	*/
   
  }
  
  //this method will automatically generate any route associated with each node. 
  public static void addRoutes(LinkedList<Node> airport, LinkedList<Route> routes){
	  
	  Node n = null;
	  
	  //this loops for every element in the node linked list
	  for(int k=0;k<airport.size();k++){
	  
		  //this loops for every element of the route linked list
		  for(int i=0;i<routes.size();i++){

			  //this will only go into this portion if the node is the same as the destination node
			  if(airport.get(k).toString().matches(routes.get(i).getDestination())){
							  
				  //this iterates for every element in the node linked list
				  for(int u=0;u<airport.size();u++){
					  
					  //this will only execute this portion if the node is the same as the origin
					  if(airport.get(u).toString().matches(routes.get(i).getOrigin())){
						  
						  n=airport.get(u);
						  
					  }
					  
				  }
				  //code then adds a route between origin and the destination
				  n.addEdge(airport.get(k), routes.get(i).getNumber());
		  
			  }
	  			
		  }
		  
	  }
  }
  
//this method will return any arriving routes into a given node
  public static LinkedList<Route> getArrivingRoutes(Node airport, LinkedList<Route> routes){
	
	  	LinkedList<Route> Arrivals = new LinkedList<Route>();
	  	int routeNum;
	  	int k;
	  	
	  //loops for every incoming route associated with that airport	
	 for(int i=0;i<airport.inEdges.size();i++){
		 
		routeNum =airport.inEdges.get(i).getRouteNumber();

		//loops for all the routes in the system
		for(k=0;k<routes.size();k++){
			
				//if the route number from the original incoming matches the route just found, it must be an incoming for this airport
				if(routeNum==routes.get(k).getNumber()){
				//System.out.println(routes.get(k).toString());
				Arrivals.add(routes.get(k)); //add them to the linked list
				
			}
			
		}
		 
	 } 
	  return Arrivals;
  }
  
  //this method will return all the routes leaving a particular node
  public static LinkedList<Route> getDepartingRoutes(Node airport, LinkedList<Route> routes){
		
	  	LinkedList<Route> Departures = new LinkedList<Route>();
	  	int routeNum;
	  	int k;
	 
	  	//loop for all the outgoing edges associated with a node
	 for(int i=0;i<airport.outEdges.size();i++){
		routeNum =airport.outEdges.get(i).getRouteNumber();

		//loop for all the routes in the system
		for(k=0;k<routes.size();k++){
			
			// if the route selected in the outermost loop matches this node, it must be a departing node
				if(routeNum==routes.get(k).getNumber()){
				//System.out.println(routes.get(k).toString());
				Departures.add(routes.get(k)); //add it to the linked list
				
			}
			
		}
		 
	 } 
	  return Departures;
}
  
  //this method will automatically create nodes for all the airports in the system
 public static LinkedList<Node> createNodes(ArrayList<Airport> airports){
	 
	 LinkedList<Node> nodes = new LinkedList<Node>();
	 
	 //loop for the whole array
	 for(int i =0; i<airports.size();i++){
		 
		 //create a new node with the given code and add it to the list of nodes
		 nodes.add(new Node(airports.get(i).getCode()));
	 }
	 
	 return nodes;
	 
 }
 
 /*
 
 public static Route getCheapest(LinkedList<Node> allAirports, LinkedList<Route> allRoutes, Node Origin, Node Destination){
	 
	LinkedList<Route> destIncoming = getArrivingRoutes(Destination, allRoutes);
	LinkedList<Route> orgOutgoing = getDepartingRoutes(Origin, allRoutes);
	System.out.println("Origin "+Origin.toString());
	System.out.println("Destination "+Destination.toString());
	LinkedList<Route> Routes = null;
	Route leastCost = new Route(-1, "YES", "Dummy", 10, "Dummy", 10, 1000000000);
	Route tempRoute;
	String tempDest;
	String tempOrg;
	
	for(int i=0;i<destIncoming.size();i++){
		
		for(int k=0;k<orgOutgoing.size();k++){
			
			if(Destination.toString().matches(orgOutgoing.get(k).getDestination())){
				
				tempRoute = orgOutgoing.get(k);
				
				if(leastCost.getCost()>=tempRoute.getCost()){
					leastCost=tempRoute;					
				}
				
			}else{
				
				if(destIncoming.get(i).getOrigin().matches(orgOutgoing.get(k).getDestination())){
					for(int u=0;u<allAirports.size();u++){
						
						if(allAirports.get(u).toString().matches(Destination.toString())){
							tempDest=allAirports.get(u);
						}
						
					}
				tempRoute=getCheapest(allAirports, allRoutes, allAirports., orgOutgoing.get(k));
				System.out.println(tempRoute.toString());
				
				}
				
			}
			
		}
		
		
		
	}
	 
	 
	 
	 return leastCost;
	 
	 
 }
   
  */
}