import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

	
	
  static class Node{
	
	FileInput f;  
    public final String AirportCode;
    public final LinkedList<Edge> inEdges;
    public final LinkedList<Edge> outEdges;
    public final LinkedList<Route> routes;
   // public final LinkedList<Route> Departures;
   // public final LinkedList<Route> Arrivals;
    
    public Node(String AirportCode) {
      this.AirportCode = AirportCode;
      inEdges = new LinkedList<Edge>();
      outEdges = new LinkedList<Edge>();
      try {
		f = new FileInput();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      routes = f.getRoutes();
      
      /*
      Departures=getDepartingRoutes(this, routes);
      Arrivals =getArrivingRoutes(this, routes);
      */
    }
    public Node addEdge(Node node,int RouteNumber){
      Edge e = new Edge(this, node,RouteNumber);
      outEdges.add(e);
      node.inEdges.add(e);
      return this;
    }
    
    public String toString() {
        return AirportCode;
      }
    /*
    public LinkedList <Route> getDepartures(){
    	return Departures;
    }
    public LinkedList<Route> getArrivals(){
    	return Arrivals;
    }
    */
    
  }

  static class Edge{
    public final Node from;
    public final Node to;
    public int RouteNumber;
    
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
	  
	  
    Node JFK = new Node("JFK");
    Node DCA = new Node("DCA");
    Node ORL = new Node("ORL");
    Node ATL = new Node("ATL");

 
    Node[] allNodes = {ATL,ORL,DCA,JFK};
    
    addRoutes(allNodes,ATL.routes);
    
   // getArrivingRoutes(ATL, ATL.routes);
    //getDepartingRoutes(ATL, ATL.routes);
    getArrivingRoutes(ORL, ORL.routes);
    //L <- Empty list that will contain the sorted elements
    ArrayList<Node> L = new ArrayList<Node>();

    //S <- Set of all nodes with no incoming edges
    LinkedList<Node> S = new LinkedList<Node>(); 
    for(Node n : allNodes){
      if(n.inEdges.size() == 0){
        S.add(n);
      }
    }
    
  }
  
  public static void addRoutes(Node[] airport, LinkedList<Route> routes){
	  
	  Node n = null;
	  
	  for(int k=0;k<airport.length;k++){
	  
		  for(int i=0;i<routes.size();i++){
	  
			  if(airport[k].toString().matches(routes.get(i).getDestination())){
				  
				  for(int u=0;u<airport.length;u++){
					  
					  if(airport[u].toString().matches(routes.get(i).getOrigin())){
						  
						  n=airport[u];
						  
					  }
					  
				  }
				  n.addEdge(airport[k], routes.get(i).getNumber());
		  
			  }
	  			
		  }
		  
	  }
  }
  

  public static LinkedList<Route> getArrivingRoutes(Node airport, LinkedList<Route> routes){
	
	  	LinkedList<Route> Arrivals = new LinkedList<Route>();
	  	int routeNum;
	  	int k;
	  	
	 for(int i=0;i<airport.inEdges.size();i++){
		 
		routeNum =airport.inEdges.get(i).getRouteNumber();

		for(k=0;k<routes.size();k++){
			
				if(routeNum==routes.get(k).getNumber()){
				System.out.println(routes.get(k).toString());
				Arrivals.add(routes.get(k));
				
			}
			
		}
		 
	 } 
	  return Arrivals;
  }
  
  public static LinkedList<Route> getDepartingRoutes(Node airport, LinkedList<Route> routes){
		
	  	LinkedList<Route> Departures = new LinkedList<Route>();
	  	int routeNum;
	  	int k;
	  	
	 for(int i=0;i<airport.outEdges.size();i++){
		routeNum =airport.outEdges.get(i).getRouteNumber();

		for(k=0;k<routes.size();k++){
			
				if(routeNum==routes.get(k).getNumber()){
				System.out.println(routes.get(k).toString());
				Departures.add(routes.get(k));
				
			}
			
		}
		 
	 } 
	  return Departures;
}
  
  
  
    /*
    //while S is non-empty do
    while(!S.isEmpty()){
      //remove a node n from S
      Node n = S.iterator().next();
      S.remove(n);

      //insert n into L
      L.add(n);

      //for each node m with an edge e from n to m do
      for(Iterator<Edge> it = n.outEdges.iterator();it.hasNext();){
        //remove edge e from the graph
        Edge e = it.next();
        Node m = e.to;
        it.remove();//Remove edge from n
        m.inEdges.remove(e);//Remove edge from m

        //if m has no other incoming edges then insert m into S
        if(m.inEdges.isEmpty()){
          S.add(m);
        }
      }
    }
    
    //Check to see if all edges are removed
    boolean cycle = false;
    for(Node n : allNodes){
      if(!n.inEdges.isEmpty()){
        cycle = true;
        break;
      }
    }
    
    if(cycle){
      System.out.println("Cycle present, topological sort not possible");
    }else{
    
      System.out.println("Topological Sort: "+Arrays.toString(L.toArray()));
    }
    */
  
  
}