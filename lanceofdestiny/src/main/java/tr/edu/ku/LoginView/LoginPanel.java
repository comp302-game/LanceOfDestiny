package tr.edu.ku.LoginView;

import javax.swing.*;

import tr.edu.ku.Constants;
import tr.edu.ku.Database.LoginRegisterHandler;
import tr.edu.ku.Main.MainApplication;
import tr.edu.ku.Main.Player;

import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
    
    
    private	LoginRegisterHandler logregHandler = new LoginRegisterHandler();
    static final int WIDTH = 600;
    static final int HEIGHT = 800;

   
    public LoginPanel(LoginFrame loginFrame) {
    	setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
 		add(u_name_input, gridm);
 		
 		//Password
 		JLabel password = new JLabel("Password:");
 		gridm.gridy ++;
 		password.setForeground(Color.white);
 		add(password, gridm);
 		
 		// Use JPasswordField for password input
        JPasswordField password_input = new JPasswordField(20);
        gridm.gridy++;
        add(password_input, gridm);
 		
 		gridm.gridy ++;
 		
 		JButton login = new JButton("Log In");
 		
 		login.setPreferredSize(new Dimension(150, 30));
 		login.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 25));
        

        add(login, gridm);
 		
        login.addActionListener(new ActionListener() {

  			@SuppressWarnings("deprecation")
            @Override
  			public void actionPerformed(ActionEvent e) {

  				int val = logregHandler.Login(u_name_input.getText(), password_input.getText());

  				if(val == 1) {
                    
                    JOptionPane.showMessageDialog(loginFrame, "Login is succesful!");
  				    int id = logregHandler.getPlayerID(u_name_input.getText(), password_input.getText());
  					loginFrame.dispose();
                    Player player = new Player(id, u_name_input.getText());
                    new MainApplication(player);
                      
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
        g.drawImage(Constants.background, 0, 0, 1600, 900, null);
    }   
	
}
