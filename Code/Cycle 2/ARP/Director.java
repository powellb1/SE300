import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;




/**
 * @author Brian
 *
 */
public class Director extends java.lang.Object implements java.lang.Cloneable{
	
	private LinkedList<Route> allRoutes;
	private ArrayList<Airport> airports;
	
	@SuppressWarnings("unchecked")
	public Object clone(){
		Director other = null;
		try {
			 other = (Director)super.clone();
			other.allRoutes = (LinkedList<Route>)allRoutes.clone();
			other.airports = (ArrayList<Airport>)airports.clone();
			return other;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return other;
	}
	
	public Director(){

	
	  FileInput f = null;
	  
	 
	  
	  
	  try {
			f = new FileInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      allRoutes = f.getRoutes();
	      airports = f.getAirports();
	}

	public LinkedList<Route> getAllRoutes() {
		return allRoutes;
	}

	public void addRoute(Route r) {
		allRoutes.add(r);
	}

	public ArrayList<Airport> getAirports() {
		return airports;
	}

	public void addAirport(Airport a) {
		airports.add(a);
	}
	
	
	
	
}
