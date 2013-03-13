
import java.io.*;
import java.util.Scanner;

public class DepthFirst {
    static int numNodes;
    static GraphNode nodes[];
    
    public static void main(String[] args) throws IOException {
       GraphNode startNode = new GraphNode();
       readGraph();
       startNode = nodes[0];
       depthFirstSearch(startNode); }

    public static void depthFirstSearch(GraphNode startNode) {
        int       stackCount, lastDfLabel = 0; stackCount = 1;
                  
        GraphNode currentNode, nextNode = null,
                  stack[] = new GraphNode[numNodes];
        
        stack[0] = startNode;
        
        System.out.print("startNode = " + startNode.nodeNumber + ", ");
        
        while (stackCount > 0) {
            currentNode = stack[stackCount-1];
            if (0 == currentNode.dfLabel){
                lastDfLabel = lastDfLabel+1; 
                currentNode.dfLabel = lastDfLabel;
                System.out.println("dfLabel(" + currentNode.nodeNumber + ") = " + currentNode.dfLabel + " ");
            }
            if (currentNode.nextToVisit == currentNode.degree){ 
                System.out.println("backtracking " + currentNode.nodeNumber + " -> " + currentNode.parent);
                stack[stackCount--] = null;}
            else{
                  System.out.print("processing (" + currentNode.nodeNumber + "," + currentNode.adjList[currentNode.nextToVisit] + ") ");          
                  nextNode = nodes[currentNode.adjList[currentNode.nextToVisit]];
                  currentNode.nextToVisit = currentNode.UpdateNext();
                  
                  if(0 == nextNode.dfLabel){            
                        System.out.print(" tree-edge, ");
                        nextNode.parent = currentNode.nodeNumber;
                        stack[stackCount] = nextNode;
                        stackCount++;
                  }
                  else if(nextNode.dfLabel < currentNode.dfLabel){
                         if(nextNode.nodeNumber != currentNode.parent)
                                 System.out.println(" back-edge");
                         else
                                 System.out.println(" tree-edge, 2nd visit");
                  }
                  else{ System.out.println(" back-edge, 2nd visit");}
                  
            }}
        }           
                     
        //loads graph data from file
    public static void readGraph() throws IOException {
        File input = new File("digraph.data");
        Scanner digraph = new Scanner(input).useDelimiter("\\D+");
        
                //if file is not empty
        if (digraph.hasNext()) {
            int nodeNum;
            numNodes = digraph.nextInt(); //System.out.println(numNodes);
            nodes = new GraphNode[numNodes];
            while (digraph.hasNext()) {
                nodeNum = digraph.nextInt();
                nodes[nodeNum] = new GraphNode(nodeNum, digraph.nextInt());
                for (int i = 0; i < nodes[nodeNum].getDegree(); i++) 
                    nodes[nodeNum].setAdjListIndex(digraph.nextInt(), i); 
            } 
        }
        else return; 
    } 
}