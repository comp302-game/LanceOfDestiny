package tr.edu.ku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException; // Import IOException for handling the exception
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements KeyListener {
    
    static final int WIDTH = 1600;
    static final int HEIGHT = 900;
    
    private GameArea gameArea;
    private Renderer renderer = new Renderer();
    private KeyboardInputHandler inputHandler = new KeyboardInputHandler();
    private BufferedImage background;
    private BufferedImage heart_icon;
    
    private int gameState = 1; //1 for resume, -1 for pause. Start at resume state.

    
    public GamePanel(Layout layout) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        try {
            // Load the image
            background = ImageIO.read(getClass().getResourceAsStream("/Assets/200Background.png"));
            heart_icon = ImageIO.read(getClass().getResourceAsStream("/Assets/Heart.png"));
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }


        setFocusable(true);    
        addKeyListener(this);

        gameArea = new GameArea(layout);

    }


    
    public void updateGameState() {
        if(gameState == 1 && KeyboardInputHandler.getX_Pressed() == true && gameArea.isGameOver() == false && gameArea.isGameFinished() == false) { //If resuming, update the game state
            gameArea.updateGame();
        }

        else{
            //do nothing
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 1600, 900, null);

        //Display the score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28)); // Font name, style, size
        g.drawString("Score: "+ (int) gameArea.getScore(), 1450, 25);


        //Display lives
        for (int i = 0; i < gameArea.getLives(); i++) {
            g.drawImage(heart_icon, 10 + 25*i, 10, 19, 19, null);
        }


        //Render Game Elements
        renderer.renderRunning((Graphics2D) g, gameArea);


        //Display Starter message
        if (KeyboardInputHandler.getX_Pressed() == false && gameArea.isGameOver() == false) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 48)); // Font name, style, size
            g.drawString("PRESS X TO START!", 650, 450);
        }

        //Display Paused Message
        if (gameState == -1) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 48)); // Font name, style, size
            g.drawString("GAME PAUSED", 650, 450);
        }
        
        
        //Display Game Over!
        if (gameArea.isGameOver() == true) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 48)); // Font name, style, size
            g.drawString("GAME OVER, Score: "+(int) gameArea.getScore(), 650, 425);
        }

        //Display Game Finished!
        if (gameArea.isGameFinished() == true) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 48)); // Font name, style, size
            g.drawString("GAME FINISHED!, Score: "+(int) gameArea.getScore(), 650, 425);
        }
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

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int newState) {
        gameState = newState;
    }

    public GameArea getGameArea() {
        return gameArea;
    }

    public void setGameArea(Layout layout) { //
        gameArea = new GameArea(layout);
    }

}