package tr.edu.ku.Main;

import tr.edu.ku.GameView.EditorPanel;
import tr.edu.ku.GameView.GamePanel;

public class GameLoop implements Runnable {

    private EditorPanel editingPanel;
    private GamePanel gamePanel;

    private int gameMode; //1 for Running, 0 for Editing 

    private boolean running;
    private final double updateRate = 1.0d/120.0d;
    private long nextStatTime;
    private int fps, ups;
    private int ymir_timer;


    public GameLoop(EditorPanel editingPanel) { //Constructor for editing mode loop
        this.editingPanel = editingPanel;
        this.gameMode = 0;
    }


    @Override
    public void run() {
        
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while(running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            while(accumulator > updateRate) {
                update();
                render();
                accumulator -= updateRate;
            }

            printStats();
        }
    }


    //Print UPS & FPS of the game.
    private void printStats() {
        if(System.currentTimeMillis() > nextStatTime) { //prints every second
            System.out.println(String.format("FPS: %d, UPS: %d in mode "+gameMode, fps, ups));
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;

            if(gamePanel != null){
                if(gamePanel.getGameArea().getGameState() == 1){
                    ymir_timer++;
                }
            }
        }
    }


    //Update Game State
    private void update() {
        if(gameMode == 1){
            gamePanel.updateGameState();

            if(ymir_timer >= 30) {
                gamePanel.getGameArea().ymirHit();
                ymir_timer = 0;
            }
        }
        else if(gameMode == 0) {
            editingPanel.updateEditingState();
        }
        ups++;
    }


    //Repaint the display elements
    private void render() {
        if(gameMode == 1){
            gamePanel.repaint();
        }
        else if(gameMode == 0) {
            editingPanel.repaint();
        }
        fps++;
    }


    public void setRunningPanel(GamePanel runningPanel) {
        gamePanel = runningPanel;
    }
    
    public void setMode(int mode) {
        gameMode = mode;
    }
    
}
