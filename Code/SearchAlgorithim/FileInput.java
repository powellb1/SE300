
import java.io.*;
import java.util.*;

public class FileInput{

	String[][] route;
	String[] airport;
	
   public FileInput() throws IOException{
        StringTokenizer st;
        
        int u=0;
        int l=0;
        int i =0;
        int k=0;
        int tokens=0;
        int temp=0;
        BufferedReader br = null;
        BufferedReader br1 = null;
        String line1 = null;
        String line = null;
        
        try{
           br = new BufferedReader(
                     new FileReader("input.txt"));
           
           br1 = new BufferedReader( new FileReader("input.txt"));
        }catch(FileNotFoundException e){
        	
        	System.out.println("File not found!");
        	
        }
			line1 = br1.readLine();
			 line = br.readLine();
		
          
           
           while(line1 != null){
        	   if(!line1.contains("#")){
        		   if(line1.length()==3 && !line1.matches("[0-9]+")){
        			u++;
        			   
        		   }
        	   		if(line1.contains(",")){
                   st = new StringTokenizer(line1,", ");
                   
                   temp=st.countTokens();
                   if(tokens<temp){
                	   tokens=temp;
                   }
                   l++;
        	   		}
        	   }
                   line1 = br1.readLine();
           }

           br1.close();
           

           
           airport = new String[u];
           route= new String[l][tokens];


           while(line != null){
        	   if(!line.contains("#")){
        		   if(line.length()==3 && !line.matches("[0-9]+")){
        			airport[i]=line; 
        			i++;
        			   
        		   }
        	   		if(line.contains(",")){
                   st = new StringTokenizer(line,", ");
                   tokens=st.countTokens();

                   
                   for(int j = 0; j<tokens; j++){
                	  route[k][j]=st.nextToken();
                        
                   }
                   k++;
        	   		}
        	   }
                   line = br.readLine();
           }

           br.close();
/*
           for(int z=0;z<airport.length;z++){
        	   
        	   System.out.println(airport[z]);
           }
           
           for(int z=0;z<route.length;z++){
        	   
        	   for(int h=0;h<route[z].length;h++){
        		   
        		   System.out.println(route[z][h]);
        		   
        	   }
        	   
           }
           
  */   
        
   } //constructor
   
public String[][] getRoutes(){
	  
	  return route;
  }

public String[] getAirports(){
	
	return airport;
}
   
} //class