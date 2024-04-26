package tr.edu.ku;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoginPanel extends JPanel {
    private CardLayout cardLayout;
    
    static final int WIDTH = 1670; //extend the panel for labels
    static final int HEIGHT = 900;
    
    private BufferedImage background;
   
    public LoginPanel(JFrame loginFrame, DatabaseConnection databaseConnection) {
    	 setPreferredSize(new Dimension(WIDTH, HEIGHT));
         
         try {
             // Load the image
             background = ImageIO.read(getClass().getResourceAsStream("/Assets/200Background.png"));
         } catch (IOException e) {
             // Handle the IOException (e.g., print an error message)
             e.printStackTrace();
         }
    	
         setLayout(new GridBagLayout()); 
         GridBagConstraints gridm = new GridBagConstraints();
 		
 	
 		gridm.gridx = 0;
 		gridm.gridy = 0;
        gridm.insets = new Insets(5, 5, 5, 5);
        
        //Username
 		JLabel u_name = new JLabel("Username:");
 		gridm.gridy ++;
 		u_name.setForeground(Color.white);
 		add(u_name, gridm);
 		
 		JTextField u_name_input = new JTextField(20);
 		gridm.gridy ++;
 		u_name_input.setForeground(Color.white);
 		add(u_name_input, gridm);
 		
 		//Password
 		JLabel password = new JLabel("Password:");
 		gridm.gridy ++;
 		password.setForeground(Color.white);
 		add(password, gridm);
 		
 		JTextField password_input = new JTextField(20);
 		gridm.gridy ++;
 		password_input.setForeground(Color.white);
 		add(password_input, gridm);
 		
 		gridm.gridy ++;
 		
 		JButton login = new JButton("Log In");
 		
 		login.setPreferredSize(new Dimension(150, 30));
 		login.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 25));
        
        
        add(login, gridm);
 		
        login.addActionListener(new ActionListener() {

  			@Override
  			public void actionPerformed(ActionEvent e) {
  				// TODO Auto-generated method stub	
  				LoginRegisterHandler logregHandler = new LoginRegisterHandler(databaseConnection);
  				int val = logregHandler.Login(u_name_input.getText(), password_input.getText());
  				
  				if(val == 1) {
  					// Success Scenario
  				}
  				else if (val == 0) {
  					JOptionPane.showMessageDialog(loginFrame, "The username is not correct.");
  				}
  				else {
  					JOptionPane.showMessageDialog(loginFrame, "The password is not correct.");	
  				}
  				
  				
  				u_name_input.setText("");
  				password_input.setText("");
  						
  			}
  			
  		});
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image at the top-left corner of the panel
        g.drawImage(background, 0, 0, 1600, 900, null);
    }   
	
}
