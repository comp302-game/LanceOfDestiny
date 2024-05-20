package tr.edu.ku.Domain.Ymir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.Spell.SpellAdapter;
import tr.edu.ku.Domain.Spell.SpellController;
import tr.edu.ku.GameArea.GameArea;

public class InfiniteVoid implements SpellAdapter {

    private Random random = new Random();
    private GameArea gameArea;

    private long spellStartingTime;
    private long spellPausedTime;
    private double delta_t;


    public InfiniteVoid(GameArea gameArea) {
        this.gameArea = gameArea;
    }


    @Override
    public void activate(int time) {
        SpellController.getInstance().setVOID(true);
        SpellController.getInstance().setCurrentVOID(this);

        spellStartingTime = System.currentTimeMillis(); // Record the game starting time
        ArrayList<Barrier> shuffledBarriers = gameArea.getAllBarriers();
        Collections.shuffle(shuffledBarriers, random);
    
        int number = 0;
        for(int i = 0; i < shuffledBarriers.size(); i++){
            shuffledBarriers.get(i).setIsFrozen(true);
            number++;

            if(number>=8) {
                break;
            }
        }

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
            SpellController.getInstance().setVOID(false);
            for(Barrier barrier : gameArea.getAllBarriers()){   //unfreeze all frozen barriers
                if(barrier.isFrozen()){
                barrier.setIsFrozen(false);
                }
            }
        }
    }
    }



    public void spellPaused() {
        spellPausedTime = System.currentTimeMillis(); // Record the game starting time
        delta_t = (spellPausedTime - spellStartingTime) / 1000; //time that have already passed
    }


    public void resume() {
        deactivate();
        activate((int) (15-delta_t));
        spellStartingTime = System.currentTimeMillis(); // Record the game starting time
    }
}
