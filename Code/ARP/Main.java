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
        //</editor-fold>

     
        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
		
		
		
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
		System.out.println(air.get(0).toString());
		/*
		//test=r.toArray();
		String[] temp;
		
		for(int i=0;i<r.size();i++){
			
			temp=r.get(i).toArray();
			
			for(int k=0;k<temp.length;i++){
				
				g.RouteTable.setValueAt(temp[k], i, k);
				
			}
		}
		
		*/

		
		
		
	}
	
	
}
