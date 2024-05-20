import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.ExplosiveBarrier;
import tr.edu.ku.Domain.ReinforcedBarrier;
import tr.edu.ku.Domain.RewardingBarrier;
import tr.edu.ku.Domain.SimpleBarrier;
import tr.edu.ku.GameArea.EditingArea;



/**
 * Requires: The selectedBarrier is not null.
 * Modifies: None.
 * Effects:  Throws an exception if the selectedBarrier is placed on a grid cell
 *           that already contains another barrier. Otherwise, does nothing.
 * @param selectedBarrier The barrier to be checked for valid placement.
 * @throws Exception if the selectedBarrier is placed on a grid cell with another barrier.
 */

public class EditingAreaTest {

    private EditingArea editingArea;
    private SimpleBarrier simpleBarrier;
    private ReinforcedBarrier reinforcedBarrier;

    @Before
    public void setUp() {
        editingArea = new EditingArea();
        simpleBarrier = new SimpleBarrier(100, 100, 50, 50, 0, 0);
        reinforcedBarrier = new ReinforcedBarrier(150, 150, 50, 50, 0, 0);
    }

    @Test
    public void testValidPlacementOnEmptyCell() {
        try {
            editingArea.checkValidPlacement(simpleBarrier);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testInvalidPlacementOnOccupiedCell() {
        try {
            editingArea.getGrid().setBarrier(1, 1, 0); // Place a barrier on cell (1, 1)
            simpleBarrier.setX(1*Constants.CELL_WIDTH + 16 ); // Place the simpleBarrier at the same position
            simpleBarrier.setY(1*Constants.CELL_HEIGHT + 15 + 40);
            editingArea.checkValidPlacement(simpleBarrier);
            fail("Exception should have been thrown");
        } catch (Exception e) {
            assertEquals("Barrier is on the same grid with another barrier", e.getMessage());
        }
    }

    @Test
    public void testPartialOverlap() {
        try {
            editingArea.getGrid().setBarrier(1, 1, 0); // Place a barrier on cell (1, 1)
            simpleBarrier.setX(1*Constants.CELL_WIDTH + 16  + 5); // Partially overlap the simpleBarrier
            simpleBarrier.setY(1*Constants.CELL_HEIGHT + 15 + 40 + 5);
            editingArea.checkValidPlacement(simpleBarrier);
            fail("Exception should have been thrown");
        } catch (Exception e) {
            assertEquals("Barrier is on the same grid with another barrier", e.getMessage());
        }
    }

  @Test
public void testPlacementAfterDeletingBarrier() {
    try {
        // Place a barrier on cell (2, 2)
        simpleBarrier.setX(2 * Constants.CELL_WIDTH + 16 ); // Assuming grid cell size is 50x50
        simpleBarrier.setY(2 * Constants.CELL_HEIGHT + 15 + 40 );
        editingArea.checkValidPlacement(simpleBarrier);
        editingArea.getGrid().setBarrier(2, 2, 0); // Place the barrier

        // Delete the barrier
        editingArea.getGrid().getCells()[2][2].deleteBarrier();

        // Place another barrier on the same cell after deletion
        ReinforcedBarrier newBarrier = new ReinforcedBarrier(2 * Constants.CELL_WIDTH + 16 , 2 * Constants.CELL_HEIGHT + 15 + 40 , 50, 50, 0, 0);
        editingArea.checkValidPlacement(newBarrier); // This should not throw an exception
    } catch (Exception e) {
        fail("Exception should not have been thrown for valid placement after deleting a barrier");
    }
}


    @Test
public void testPlacementOfDifferentBarrierTypes() {
    try {
        // Place a SimpleBarrier and check
        SimpleBarrier simpleBarrier = new SimpleBarrier(1 * Constants.CELL_WIDTH + 16, 1 * Constants.CELL_HEIGHT + 15 + 40, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, 0, 0);
        editingArea.checkValidPlacement(simpleBarrier);

        // Place a ReinforcedBarrier and check
        ReinforcedBarrier reinforcedBarrier = new ReinforcedBarrier(2 * Constants.CELL_WIDTH + 16, 2 * Constants.CELL_HEIGHT + 15 + 40, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, 0, 0);
        editingArea.checkValidPlacement(reinforcedBarrier);

        // Place an ExplosiveBarrier and check
        ExplosiveBarrier explosiveBarrier = new ExplosiveBarrier(3 * Constants.CELL_WIDTH + 16, 3 * Constants.CELL_HEIGHT + 15 + 40, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, 0, 0);
        editingArea.checkValidPlacement(explosiveBarrier);

        // Place a RewardingBarrier and check
        RewardingBarrier rewardingBarrier = new RewardingBarrier(4 * Constants.CELL_WIDTH + 16, 4 * Constants.CELL_HEIGHT + 15 + 40, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, 0, 0);
        editingArea.checkValidPlacement(rewardingBarrier);
    } catch (Exception e) {
        fail("Exception should not have been thrown for valid placement of different barrier types");
    }
}

}



