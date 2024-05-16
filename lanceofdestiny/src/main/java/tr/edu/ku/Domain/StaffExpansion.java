package tr.edu.ku.Domain;

import java.util.Timer;
import java.util.TimerTask;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Spell;
import tr.edu.ku.Domain.SpellController;

public class StaffExpansion extends Spell {

    public StaffExpansion() {
        super("Magical Staff Expansion", 30);
    }


    public void activate(MagicalStaff staff) {
        SpellController.setMSE(true);
        staff.setWidth(Constants.STAFF_ENLARGED_WIDTH);
        staff.setHeight(Constants.STAFF_ENLARGED_HEIGHT);

        // Start timer for duration of spell
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                deactivate(staff);
            }
        }, 30 * 1000);
    }
    

    private void deactivate(MagicalStaff staff) {
        SpellController.setMSE(false);
        staff.setWidth(Constants.STAFF_WIDTH);
        staff.setHeight(Constants.STAFF_HEIGHT);
    }
}
