package tr.edu.ku.GameArea;

import java.util.ArrayList;

import javax.swing.JPanel;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.EnlargementSpell;
import tr.edu.ku.Domain.ExplosiveBarrier;
import tr.edu.ku.Domain.FelixFelicis;
import tr.edu.ku.Domain.FireBall;
import tr.edu.ku.Domain.Hex;
import tr.edu.ku.Domain.MagicalStaff;
import tr.edu.ku.Domain.ReinforcedBarrier;
import tr.edu.ku.Domain.RewardingBarrier;
import tr.edu.ku.Domain.SimpleBarrier;
import tr.edu.ku.Domain.StaffExpansion;
import tr.edu.ku.GameEngine.CollisionHandler;
import tr.edu.ku.GameEngine.KeyboardInputHandler;
import tr.edu.ku.GameEngine.MovementHandler;
import tr.edu.ku.SpellController;

public class GameArea extends JPanel {
	
	private ArrayList<SimpleBarrier> simpleBarriers = new ArrayList<>();
	private ArrayList<ReinforcedBarrier> reinforcedBarriers= new ArrayList<>();
	private ArrayList<ExplosiveBarrier> explosiveBarriers= new ArrayList<>();
	private ArrayList<RewardingBarrier> rewardingBarriers= new ArrayList<>();

	private ArrayList<EnlargementSpell> OFB = new ArrayList<>();
	private ArrayList<StaffExpansion> MSE = new ArrayList<>();
	private ArrayList<FelixFelicis> FELIX = new ArrayList<>();
	private ArrayList<Hex> HEX = new ArrayList<>();


	private MagicalStaff paddle;
	private FireBall ball;

	private double score = 0;
	private boolean isGameOver = false;
	private int lives = 3;
	private int progression = 0;
	
	private long gameStartingTime = System.currentTimeMillis(); // Record the game starting time
	private MovementHandler movement;
    private CollisionHandler collision;
	
	public GameArea(Layout layout) { //Form a game area using the spesified layout
        paddle = new MagicalStaff();
        ball = new FireBall((Constants.GAMEPANEL_WIDTH / 2 - Constants.FIREBALL_SIZE / 2), 800.0);

        // Create deep copies of barrier lists from the layout
        initBarriers(layout);

        collision = new CollisionHandler(ball, paddle, reinforcedBarriers, simpleBarriers, explosiveBarriers, rewardingBarriers);
		movement = new MovementHandler(collision);

        KeyboardInputHandler.setXPressed(false);
	}

    
	public void updateGame() {
		int fireball_incident = movement.updateFireBall(ball);
		if(fireball_incident==1) { //fireball has fallen below
			lives--;
			if(lives==0) {
				isGameOver = true;
			}

			ball.reset(); // reset the ball position after fall
			paddle.reset();

		} else if (fireball_incident==2) { //Score calculation
			long currentTime = System.currentTimeMillis(); // Get the current time
        	double timeDifference = (currentTime - gameStartingTime) / 1000.0; // Convert milliseconds to seconds
			double scoreIncrement = 300 / timeDifference;
			score = score + scoreIncrement;
		}
		
		movement.updateStaff(paddle);
		movement.updateBarriers(reinforcedBarriers, simpleBarriers, explosiveBarriers, rewardingBarriers);

		if(SpellController.is_HEX_Active()) {
			movement.updateBullets(SpellController.getCurrentHEX().getBullets());
		}

		int staff_col = collision.checkStaffCollisions();

		if(staff_col == 1) {
			lives--;
			if(lives==0) {
				isGameOver = true;
			}
		}

		//create and add spells to inventory
		else if (staff_col == 2) {
			if (progression == 0) {
				HEX.add(new Hex());
				progression++;
			}
			else if(progression == 1) {
				OFB.add(new EnlargementSpell());
				progression++;
			}
			else if (progression == 2) {
				MSE.add(new StaffExpansion());
				progression++;
			}
			else if (progression == 3) {
				FELIX.add(new FelixFelicis());
				progression = 0;
			}
				
		}
	}
    
    

