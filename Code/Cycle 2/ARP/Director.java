import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JTextArea;




/**
 * @author Brian
 *
 */
public class Director{

	private LinkedList<Route> allRoutes;
	private LinkedList<Airport> allAirports;
	private JTextArea history;
	
	public Director(JTextArea history){


		FileInput f = null;
		this.history=history;



		try {
			f = new FileInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allRoutes = f.getRoutes();
		allAirports = f.getAirports();
	}

	public LinkedList<Route> getAllRoutes() {
		return allRoutes;
	}

	public void addRoute(Route r) {
		allRoutes.add(r);
		history.append("Route "+allRoutes.getLast().toString()+" added to system!\n");
	}

	public LinkedList<Airport> getAirports() {
		return allAirports;
	}

	public void addAirport(Airport a) {
		allAirports.add(a);
		history.append("Airport "+allAirports.get(allAirports.size()-1)+" added to the system!\n");

	}
	
	public void editAirport(Airport a,int closeBegin,int closeEnd){
		
		for(int i=0;i<allAirports.size();i++){
			
			if(allAirports.get(i).toString().matches(a.toString())){
				
				a.setCloseBegin(closeBegin);
				a.setCloseEnd(closeEnd);
				int index=allAirports.indexOf(a);
				history.append("Airport "+allAirports.get(index)+" will be closed from "+closeBegin+" to "+closeEnd+" !\n");
				break;
			}
			
		}
		for(int i=0;i<allRoutes.size();i++){
			
			if(allRoutes.get(i).getOrigin().toString().matches(a.toString())){
				
				if(allRoutes.get(i).getDepTime()>closeBegin&&allRoutes.get(i).getDepTime()<closeEnd){
					
					allRoutes.get(i).setValid(false);
					
				}
				
				
				
			}
			
			if(allRoutes.get(i).getDestination().toString().matches(a.toString())){
				
				if(allRoutes.get(i).getArrivalTime()>closeBegin&&allRoutes.get(i).getDepTime()<closeEnd){
					
					allRoutes.get(i).setValid(false);
					
				}
				
			}
			
		}
		
	}
	
	public void editRoute(Route r){
		
		for(int i=0;i<allRoutes.size();i++){
			
			if(allRoutes.get(i).toString().matches(r.toString())){
				
				allRoutes.set(i, r);
				history.append("Route "+allRoutes.get(i).toString()+" modified!\n");
			}
			
		}
		
	}

	public void deleteAirport(Airport a){
		allAirports.remove(a);
		LinkedList <Route> toBeRemoved = new LinkedList<Route>();
		
		for(int i=0;i<allRoutes.size();i++){

			if(allRoutes.get(i).getOrigin().toString().matches(a.toString())||allRoutes.get(i).getDestination().toString().matches(a.toString())){
				toBeRemoved.add(allRoutes.get(i));

			}

		}
		
		for(int i=0;i<toBeRemoved.size();i++){
			history.append("Route "+toBeRemoved.get(i).toString()+" has been removed!\n");
			allRoutes.remove(toBeRemoved.get(i));
			
		}
	

	}

	public void deleteRoute(Route r){
		
		allRoutes.remove(r);
		history.append("Route "+r.toString()+" has been removed!\n");
		
		for(int i=0;i<allRoutes.size();i++){
			
			allRoutes.get(i).setNumber(i+1);

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
		for(int i=0;i<allAirports.size();i++){

			writer.println(allAirports.get(i).toString());

		}
		writer.println("\n\n# The list of routes are as follows:\n");
		for(int i=0;i<allRoutes.size();i++){

			writer.println(allRoutes.get(i).Stringify());

		}
		writer.println("\n\n");
		writer.close();


	}



}
