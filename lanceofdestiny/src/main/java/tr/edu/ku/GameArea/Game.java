package tr.edu.ku.GameArea;
import java.io.Serializable;
import java.util.*;

import tr.edu.ku.Domain.SimpleBarrier;

public class Game implements Serializable {

	private static final long serialVersionUID = 2L;

    //Game objects to be saved
	private ArrayList<SimpleBarrier> simpleBarriers = new ArrayList<>();
	private ArrayList<ReinforcedBarrier> reinforcedBarriers= new ArrayList<>();
	private ArrayList<ExplosiveBarrier> explosiveBarriers= new ArrayList<>();
	private MagicalStaff staff;
	private FireBall fireBall;

	private double score;
	private boolean isGameOver;
	private int lives;


    public Game(ArrayList<SimpleBarrier> simpleBarriers, ArrayList<ReinforcedBarrier> reinforcedBarriers, ArrayList<ExplosiveBarrier> explosiveBarriers, MagicalStaff staff, FireBall fireBall, double score, boolean isGameOver, int lives) {

		this.simpleBarriers = simpleBarriers;
		this.reinforcedBarriers = reinforcedBarriers;
		this.explosiveBarriers = explosiveBarriers;
		this.staff = staff;
		this.fireBall = fireBall;
		this.score = score;
		this.isGameOver = isGameOver;
		this.lives = lives;
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

	public FireBall getBall() {
		return fireBall;
	}

	public MagicalStaff getStaff() {
		return staff;
	}

	public double getScore() {
		return score;
	}

	public int getLives() {
		return lives;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

}
