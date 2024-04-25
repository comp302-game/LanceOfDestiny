package tr.edu.ku;

import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.Random;


public class EditingArea {

    private ArrayList<SimpleBarrier> simpleBarriers;
	private ArrayList<ReinforcedBarrier> reinforcedBarriers;
    private ArrayList<ExplosiveBarrier> explosiveBarriers;
    private static final Random random = new Random();

    public EditingArea() {
        simpleBarriers = new ArrayList<>();
        reinforcedBarriers = new ArrayList<>();
        explosiveBarriers = new ArrayList<>();
        
    }


    public void updateEditingState() {

    }


    //Check correct barrier placement 
    public void checkValidPlacement(Barrier selectedBarrier) throws Exception {
            for(SimpleBarrier sbarrier : simpleBarriers){
                if(sbarrier.getBounds().intersects(selectedBarrier.getBounds()) && selectedBarrier != sbarrier){
                    throw new Exception("Barrier is on collision with another barrier");
                }
            }

            for(ReinforcedBarrier rbarrier : reinforcedBarriers){
                if(rbarrier.getBounds().intersects(selectedBarrier.getBounds()) && selectedBarrier != rbarrier){
                    throw new Exception("Barrier is on collision with another barrier");
                }
            }

            for(ExplosiveBarrier eBarrier : explosiveBarriers){
                if(eBarrier.getBounds().intersects(selectedBarrier.getBounds()) && selectedBarrier != eBarrier){
                    throw new Exception("Barrier is on collision with another barrier");
                }
            }
    }


    //Check threshold for barrier numbers.
    public void checkCorrectNum() throws Exception{

        if (simpleBarriers.size() < 1) {
            throw new Exception("At least 75 Simple Barriers need to be added to layout.");
        }

        if (reinforcedBarriers.size() < 1) {
            throw new Exception("At least 10 Reinforced Barriers need to be added to layout.");
        }

        if (explosiveBarriers.size() < 1) {
            throw new Exception("At least 5 Explosive Barriers need to be added to layout.");
        }
    }


    public ArrayList<SimpleBarrier> getSimpleBarriers() {
        return simpleBarriers;
    }

    public ArrayList<ReinforcedBarrier> getReinforcedBarriers() {
        return reinforcedBarriers;
    }

    public ArrayList<ExplosiveBarrier> getExplosiveBarriers() {
        return explosiveBarriers;
    }

    private void reset() {
        simpleBarriers.clear();
        reinforcedBarriers.clear();
        explosiveBarriers.clear();
    }


    public void LoadLayout(Layout layout) { //Method to copy every barrier into gamearea barrier lists
        
        reset();

		for(int i = 0; i<layout.getSimpleBarriers().size(); i++){
			SimpleBarrier temp = layout.getSimpleBarriers().get(i);
			try {
				SimpleBarrier sBarrier = (SimpleBarrier) temp.clone();
				simpleBarriers.add(sBarrier); 
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		for(int i = 0; i<layout.getReinforcedBarriers().size(); i++){
			ReinforcedBarrier temp = layout.getReinforcedBarriers().get(i);
			try {
				ReinforcedBarrier rBarrier = (ReinforcedBarrier) temp.clone();
				reinforcedBarriers.add(rBarrier); 
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		for(int i = 0; i<layout.getExplosiveBarriers().size(); i++){
			ExplosiveBarrier temp = layout.getExplosiveBarriers().get(i);
			try {
				ExplosiveBarrier eBarrier = (ExplosiveBarrier) temp.clone();
				explosiveBarriers.add(eBarrier); 
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
    }


    public void createEditingArea(int sbar, int rbar, int ebar) {
        
        reset();

        int total_number = sbar + rbar + ebar;
        int maxBarriers_perRow = 30;
        // Define the number of rows needed
        int totalRows = (int) Math.ceil((double) total_number / maxBarriers_perRow);

        Double x = 20.0;
        Double y = 55.0;
        ArrayList<Point2D> points = new ArrayList<>();

        for(int row = 0; row<totalRows; row++) {
            int barriersInThisRow = Math.min(maxBarriers_perRow, total_number - row * maxBarriers_perRow);
            
            for(int col = 0; col<barriersInThisRow; col++) {
                Point2D.Double point = new Point2D.Double(x + col*52, y + row*60);
                points.add(point);
            }
        }

        Collections.shuffle(points, random);


        for(int i=0; i< sbar; i++)  {
            SimpleBarrier sBarrier = new SimpleBarrier((int) points.get(i).getX(), (int) points.get(i).getY(), 32, 20);
            simpleBarriers.add(sBarrier);
        }

        for(int i=0; i<rbar; i++)  {
            ReinforcedBarrier rBarrier = new ReinforcedBarrier((int) points.get(sbar + i).getX(), (int) points.get(sbar + i).getY(), 32, 20);
            reinforcedBarriers.add(rBarrier);
        }

        for(int i=0; i<ebar; i++)  {
            ExplosiveBarrier eBarrier = new ExplosiveBarrier((int) points.get(sbar + rbar + i).getX(), (int) points.get(sbar + rbar + i).getY(), 32, 20);
            explosiveBarriers.add(eBarrier);
        }

    } 

    
}
