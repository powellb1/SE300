import java.util.HashSet;

/*
 * This class is used as a basis for our Nodes in the graph.
 * Each object will have a name, as well as a list of closures. 
 */

public class Airport {

	private int beginDiff;
	private int endDiff;
	private int closeBegin;
	private int closeEnd;
	private int openBegin;
	private int openEnd;
	private String name;
	private HashSet<Integer> closeBeginList= new HashSet<Integer>();

	private HashSet<Integer> closeEndList= new HashSet<Integer>(); 

	public Airport(String name,Integer closeBegin, Integer closeEnd){
	
	
		//this.open = open;
		this.name = name;

		closeBeginList.add(closeBegin);
		closeEndList.add(closeEnd);

	}
	public Airport(String name){
		
		this.name=name;
		
	}

	//getters and setters. Nothing unusual here. 
	
	public String toString(){

		return name;
	}

	public String getCode(){

		return name;
	}

	public HashSet<Integer> getCloseBegin() {
		return closeBeginList;
	}

	public void setCloseBegin(int closeBegin) {
		closeBeginList.add(closeBegin);
		this.closeBegin = closeBegin;
	}

	public HashSet<Integer> getCloseEnd() {
		return closeEndList;
	}

	public void setCloseEnd(int closeEnd) {
		closeEndList.add(closeEnd);
		this.closeEnd = closeEnd;
	}

	public void setOpen(int openBegin, int openEnd){
		
		beginDiff = openBegin - closeBegin;
		
		endDiff = closeEnd - openEnd;
		
		closeBeginList.clear();
		closeEndList.clear();
		
		if(beginDiff < 0){
			this.beginDiff = 0;
			closeBeginList.clear();
		}
		
		if (beginDiff > 0){
			closeBeginList.add(closeBegin);
			closeEndList.add(openBegin);
		}
		
		if(endDiff < 0){
			this.endDiff = 0;
			closeEndList.clear();
		}
		
		if(endDiff > 0){
			closeBeginList.add(openEnd);
			closeEndList.add(closeEnd);
		}
		
	}
	
	
		
		


}