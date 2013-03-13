
public class Airport {
	
	private boolean open;
	private String name;
	
	public Airport(boolean open, String name){
		
		this.open = open;
		this.name = name;
		
	}
	
	public String toString(){
		
		return String.format("Airport code is: %s\nOpen is: %b",name,open);
		
	}
	
	public String getCode(){
		
		return name;
	}
	

}

