
public class Airport {

	private String name;
	private int closeBegin;
	private int closeEnd;

	public Airport(String name,int closeBegin, int closeEnd){

		//this.open = open;
		this.name = name;
		this.closeBegin = closeBegin;
		this.closeEnd=closeEnd;

	}

	public String toString(){

		return name;
	}

	public String getCode(){

		return name;
	}

	public int getCloseBegin() {
		return closeBegin;
	}

	public void setCloseBegin(int closeBegin) {
		this.closeBegin = closeBegin;
	}

	public int getCloseEnd() {
		return closeEnd;
	}

	public void setCloseEnd(int closeEnd) {
		this.closeEnd = closeEnd;
	}



}
