package tr.edu.ku;

import javax.swing.*;

public class LanceOfDestiny extends JFrame {

    public LanceOfDestiny (GamePanel gamePanel) {
        setTitle("Lance Of Destiny");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
