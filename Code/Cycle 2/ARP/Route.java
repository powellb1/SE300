
public class Route {

	private int number;
	private String Airline;
	private String Origin;
	private int depTime;
	private String Destination;
	private int arrivalTime;
	private double cost;
	
	public Route(int number, String Airline, String Origin, int depTime, String Destination, int arrivalTime, double cost){
		
		this.number=number;
		this.Airline = Airline;
		this.Origin = Origin;
		this.depTime = depTime;
		this.Destination = Destination;
		this.arrivalTime = arrivalTime;
		this.cost = cost;
		
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

	public String getOrigin() {
		return Origin;
	}

	public void setOrigin(String origin) {
		Origin = origin;
	}

	public double getDepTime() {
		return depTime;
	}

	public void setDepTime(int depTime) {
		this.depTime = depTime;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public double getArrivalTime() {
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
	
	public String toString(){
		
		//System.out.println("Number is: "+ number +"Airline is: " + Airline + "Origin is: " + Origin + "Departure time is: "+ depTime + "Destination is: "+ Destination + "Arrival time is: " + arrivalTime + "Cost: " + cost );
		return String.format("Number is: %d \nAirline is: %s \nOrigin is: %s \nDeparture time is: %d \nDestination is: %s \nArrival time is: %d \nCost: %f \n",number,Airline,Origin,depTime,Destination,arrivalTime,cost);
		
	}
	
}
