package tr.edu.ku;

public class MovementHandler {

	
    public void reflect(SimpleBarrier barrier, FireBall ball) {
		
		double centerBallx= ball.getX() + (ball.getSize()/ 2);
    	double centerBally= ball.getY() + (ball.getSize()/ 2);
    	double centerBarrier_x= barrier.getX() + (barrier.getWidth() / 2);
    	double centerBarrier_y= barrier.getY() + (barrier.getHeight() / 2);
    	
    	double dx = Math.abs(centerBallx-centerBarrier_x);
    	double dy = Math.abs(centerBally-centerBarrier_y);
    	
    	if(dx == 0) {
    		dx = (float) 0.001;
    	}
   	
    	double slope = dy/dx;

		//calculate boundary slope
		double dx2 = barrier.getWidth()/2 + ball.getSize()/2 - ball.getSpeedX();
		double dy2 = barrier.getHeight()/2 + ball.getSize()/2 - ball.getSpeedY();
    	double cornerSlope = Math.abs(dy2/dx2);
    	
    	System.out.println(slope);
    	
        // Determine collision side based on ball's movement direction
        if (slope > cornerSlope) {
            // Collision from top and bottom
            ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
            
        } 
        
        if (slope < cornerSlope) {
            // Collision from the sides
            ball.setSpeedX(ball.getSpeedX() * -1); // Reverse horizontal direction
        } 

        if (slope == cornerSlope){
            // Collision from top or bottom side
            ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
            ball.setSpeedX(ball.getSpeedX() * -1); // Reverse vertical direction
        }
        barrier.setVisible(false);	
	}
	
	
	
	public void reflect(ReinforcedBarrier barrier, FireBall ball) {
		
		double centerBallx= ball.getX() + (ball.getSize()/ 2);
    	double centerBally= ball.getY() + (ball.getSize()/ 2);
    	double centerBarrier_x= barrier.getX() + (barrier.getWidth() / 2);
    	double centerBarrier_y= barrier.getY() + (barrier.getHeight() / 2);
    	
    	double dx = Math.abs(centerBallx-centerBarrier_x);
    	double dy = Math.abs(centerBally-centerBarrier_y);
    	
    	if(dx == 0) {
    		dx = (float) 0.001;
    	}
   	
    	double slope = dy/dx;

    	//calculate boundary slope
		double dx2 = barrier.getWidth()/2 + ball.getSize()/2 - ball.getSpeedX();
		double dy2 = barrier.getHeight()/2 + ball.getSize()/2 - ball.getSpeedY();
    	double cornerSlope = Math.abs(dy2/dx2);
    	

    	System.out.println(slope);
    	
        // Determine collision side based on ball's movement direction
        if (slope > cornerSlope) {
            // Collision from top and bottom
            ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
            
        } 
        
        if (slope < cornerSlope) {
            // Collision from the sides
            ball.setSpeedX(ball.getSpeedX() * -1); // Reverse horizontal direction
        } 

        if (slope == cornerSlope){
            // Collision from top or bottom side
            ball.setSpeedY(ball.getSpeedY() * -1); // Reverse vertical direction
            ball.setSpeedX(ball.getSpeedX() * -1); // Reverse vertical direction
        }

		System.out.println("call hit");
        barrier.hit();	
	}


	
	
