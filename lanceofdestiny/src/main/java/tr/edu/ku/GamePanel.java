package tr.edu.ku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener {
    
    private GameArea gameArea;
    private Renderer renderer = new Renderer();
    private KeyboardInputHandler inputHandler = new KeyboardInputHandler();
    
    private int gameState = 1; //1 for resume, -1 for pause. Start at resume state.

    
    public GamePanel(Layout layout) {
        setPreferredSize(new Dimension(Constants.GAMEPANEL_WIDTH, Constants.GAMEPANEL_HEIGHT));
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
        g.drawImage(Constants.background, 0, 0, 1600, 900, null);

        //Display the score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28)); // Font name, style, size
        g.drawString("Score: "+ (int) gameArea.getScore(), 1450, 25);


        //Display lives
        for (int i = 0; i < gameArea.getLives(); i++) {
            g.drawImage(Constants.heart_icon, 10 + 25*i, 10, 19, 19, null);
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


    //Save layout to players layout list
    public void saveGame(SaveLoadGame game_manager) {
        game_manager.SaveGame(gameArea);
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