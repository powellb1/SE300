// imports

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brittany
 */
public class editRoute extends javax.swing.JFrame {

    /**
     * Creates new form editRoute
     */
    
    private String routeNumber;
    private Object originAirport;
    private String originString;
    private Object destAirport;
    private String destString;
    private String departText;
    private int departTime;
    private String arrivalText;
    private int arrivalTime;
    private Object airlineUsed;
    private String airlineString;
    private String costString;
    private double costDouble;
    private boolean departure;
    private boolean arrival;
    private boolean airports;
    private boolean times;
    private boolean airline;
    private boolean cost;
    private int flightDifference;
    
    Director d;
    // JTextArea history;
    
    
    public editRoute(Director d /*, JTextArea history*/) {
        this.d=d;
        //this.history=history;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleL = new javax.swing.JLabel();
        routeL = new javax.swing.JLabel();
        routeBox = new javax.swing.JComboBox();
        originSet = new javax.swing.JTextField();
        destSet = new javax.swing.JTextField();
        departSet = new javax.swing.JTextField();
        arrivalSet = new javax.swing.JTextField();
        airlineSet = new javax.swing.JTextField();
        departEdit = new javax.swing.JTextField();
        arrivalEdit = new javax.swing.JTextField();
        originL = new javax.swing.JLabel();
        destL = new javax.swing.JLabel();
        departL = new javax.swing.JLabel();
        arrivalL = new javax.swing.JLabel();
        airlineL = new javax.swing.JLabel();
        modifyButton = new javax.swing.JButton();
        costL = new javax.swing.JLabel();
        costEdit = new javax.swing.JTextField();
        costSet = new javax.swing.JTextField();
        originEdit = new javax.swing.JComboBox();
        destEdit = new javax.swing.JComboBox();
        airlineEdit = new javax.swing.JComboBox();
        departValidL = new javax.swing.JLabel();
        arrivalValidL = new javax.swing.JLabel();
        costValidL = new javax.swing.JLabel();
        airportValidL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modify Route");
        setName("editFrame"); // NOI18N

        titleL.setText("Please Select a Route to Edit");

        routeL.setText("Route #");

        routeBox.setModel(new javax.swing.DefaultComboBoxModel(d.getAllRoutes().toArray()));
        routeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                routeBoxActionPerformed(evt);
            }
        });

        originSet.setEditable(false);
        originSet.setPreferredSize(new java.awt.Dimension(84, 28));

        destSet.setEditable(false);
        destSet.setPreferredSize(new java.awt.Dimension(84, 28));

        departSet.setEditable(false);
        departSet.setPreferredSize(new java.awt.Dimension(84, 28));

        arrivalSet.setEditable(false);
        arrivalSet.setPreferredSize(new java.awt.Dimension(84, 28));

        airlineSet.setEditable(false);
        airlineSet.setPreferredSize(new java.awt.Dimension(84, 28));

        departEdit.setText("               ");
        departEdit.setPreferredSize(new java.awt.Dimension(84, 28));

        arrivalEdit.setText("               ");
        arrivalEdit.setPreferredSize(new java.awt.Dimension(84, 28));

        originL.setText("Origin");

        destL.setText("Destination");

        departL.setText("Depart Time");

        arrivalL.setText("Arrival Time");

        airlineL.setText("Airline");

        modifyButton.setText("Edit");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        costL.setText("Cost");

        costEdit.setText("               ");
        costEdit.setPreferredSize(new java.awt.Dimension(84, 28));

        costSet.setEditable(false);
        costSet.setPreferredSize(new java.awt.Dimension(84, 28));

        originEdit.setModel(new javax.swing.DefaultComboBoxModel(d.getAirports().toArray()));

        destEdit.setModel(new javax.swing.DefaultComboBoxModel(d.getAirports().toArray()));

        airlineEdit.setModel(new javax.swing.DefaultComboBoxModel(d.getAirports().toArray()));

        departValidL.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        departValidL.setText("           ");

        arrivalValidL.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        arrivalValidL.setText("          ");

        costValidL.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        costValidL.setText("          ");

        airportValidL.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        airportValidL.setText("                              ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(routeL)
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(originSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(originL))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(originEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(destEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destL)
                    .addComponent(destSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(departEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(arrivalEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(airlineEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(costEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(departSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departL))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(arrivalSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(arrivalL))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(airlineSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(airlineL)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(costSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(23, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(costL)
                                .addGap(52, 52, 52))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(routeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(modifyButton)
                .addGap(333, 333, 333))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(titleL)
                        .addGap(282, 282, 282))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(airportValidL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(departValidL, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(arrivalValidL, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156)
                        .addComponent(costValidL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(titleL)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originL)
                    .addComponent(destL)
                    .addComponent(departL)
                    .addComponent(arrivalL)
                    .addComponent(airlineL)
                    .addComponent(costL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(routeL, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(originSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(destSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(departSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(arrivalSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(airlineSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(costSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(routeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arrivalEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(airlineEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departValidL)
                    .addComponent(arrivalValidL)
                    .addComponent(costValidL)
                    .addComponent(airportValidL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(modifyButton)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void routeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_routeBoxActionPerformed
        // routeBox route selection handling
        
        int i = routeBox.getSelectedIndex();
	arrivalSet.setText(d.getAllRoutes().get(i).getAirline());
	departSet.setText(Integer.toString(d.getAllRoutes().get(i).getArrivalTime()));
	airlineSet.setText(Double.toString(d.getAllRoutes().get(i).getCost()));
	departSet.setText(Integer.toString(d.getAllRoutes().get(i).getDepTime()));
	destSet.setText(d.getAllRoutes().get(i).getDestination().toString());
	originSet.setText(d.getAllRoutes().get(i).getOrigin().toString());
        
    }//GEN-LAST:event_routeBoxActionPerformed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        // prompt warning for modifications
        int result=JOptionPane.showConfirmDialog(this,
				("Are you sure you want to edit "+routeBox.getSelectedItem().toString()+" ?"),"Confirmation", JOptionPane.INFORMATION_MESSAGE);
		if(result==JOptionPane.OK_OPTION){
                    
                    // verify editted data entry
                    
                    // departEdit
                    Pattern q = Pattern.compile("[0-9]{4}");
                    Matcher o = q.matcher(departEdit.getText());
                    
                    if(o.matches()){
                        departText = departEdit.getText();
                        departTime = Integer.parseInt(departText);
                        
                        if(0000 < departTime && departTime < 2400){
                        departure = true;
                        }
                    }
                    
                    else{
                        departValidL.setText("Invalid");
                    }
                    
                    // arrivalEdit
                    Matcher n = q.matcher(arrivalEdit.getText());
                    
                    if(n.matches()){
                        arrivalText = arrivalEdit.getText();
                        arrivalTime = Integer.parseInt(arrivalText);
                        
                        if(0000 < arrivalTime && arrivalTime < 2400)
                        {arrival = true;
                        }
                    }
                    
                    else{
                        arrivalValidL.setText("Invalid");
                    }
                    
                    // costEdit
                    Pattern k = Pattern.compile("[0-9]+\\.+[0-9]*");
                    Matcher l = k.matcher(costEdit.getText());
                    
                    if(l.matches()){
                        costString = costEdit.getText();
                        costDouble = Double.parseDouble(costString);
                        costValidL.setText("Valid");
                        cost = true;
                        costSet.setText(costString);
                    }
                    else{
                        costValidL.setText("Invalid");
                    }
                    // times Edit
                    if(departure == true && arrival == true){
                        flightDifference = arrivalTime - departTime;
                        if(flightDifference >= 30){
                            departValidL.setText("Valid");
                            arrivalValidL.setText("Valid");
                            times = true;
                            arrivalSet.setText(arrivalText);
                            departSet.setText(departText);
                        }
                        else{
                            departValidL.setText("Invalid");
                            arrivalValidL.setText("Invalid");
                        }
                    }
                    else{
                        departValidL.setText("Invalid");
                        arrivalValidL.setText("Invalid");
                    }
                    
                    // airport Edit
                    if(originEdit.getSelectedItem() == destEdit.getSelectedItem()){
                        airportValidL.setText("Origin or Destination Invalid");
                    }
                    else{
                        
                        airportValidL.setText("Valid");
                        airports = true;
                        originAirport = originEdit.getSelectedItem();
                        originString = originAirport.toString();
                        originSet.setText(originString);
                        destAirport = destEdit.getSelectedItem();
                        destString = destAirport.toString();
                        destSet.setText(destString);
                    }
                    
                    // airlineEdit
                    airlineUsed = airlineEdit.getSelectedItem();
                    airlineString = airlineUsed.toString();
                    airlineSet.setText(airlineString);
                } // end if (OK to edit)
    
        
        // 
    }//GEN-LAST:event_modifyButtonActionPerformed

    /*
     * @param args the command line arguments
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editRoute().setVisible(true);
            }
        });
    }
   */
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox airlineEdit;
    private javax.swing.JLabel airlineL;
    private javax.swing.JTextField airlineSet;
    private javax.swing.JLabel airportValidL;
    private javax.swing.JTextField arrivalEdit;
    private javax.swing.JLabel arrivalL;
    private javax.swing.JTextField arrivalSet;
    private javax.swing.JLabel arrivalValidL;
    private javax.swing.JTextField costEdit;
    private javax.swing.JLabel costL;
    private javax.swing.JTextField costSet;
    private javax.swing.JLabel costValidL;
    private javax.swing.JTextField departEdit;
    private javax.swing.JLabel departL;
    private javax.swing.JTextField departSet;
    private javax.swing.JLabel departValidL;
    private javax.swing.JComboBox destEdit;
    private javax.swing.JLabel destL;
    private javax.swing.JTextField destSet;
    private javax.swing.JButton modifyButton;
    private javax.swing.JComboBox originEdit;
    private javax.swing.JLabel originL;
    private javax.swing.JTextField originSet;
    private javax.swing.JComboBox routeBox;
    private javax.swing.JLabel routeL;
    private javax.swing.JLabel titleL;
    // End of variables declaration//GEN-END:variables
}