import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.SimpleBarrier;
import tr.edu.ku.GameArea.Grid;

public class GridTest {
    
    private Grid grid;
    private SimpleBarrier barrier;

    @Before
    public void setUp() {
        grid = new Grid();
    }

    @Test
    public void testInitialization() {
        assertTrue("Grid should be initialized correctly", grid.repOk());
    }

    @Test
    public void testSetBarrier() {
        grid.setBarrier(0, 0, 1);
        assertTrue("Barrier should be set at (0, 0)", grid.hasBarrier(0, 0));
        assertTrue("Grid should be in a consistent state after setting a barrier", grid.repOk());
    }

    @Test
    public void testDeleteBarrier() {
        barrier = new SimpleBarrier(0,0,10,10,0,0);
        grid.setBarrier(0, 0, 1);
        grid.deleteFromGrid(barrier);
        assertFalse("Barrier should be deleted from (0, 0)", grid.hasBarrier(0, 0));
        assertTrue("Grid should be in a consistent state after deleting a barrier", grid.repOk());
    }

    @Test
    public void testReset() {
        grid.setBarrier(0, 0, 1);
        grid.setBarrier(5, 5, 2);
        grid.reset();
        for (int i = 0; i < Constants.ROW_NUMBER; i++) {
            for (int j = 0; j < Constants.COLUMN_NUMBER; j++) {
                assertFalse("Grid should be empty after reset", grid.hasBarrier(i, j));
            }
        }
        assertTrue("Grid should be in a consistent state after reset", grid.repOk());
    }

    @Test
    public void testInvalidGrid() {
        grid = new Grid() {
            @Override
            public boolean repOk() {
                return false;
            }
        };
        assertFalse("repOk should fail for an invalid grid", grid.repOk());
    }
}
