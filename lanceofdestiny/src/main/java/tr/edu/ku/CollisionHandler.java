package tr.edu.ku;

import java.util.ArrayList;

public class CollisionHandler {

    private FireBall ball;
    private MagicalStaff paddle;
    private ArrayList<SimpleBarrier> simpleBarriers;
	private ArrayList<ReinforcedBarrier> reinforcedBarriers;
    private MovementHandler movement = new MovementHandler();

    public CollisionHandler(FireBall fireball, MagicalStaff staff, ArrayList<ReinforcedBarrier>  rbList, ArrayList<SimpleBarrier> sbList){
        this.ball = fireball;
        this.paddle = staff;
        this.reinforcedBarriers = rbList;
        this.simpleBarriers = sbList;
    }
    
    public void checkAnyCollision() {
        // Check collision between ball and paddle
        if (paddle.getPolygon().intersects(ball.getBounds())) {
            movement.reflect(paddle, ball);
        }
        
        // Check collision with simple barriers
        for (SimpleBarrier barrier : simpleBarriers) {
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds())) {           	
            	movement.reflect(barrier, ball);
            }
        }
        
        // Check collision with reinforced barriers
        for (ReinforcedBarrier barrier : reinforcedBarriers) {
            if (barrier.isVisible() && barrier != null && ball.intersects(barrier.getBounds())) {            	
                movement.reflect(barrier, ball);       
            }
        }
    }

}
