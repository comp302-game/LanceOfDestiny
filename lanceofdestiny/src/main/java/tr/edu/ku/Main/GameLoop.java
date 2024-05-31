package tr.edu.ku.Main;

import tr.edu.ku.View.GameView.EditorPanel;
import tr.edu.ku.View.GameView.GamePanel;

public class GameLoop implements Runnable {

    private EditorPanel editingPanel;
    private GamePanel gamePanel;
    private MainApplication app;


    private int gameMode; //1 for Running, 0 for Editing 

    private boolean running;
    private final double updateRate = 1.0d/120.0d;
    private long nextStatTime;
    private int fps, ups;
    private int ymir_timer;


    public GameLoop(EditorPanel editingPanel, MainApplication app) { //Constructor for editing mode loop
        this.editingPanel = editingPanel;
        this.app = app;
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
                
                if(app.getGameMode() == 0) {
                    updateSinglePlayer();
                }
                else {
                    updateMultiPlayer();
                }
                
                render();
                
                if((app.getGameMode() == 1 || app.getGameMode() == 2) && gamePanel!= null && gamePanel.getGameArea() != null && app.getMultiGameReady()) {
                    app.sendMultiInfo((int) gamePanel.getGameArea().getScore(), gamePanel.getGameArea().getAllBarriers().size(), gamePanel.getGameArea().getLives());

                    if(gamePanel.getGameArea().getAttackFlag() == true) {
                        app.sendAttack(gamePanel.getGameArea().getAttackSpell());
                        gamePanel.getGameArea().setAttackFlag(false);
                    }
                }
                
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
    private void updateSinglePlayer() {
        if(gameMode == 1){
            
            gamePanel.updateGameState();

            if(gamePanel.getGameArea().getLoadedFlag()) {
                ymir_timer = 0;
                gamePanel.getGameArea().setLoadedFlag(false);
            }

            if(ymir_timer >= 30) {
                gamePanel.getGameArea().ymirHit();
                ymir_timer = 0;
            }
        }
        else if(gameMode == 0) {
            ymir_timer = 0;
            editingPanel.updateEditingState();
        }
        ups++;
    }



    private void updateMultiPlayer() {
        if(gameMode == 1){
            gamePanel.updateGameState();
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

    public void stop() {
        running = false;
    }
    
}
