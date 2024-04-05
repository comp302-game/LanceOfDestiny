package tr.edu.ku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException; // Import IOException for handling the exception
import javax.imageio.ImageIO;
import java.io.File;

public class GamePanel extends JPanel implements KeyListener {
    
    static final int WIDTH = 1600;
    static final int HEIGHT = 900;
    
    private GameArea gameArea = new GameArea();
    private Renderer renderer = new Renderer();
    private KeyboardInputHandler inputHandler = new KeyboardInputHandler();
    private BufferedImage background;

    
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        try {
            // Load the image
            background = ImageIO.read(getClass().getResourceAsStream("/Assets/200Background.png"));
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }
        setFocusable(true);    
        addKeyListener(this);
        }


    public void updateGameState() {
        gameArea.updateGameView();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 1600, 900, null);
        renderer.renderRunning((Graphics2D) g, gameArea);
    }



    @Override
    public void keyPressed(KeyEvent e) {
        inputHandler.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputHandler.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


}