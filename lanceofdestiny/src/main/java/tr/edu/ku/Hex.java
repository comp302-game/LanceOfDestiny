package tr.edu.ku.Domain;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import tr.edu.ku.Constants;
import tr.edu.ku.SpellController;

public class Hex extends Spell {

    private int elapsedTime = 0;
    private Timer timer;
    private MagicalStaff staff;
    private ArrayList<Bullet> Bullets = new ArrayList<>();

    public Hex() {
        super("Hex", 30);
    }


    public void activate(MagicalStaff staff) {
        SpellController.setCurrentHEX(this);
        SpellController.setHEX(true);
        this.staff = staff;
        timer = new Timer();
        elapsedTime = 0;
        timer.scheduleAtFixedRate(new SpawnTask(), 0, 1000); // Spawn a new object every 1000 milliseconds (1 second)
        timer.schedule(new StopTask(), 30000); // Stop spawning after 30 seconds
    }


    private class SpawnTask extends TimerTask {
        @Override
        public void run() {
            double normalX = Math.cos(Math.toRadians(staff.getRotationAngle() + 90));
            double normalY = -Math.sin(Math.toRadians(staff.getRotationAngle() + 90));
            double speedX = normalX* Constants.HEX_SPEED;
            double speedY = normalY* Constants.HEX_SPEED;
            
            Bullet bullet = new Bullet(staff.getX()+((staff.getWidth()/2)*Math.cos(Math.toRadians(staff.getRotationAngle()))), staff.getY()-((staff.getWidth()/2)*Math.sin(Math.toRadians(staff.getRotationAngle()))), speedX, speedY);
            Bullet bullet2 = new Bullet(staff.getX()-((staff.getWidth()/2)*Math.cos(Math.toRadians(staff.getRotationAngle()))), staff.getY()+((staff.getWidth()/2)*Math.sin(Math.toRadians(staff.getRotationAngle()))), speedX, speedY);
            Bullets.add(bullet);
            Bullets.add(bullet2);
            elapsedTime += 1000; // Increment elapsed time
        }
    }


    private class StopTask extends TimerTask {
        @Override
        public void run() {
            SpellController.setHEX(false);
            timer.cancel(); // Cancel the timer after 30 seconds
        }
    }


    public ArrayList<Bullet> getBullets() {
        return Bullets;
    }
}
