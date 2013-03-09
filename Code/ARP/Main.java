import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class Main {

	
	public static void main(String[] args) {
		
		
		FileInput f = null;
		ArrayList <Airport> air;
		LinkedList <Route> r;
		try {
			f = new FileInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		air=f.getAirports();
		r=f.getRoutes();
		
		System.out.println(r.getFirst().toString());
		
		
	}
	
}
