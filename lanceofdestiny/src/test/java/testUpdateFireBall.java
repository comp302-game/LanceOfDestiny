import java.awt.Point;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals; 
import org.junit.jupiter.api.Test;
import javax.swing.JTextField;
import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.FireBall;
import tr.edu.ku.Domain.MagicalStaff;
import tr.edu.ku.Domain.SimpleBarrier;
import tr.edu.ku.GameArea.Grid;
import tr.edu.ku.GameEngine.CollisionHandler;
import tr.edu.ku.GameEngine.MovementHandler;
import tr.edu.ku.MathBase;
import tr.edu.ku.Constants;
import tr.edu.ku.GameEngine.KeyboardInputHandler;
import java.awt.event.KeyEvent;

public class testUpdateFireBall{
    Grid grid = new Grid();
    FireBall ball = new FireBall(0, 0);
    SimpleBarrier bar;
    ArrayList<Barrier> bar_l = new ArrayList<Barrier>();
    CollisionHandler chandler;
    MovementHandler mhandler;
    KeyboardInputHandler khandler = new KeyboardInputHandler();

    public testUpdateFireBall(){

        Point p = MathBase.getGrid(100, 100);
        
        bar = new SimpleBarrier(100, 100, 100, 20,  (int)p.getY(), (int)p.getX());
        bar_l.add(bar);
        grid.setBarrier(bar.getRow(), bar.getColumn(), 0);
        chandler = new CollisionHandler(ball, new MagicalStaff(), grid, bar_l, new ArrayList<Barrier>());
        mhandler = new MovementHandler(chandler);
    }

    @Test void testNoCollision(){
        ball.setSpeedY(0);
        int result = mhandler.updateFireBall(ball);

        assertEquals(0, result);
    }

    @Test void testFallsBelowGameHeight(){
        ball.setX(0.0);
        ball.setY((double)Constants.GAMEPANEL_HEIGHT-1);
        ball.setSpeedY(2);
        
        khandler.keyPressed(new KeyEvent(new JTextField(), 0, 0, 0, KeyEvent.VK_X));
        
        int result = mhandler.updateFireBall(ball);
        assertEquals(1, result);
        assertEquals(false, KeyboardInputHandler.getX_Pressed());
    }

    @Test void testCollision(){

        double y_pos = bar.getRow()*Constants.CELL_HEIGHT + 40;
        double x_pos = (bar.getColumn()+1)*Constants.CELL_WIDTH;

        double x = y_pos - (Constants.FIREBALL_SIZE/2);
        double y = x_pos - (Constants.FIREBALL_SIZE/2);
       
        ball.setX(x);
        ball.setY(y);

        int result = mhandler.updateFireBall(ball);
        assertEquals(2, result);
    }

    @Test void testBallPositionUpdate(){
        ball.setSpeedX(14);
        ball.setSpeedY(15);

        double prev_x = ball.getX()+ball.getSpeedX();
        double prev_y = ball.getY()+ball.getSpeedY();

        mhandler.updateFireBall(ball);
        assertEquals(prev_x, ball.getX(), 0.001);
        assertEquals(prev_y, ball.getY(), 0.001);
    }

    @Test void testWallBounce(){
        ball.setX(2.0);
        ball.setY(0.0);

        ball.setSpeedX(-6);
        ball.setSpeedY(10);

        mhandler.updateFireBall(ball);
        assertEquals(4, ball.getX(), 0.001);
        assertEquals(10, ball.getY(), 0.001);

    }
}
