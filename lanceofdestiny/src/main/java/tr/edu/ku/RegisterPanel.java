package tr.edu.ku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPanel extends JPanel {
    
    static final int WIDTH = 600; //extend the panel for labels
    static final int HEIGHT = 800;
  	private LoginRegisterHandler logregHandler = new LoginRegisterHandler();


    public RegisterPanel(LoginFrame loginFrame) {

    	setPreferredSize(new Dimension(WIDTH, HEIGHT));    
        setLayout(new GridBagLayout()); 
        GridBagConstraints gridm = new GridBagConstraints();
 		
 	
 		gridm.gridx = 0;
 		gridm.gridy = 0;
        gridm.insets = new Insets(5, 5, 5, 5);
 		
 		// Name
 		JLabel name = new JLabel("Name:");
 		gridm.gridy ++;
 		name.setForeground(Color.white);
 		add(name, gridm);
 		
 		JTextField name_input = new JTextField(20);
 		gridm.gridy ++;
 		add(name_input, gridm);
 		
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
 		
 		JTextField password_input = new JTextField(20);
 		gridm.gridy ++;
 		add(password_input, gridm);
 		
 		//Email
 		JLabel email = new JLabel("Email:");
 		gridm.gridy ++;
 		email.setForeground(Color.white);
 		add(email, gridm);
 		
 		JTextField email_input = new JTextField(20);
 		gridm.gridy ++;
 		add(email_input, gridm);
         
 		gridm.gridy ++;
 		
 		JButton register = new JButton("Register");
 		
 		register.setPreferredSize(new Dimension(150, 30));
        register.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 25));
        
        
        add(register, gridm);
 		
        register.addActionListener(new ActionListener() {

  			@Override
  			public void actionPerformed(ActionEvent e) {

  				int val = logregHandler.Register(name_input.getText(), u_name_input.getText(), password_input.getText(), email_input.getText());
				
  				if(val == 1) {
					JOptionPane.showMessageDialog(loginFrame, "Registration is succesful!");
					loginFrame.getLogRegPanel().removeAll();
					loginFrame.getLogRegPanel().add(loginFrame.getLoginPanel());
					loginFrame.getLogRegPanel().revalidate();
					loginFrame.revalidate();
  				}
  				else if (val == -1) {
  					JOptionPane.showMessageDialog(loginFrame, "This username is taken.");
  				}
  				else {
  					JOptionPane.showMessageDialog(loginFrame, "The password is not valid.");	
  				}
  				
  				name_input.setText("");
  				u_name_input.setText("");
  				password_input.setText("");
  				email_input.setText("");  				
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