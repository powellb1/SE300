
public class Airport {
	
	private boolean open;
	private String name;
	private int closeBegin;
	private int closeEnd;
	
	public Airport(String name, boolean open,int closeBegin, int closeEnd){
		
		//this.open = open;
		this.name = name;
		this.closeBegin = closeBegin;
		this.closeEnd=closeEnd;
		this.open=open;
		
	}
	
	public String toString(){
		
		return name;
	}
	/*
	public String toString(){
		
		String s;
		
		if(closeEnd-closeBegin>0){
			
			s= String.format("Airport code is: %s\nOpen is: %b\nBeginning of close period is: %d\nEnd of close period is %d",name,open,closeBegin,closeEnd);
			
		}else{
			
			s= String.format("Airport code is: %s\nOpen is: %b",name,open);
		}
		
		return s;
		
	}
	*/
	public String getCode(){
		
		return name;
	}
	
	public void setOpen(boolean open){
		
		this.open=open;
	
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