    private void initBarriers(Layout layout) { //Method to clone every barrier into gamearea barrier lists from layout object

		setSimpleBarriers(layout.getSimpleBarriers());
		setReinforcedBarriers(layout.getReinforcedBarriers());
		setExplosiveBarriers(layout.getExplosiveBarriers());
		setRewardingBarriers(layout.getRewardingBarriers());
    }

	
	//Method to load every barrier into gamearea barrier lists. This method loads the saved game into  gamearea.
	//It resets all the game objects and clone the saved ones. Including every game info (lives, score ...)
    public void LoadSavedGame(Game game) { 
		
        reset();

		setSimpleBarriers(game.getSimpleBarriers());
		setReinforcedBarriers(game.getReinforcedBarriers());
		setExplosiveBarriers(game.getExplosiveBarriers());
		setRewardingBarriers(game.getRewardingBarriers());

		setMSE(game.getMSE());
		setFELIX(game.getFELIX());
		setHEX(game.getHEX());
		setOFB(game.getOFB());

		setPaddle(game.getStaff());
		setBall(game.getBall());

		setScore(game.getScore());
		setLives(game.getLives());
		setGameOver(game.isGameOver());
		
		SpellController.reset();
        collision = new CollisionHandler(ball, paddle, reinforcedBarriers, simpleBarriers, explosiveBarriers, rewardingBarriers);
		movement = new MovementHandler(collision);
    }


	private void reset() {
        simpleBarriers.clear();
        reinforcedBarriers.clear();
        explosiveBarriers.clear();
		ball = null;
		paddle = null;
		KeyboardInputHandler.setXPressed(false);
	}

	

    //GETTERS AND SETTERS

	public ArrayList<SimpleBarrier> getSimpleBarriers() {
		return simpleBarriers;
	}

	public ArrayList<ReinforcedBarrier> getReinforcedBarriers() {
		return reinforcedBarriers;
	}

	public ArrayList<ExplosiveBarrier> getExplosiveBarriers() {
		return explosiveBarriers;
	}

	public ArrayList<RewardingBarrier> getRewardingBarriers() {
		return rewardingBarriers;
	}


	public void setSimpleBarriers(ArrayList<SimpleBarrier> sBarriers) {
		this.simpleBarriers = sBarriers;
	}

	public void setReinforcedBarriers(ArrayList<ReinforcedBarrier> rBarriers) {
		this.reinforcedBarriers = rBarriers;
	}

	public void setExplosiveBarriers(ArrayList<ExplosiveBarrier> eBarriers) {
		this.explosiveBarriers = eBarriers;
	}

	public void setRewardingBarriers(ArrayList<RewardingBarrier> wBarriers) {
		this.rewardingBarriers = wBarriers;
	}


	public ArrayList<EnlargementSpell> getOFB() {
		return OFB;
	}

	public ArrayList<StaffExpansion> getMSE() {
		return MSE;
	}

	public ArrayList<FelixFelicis> getFELIX() {
		return FELIX;
	}

	public ArrayList<Hex> getHEX() {
		return HEX;
	}


	public void setHEX(ArrayList<Hex> hlist) {
		this.HEX = hlist;
	}

	public void setMSE(ArrayList<StaffExpansion> slist) {
		this.MSE = slist;
	}

	public void setOFB(ArrayList<EnlargementSpell> elist) {
		this.OFB = elist;
	}

	public void setFELIX(ArrayList<FelixFelicis> flist) {
		this.FELIX = flist;
	}



	public MagicalStaff getPaddle() {
		return paddle;
	}

	public void setPaddle(MagicalStaff staff) {
		this.paddle = staff;
	}


	public FireBall getBall() {
		return ball;
	}

	public void setBall(FireBall fireball) {
		this.ball = fireball;
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

	public void setScore(double new_score) {
		score = new_score;
	}

	public void setGameOver(Boolean state) {
		isGameOver = state;
	}

	public void setLives(int new_life) {
		lives = new_life;
	}

	public boolean isGameFinished() {
		if(simpleBarriers.isEmpty() && reinforcedBarriers.isEmpty() && explosiveBarriers.isEmpty() && rewardingBarriers.isEmpty()){
			return true;
		}
		else {return false;}
	}



}