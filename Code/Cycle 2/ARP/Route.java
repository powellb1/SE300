
/*
 * This class is how we make a route. It takes in a number, servicing airline,
 * a node for origin, a node for destination, a departure time, an arrival time, a
 * cost and a field that will tell us if its valid (the airport where it goes or comes
 * from is closed). Through this class, making the graph and getting other information 
 * became significantly easier.
 * 
 */

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

	/*
	 * getters and setters for all the variables. Nothing unusual here.
	 */

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

	//returns a string representation of the route. Makes writing to the file easier
	public String Stringify(){
		return String.format("%2d\t%s\t%4s\t%5d\t%4s\t%5d\t%.2f",number,Airline,Origin,depTime,Destination,arrivalTime,cost);

	}

	public String toString(){

		return Integer.toString(number);
	}
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	

}
