import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


/*
 * This class is here to manage the changes to any list of type route or airport. Every 
 * class requires an argument of Director in it's constructor. This is so that any updates in 
 * one area of the code will reflect to any correlating area. 
 */


public class Director{

	private LinkedList<Route> allRoutes;
	private LinkedList<Airport> allAirports;
	private JTextArea history;
	private JTable info;
	private JComboBox destBox;
	private JComboBox originBox;
	
	public Director(JTextArea history, JTable info, JComboBox destBox, JComboBox originBox){

		//create a new fileinput class
		FileInput f = null;
		this.history=history;
		this.info = info;
		this.destBox = destBox;
		this.originBox = originBox;



		try {
			f = new FileInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get the routes and airports in the file
		allRoutes = f.getRoutes();
		allAirports = f.getAirports();
	}
	//returns what routes are in the system
	public LinkedList<Route> getAllRoutes() {
		return allRoutes;
	}

	//adds a route to the system
	public void addRoute(Route r) {
		allRoutes.add(r);
		history.append("Route "+allRoutes.getLast().toString()+" added to system!\n");
	}

	//returns what airports are in the system
	public LinkedList<Airport> getAirports() {
		return allAirports;
	}

	//allows the addition of an airport to the system
	public void addAirport(Airport a) {
		allAirports.add(a);
		history.append("Airport "+allAirports.get(allAirports.size()-1)+" added to the system!\n");
		JOptionPane.showMessageDialog(null,
				("Airport "+a.toString()+" has been added!"),"Buh bye!", JOptionPane.INFORMATION_MESSAGE);
		originBox.setModel(new javax.swing.DefaultComboBoxModel(allAirports.toArray()));
		destBox.setModel(new javax.swing.DefaultComboBoxModel(allAirports.toArray()));

	}
	
	//closes an airport
	public void closeAirport(Airport a,int closeBegin,int closeEnd){
		
		for(int i=0;i<allAirports.size();i++){
			//if the airport we pass matches
			if(allAirports.get(i).toString().matches(a.toString())){
				//add the begining and ending closing time to the list of closures
				a.setCloseBegin(closeBegin);
				a.setCloseEnd(closeEnd);
				int index=allAirports.indexOf(a);
				history.append("Airport "+allAirports.get(index)+" will be closed from "+closeBegin+" to "+closeEnd+" !\n");
				break;
			}
			
		}
		//if a route departs or arrives during that block, we have to tell the route its invalid since the airport will be closed
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
	
	//method under construction
	public void openAirport(Airport a, int openBegin, int openEnd){
		
		for(int i=0;i<allAirports.size();i++){
			
			if(allAirports.get(i).toString().matches(a.toString())){
				
				//a.set(closeBegin);
				//a.setCloseEnd(closeEnd);
				int index=allAirports.indexOf(a);
				//history.append("Airport "+allAirports.get(index)+" will be closed from "+closeBegin+" to "+closeEnd+" !\n");
				break;
			}
			
		}
		
		
		
	}
	
	//modifies the existing route in the sytem
	public void editRoute(Route r){
		
		for(int i=0;i<allRoutes.size();i++){
			
			if(allRoutes.get(i).toString().matches(r.toString())){
				
				allRoutes.set(i, r);
				history.append("Route "+allRoutes.get(i).toString()+" modified!\n");
			}
			
		}
		
	}

	//deletes the airport from the system
	public void deleteAirport(Airport a){
		allAirports.remove(a);
		LinkedList <Route> toBeRemoved = new LinkedList<Route>();
		
		//we also need to delete any route going to or coming from this destination
		for(int i=0;i<allRoutes.size();i++){

			if(allRoutes.get(i).getOrigin().toString().matches(a.toString())||allRoutes.get(i).getDestination().toString().matches(a.toString())){
				toBeRemoved.add(allRoutes.get(i));

			}

		}
		
		for(int i=0;i<toBeRemoved.size();i++){
			history.append("Route "+toBeRemoved.get(i).toString()+" has been removed!\n");
			allRoutes.remove(toBeRemoved.get(i));
			
		}
		originBox.setModel(new javax.swing.DefaultComboBoxModel(allAirports.toArray()));
		destBox.setModel(new javax.swing.DefaultComboBoxModel(allAirports.toArray()));
		UpdateInfo();
		JOptionPane.showMessageDialog(null,
				("Airport "+a.toString()+" has been deleted!"),"Buh bye!", JOptionPane.INFORMATION_MESSAGE);
	}

	//deletes a route
	public void deleteRoute(Route r){
		
		allRoutes.remove(r);
		history.append("Route "+r.toString()+" has been removed!\n");
		
		/*
		//renumbers the routes
		for(int i=0;i<allRoutes.size();i++){
			
			allRoutes.get(i).setNumber(i+1);

		}
*/
		
	}
	//saves the information to a text file
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
	public void UpdateInfo(){
		deleteAllRows((DefaultTableModel) info.getModel());
		
		for(int i=0;i<allRoutes.size();i++){

				( (DefaultTableModel) info.getModel() ).addRow(new String[]{Integer.toString(allRoutes.get(i).getNumber()),allRoutes.get(i).getOrigin().toString(),
						allRoutes.get(i).getDestination().toString(),Integer.toString(allRoutes.get(i).getDepTime()),
						Integer.toString(allRoutes.get(i).getArrivalTime()),allRoutes.get(i).getAirline(),"$"+Double.toString(allRoutes.get(i).getCost())});

		}
		
	}

	public static void deleteAllRows(final DefaultTableModel model) {
		for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
			model.removeRow(i);
		}
	}
	

}
