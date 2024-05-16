package tr.edu.ku.Main;

import tr.edu.ku.AppInterface.ModeSwitchListener;
import tr.edu.ku.GameArea.Layout;
import tr.edu.ku.GameView.LanceOfDestiny;
import tr.edu.ku.GameView.RunningFrame;

public class MainApplication implements ModeSwitchListener {
    
    private final LanceOfDestiny editingFrame;
    private RunningFrame runningFrame;
    private final Player player;

    private final GameLoop gloop;

    public MainApplication(Player user) {
        this.player = user;
        editingFrame = new LanceOfDestiny(this);
        runningFrame = null; // Initially not created
        gloop = new GameLoop(editingFrame.getEditingPlane());
        //Game Loop
        Thread gameThread = new Thread(gloop);
        gameThread.start();
    }


    public void switchToRunningMode(Layout layout) {
        if (runningFrame == null) {
            runningFrame = new RunningFrame(layout, this);
            gloop.setRunningPanel(runningFrame.getGamePanel());

        } else {
            runningFrame.LoadGamePanel(layout); //Load gamePanel's game area with layout
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
