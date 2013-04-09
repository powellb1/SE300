
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileInput{

	LinkedList<Route> Routes;
	LinkedList<Airport> Airports;

	public FileInput() throws IOException {

		readFile();


	} //constructor

	private void readFile() throws IOException{
		BufferedReader br = null;
		String line = null;

		Airports = new LinkedList<Airport>();
		Routes = new LinkedList<Route>();


		try{
			br = new BufferedReader(
					new FileReader("input.txt"));

		}catch(FileNotFoundException e){

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


		String regex = "R(\\d{3})\\W*([A-Z]*\\s*[A-Z]+)\\W*([A-Z]{3})\\W*([0-9]{4})\\W*([A-Z]{3})\\W*([0-9]{4})\\W*([0-9]*\\.*[0-9]*).*";
		Pattern pattern = Pattern.compile(regex);
		String regexClose = "([A-Z]{3})\\W*([0-9]{4})\\W*([0-9]{4}).*";
		Pattern patternClose = Pattern.compile(regexClose);
		String regexAirport = "[A-Z]{3}";
		Pattern patternAirport = Pattern.compile(regexAirport);


		while(line != null){
			Matcher matcher = pattern.matcher(line);
			Matcher matcherClose = patternClose.matcher(line);
			Matcher matcherAirport = patternAirport.matcher(line);
			if(!line.contains("#")){
				if(matcherAirport.matches()){

					Airports.add(new Airport(line,0,0));

				}

				if(matcher.matches()){
					if(Integer.parseInt(matcher.group(4))<Integer.parseInt(matcher.group(6))&& Integer.parseInt(matcher.group(6))-Integer.parseInt(matcher.group(4))>30){





						Routes.add(new Route(Integer.parseInt(matcher.group(1)),matcher.group(2), new Node(matcher.group(3)), Integer.parseInt(matcher.group(4)), new Node(matcher.group(5)), Integer.parseInt(matcher.group(6)),Double.parseDouble(matcher.group(7)),true));

					}
				}
				if(matcherClose.matches()){

					for(int i=0;i<Airports.size();i++){

						if(matcherClose.group(1).matches(Airports.get(i).getCode())){

							if(Integer.parseInt(matcherClose.group(2))<Integer.parseInt(matcherClose.group(3))&& Integer.parseInt(matcherClose.group(3))-Integer.parseInt(matcherClose.group(2))>30){
							
							Airports.get(i).setCloseBegin(Integer.parseInt(matcherClose.group(2)));
							Airports.get(i).setCloseEnd(Integer.parseInt(matcherClose.group(3)));
							}

							for(int k=0;k<Routes.size();k++){
								
								if(Routes.get(k).getOrigin().toString().matches(Airports.get(i).toString())){
									
									if(Routes.get(k).getDepTime()>Integer.parseInt(matcherClose.group(2))&&Routes.get(k).getDepTime()<Integer.parseInt(matcherClose.group(3))){
										
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

		br.close();
		
		if(Routes.isEmpty()&&Airports.isEmpty()){
			
			readFile();
			
		}
		
	}
	
	
	public LinkedList<Route> getRoutes(){

		return Routes;
	}

	public LinkedList<Airport> getAirports(){

		return Airports;
	}
	
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