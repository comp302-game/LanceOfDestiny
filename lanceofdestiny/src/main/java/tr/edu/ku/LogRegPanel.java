package tr.edu.ku;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LogRegPanel extends JPanel {
    private CardLayout cardLayout;
    
    static final int WIDTH = 1670; //extend the panel for labels
    static final int HEIGHT = 900;
    
    private BufferedImage background;

    private JButton login;
    private JButton register;
    
    public LogRegPanel(JFrame loginFrame, DatabaseConnection databaseConnection) {
    	 setPreferredSize(new Dimension(WIDTH, HEIGHT));
         
         try {
             // Load the image
             background = ImageIO.read(getClass().getResourceAsStream("/Assets/200Background.png"));
         } catch (IOException e) {
             // Handle the IOException (e.g., print an error message)
             e.printStackTrace();
         }
         
         setLayout(new GridBagLayout());
         
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.insets = new Insets(5, 5, 5, 5);
         
         login = new JButton("Log In");
         register = new JButton("Register");
         
         login.setPreferredSize(new Dimension(150, 30));
         login.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 25));
         
         register.setPreferredSize(new Dimension(150, 30));
         register.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 25));

       
         add(login, gbc);
         
         gbc.gridx++;
         
         add(register, gbc);
         
         login.addActionListener(new ActionListener() {

 			@Override
 			public void actionPerformed(ActionEvent e) {
 				// TODO Auto-generated method stub	
 					LoginPanel log = new LoginPanel(loginFrame, databaseConnection);
					removeAll();
					setLayout(new BorderLayout());
					
					add(log);
					revalidate();
 					
 			}
 			
 		});
         
        
         register.addActionListener(new ActionListener() {

  			@Override
  			public void actionPerformed(ActionEvent e) {
  				// TODO Auto-generated method stub	
  					RegisterPanel reg = new RegisterPanel(loginFrame, databaseConnection);
  					removeAll();
  					setLayout(new BorderLayout());
  					
  					add(reg);
  					revalidate();
  			}
  			
  		});
    
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image at the top-left corner of the panel
        g.drawImage(background, 0, 0, 1600, 900, null);
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        
        LogRegPanel loginPanel = new LogRegPanel(frame, databaseConnection);
        frame.add(loginPanel);
        
        frame.setVisible(true);
    }
}