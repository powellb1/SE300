import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

	
	
  static class Node{
	
	  FileInput f = null;
	
	  
	  
    public final String AirportCode;
    public final LinkedList<Edge> inEdges;
    public final LinkedList<Edge> outEdges;
    
    public Node(String AirportCode) {
      this.AirportCode = AirportCode;
      inEdges = new LinkedList<Edge>();
      outEdges = new LinkedList<Edge>();
    }
    public Node addEdge(Node node,String RouteNumber){
      Edge e = new Edge(this, node,RouteNumber);
      outEdges.add(e);
      node.inEdges.add(e);
      return this;
    }
    
    public String toString() {
        return AirportCode;
      }
    
  }

  static class Edge{
    public final Node from;
    public final Node to;
    public Edge(Node from, Node to, String RouteNumber) {
      this.from = from;
      this.to = to;
    }
    @Override
    public boolean equals(Object obj) {
      Edge e = (Edge)obj;
      return e.from == from && e.to == to;
    }
  }

  public static void main(String[] args) {
    Node JFK = new Node("JFK");
    Node DCA = new Node("DCA");
    Node ORL = new Node("ORL");
    Node ATL = new Node("ATL");

   ATL.addEdge(ORL, "12");
   ATL.addEdge(ORL, "13");
   ORL.addEdge(ATL, "4");
   ORL.addEdge(ATL, "5");
   DCA.addEdge(ORL, "14");
   DCA.addEdge(ORL, "15");
   ORL.addEdge(DCA, "7");
   ORL.addEdge(DCA, "6");
   JFK.addEdge(ATL, "9");
   JFK.addEdge(ORL, "11");
   JFK.addEdge(ATL, "10");
   JFK.addEdge(DCA, "16");
   ATL.addEdge(JFK, "1");
   ATL.addEdge(JFK, "2");
   DCA.addEdge(JFK, "8");
   ORL.addEdge(JFK, "3");
   
   

    Node[] allNodes = {ATL,ORL,DCA,JFK};
    //L <- Empty list that will contain the sorted elements
    ArrayList<Node> L = new ArrayList<Node>();

    //S <- Set of all nodes with no incoming edges
    LinkedList<Node> S = new LinkedList<Node>(); 
    for(Node n : allNodes){
      if(n.inEdges.size() == 0){
        S.add(n);
      }
    }

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
    /*
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
    */
      System.out.println("Topological Sort: "+Arrays.toString(L.toArray()));
    }
  }
//}