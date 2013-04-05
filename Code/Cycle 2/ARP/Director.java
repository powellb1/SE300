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
public class Director{

	private LinkedList<Route> allRoutes;
	private ArrayList<Airport> airports;

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

	public void deleteAirport(Airport a){
		airports.remove(a);
		LinkedList <Route> toBeRemoved = new LinkedList<Route>();
		
		for(int i=0;i<allRoutes.size();i++){

			if(allRoutes.get(i).getOrigin().toString().matches(a.toString())||allRoutes.get(i).getDestination().toString().matches(a.toString())){
				toBeRemoved.add(allRoutes.get(i));

			}

		}
		
		for(int i=0;i<toBeRemoved.size();i++){
			
			allRoutes.remove(toBeRemoved.get(i));
			
		}
	

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
