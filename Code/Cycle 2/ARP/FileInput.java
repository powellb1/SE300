
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * This class is responsible for reading in the file to our system.
 * It will handle the file not found exception with a JFileChooser 
 * as well as make sure there is something sensical in the file. 
 * 
 */

public class FileInput{

	LinkedList<Route> Routes;
	LinkedList<Airport> Airports;
	LinkedList<Route> NoDupRoutes;
	LinkedList<Airport> NoDupAirports;

	public FileInput() throws IOException {

		readFile();


	} 

	//this method reads in the file and handles the file not found exception
	private void readFile() throws IOException{
		BufferedReader br = null;
		String line = null;

		Airports = new LinkedList<Airport>();
		Routes = new LinkedList<Route>();


		try{
			br = new BufferedReader(
					new FileReader("input.txt"));

		}catch(FileNotFoundException e){

			/*
			 * if the file is not in the working directory of the program, the program opens
			 * a filechooser to allow the user to pick a file. The program will then scan it. If it determines
			 * that nothing in that file matches the specified format, it attempts again.
			 * It will continue to allow the user to pick a file until a valid one is chosen
			 * or the user closes the box, at which point the system will exit
			 */
			JFileChooser inputFileChooser = new JFileChooser();
			inputFileChooser.setMultiSelectionEnabled(false);
			inputFileChooser.setFileFilter(new TextFileFilter());
			int returnVal = inputFileChooser.showDialog(null,"Open a text file");
			
			if(returnVal == JFileChooser.APPROVE_OPTION){

				br = new BufferedReader(new FileReader(inputFileChooser.getSelectedFile()));
				
			}else{
				
				JOptionPane.showMessageDialog(null,
					    "No file chosen! System will now exit!","No File Chosen",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			
		}

		line = br.readLine();

		// we match this big old regular expression to the route format we want
		String regex = "R(\\d{3})\\W*([A-Z]*\\s*[A-Z]+)\\W*([A-Z]{3})\\W*([0-9]{4})\\W*([A-Z]{3})\\W*([0-9]{4})\\W*([0-9]*\\.*[0-9]*).*";
		Pattern pattern = Pattern.compile(regex);
		//same thing for closures
		String regexClose = "([A-Z]{3})\\W*([0-9]{4})\\W*([0-9]{4}).*";
		Pattern patternClose = Pattern.compile(regexClose);
		//or openings
		String regexAirport = "[A-Z]{3}";
		Pattern patternAirport = Pattern.compile(regexAirport);
		
		boolean exists = false;
		boolean origin = false;
		boolean destination = false;

		//NOTE: if the user happens to have a text document that fits this EXACT format, but doesn't have anything useful in it,
		//that's quite the coincidence......

		//while we still have lines in the file
		while(line != null){
			Matcher matcher = pattern.matcher(line);
			Matcher matcherClose = patternClose.matcher(line);
			Matcher matcherAirport = patternAirport.matcher(line);
			//and if it doesn't have a #, representing a comment
			if(!line.contains("#")){
				if(matcherAirport.matches()){
					
					for(int i=0;i<Airports.size();i++){
						
						if(Airports.get(i).toString().matches(line)){
							exists=true;
							
						}
						
					}
					
					if(!exists){
						Airports.add(new Airport(line));
					}
					exists=false;

				}

				if(matcher.matches()){
					//if it doesn't depart before it arrives and if there is atleast a 30 minute travel time
					if(Integer.parseInt(matcher.group(4))<Integer.parseInt(matcher.group(6))&& Integer.parseInt(matcher.group(6))-Integer.parseInt(matcher.group(4))>30&&0<Integer.parseInt(matcher.group(4))&&2400>Integer.parseInt(matcher.group(4))&&0<Integer.parseInt(matcher.group(6))&&2400>Integer.parseInt(matcher.group(6))){

						for(int i=0;i<Airports.size();i++){
							
							if(Airports.get(i).toString().matches(matcher.group(3))){
								origin=true;
								
							}
							if(Airports.get(i).toString().matches(matcher.group(5))){
								destination=true;
								
							}
						}
						
						for(int i=0;i<Routes.size();i++){
	
							if(Routes.get(i).getNumber()==Integer.parseInt(matcher.group(1))){
								exists=true;
								
							}
							
						}
						
						if(destination&&origin&&!matcher.group(3).matches(matcher.group(5))&&!exists){
		
						Routes.add(new Route(Integer.parseInt(matcher.group(1)),matcher.group(2), new Node(matcher.group(3)), Integer.parseInt(matcher.group(4)), new Node(matcher.group(5)), Integer.parseInt(matcher.group(6)),Double.parseDouble(matcher.group(7)),true));
						}
						destination=false;
						origin=false;
						exists=false;
					}
				}
				//if the airport is closed, we need to set all the routes attached to invalid
				if(matcherClose.matches()){

					for(int i=0;i<Airports.size();i++){

						if(matcherClose.group(1).matches(Airports.get(i).getCode())){
							//provided the difference between closing times is greater than 30
							if(Integer.parseInt(matcherClose.group(2))<Integer.parseInt(matcherClose.group(3))&& Integer.parseInt(matcherClose.group(3))-Integer.parseInt(matcherClose.group(2))>30){
							
							Airports.get(i).setCloseBegin(Integer.parseInt(matcherClose.group(2)));
							Airports.get(i).setCloseEnd(Integer.parseInt(matcherClose.group(3)));
							}

							for(int k=0;k<Routes.size();k++){
								
								if(Routes.get(k).getOrigin().toString().matches(Airports.get(i).toString())){
									
									if(Routes.get(k).getDepTime()>=Integer.parseInt(matcherClose.group(2))&&Routes.get(k).getDepTime()<=Integer.parseInt(matcherClose.group(3))){
										
										Routes.get(k).setValid(false);

									}
									
									
									
								}
								
								if(Routes.get(k).getDestination().toString().matches(Airports.get(i).toString())){
									
									if(Routes.get(k).getArrivalTime()>Integer.parseInt(matcherClose.group(2))&&Routes.get(k).getDepTime()<Integer.parseInt(matcherClose.group(3))){
										
										Routes.get(k).setValid(false);

										
									}
									
								}
								
							}
							
							
						}

					}

				}


			}
			line = br.readLine();
		}
		
		//NoDupRoutes = new LinkedList<Route>(new HashSet<Route>(Routes));
		//NoDupAirports = new LinkedList<Airport>(new HashSet<Airport>(Airports));
		
		br.close();
		
		//if nothing matched, bad file. Recurse.
		if(Routes.isEmpty()&&Airports.isEmpty()){
			
			readFile();
			
		}
		
	}
	
	//return our routes
	public LinkedList<Route> getRoutes(){
		return Routes;
		//return NoDupRoutes;
	}

	//return our airports
	public LinkedList<Airport> getAirports(){
		return Airports;
		//return NoDupAirports;
	}
	
	//create a filter for what files the JFileChooser defaults to
	public class TextFileFilter extends javax.swing.filechooser.FileFilter  
	{  
	     public boolean accept(File file)  
	     {  
	          //Convert to lower case before checking extension  
	         return (file.getName().toLowerCase().endsWith(".txt")  ||  
	            file.isDirectory()); 
	    }  
	  
	    public String getDescription()  
	    {  
	        return "Text File (*.txt)";  
	    }  
	}  
		
	

} //class