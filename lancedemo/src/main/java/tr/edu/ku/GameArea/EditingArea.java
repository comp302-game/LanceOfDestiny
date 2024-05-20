package tr.edu.ku.GameArea;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import tr.edu.ku.Constants;
import tr.edu.ku.MathBase;
import tr.edu.ku.Domain.Barrier;


public class EditingArea {
    
    private Grid grid;
    private int simple_number = 0;
    private int reinforced_number = 0;
    private int explosive_number = 0;
    private int rewarding_number = 0;

    
    private static final Random random = new Random();

    public EditingArea() {
        grid = new Grid();
    }

    public void updateEditingState() {
    }



    //Check correct barrier placement 
    public void checkValidPlacement(Barrier selectedBarrier) throws Exception {

        double x_pos = selectedBarrier.getX();
        double y_pos = selectedBarrier.getY();

        try{
            Point p = MathBase.getGridPos(x_pos, y_pos);
            
            if(grid.getCells()[(int) p.getX()][(int) p.getY()].hasBarrier()){
                throw new Exception("Barrier is on the same grid with another barrier");
            }
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }



    //Check threshold for barrier numbers.
    public void checkCorrectNum() throws Exception{
        
        if (simple_number < 75) {
            throw new Exception("At least 75 Simple Barriers need to be added to layout.");
        }

        if (reinforced_number < 10) {
            throw new Exception("At least 10 Reinforced Barriers need to be added to layout.");
        }

        if (explosive_number < 5) {
            throw new Exception("At least 5 Explosive Barriers need to be added to layout.");
        }

        if (rewarding_number < 5) {
            throw new Exception("At least 5 Rewarding Barriers need to be added to layout.");
        }
    }



    public void LoadLayout(Grid grid) { //Method to copy every barrier into gamearea barrier lists.
        setGrid(grid);
    }


    public void createEditingArea(int sbar, int rbar, int ebar, int wbar) {
     
        reset();
        ArrayList<GridCell> grids = new ArrayList<>();

        for (int i = 0; i < Constants.ROW_NUMBER; i++) {
            for (int j = 0; j < Constants.COLUMN_NUMBER; j++) {
                grids.add(grid.getCells()[i][j]);
            }
        }

        Collections.shuffle(grids, random);

        for(int i=0; i< sbar; i++)  {
            grid.setBarrier(grids.get(i).getRowNum(), grids.get(i).getColNum(), 0);
        }

        for(int i=0; i<rbar; i++)  {
            grid.setBarrier(grids.get(i+sbar).getRowNum(), grids.get(i+sbar).getColNum(), 1);
        }

        for(int i=0; i<ebar; i++)  {
            grid.setBarrier(grids.get(i+rbar+sbar).getRowNum(), grids.get(i+rbar+sbar).getColNum(), 2);
        }

        for(int i=0; i<wbar; i++)  {
            grid.setBarrier(grids.get(i+sbar+rbar+ebar).getRowNum(), grids.get(i+sbar+rbar+ebar).getColNum(), 3);
        }

        simple_number = sbar;
        reinforced_number = rbar;
        explosive_number = ebar;
        rewarding_number = wbar;
    }

    

    
    //GETTER SETTER
    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid g) {
		this.grid = g;
	}

    private void reset() {
        simple_number = 0;
        rewarding_number = 0;
        explosive_number = 0;
        reinforced_number = 0;
        grid.reset();
    }

    public int getSimpleNum() {
        return simple_number;
    }

    public int getReinforcedNum() {
        return reinforced_number;
    }

    public int getExplosiveNum() {
        return explosive_number;
    }

    public int getRewardingNum() {
        return rewarding_number;
    }

    public void setSimpleNum(int simpleNum) {
        this.simple_number = simpleNum;
    }

    public void setReinforcedNum(int reinforcedNum) {
        this.reinforced_number = reinforcedNum;
    }

    public void setExplosiveNum(int explosiveNum) {
        this.explosive_number = explosiveNum;
    }

    public void setRewardingNum(int rewardingNum) {
        this.rewarding_number = rewardingNum;
    }

    
}
