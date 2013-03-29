import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class Main {

	
	public static void main(String[] args) {
		
				
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    
        
        
          
               DaGUI g = new DaGUI();
               g.setVisible(true);
   
		
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
		
		System.out.println(r.get(2).toString());
		System.out.println(air.get(2).toString());

		
	}
	
	
}
