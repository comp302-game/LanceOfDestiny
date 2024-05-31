package tr.edu.ku.Domain.Ymir;

import java.util.Timer;
import java.util.TimerTask;

import tr.edu.ku.Domain.Spell.SpellAdapter;
import tr.edu.ku.Domain.Spell.SpellController;
import tr.edu.ku.GameArea.GameArea;

public class DoubleAccel implements SpellAdapter {

    private GameArea gameArea;
    private long spellStartingTime;
    private long spellPausedTime;
    private double delta_t;
    private Timer timer;

    public DoubleAccel(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    @Override
    public void activate(int time) {
        SpellController.getInstance().setACCEL(true);
        SpellController.getInstance().setCurrentACCEL(this);

        synchronized(gameArea.getLock()) {
            gameArea.getBall().setSpeedX(gameArea.getBall().getSpeedX()/2);
            gameArea.getBall().setSpeedY(gameArea.getBall().getSpeedY()/2);
        }
        
        spellStartingTime = System.currentTimeMillis(); // Record the game starting time

        // Start timer for duration of spell
        timer = new Timer();
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
            SpellController.getInstance().setACCEL(false);
            gameArea.getBall().setSpeedX(gameArea.getBall().getSpeedX()*2);
            gameArea.getBall().setSpeedY(gameArea.getBall().getSpeedY()*2);
        }
    }
        timer.cancel();   
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
