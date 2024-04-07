package tr.edu.ku;

import java.util.ArrayList;

public class EditingArea {

    private ArrayList<SimpleBarrier> simpleBarriers;
	private ArrayList<ReinforcedBarrier> reinforcedBarriers;

    private CollisionHandler collision;

    public EditingArea() {
        simpleBarriers = new ArrayList<>();
        reinforcedBarriers = new ArrayList<>();
        
    }


    public void updateEditingState() {

    }


    public void checkValidPlacement() {

    }

    public void checkCorrectNum() {


    }

    public ArrayList<SimpleBarrier> getSimpleBarriers() {
        return simpleBarriers;
    }

    
    public ArrayList<ReinforcedBarrier> getReinforcedBarriers() {
        return reinforcedBarriers;
    }

    public void reset() {
        simpleBarriers.clear();
        reinforcedBarriers.clear();
    }
    
}
