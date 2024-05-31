package tr.edu.ku.View.MenuView;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tr.edu.ku.Main.MainApplication;

public class WaitingForPlayerView extends JFrame {
    
    private JLabel ipLabel;
    
    public WaitingForPlayerView(MainApplication mainApp) {
        // Set the title of the JFrame
        super("Multiplayer Waiting Room");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the JFrame
        setSize(400, 200);

        // Create a custom panel with background image
        StarterPanel backgroundPanel = new StarterPanel();
        backgroundPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for layout management

        // Create a GridBagConstraints object for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Create the "Waiting for a player to join..." label
        JLabel message1 = new JLabel("Waiting for a player to join...");
        message1.setForeground(Color.WHITE); // Change the color as needed
        backgroundPanel.add(message1, gbc);

        // Create the "Share this IP address with your friend" label
        JLabel message2 = new JLabel("Share this IP address with your friend:");
        message2.setForeground(Color.WHITE); // Change the color as needed
        gbc.gridy = 1;
        backgroundPanel.add(message2, gbc);

        // Create the IP address display label
        ipLabel = new JLabel();
        ipLabel.setForeground(Color.WHITE); // Change the color as needed
        gbc.gridy = 2;
        backgroundPanel.add(ipLabel, gbc);

        // Create buttons
        JButton exitButton = new JButton("EXIT");
        gbc.gridy = 3;
        backgroundPanel.add(exitButton, gbc);


        //exit button
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainApp.exitGame();
            }
        });


        // Set the content pane to the custom panel
        setContentPane(backgroundPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public JLabel getIPLabel() {
        return ipLabel;
    }

}
