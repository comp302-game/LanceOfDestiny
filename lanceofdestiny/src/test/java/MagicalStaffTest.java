import static org.junit.Assert.*;

import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.Arrays;

import org.junit.Test;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.MagicalStaff;

public class MagicalStaffTest {

    @Test
    public void testDefaultPosition() {
        MagicalStaff staff = new MagicalStaff();
        Polygon polygon = staff.getPolygon();
        // Assumes default position is the center bottom of the game panel
        assertTrue("Default position test", polygonContains(polygon, Constants.GAMEPANEL_WIDTH / 2, Constants.GAMEPANEL_HEIGHT - 40));
    }
    
    @Test
    public void testRotation() {
        MagicalStaff staff = new MagicalStaff();
        staff.setRotationAngle(90); // Rotate 90 degrees
        Polygon polygon = staff.getPolygon();
        // Check if the top right corner becomes the new bottom right corner
        assertTrue("Rotation test", polygonContains(polygon, Constants.GAMEPANEL_WIDTH / 2, Constants.GAMEPANEL_HEIGHT - 20 - staff.getHeight()));
    }

    @Test
    public void testBoundaryPosition() {
        MagicalStaff staff = new MagicalStaff();
        staff.setX(0); // Move to the left edge
        staff.setY(0); // Move to the top edge
        Polygon polygon = staff.getPolygon();
        assertTrue("Boundary position test", polygonContains(polygon, 0, 0));
    }

    @Test
    public void testWidthAndHeightChange() {
        MagicalStaff staff = new MagicalStaff();
        staff.setWidth(200);
        staff.setHeight(100);
        Polygon polygon = staff.getPolygon();
        // Verify the polygon reflects new dimensions
        assertEquals("Width change test", 200, polygon.getBounds().width);
        assertEquals("Height change test", 100, polygon.getBounds().height);
    }


    // Utility method to check if a polygon contains a point
    private boolean polygonContains(Polygon polygon, int x, int y) {
        return polygon.contains(new Point2D.Double(x, y));
    }


    @Test
    public void testExtremeRotation() {
        MagicalStaff staff = new MagicalStaff();
        staff.setRotationAngle(0); // No rotation
        Polygon polygonNoRotation = staff.getPolygon();
        
        staff.setRotationAngle(360); // Full rotation
        Polygon polygonFullRotation = staff.getPolygon();

        assertTrue("Extreme rotation test", polygonsAreEqual(polygonNoRotation, polygonFullRotation));
    }

        // Utility method to check if two polygons are equal
    private boolean polygonsAreEqual(Polygon p1, Polygon p2) {
        return Arrays.equals(p1.xpoints, p2.xpoints) && Arrays.equals(p1.ypoints, p2.ypoints) && p1.npoints == p2.npoints;
    }


}