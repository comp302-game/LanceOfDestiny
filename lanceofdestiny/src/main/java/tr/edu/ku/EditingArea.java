package tr.edu.ku.GameArea;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.ExplosiveBarrier;
import tr.edu.ku.Domain.ReinforcedBarrier;
import tr.edu.ku.Domain.RewardingBarrier;
import tr.edu.ku.Domain.SimpleBarrier;


public class EditingArea {

    private ArrayList<SimpleBarrier> simpleBarriers;
	private ArrayList<ReinforcedBarrier> reinforcedBarriers;
    private ArrayList<ExplosiveBarrier> explosiveBarriers;
    private ArrayList<RewardingBarrier> rewardingBarriers;

    private static final Random random = new Random();

    public EditingArea() {
        simpleBarriers = new ArrayList<>();
        reinforcedBarriers = new ArrayList<>();
        explosiveBarriers = new ArrayList<>();
        rewardingBarriers = new ArrayList<>();
        
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


            for(RewardingBarrier wBarrier : rewardingBarriers){
                if(wBarrier.getBounds().intersects(selectedBarrier.getBounds()) && selectedBarrier != wBarrier){
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

        if (rewardingBarriers.size() < 1) {
            throw new Exception("At least 5 Rewarding Barriers need to be added to layout.");
        }
    }


    private void reset() {
        simpleBarriers.clear();
        reinforcedBarriers.clear();
        explosiveBarriers.clear();
        rewardingBarriers.clear();
    }


    public void LoadLayout(Layout layout) { //Method to copy every barrier into gamearea barrier lists
        
        reset();

        setSimpleBarriers(layout.getSimpleBarriers());
		setReinforcedBarriers(layout.getReinforcedBarriers());
		setExplosiveBarriers(layout.getExplosiveBarriers());
        setRewardingBarriers(layout.getRewardingBarriers());
		
    }


    public void createEditingArea(int sbar, int rbar, int ebar, int wbar) {
        
        reset();

        int total_number = sbar + rbar + ebar + wbar;
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

        for(int i=0; i<wbar; i++)  {
            RewardingBarrier wBarrier = new RewardingBarrier((int) points.get(sbar + rbar + ebar + i).getX(), (int) points.get(sbar + rbar + ebar + i).getY(), 32, 20);
            rewardingBarriers.add(wBarrier);
        }

    }
    
    

    




    //GETTER SETTERS
    
    public ArrayList<SimpleBarrier> getSimpleBarriers() {
        return simpleBarriers;
    }

    public ArrayList<ReinforcedBarrier> getReinforcedBarriers() {
        return reinforcedBarriers;
    }

    public ArrayList<ExplosiveBarrier> getExplosiveBarriers() {
        return explosiveBarriers;
    }

    public ArrayList<RewardingBarrier> getRewardingBarriers() {
        return rewardingBarriers;
    }

	public void setSimpleBarriers(ArrayList<SimpleBarrier> sBarriers) {
		this.simpleBarriers = sBarriers;
	}

	public void setReinforcedBarriers(ArrayList<ReinforcedBarrier> rBarriers) {
		this.reinforcedBarriers = rBarriers;
	}

	public void setExplosiveBarriers(ArrayList<ExplosiveBarrier> eBarriers) {
		this.explosiveBarriers = eBarriers;
	}

    public void setRewardingBarriers(ArrayList<RewardingBarrier> wBarriers) {
		this.rewardingBarriers = wBarriers;
	}

    
}
