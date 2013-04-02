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
	
	LinkedList <Path> p;
	
    static class Path{
    	
    	public final LinkedList<Route> r;
    	
    	@SuppressWarnings("unchecked")
		public Path(LinkedList <Route> r){ 
    		this.r=(LinkedList<Route>)r.clone();
    
    	}
    	
    	public LinkedList<Route> getPath(){
    		
    		return r;
    		
    	}
    	
    	public double getCheapest(LinkedList<Path> p){
			
    		
    		return 0;
    		
    		
    	}
	
    }
    		

  public static void main(String[] args) {
	
	  LinkedList<Route> allRoutes;
	  ArrayList<Airport> airports;
	  FileInput f = null;
	  LinkedList<Route> a;
	  LinkedList <Path> path = new LinkedList<Path>();
	  
	  
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
    
   //a= getArrivingRoutes(allAirports.get(2));
   //System.out.println(a.getFirst().toString());
   
  pathFind(allAirports.get(0), allAirports.get(1));
   //System.out.println(path.isEmpty());
   
   System.out.println(path.size());
   if(!path.isEmpty()){
	   LinkedList <Route> r;
	   for(int i=0;i<path.size();i++){
		   
		  r= path.get(i).getPath();
		  System.out.println(r.size());
		  
		  for(int k=0;k<r.size();k++){
			  
			  System.out.println(r.get(k).toString());
		  }
	   }
	   
   }
     
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
 public static LinkedList<Node> createNodes(ArrayList<Airport> airports){
	 
	 LinkedList<Node> nodes = new LinkedList<Node>();
	 
	 //loop for all airports in the system
	 for(int i =0; i<airports.size();i++){
		 
		 //create a new node with the given code and add it to the list of nodes
		 nodes.add(new Node(airports.get(i).getCode()));
	 }
	 
	 return nodes;
	 
 }
 
 
 public static void pathFind(Node Origin, Node Destination){
	 
	 Stack <Node> nodeStack = new Stack <Node>();
	 LinkedList <Node> visibleNodes = new LinkedList<Node>();
	 
	 nodeStack.push(Origin);
	 
	if(nodeStack.size()==0){
		
		return;
		
	}
	
	if(Origin.toString().matches(Destination.toString())){
		System.out.println(nodeStack.size());
		System.out.println(nodeStack.toString());
		
		
	}else{
		
		visibleNodes=getVisibleNodes(Origin);
		
		for(int i=0;i<visibleNodes.size();i++){
			
			pathFind(visibleNodes.get(i),Destination);
			
		}
		
		
	}
	 nodeStack.pop();
 }
 
 
 /*
 public static void pathFind(Node Origin, Node Destination, LinkedList <Node> allAirports,LinkedList <Path> path){
		
	 //System.out.println("Origin: "+Origin.toString()+" \tDestination: "+ Destination.toString());
	 //LinkedList<Path> path;
	 //LinkedList<Route> destIncoming = getArrivingRoutes(Destination, allRoutes);
	 LinkedList<Route> orgOutgoing = getDepartingRoutes(Origin);
	 
	// this.path=(LinkedList<Path>)path.clone();
	 LinkedList <Route> r = new LinkedList<Route>();
	 Stack <Route> routeStack = new Stack <Route>();
	
	 System.out.println("Size of outgoing: " + orgOutgoing.size());
	 
	 //System.out.println(orgOutgoing.peekFirst().toString());
	 
	 if(orgOutgoing.size()==0){
			return;
			
		}

	 for(int i=0;i<orgOutgoing.size();i++){
			r.add(orgOutgoing.get(i));
			System.out.println("Size of LinkedList: "+r.size());
			routeStack.push(orgOutgoing.get(i));
			
				
			//System.out.println("Origin: "+orgOutgoing.get(i).getOrigin().toString());
			//System.out.println("Destination: "+orgOutgoing.get(i).getDestination().toString());
			
			
			
			if(Destination.toString().matches(orgOutgoing.get(i).getDestination().toString())){
				
				System.out.println("Size of r "+r.size());
				  path.add(new Path(r));
				 System.out.println(routeStack.toString());
				  
			}
			else{
				
				for(int k=0;k<orgOutgoing.size();k++){
					//System.out.println("K "+ k);
					for(int u=0;u<allAirports.size();u++){
						//System.out.println("U "+u);
						if(allAirports.get(u).toString().matches(orgOutgoing.get(k).getDestination().toString())){
					//System.out.println(orgOutgoing.get(k).getDestination().toString());
					//System.out.println(Destination.toString());

					//System.out.println("New Origin: "+orgOutgoing.get(k).getDestination().toString());
					//pathFind(allAirports.get(u),Destination, allAirports,path);
						}
					
					}
				}
				
			}
					
			routeStack.pop();
			r.removeLast();
			//System.out.println("Size of paths: "+r.size());	
	 		
	 
			}
	 //return path; 

	 } 
 */
 public static LinkedList <Node> getVisibleNodes(Node n){
	 
	 LinkedList<Node> visibleNodesunSorted = new LinkedList<Node>();
	 
	 for(int i=0;i<n.getInEdges().size();i++){
		 
		 visibleNodesunSorted.add(n.getInEdges().get(i).getFrom());
		 
	 }
	 
	 for(int i=0;i<n.getOutEdges().size();i++){
		 
		 visibleNodesunSorted.add(n.getOutEdges().get(i).getTo());
		 
	 }
	 
	 LinkedList <Node> visibleNodes = new LinkedList<Node>(new HashSet<Node>(visibleNodesunSorted));
	 
	 return visibleNodes;
	 
 }
 
}