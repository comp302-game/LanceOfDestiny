package tr.edu.ku.GameView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import tr.edu.ku.Constants;
import tr.edu.ku.Database.SaveLoadGame;
import tr.edu.ku.GameArea.Layout;
import tr.edu.ku.GameEngine.KeyboardInputHandler;
import tr.edu.ku.Render.Renderer;
import tr.edu.ku.SpellController;

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

        //Display Spells
        g.drawImage(Constants.fireball2x, 600, 8, 25, 25, null); //OWFB SPELL
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14)); // Font name, style, size
        g.drawString(""+ (int) gameArea.getOFB().size(), 630, 23);

        g.drawImage(Constants.player2x, 645, 8, 25, 25, null); //OWFB SPELL
        g.drawString(""+ (int) gameArea.getMSE().size(), 675, 23);

        g.drawImage(Constants.felix, 690, 8, 25, 25, null); //FELIX SPELL
        g.drawString(""+ (int) gameArea.getFELIX().size(), 720, 23);

        g.drawImage(Constants.hex, 735, 8, 25, 25, null); //HEX SPELL
        g.drawString(""+ (int) gameArea.getHEX().size(), 765, 23);


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

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_O) {
            
            if(!gameArea.getOFB().isEmpty() && SpellController.is_OVM_Active() == false) {
                gameArea.getOFB().get(0).activate(gameArea.getBall());
                gameArea.getOFB().remove(0);
            }
            

        } else if (key == KeyEvent.VK_F) {

            if(!gameArea.getFELIX().isEmpty() && gameArea.getLives() < 3) {
                gameArea.getFELIX().get(0).activate(gameArea);
                gameArea.getFELIX().remove(0);
            }
            
        } else if (key == KeyEvent.VK_M) {

            if(!gameArea.getMSE().isEmpty() && SpellController.is_MSE_Active() == false) {
                gameArea.getMSE().get(0).activate(gameArea.getPaddle());
                gameArea.getMSE().remove(0);
            }
            
        } else if (key == KeyEvent.VK_H) {

            if(!gameArea.getHEX().isEmpty() && SpellController.is_HEX_Active() == false) {
                gameArea.getHEX().get(0).activate(gameArea.getPaddle());
                gameArea.getHEX().remove(0);
            }
            
        }
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