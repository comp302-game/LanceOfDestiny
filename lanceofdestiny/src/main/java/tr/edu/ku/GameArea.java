package tr.edu.ku;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GameArea extends JPanel {
	
	private ArrayList<SimpleBarrier> simpleBarriers;
	private static final int NUM_SIMPLE_BARRIERS = 10; // Number of simple barriers
	    
	private ArrayList<ReinforcedBarrier> reinforcedBarriers;
	private static final int NUM_REINFORCED_BARRIERS = 10; // Number of simple barriers
	    
	private MagicalStaff paddle;
	private FireBall ball;
	
	private MovementHandler movement;
	private CollisionHandler collision;
    
	
	public GameArea() {
        paddle = new MagicalStaff();
        ball = new FireBall();

        simpleBarriers = new ArrayList<>();
        reinforcedBarriers = new ArrayList<>();
        initBarriers();

		
		movement = new MovementHandler();
        collision = new CollisionHandler(ball, paddle, reinforcedBarriers, simpleBarriers);
        	
	}

    
	private void updateMovement() {

		movement.updateFireBall(ball);
		movement.updateStaff(paddle);
	}
	
    

    public void updateGameView() {
    	updateMovement();
		collision.checkAnyCollision();	
    }
    
    
    

    private void initBarriers() {
    	// initialize simple barriers
        int barrierWidth = 32; //  L/5 = 32
        int barrierHeight = 20;
        int barrierY = 100;
        int barrierSpacing = (GamePanel.WIDTH - NUM_SIMPLE_BARRIERS * barrierWidth) / (NUM_SIMPLE_BARRIERS + 1);

        for (int i = 0; i < NUM_SIMPLE_BARRIERS; i++) {
            int barrierX = (i + 1) * barrierSpacing + i * barrierWidth;
            simpleBarriers.add(new SimpleBarrier(barrierX, barrierY, barrierWidth, barrierHeight));
        }
        
        // initialize reinforced barriers
        int reinforcedBarrierWidth = 32; //  L/5 = 32
        int reinforcedBarrierHeight = 20;
        int reinforcedBarrierY = 200;
        int reinforcedBarrierSpacing = (GamePanel.WIDTH - NUM_REINFORCED_BARRIERS * reinforcedBarrierWidth) / (NUM_REINFORCED_BARRIERS + 1);

        for (int i = 0; i < NUM_REINFORCED_BARRIERS; i++) {
            int reinforcedBarrierX = (i + 1) * reinforcedBarrierSpacing + i * reinforcedBarrierWidth;
            reinforcedBarriers.add(new ReinforcedBarrier(reinforcedBarrierX, reinforcedBarrierY, reinforcedBarrierWidth, reinforcedBarrierHeight));
        }
       
    }


    
    
    
    //GETTERS AND SETTERS
	public List<SimpleBarrier> getSimpleBarriers() {
		return simpleBarriers;
	}

	public List<ReinforcedBarrier> getReinforcedBarriers() {
		return reinforcedBarriers;
	}


	public MagicalStaff getPaddle() {
		return paddle;
	}


	public FireBall getBall() {
		return ball;
	}


	// Reset the state of the GameArea
    public void reset() {
        paddle = new MagicalStaff();
        ball = new FireBall();
        simpleBarriers = new ArrayList<>();
        reinforcedBarriers = new ArrayList<>();
        initBarriers();
    }

}