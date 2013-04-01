import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
	
	public void saveMeh(){
	
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("savedOutput.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.println("# The list of airports are as follows:\n");
		for(int i=0;i<airports.size();i++){
			
			writer.println(airports.get(i).toString());
			
		}
		writer.println("\n\n# The list of routes are as follows:\n");
		for(int i=0;i<allRoutes.size();i++){
			
			writer.println(allRoutes.get(i).Stringify());
			
		}
		writer.println("\n\n");
		writer.close();
		
		
	}
	
	
	
}
