package tr.edu.ku.Domain.Spell;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Bullet;
import tr.edu.ku.GameArea.GameArea;

public class Hex implements SpellAdapter {

    private final ConcurrentLinkedQueue<Bullet> Bullets = new ConcurrentLinkedQueue<>();
    private final Object spellLock = new Object(); // Lock object for bullet synchronization

    private Timer timer;
    private GameArea gameArea;

    private long spellStartingTime;
    private long spellPausedTime;
    private double delta_t;


    public Hex(GameArea gameArea) {
        this.gameArea = gameArea;
        }


    public void activate(int time) {
        SpellController.getInstance().setCurrentHEX(this);
        SpellController.getInstance().setHEX(true);
        spellStartingTime = System.currentTimeMillis(); // Record the game starting time
        timer = new Timer();
        timer.scheduleAtFixedRate(new SpawnTask(), 0, 1000); // Spawn a new object every 1000 milliseconds (1 second)
        timer.schedule(new StopTask(), time*1000); // Stop spawning after 30 seconds
    }



    public void deactivate() {
        SpellController.getInstance().setHEX(false); // Set HEX status to false
        timer.cancel(); // Stop the timer if it's running
    }




    private class SpawnTask extends TimerTask {
        @Override
        public void run() {
            double normalX = Math.cos(Math.toRadians(gameArea.getStaff().getRotationAngle() + 90));
            double normalY = -Math.sin(Math.toRadians(gameArea.getStaff().getRotationAngle() + 90));
            double speedX = normalX* Constants.HEX_SPEED;
            double speedY = normalY* Constants.HEX_SPEED;
            
            Bullet bullet = new Bullet(gameArea.getStaff().getX()+((gameArea.getStaff().getWidth()/2)*Math.cos(Math.toRadians(gameArea.getStaff().getRotationAngle()))), gameArea.getStaff().getY()-((gameArea.getStaff().getWidth()/2)*Math.sin(Math.toRadians(gameArea.getStaff().getRotationAngle()))), speedX, speedY);
            Bullet bullet2 = new Bullet(gameArea.getStaff().getX()-((gameArea.getStaff().getWidth()/2)*Math.cos(Math.toRadians(gameArea.getStaff().getRotationAngle()))), gameArea.getStaff().getY()+((gameArea.getStaff().getWidth()/2)*Math.sin(Math.toRadians(gameArea.getStaff().getRotationAngle()))), speedX, speedY);
            
            if(gameArea.getGameState() == 1) {
                synchronized(spellLock) {
                    Bullets.add(bullet);
                    Bullets.add(bullet2);
                }
            }
        }
    }


    private class StopTask extends TimerTask {
        @Override
        public void run() {
            if(gameArea.getGameState() == 1){
                SpellController.getInstance().setHEX(false);
                timer.cancel(); // Cancel the timer after 30 seconds
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



    public ConcurrentLinkedQueue<Bullet> getBullets() {
        return Bullets;
    }

    
    public Object getLock() {
        return spellLock;
    }
}