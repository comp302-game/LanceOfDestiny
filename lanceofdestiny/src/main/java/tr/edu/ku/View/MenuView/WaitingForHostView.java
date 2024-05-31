package tr.edu.ku.View.MenuView;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WaitingForHostView extends JFrame {

    public WaitingForHostView() {
        // Set frame properties
        setTitle("Lobby");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        
        // Create a custom panel with background image
        StarterPanel backgroundPanel = new StarterPanel();
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for layout management

        // Create and add labels
        JLabel message1 = new JLabel("You are at the lobby.");
        JLabel message2 = new JLabel("Waiting for host to start the game.");

        // Set text color
        message1.setForeground(Color.WHITE); // Change the color as needed
        message2.setForeground(Color.WHITE); // Change the color as needed

        message1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        message2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        backgroundPanel.add(message1);
        backgroundPanel.add(message2);

        // Set the content pane to the custom panel
        setContentPane(backgroundPanel);

        // Display the frame
        setVisible(true);
    }
}

