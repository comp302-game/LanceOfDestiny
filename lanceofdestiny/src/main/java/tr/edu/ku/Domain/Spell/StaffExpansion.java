package tr.edu.ku.Domain.Spell;

import java.util.Timer;
import java.util.TimerTask;

import tr.edu.ku.Constants;
import tr.edu.ku.GameArea.GameArea;

public class StaffExpansion implements SpellAdapter {

    private GameArea gameArea;
    private long spellStartingTime;
    private long spellPausedTime;
    private double delta_t;

    public StaffExpansion(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    @Override
    public void activate(int time) {
        SpellController.getInstance().setMSE(true);
        SpellController.getInstance().setCurrentMSE(this);

        synchronized(gameArea.getLock()) {
            gameArea.getStaff().setWidth(Constants.STAFF_ENLARGED_WIDTH);
            gameArea.getStaff().setHeight(Constants.STAFF_ENLARGED_HEIGHT);
        }
        
        spellStartingTime = System.currentTimeMillis(); // Record the game starting time

        // Start timer for duration of spell
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                deactivate();
            }
        }, time * 1000);
    }
    
    @Override
    public void deactivate() {
        synchronized(gameArea.getLock()){
        if(gameArea.getGameState() == 1){
            SpellController.getInstance().setMSE(false);
            gameArea.getStaff().setWidth(Constants.STAFF_WIDTH);
            gameArea.getStaff().setHeight(Constants.STAFF_HEIGHT);
        }
        }
    }


    public void spellPaused() {
        spellPausedTime = System.currentTimeMillis(); // Record the game starting time
        delta_t = (spellPausedTime - spellStartingTime) / 1000; //time that have already passed
    }


    public void resume() {
        deactivate();
        activate((int) (30-delta_t));
        spellStartingTime = System.currentTimeMillis(); // Record the game starting time
    }

}