	public void reflect(MagicalStaff staff, FireBall ball) {

		double staffCenterX = staff.getX();
        double staffCenterY = staff.getY();
		double centerBallx= ball.getX() + (ball.getSize()/ 2);
    	double centerBally= ball.getY() + (ball.getSize()/ 2);
        
		// Calculate the normal vector of the staff at top and bot surface
        double normalX = Math.cos(Math.toRadians(staff.getRotationAngle() + 90));
        double normalY = -Math.sin(Math.toRadians(staff.getRotationAngle() + 90));

        // Calculate the normal vector of the staff side surfaces at the point of collision
        double normalX1 = Math.cos(Math.toRadians(staff.getRotationAngle()));
        double normalY1 = -Math.sin(Math.toRadians(staff.getRotationAngle()));
        
        // Calculate the vector from the ball to the staff center
        double ballToStaffCenterX = Math.abs(staffCenterX - centerBallx);
        double ballToStaffCenterY = Math.abs(staffCenterY - centerBally);

		double slope = ballToStaffCenterY / ballToStaffCenterX;
		
		
		//Calculate boundaries
		double dx1 = Math.cos(Math.toRadians(staff.getRotationAngle()))*(staff.getWidth()/2 + ball.getSize() - ball.getSpeedX()) - Math.sin(Math.toRadians(staff.getRotationAngle()))*(ball.getSize() - ball.getSpeedY());
		double dy1 = -Math.sin(Math.toRadians(staff.getRotationAngle()))*(staff.getWidth()/2 + ball.getSize() - ball.getSpeedX()) - Math.cos(Math.toRadians(staff.getRotationAngle()))*(ball.getSize() - ball.getSpeedY());

		double dx2 = Math.cos(Math.toRadians(staff.getRotationAngle()))*(staff.getWidth()/2 + ball.getSize() - ball.getSpeedX()) + Math.sin(Math.toRadians(staff.getRotationAngle()))*(ball.getSize() - ball.getSpeedY());
		double dy2 = -Math.sin(Math.toRadians(staff.getRotationAngle()))*(staff.getWidth()/2 + ball.getSize() - ball.getSpeedX()) + Math.cos(Math.toRadians(staff.getRotationAngle()))*(ball.getSize() - ball.getSpeedY());

		double limit1 = Math.abs(dy1/dx1);
		double limit2 = Math.abs(dy2/dx2);

		if( (limit2 <= slope && limit1 >= slope || limit1 <= slope && limit2 >= slope) ) {
       	 	// Calculate the dot product of the ball-to-staff-center vector and the normal vector
        	double dotProduct = ball.getSpeedX() * normalX1 + ball.getSpeedY() * normalY1;
        
        	// Calculate the reflected direction vector
        	double reflectedDirX = ball.getSpeedX() - 2 * dotProduct * normalX1;
        	double reflectedDirY = ball.getSpeedY() - 2 * dotProduct * normalY1;

			// Update the ball's speed components based on the reflected direction
			ball.setSpeedX(reflectedDirX);
			ball.setSpeedY(reflectedDirY);
	
		}

		else {
        	// Calculate the dot product of the ball-to-staff-center vector and the normal vector
        	double dotProduct = ball.getSpeedX() * normalX + ball.getSpeedY() * normalY;
        
        	// Calculate the reflected direction vector
        	double reflectedDirX = ball.getSpeedX() - 2 * dotProduct * normalX;
        	double reflectedDirY = ball.getSpeedY() - 2 * dotProduct * normalY;

			// Update the ball's speed components based on the reflected direction
			ball.setSpeedX(reflectedDirX);
			ball.setSpeedY(reflectedDirY);

		}


	}
	
	
	public void updateStaff(MagicalStaff staff) {
		 if (KeyboardInputHandler.getLeftPressed() && staff.getX() > staff.getWidth()/2) {
	            staff.setX(staff.getX() - staff.getSpeed());
	        }
	     if (KeyboardInputHandler.getRightPressed() && staff.getX() < GamePanel.WIDTH - staff.getWidth()/2) {
	    	 staff.setX(staff.getX() + staff.getSpeed());
	        }
		if (KeyboardInputHandler.getA_Pressed() && staff.getRotationAngle() < 45) {
	    	 staff.setRotationAngle(staff.getRotationAngle() + staff.getRotationSpeed()); // Adjust rotation 
	        }
		if (KeyboardInputHandler.getD_Pressed() && staff.getRotationAngle() > -45) {
	    	 staff.setRotationAngle(staff.getRotationAngle() - staff.getRotationSpeed()); // Adjust rotation 
	        }

		
	}
	
	
	
	public void updateFireBall(FireBall ball) {
		ball.setX((int) (ball.getX() + ball.getSpeedX()));
		ball.setY((int) (ball.getY() + ball.getSpeedY()));
       

        // Bounce off walls
        if (ball.getX() <= 0 || ball.getX() >= GamePanel.WIDTH - ball.getSize()) {
            ball.setSpeedX(ball.getSpeedX()* -1);
        }
        if (ball.getY() <= 0) {
        	 ball.setSpeedY(ball.getSpeedY()* -1);
        }
	}

}
