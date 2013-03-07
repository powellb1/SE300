public class GraphNode {
                int parent, degree, nextToVisit, nodeNumber, dfLabel, adjList[];
            
            //default constructor
            public GraphNode() {
                parent = dfLabel = 0;
                adjList = null; }
            
            //constructor
            public GraphNode(int nameOrNumber, int Degrees) {
                nodeNumber = nameOrNumber;
                degree = Degrees;
                adjList = new int[degree];
                }
            
            //get-accessor methods
            public int getDegree() {
                return degree; }  
            public int getNextToVisit(){
                return nextToVisit;}
            public int UpdateNext(){
                if(nextToVisit < degree)
                return nextToVisit+1;
                else
                return 0;
            }
            public int getAdjListIndex(int index) {
                return adjList[index]; }
            
            //set-accessor methods
            public void setAdjListIndex(int adjNode, int index) {
                adjList[index] = adjNode; }
        }
