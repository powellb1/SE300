
import java.io.*;
import java.util.*;

public class FileInput{

   public static void main(String args[]) throws IOException{
        StringTokenizer st;
        
        int u=0;
        int l=0;
        int i =0;
        int k=0;
        int tokens=0;

           BufferedReader br = new BufferedReader(
                     new FileReader("input.txt"));
           
           BufferedReader br1 = new BufferedReader( new FileReader("input.txt"));
           String line1 = br1.readLine();
           
           while(line1 != null){
        	   if(!line1.contains("#")){
        		   if(line1.length()==3 && !line1.matches("[0-9]+")){
        			u++;
        			   
        		   }
        	   		if(line1.contains(",")){
                   st = new StringTokenizer(line1,", ");
                   tokens=st.countTokens();
                   l++;
        	   		}
        	   }
                   line1 = br1.readLine();
           }

           br1.close();
           

           String line = br.readLine();
           String[] airport = new String[u];
           String[][] route= new String[l][tokens];


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
           avg = avg/count;
           System.out.println("Avg: " + avg);
           
*/
           
          // System.out.println(airport.length);
           for(int z=0;z<airport.length;z++){
        	   
        	   System.out.println(airport[z]);
           }
           
           for(int z=0;z<route.length;z++){
        	   
        	   for(int h=0;h<route[z].length;h++){
        		   
        		   System.out.println(route[z][h]);
        		   
        	   }
        	   
           }
           
        
        
   } //main
} //class