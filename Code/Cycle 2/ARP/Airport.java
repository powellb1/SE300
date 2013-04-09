import java.util.LinkedList;


public class Airport {

	private String name;
	private static LinkedList<Integer> closeBeginList = new LinkedList<Integer>();
	private static LinkedList<Integer> closeEndList = new LinkedList<Integer>();

	public Airport(String name,Integer closeBegin, Integer closeEnd){

		//this.open = open;
		this.name = name;
		closeBeginList.add(closeBegin);
		closeEndList.add(closeEnd);

	}

	public String toString(){

		return name;
	}

	public String getCode(){

		return name;
	}

	public LinkedList<Integer> getCloseBegin() {
		return closeBeginList;
	}

	public void setCloseBegin(int closeBegin) {
		closeBeginList.add(closeBegin);
	}

	public LinkedList<Integer> getCloseEnd() {
		return closeEndList;
	}

	public void setCloseEnd(int closeEnd) {
		closeEndList.add(closeEnd);
	}



}
