package tr.edu.ku.GameArea;
import java.io.Serializable;
import java.util.ArrayList;

import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.FireBall;
import tr.edu.ku.Domain.HollowBarrier;
import tr.edu.ku.Domain.MagicalStaff;
import tr.edu.ku.Domain.Ymir.DoubleAccel;
import tr.edu.ku.Domain.Ymir.InfiniteVoid;
import tr.edu.ku.Domain.Ymir.Ymir;

public class Game implements Serializable {

	private static final long serialVersionUID = 2L;

    //Game objects to be saved
	private Grid grid;
	private ArrayList<Barrier> allBarriers;
	private ArrayList<Barrier> movingBarriers;

	private int HEX_number = 0;
	private int MSE_number = 0;
	private int OFB_number = 0;
	private int FELIX_number = 0;
	
	private MagicalStaff staff;
	private FireBall fireBall;

	private double score;
	private boolean isGameOver;
	private int lives;

	private int lastSpell;
	private int secondLastSpell;


    public Game(Grid grid, ArrayList<Barrier> allBarriers, ArrayList<Barrier> movingBarriers, MagicalStaff staff, FireBall fireBall, double score, boolean isGameOver, int lives, int HEX_number, int MSE_number, int OFB_number, int FELIX_number, Ymir ymir) {

		this.grid = grid;
		this.staff = staff;
		this.fireBall = fireBall;
		this.score = score;
		this.isGameOver = isGameOver;
		this.lives = lives;
		this.HEX_number = HEX_number;
		this.MSE_number = MSE_number;
		this.OFB_number = OFB_number;
		this.FELIX_number = FELIX_number;
		this.allBarriers = allBarriers;
		this.movingBarriers = movingBarriers;

		if(ymir.getLastSpelll() instanceof DoubleAccel) {
			lastSpell = 0;
		}

		else if(ymir.getLastSpelll() instanceof HollowBarrier) {
			lastSpell = 1;
		}

		else if(ymir.getLastSpelll() instanceof InfiniteVoid) {
			lastSpell = 2;
		}

		else if(ymir.getLastSpelll() == null) {
			lastSpell = -1;
		}


		if(ymir.getSecondLastSpelll() instanceof DoubleAccel) {
			secondLastSpell = 0;
		}

		else if(ymir.getSecondLastSpelll() instanceof HollowBarrier) {
			secondLastSpell = 1;
		}

		else if(ymir.getSecondLastSpelll() instanceof InfiniteVoid) {
			secondLastSpell = 2;
		}

		else if(ymir.getSecondLastSpelll() == null) {
			secondLastSpell = -1;
		}
    }
    



//GETTER SETTER 
    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid g) {
		this.grid = g;
	}

	public int getOFB() {
		return OFB_number;
	}

	public int getMSE() {
		return MSE_number;
	}

	public int getFELIX() {
		return FELIX_number;
	}

	public int getHEX() {
		return HEX_number;
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

	public ArrayList<Barrier> getAllBarriers() {
        return allBarriers;
    }

    public void setAllBarriers(ArrayList<Barrier> list) {
        this.allBarriers = list;
    }


	public ArrayList<Barrier> getMovingBarriers() {
        return movingBarriers;
    }

    public void setMovingBarriers(ArrayList<Barrier> list) {
        this.movingBarriers = list;
    }

	public int getlastSpell(){
		return lastSpell;
	}

	public int getSecondLastSpell(){
		return secondLastSpell;
	}

	public void setLastSpell(int s) {
		lastSpell = s;
	}

	public void setSecondLastSpell(int s) {
		secondLastSpell = s;
	}
}
