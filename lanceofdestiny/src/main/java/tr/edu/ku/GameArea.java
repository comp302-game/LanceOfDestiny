package tr.edu.ku;

import java.util.ArrayList;

import javax.swing.*;

public class GameArea extends JPanel {
	
	private ArrayList<SimpleBarrier> simpleBarriers = new ArrayList<>();
	private ArrayList<ReinforcedBarrier> reinforcedBarriers= new ArrayList<>();
	private ArrayList<ExplosiveBarrier> explosiveBarriers= new ArrayList<>();
	private long gameStartingTime = System.currentTimeMillis(); // Record the game starting time

	private MagicalStaff paddle;
	private FireBall ball;

	private double score = 0;
	private boolean isGameOver = false;
	private int lives = 3;
	
	
	private MovementHandler movement;
    private CollisionHandler collision;
	
	public GameArea(Layout layout) { //Form a game area using the spesified layout
        paddle = new MagicalStaff();
        ball = new FireBall();

        // Create deep copies of barrier lists from the layout
        initBarriers(layout);

        collision = new CollisionHandler(ball, paddle, reinforcedBarriers, simpleBarriers, explosiveBarriers);
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
		
		movement.updateBarriers(reinforcedBarriers, simpleBarriers, explosiveBarriers);


		if(collision.checkStaffCollisions() == 1) {
			lives--;
			if(lives==0) {
				isGameOver = true;
			}
		}
	}
    
    

    private void initBarriers(Layout layout) { //Method to clone every barrier into gamearea barrier lists from layout object
		for(int i = 0; i<layout.getSimpleBarriers().size(); i++){
			SimpleBarrier temp = layout.getSimpleBarriers().get(i);
			try {
				SimpleBarrier sBarrier = (SimpleBarrier) temp.clone();
				simpleBarriers.add(sBarrier); 
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		for(int i = 0; i<layout.getReinforcedBarriers().size(); i++){
			ReinforcedBarrier temp = layout.getReinforcedBarriers().get(i);
			try {
				ReinforcedBarrier rBarrier = (ReinforcedBarrier) temp.clone();
				reinforcedBarriers.add(rBarrier); 
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		for(int i = 0; i<layout.getExplosiveBarriers().size(); i++){
			ExplosiveBarrier temp = layout.getExplosiveBarriers().get(i);
			try {
				ExplosiveBarrier eBarrier = (ExplosiveBarrier) temp.clone();
				explosiveBarriers.add(eBarrier); 
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

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


	public MagicalStaff getPaddle() {
		return paddle;
	}


	public FireBall getBall() {
		return ball;
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
		if(simpleBarriers.isEmpty() && reinforcedBarriers.isEmpty() && explosiveBarriers.isEmpty()){
			return true;
		}
		else {return false;}
	}

	
	//Method to clone every barrier into gamearea barrier lists. This method loads the saved game into  gamearea.
	//It resets all the game objects and clone the saved ones. Including every game info (lives, score ...)
    public void LoadSavedGame(SavedGame game) { 
		
        reset();

		for(int i = 0; i<game.getSimpleBarriers().size(); i++){
			SimpleBarrier temp = game.getSimpleBarriers().get(i);
			try {
				SimpleBarrier sBarrier = (SimpleBarrier) temp.clone();
				simpleBarriers.add(sBarrier); 
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		for(int i = 0; i<game.getReinforcedBarriers().size(); i++){
			ReinforcedBarrier temp = game.getReinforcedBarriers().get(i);
			try {
				ReinforcedBarrier rBarrier = (ReinforcedBarrier) temp.clone();
				reinforcedBarriers.add(rBarrier); 
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		for(int i = 0; i<game.getExplosiveBarriers().size(); i++){
			ExplosiveBarrier temp = game.getExplosiveBarriers().get(i);
			try {
				ExplosiveBarrier eBarrier = (ExplosiveBarrier) temp.clone();
				explosiveBarriers.add(eBarrier); 
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		try {
				ball = (FireBall) game.getBall().clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		
		try {
				paddle = (MagicalStaff) game.getStaff().clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		
		setScore(game.getScore());
		isGameOver = game.isGameOver();
		setLives(game.getLives());

        collision = new CollisionHandler(ball, paddle, reinforcedBarriers, simpleBarriers, explosiveBarriers);
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


}