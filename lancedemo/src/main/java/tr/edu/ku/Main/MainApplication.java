package tr.edu.ku.Main;

import tr.edu.ku.AppInterface.ModeSwitchListener;
import tr.edu.ku.GameArea.Grid;
import tr.edu.ku.GameView.LanceOfDestiny;
import tr.edu.ku.GameView.RunningFrame;

public class MainApplication implements ModeSwitchListener {
    
    private LanceOfDestiny editingFrame;
    private RunningFrame runningFrame = null;  // Initially not created
    private Player player;

    private final GameLoop gloop;

    public MainApplication(Player user) {
        this.player = user;
        this.editingFrame = new LanceOfDestiny(this);

        //Game Loop
        gloop = new GameLoop(editingFrame.getEditingPlane());
        Thread gameThread = new Thread(gloop);
        gameThread.start();
    }


    public void switchToRunningMode(Grid grid) {
        if (runningFrame == null) {
            runningFrame = new RunningFrame(grid, this);
            gloop.setRunningPanel(runningFrame.getGamePanel());

        } else {
            runningFrame.LoadGamePanel(grid); //Load gamePanel's game area with layout
            runningFrame.setVisible(true);
        }

        editingFrame.setVisible(false);
        gloop.setMode(1);
    }


    public void switchToEditingMode() {
        editingFrame.setVisible(true);
        if (runningFrame != null) {
            gloop.setMode(0);
            runningFrame.setVisible(false);
        }
    }

    public Player getPlayer() {
        return player;
    }

}
