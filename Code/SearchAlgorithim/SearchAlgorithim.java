import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SearchAlgorithim {

	
	
	
	
	
	/*
	FileInput f;
	String filter = "cost";
	String origin = "JFK";
	String destination = "ATL";
	String[][] routes;
	String[] airports;
	int k=0;
	int difference = 0;
	Double temp =0.0;
	Double cost =100000000.0;
	
	Map<String,String> matchesOrigin = new HashMap<String,String>();
	Map<String,String> matchesDestination = new HashMap<String,String>();
	Map<String,Double> matchesDestinationCost = new HashMap<String,Double>();
	
	public SearchAlgorithim(String filter){
		this.filter=filter;
		try {
			 f = new FileInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(filter.matches("cost")){
			
			
			routes=f.getRoutes();
			airports=f.getAirports();
			
			for(int i=0;i<routes.length;i++){
				if(routes[i][2].matches(origin)){
					matchesOrigin.put(routes[i][0],routes[i][4]);
				}
				
			}
			for(int i=0; i<routes.length;i++){
				if(routes[i][4].matches(destination)){
					matchesDestination.put(routes[i][0], routes[i][2]);
					matchesDestinationCost.put(routes[i][0], Double.parseDouble(routes[i][6]));
				}
				
			}
	
			for(Iterator<String> i = matchesDestination.keySet().iterator();i.hasNext();){
				String key =(String)i.next();

				if(matchesDestination.get(key).equals(origin)){
					temp=matchesDestinationCost.get(key);

					if(cost>temp){
						cost=temp;

					}
					
					
				}
					
					
				
				
			}
			
			
		}
		System.out.println(cost);
		
	}
	
	public static void main(String[] args) {
		SearchAlgorithim s = new SearchAlgorithim("cost");
	}
	*/
}
