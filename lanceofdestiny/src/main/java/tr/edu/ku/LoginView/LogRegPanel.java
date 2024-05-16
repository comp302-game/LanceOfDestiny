package tr.edu.ku.LoginView;

import javax.swing.*;

import tr.edu.ku.Constants;

import java.awt.*;
import java.awt.event.*;

public class LogRegPanel extends JPanel {
    
    static final int WIDTH = 800; //extend the panel for labels
    static final int HEIGHT = 600;
    
    private JButton login_Button;
    private JButton register_Button;


    public LogRegPanel(LoginFrame loginFrame) {
    	setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
         
        login_Button = new JButton("Log In");
        register_Button = new JButton("Register");
         
        login_Button.setPreferredSize(new Dimension(150, 30));
        login_Button.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 25));
         
        register_Button.setPreferredSize(new Dimension(150, 30));
        register_Button.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 25));

       
        add(login_Button, gbc);
         
        gbc.gridx++;
         
        add(register_Button, gbc);
         
        login_Button.addActionListener(new ActionListener() {

 		    @Override
 		    public void actionPerformed(ActionEvent e) {
				removeAll();
				setLayout(new BorderLayout());
					
				add(loginFrame.getLoginPanel());
				revalidate();
 					
 			}
 			
 		});
         
        
        register_Button.addActionListener(new ActionListener() {

  		    @Override
  			public void actionPerformed(ActionEvent e) {
  				removeAll();
  				setLayout(new BorderLayout());
  					
  				add(loginFrame.getRegisterPanel());
  				revalidate();
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