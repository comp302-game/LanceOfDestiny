package tr.edu.ku.View.MenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import tr.edu.ku.Main.MainApplication;
import tr.edu.ku.Main.Player;
import tr.edu.ku.View.LoginView.LoginFrame;

public class StarterMenuView extends JFrame {
    
    public StarterMenuView(Player player) {
        setTitle("Game Menu");
        setSize(800, 600); // Adjust the size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Custom JPanel with background image
        StarterPanel backgroundPanel = new StarterPanel();
        backgroundPanel.setLayout(null); // Using absolute positioning for custom layout

        // Create buttons
        JButton joinGameButton = new JButton("Join Game");
        JButton hostGameButton = new JButton("Host Game");
        JButton singleplayerButton = new JButton("Singleplayer");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");
        

        // Set button bounds (x, y, width, height)
        joinGameButton.setBounds(300, 150, 200, 50);
        hostGameButton.setBounds(300, 220, 200, 50);
        singleplayerButton.setBounds(300, 290, 200, 50);
        exitButton.setBounds(300, 430, 200, 50);
        helpButton.setBounds(300, 360, 200, 50);
        

        // Add buttons to the panel
        backgroundPanel.add(joinGameButton);
        backgroundPanel.add(hostGameButton);
        backgroundPanel.add(singleplayerButton);
        backgroundPanel.add(exitButton);
        backgroundPanel.add(helpButton);

        // Add action listeners (optional)
        joinGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainApplication(player, 2);
            }
        });

        hostGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainApplication(player, 1);
            }
        });

        singleplayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainApplication(player, 0);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                
                SwingUtilities.invokeLater(() -> {
                    new LoginFrame();
                });
            }
        });

        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HelpView();
            }
        });


        // Add the panel to the frame
        add(backgroundPanel);
        requestFocus();
        setVisible(true);
    }
    
}

