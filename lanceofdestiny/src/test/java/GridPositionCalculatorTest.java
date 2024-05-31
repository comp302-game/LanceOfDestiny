import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.awt.Point;

import org.junit.Test;

import tr.edu.ku.Constants;
import tr.edu.ku.GameEngine.MathBase;

public class GridPositionCalculatorTest {

    @Test
    public void testValidPositionInGrid() throws Exception {
        Point result = MathBase.getGridPos(800, 450);
        assertEquals(new Point(8, 12), result); // Assuming Constants.CELL_HEIGHT = 40 and Constants.CELL_WIDTH = 40
    }


    @Test
    public void testBoundaryPositionLowerBound() throws Exception {
        Point result = MathBase.getGridPos(0, 40);
        assertEquals(new Point(0, 0), result); // Assuming the first row starts at y=40
    }

    @Test
    public void testPositionOutOfBoundaryNegativeCoordinates() {
        Exception exception = assertThrows(Exception.class, () -> {
            MathBase.getGridPos(-10, 450);
        });
        assertEquals("position is out of boundary", exception.getMessage());
    }

    @Test
    public void testPositionOutOfBoundaryExceedingMaxCoordinates() {
        Exception exception = assertThrows(Exception.class, () -> {
            MathBase.getGridPos(1700, 950);
        });
        assertEquals("position is out of boundary", exception.getMessage());
    }

    @Test
    public void testValidGridPosition() throws Exception {
        Point result = MathBase.getGridPos(160, 200); // Assuming grid size allows this
        int expectedRow = (int) Math.floor((200 - 40) / Constants.CELL_HEIGHT);
        int expectedCol = (int) Math.floor(160 / Constants.CELL_WIDTH);
        assertEquals(new Point(expectedRow, expectedCol), result);
    }

    @Test
    public void testPositionOnGridBoundary() throws Exception {
        Point result = MathBase.getGridPos(Constants.CELL_WIDTH * Constants.COLUMN_NUMBER, 40 + Constants.CELL_HEIGHT * Constants.ROW_NUMBER);
        assertEquals(new Point(Constants.ROW_NUMBER, Constants.COLUMN_NUMBER), result);
    }

    @Test
    public void testPositionJustOutsideUpperBoundary() {
        Exception exception = assertThrows(Exception.class, () -> {
            MathBase.getGridPos(1600.1, 900);
        });
        assertEquals("position is out of boundary", exception.getMessage());
    }

    @Test
    public void testPositionJustOutsideLowerBoundary() {
        Exception exception = assertThrows(Exception.class, () -> {
            MathBase.getGridPos(0, 39.9); // Assuming y=40 is the first row boundary
        });
        assertEquals("position is out of grid", exception.getMessage());
    }

}
