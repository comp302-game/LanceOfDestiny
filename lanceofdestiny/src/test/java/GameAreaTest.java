import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import tr.edu.ku.GameArea.GameArea;
import tr.edu.ku.GameArea.Grid;
import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.MagicalStaff;
import tr.edu.ku.Domain.FireBall;
import tr.edu.ku.GameEngine.MovementHandler;
import tr.edu.ku.GameEngine.CollisionHandler;
import tr.edu.ku.Constants;

public class GameAreaTest {

    private int expectedBarrierCount;

    @Before
    public void setup() {
        expectedBarrierCount = 5; // example expected barrier count
    }

    // method to create valid grid with barriers
    private Grid createValidGrid() {
        Grid grid = new Grid();
        // adding barriers to grid
        grid.setBarrier(0, 0, 1);
        grid.setBarrier(1, 1, 2);
        grid.setBarrier(2, 2, 3);
        grid.setBarrier(3, 3, 4);
        grid.setBarrier(4, 4, 5);
        return grid;
    }

    // method to create empty grid without barriers
    private Grid createEmptyGrid() {
        return new Grid();
    }

    // method to create grid with specific barriers
    private Grid createGridWithSpecificBarriers() {
        Grid grid = new Grid();
        // adding specific barriers to the grid
        grid.setBarrier(0, 0, 1);
        grid.setBarrier(1, 1, 2);
        return grid;
    }

    // method to get expected barriers from grid
    private List<Barrier> getExpectedBarriers(Grid grid) {
        List<Barrier> barriers = new ArrayList<>();
        for (int i = 0; i < Constants.ROW_NUMBER; i++) {
            for (int j = 0; j < Constants.COLUMN_NUMBER; j++) {
                if (grid.hasBarrier(i, j)) {
                    barriers.add(grid.getCells()[i][j].getBarrier());
                }
            }
        }
        return barriers;
    }

    // Black-Box Test
    @Test
    public void testConstructorWithValidGrid() {
        Grid validGrid = createValidGrid();
        GameArea gameArea = new GameArea(validGrid);
        assertEquals(expectedBarrierCount, gameArea.getAllBarriers().size());
    }

    // Black-Box Test
    @Test
    public void testConstructorWithEmptyGrid() {
        Grid emptyGrid = createEmptyGrid();
        GameArea gameArea = new GameArea(emptyGrid);
        assertEquals(0, gameArea.getAllBarriers().size());
    }

    // Black-Box Test
    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullGrid() {
        GameArea gameArea = new GameArea(null);
    }

    // Glass-Box Test
    @Test
    public void testInitializationOfBarriers() {
        Grid gridWithBarriers = createGridWithSpecificBarriers();
        GameArea gameArea = new GameArea(gridWithBarriers);
        List<Barrier> expectedBarriers = getExpectedBarriers(gridWithBarriers);
        assertEquals(expectedBarriers, gameArea.getAllBarriers());
    }

    // New Glass-Box Test
    @Test
    public void testInitializationOfStaffAndBall() {
        Grid validGrid = createValidGrid();
        GameArea gameArea = new GameArea(validGrid);
        // verifying correct staff initialization
        MagicalStaff staff = gameArea.getStaff();
        assertNotNull(staff);
        // verifying correct ball initialization
        FireBall ball = gameArea.getBall();
        assertNotNull(ball);
        //verifying initial position of the ball
        double expectedBallX = (Constants.GAMEPANEL_WIDTH / 2 - Constants.FIREBALL_SIZE / 2);
        double expectedBallY = 800.0;
        assertEquals(expectedBallX, ball.getX(), 0.001);
        assertEquals(expectedBallY, ball.getY(), 0.001);
    }
}
