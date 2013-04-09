import java.util.Vector;


public class Route {

	private int number;
	private String Airline;
	private Node Origin;
	private int depTime;
	private Node Destination;
	private int arrivalTime;
	private double cost;
	private boolean valid;


	public Route(int number, String Airline, Node Origin, int depTime, Node Destination, int arrivalTime, double cost,boolean valid){

		this.number=number;
		this.Airline = Airline;
		this.Origin = Origin;
		this.depTime = depTime;
		this.Destination = Destination;
		this.arrivalTime = arrivalTime;
		this.cost = cost;
		this.valid=valid;

	}

	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getAirline() {
		return Airline;
	}

	public void setAirline(String airline) {
		Airline = airline;
	}

	public Node getOrigin() {
		return Origin;
	}

	public void setOrigin(Node origin) {
		Origin = origin;
	}

	public int getDepTime() {
		return depTime;
	}

	public void setDepTime(int depTime) {
		this.depTime = depTime;
	}

	public Node getDestination() {
		return Destination;
	}

	public void setDestination(Node destination) {
		Destination = destination;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String Stringify(){
		return String.format("%2d\t%s\t%4s\t%5d\t%4s\t%5d\t%.2f",number,Airline,Origin,depTime,Destination,arrivalTime,cost);

	}

	public String toString(){

		//System.out.println("Number is: "+ number +"Airline is: " + Airline + "Origin is: " + Origin + "Departure time is: "+ depTime + "Destination is: "+ Destination + "Arrival time is: " + arrivalTime + "Cost: " + cost );
		return Integer.toString(number);
	}
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	

}
