package tr.edu.ku.GameEngine;

import java.awt.Point;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.FireBall;

public class MathBase {
    //OVERVIEW: MathBase class offers mathematical base for the calculation of the grid position given the object positions or coordinate positions itself.

    public static Point getGridPos(Barrier barrier) {
        //Effects: Returns the grid position of the provided barrier
        double x_pos = barrier.getCenterX();
        double y_pos = barrier.getCenterY();

        double row = (y_pos - 40) / Constants.CELL_HEIGHT;
        double col = x_pos / Constants.CELL_WIDTH;

        return new Point((int) Math.floor(row), (int) Math.floor(col));
    }


    public static Point getGridPos(double x_pos, double y_pos) throws Exception{
        //Requires: x_pos and y_pos are inside 1600x900 rectangle.
        //          x_pos and y_pos should be in grid
        //Effects: Returns the grid position of the provided coordinate positions.
        if(x_pos < 0 || y_pos < 0 || x_pos > 1600 || y_pos > 900) {
            throw new Exception("position is out of boundary");
        }

        double row = (y_pos - 40) / Constants.CELL_HEIGHT;
        double col = x_pos / Constants.CELL_WIDTH;

        if(row<0 || row>Constants.ROW_NUMBER || col<0 || col>Constants.COLUMN_NUMBER) {
            throw new Exception("position is out of grid");
        }

        return new Point((int) Math.floor(row), (int) Math.floor(col));
    }


    public static Point getGridPos(FireBall ball) {
        //Effects: Returns the grid position of the provided coordinate positions.

        double x_pos = ball.getX() + (Constants.FIREBALL_SIZE/2);
        double y_pos = ball.getY() + (Constants.FIREBALL_SIZE/2);

        double row = (y_pos - 40) / Constants.CELL_HEIGHT;
        double col = x_pos / Constants.CELL_WIDTH;

        return new Point((int) Math.floor(row), (int) Math.floor(col));
    }
}
