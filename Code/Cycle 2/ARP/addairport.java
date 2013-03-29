import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class addairport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		
					
					JFrame frame = new JFrame("AddAirport");
					JPanel panel = new JPanel();
					panel.setLayout(new GridLayout(2, 2));
					final JTextField airport = new JTextField();
			        JLabel text = new JLabel("airport code:");
			        final JLabel result = new JLabel("?");
			        final JButton submit = new javax.swing.JButton("submit");
			        submit.addActionListener(new java.awt.event.ActionListener() {
			            public void actionPerformed(java.awt.event.ActionEvent evt) {
			            	submitActionPerformed(evt,airport,result);
			            	}
			            private void submitActionPerformed(ActionEvent evt, JTextField airport,JLabel result){
			                String temp = airport.getText();

			                Pattern p=Pattern.compile("[a-z]{3}");
			               
			                Matcher m=p.matcher(temp);
			                int i=0;
			                while(m.find()){
			                 i++;
			                }
			                System.out.println(i);
			                if(i==1)
			                {
			                	FileWriter fw;
								try {
									fw = new FileWriter("./1.txt");
									fw.write(temp);
				                	fw.flush();
				                	fw.close();
				                	
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			                	
			                	result.setText("Success£°");
			                }else{
			                	result.setText("Failed£¨the format  is incorrect£°");
			                }
			              
			                	
			                
			            }
			          
			        });

				       // convertTemp.addActionListener(this); 
				        panel.add(text);
				        panel.add(airport);
				        panel.add(submit);
				        panel.add(result);

				      
				        frame.getContentPane().add(
						          panel,BorderLayout.CENTER);
						        frame.pack();// œ‘ æ±‰ªª≥Ã–Ú
						        frame.setSize(320, 240);
						        frame.setVisible(true);
						        

						      
						       
						};
						

						
		           
		       

	}


