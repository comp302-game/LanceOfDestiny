package tr.edu.ku.View.MenuView;
import java.awt.Graphics;
import javax.swing.JPanel;

import tr.edu.ku.Constants;


// Custom JPanel to handle background image
    class StarterPanel extends JPanel {
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(Constants.background, 0, 0, getWidth(), getHeight(), this);
        }   
    }
