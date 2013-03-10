
public class Airport {
	
	boolean open;
	String name;
	
	public Airport(boolean open, String name){
		
		this.open = open;
		this.name = name;
		
	}
	
	public String toString(){
		
		return String.format("Airport code is: %s\nOpen is: %b",name,open);
		
	}
	
	

}
