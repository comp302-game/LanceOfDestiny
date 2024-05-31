package tr.edu.ku.View.GameView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tr.edu.ku.Constants;
import tr.edu.ku.Database.SaveLoadGame;
import tr.edu.ku.Domain.Spell.SpellController;
import tr.edu.ku.GameArea.GameArea;
import tr.edu.ku.GameArea.Grid;
import tr.edu.ku.GameEngine.KeyboardInputHandler;
import tr.edu.ku.GameEngine.Render.Renderer;


public class GamePanel extends JPanel implements KeyListener {
    
    private GameArea gameArea;
    private Renderer renderer = new Renderer();
    private KeyboardInputHandler inputHandler = new KeyboardInputHandler();
    private int game_mode;

    
    public GamePanel(Grid grid, int game_mode) {
        setPreferredSize(new Dimension(Constants.GAMEPANEL_WIDTH, Constants.GAMEPANEL_HEIGHT));
        setFocusable(true);    
        addKeyListener(this);

        this.game_mode = game_mode;
        gameArea = new GameArea(grid, game_mode);
    }


    
    public void updateGameState() {
        gameArea.update();
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
        if (gameArea.getGameState() == -1) {
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
        try{
            gameArea.setGameState();
            String name = JOptionPane.showInputDialog(null, "Enter layout name:", "Save Name", JOptionPane.QUESTION_MESSAGE);
            game_manager.SaveGame(gameArea, name);
            JOptionPane.showMessageDialog(this, "Game saved.");
            gameArea.setGameState();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Something went wrong. Couldn't save the game.");
        }
    }



    @Override
    public void keyPressed(KeyEvent e) {
        inputHandler.keyPressed(e);
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_O) {
            
            if(gameArea.getOFB() > 0 && SpellController.getInstance().is_OFB_Active() == false) {
                gameArea.spellUsed("OFB");
            }
            

        } else if (key == KeyEvent.VK_F) {

            if(gameArea.getFELIX() > 0 && gameArea.getLives() < 3) {
                gameArea.spellUsed("FELIX");
            }
            
        } else if (key == KeyEvent.VK_M) {

            if(gameArea.getMSE() > 0 && SpellController.getInstance().is_MSE_Active() == false) {
                gameArea.spellUsed("MSE");
            }
            
        } else if (key == KeyEvent.VK_H) {

            if(gameArea.getHEX() > 0 && SpellController.getInstance().is_HEX_Active() == false) {
                gameArea.spellUsed("HEX");
            } 

        }
        

        //FOR MULTIPLAYER PART
        if(game_mode == 1 || game_mode == 2) {

            if (key == KeyEvent.VK_1) {

                if(gameArea.getACCEL() > 0) {
                    gameArea.attackOpponent("ACCEL");
                } 

            } else if (key == KeyEvent.VK_2) {

                if(gameArea.getVOID() > 0) {
                    gameArea.attackOpponent("VOID");
                } 

            } else if (key == KeyEvent.VK_3) {

                if(gameArea.getHOLLOW() > 0) {
                    gameArea.attackOpponent("HOLLOW");
                } 
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

    public GameArea getGameArea() {
        return gameArea;
    }

    public void setGameArea(Grid grid) {
        gameArea = new GameArea(grid, game_mode);
    }

}