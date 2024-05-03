package tr.edu.ku.Domain;

import java.util.Timer;
import java.util.TimerTask;

import tr.edu.ku.Constants;
import tr.edu.ku.SpellController;

public class EnlargementSpell extends Spell {

    public EnlargementSpell() {
        super("Overwhelming FireBall", 30);
    }


    public void activate(FireBall fireball) {
        SpellController.setOVM(true);
        fireball.setSize(Constants.FIREBALL_SIZE_ENLARGED);

        // Start timer for duration of spell
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                deactivate(fireball);
            }
        }, 30 * 1000);
    }
    

    private void deactivate(FireBall fireball) {
        SpellController.setOVM(false);
        fireball.setSize(Constants.FIREBALL_SIZE);
    }
}