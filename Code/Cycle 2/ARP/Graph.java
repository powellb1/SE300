import java.io.IOException;
import java.util.ArrayList;
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

	/*
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
    public Node addEdge(Node node,Route r){
      Edge e = new Edge(this, node,r);
      outEdges.add(e);
      node.inEdges.add(e);
      return this;
    }
    
    //returns the airport code
    public String toString() {
        return AirportCode;
      }

    
  }
  

  //this class generates the routes between each node
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

  }
    */
  
    static class Path{
    	public final LinkedList <Route> r;
    	
    	
    	public Path(LinkedList <Route> r){ 
    		this.r=r;
    		
    		
    	}
    
    	    	
    	
    }
    		
    		
    	
    	
    	
  
    	
    	
    
    
    
  

  public static void main(String[] args) {
	
	  LinkedList<Route> allRoutes;
	  ArrayList<Airport> airports;
	  FileInput f = null;
	  LinkedList<Route> a;
	  LinkedList <Path> path;
	
	  
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
    
   a= getArrivingRoutes(allAirports.get(2));
   System.out.println(a.getFirst().toString());
   
   path = getPath(allAirports.get(0), allAirports.get(4));
   System.out.println(path.isEmpty());
     
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
  
//this method iwll return any route departing from a given node
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
 public static LinkedList<Node> createNodes(ArrayList<Airport> airports){
	 
	 LinkedList<Node> nodes = new LinkedList<Node>();
	 
	 //loop for all airports in the system
	 for(int i =0; i<airports.size();i++){
		 
		 //create a new node with the given code and add it to the list of nodes
		 nodes.add(new Node(airports.get(i).getCode()));
	 }
	 
	 return nodes;
	 
 }
 
 public static LinkedList <Path> getPath(Node Origin, Node Destination){
		
	 System.out.println("Origin: "+Origin.toString()+" \tDestination: "+ Destination.toString());
	 
	 //LinkedList<Route> destIncoming = getArrivingRoutes(Destination, allRoutes);
	 LinkedList<Route> orgOutgoing = getDepartingRoutes(Origin);
	 LinkedList<Path> path = new LinkedList<Path>();
	 LinkedList <Route> r = new LinkedList<Route>();
	 Stack <Route> routeStack = new Stack <Route>();
	 
	 for(int i=0;i<orgOutgoing.size();i++){
			routeStack.push(orgOutgoing.get(i));
			
			if(Destination.toString().matches(orgOutgoing.get(i).getDestination().toString())){
				r.add(routeStack.firstElement());
				//System.out.println(r.getFirst().toString());
				path.add(new Path(r));
			}
			else{
				
				for(int k=0;k<orgOutgoing.size();k++){
					
					//System.out.println(orgOutgoing.get(k).getDestination().toString());
					//System.out.println(Destination.toString());
					System.out.println("K: "+k);
					getPath(orgOutgoing.get(k).getOrigin(),Destination);
					
				}
				
			}
					
			routeStack.pop();
				
	 		
	 
			}
	 return path;
	 }
	 
	 
 
 /*
 //this method will generate paths between 
  public static LinkedList<Route> getPath(Node Origin, Node Destination, LinkedList<Route> allRoutes){
	 
	 LinkedList<Route> destIncoming = getArrivingRoutes(Destination, allRoutes);
	 LinkedList<Route> orgOutgoing = getDepartingRoutes(Origin, allRoutes);
	 LinkedList<Route> path = new LinkedList<Route>();
	 
	 for(int i=0;i<destIncoming.size();i++){
			
			for(int k=0;k<orgOutgoing.size();k++){
				
				if(Destination.toString().matches(orgOutgoing.get(k).getDestination())){
					
					path.add(orgOutgoing.get(k));
					System.out.println("Direct route found.");
					System.out.println(orgOutgoing.get(k).toString());

				}else{
					if(orgOutgoing.get(k).getDestination().matches(destIncoming.get(i).getOrigin())){
						
						if(destIncoming.get(i).getDepTime()-orgOutgoing.get(k).getArrivalTime()>30){
							
						System.out.println("Intermediate Route found!");
						System.out.println(orgOutgoing.get(k).toString());
						path.add(orgOutgoing.get(k));
						path.add(destIncoming.get(i));
						
						System.out.println(destIncoming.get(i).toString());
						}
						
					}
					
				}
			}
	 }
	 
	 
	 return path;
	 
 }
  */
  
 
}