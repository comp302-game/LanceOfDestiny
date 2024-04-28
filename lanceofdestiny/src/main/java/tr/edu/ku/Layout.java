package tr.edu.ku;

import java.util.ArrayList;
import java.io.Serializable;

public class Layout implements Serializable{

    private static final long serialVersionUID = 1L;

    private ArrayList<SimpleBarrier> simpleBarriers = new ArrayList<>();
	private ArrayList<ReinforcedBarrier> reinforcedBarriers = new ArrayList<>();
    private ArrayList<ExplosiveBarrier> explosiveBarriers = new ArrayList<>();

    public Layout(ArrayList<SimpleBarrier> sbarriers, ArrayList<ReinforcedBarrier> rbarriers, ArrayList<ExplosiveBarrier> ebarriers) {
        
		this.simpleBarriers = sbarriers;
		this.reinforcedBarriers = rbarriers;
		this.explosiveBarriers = ebarriers;
    }

    public ArrayList<SimpleBarrier> getSimpleBarriers() {
        return simpleBarriers;
    }

    public ArrayList<ReinforcedBarrier> getReinforcedBarriers() {
        return reinforcedBarriers;
    }

    public ArrayList<ExplosiveBarrier> getExplosiveBarriers() {
        return explosiveBarriers;
    }

    public void setSimpleBarriers(ArrayList<SimpleBarrier> simpleBarriers) {
        this.simpleBarriers = simpleBarriers;
    }

    public void setReinforcedBarriers(ArrayList<ReinforcedBarrier> reinforcedBarriers) {
        this.reinforcedBarriers = reinforcedBarriers;
    }

    public void setExplosiveBarriers(ArrayList<ExplosiveBarrier> explosiveBarriers) {
        this.explosiveBarriers = explosiveBarriers;
    }

}
