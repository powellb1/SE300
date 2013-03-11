
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInput{

	LinkedList<Route> Routes;
	ArrayList<Airport> Airports;
	
   public FileInput() throws IOException{
        
        BufferedReader br = null;
        String line = null;
        
        Airports = new ArrayList<Airport>();
        Routes = new LinkedList<Route>();
        
        
        try{
           br = new BufferedReader(
                     new FileReader("input.txt"));
    
        }catch(FileNotFoundException e){
        	
        	System.out.println("File not found!");
        	
        }

			 line = br.readLine();
		

			 String regex = "R(\\d{3})\\W*([A-Z]+)\\W*([A-Z]{3})\\W*([0-9]{4})\\W*([A-Z]{3})\\W*([0-9]{4})\\W*([0-9]*\\.*[0-9]*).*";
			 Pattern pattern = Pattern.compile(regex);
			 
			 
           while(line != null){
        	   if(!line.contains("#")){
        		   if(line.length()==3 && !line.matches("[0-9]+")){
  
        			Airports.add(new Airport(true,line));
        			   
        		   }
        		   Matcher matcher = pattern.matcher(line);
        	   		if(matcher.matches()){
                
        	   			
        	   			Routes.add(new Route(Integer.parseInt(matcher.group(1)),matcher.group(2) , matcher.group(3), Integer.parseInt(matcher.group(4)),matcher.group(5), Integer.parseInt(matcher.group(6)),Double.parseDouble(matcher.group(7))));
        	   		}
        	   		
        	   }
                   line = br.readLine();
           }

           br.close();
  
        
   } //constructor
   
public LinkedList<Route> getRoutes(){
	  
	  return Routes;
  }

public ArrayList<Airport> getAirports(){
	
	return Airports;
}
   
} //class