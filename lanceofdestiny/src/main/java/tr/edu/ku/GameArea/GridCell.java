package tr.edu.ku.GameArea;

import java.io.Serializable;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.ExplosiveBarrier;
import tr.edu.ku.Domain.HollowBarrier;
import tr.edu.ku.Domain.ReinforcedBarrier;
import tr.edu.ku.Domain.RewardingBarrier;
import tr.edu.ku.Domain.SimpleBarrier;

public class GridCell implements Serializable {
    
    private Barrier barrier;
    private boolean hasBarrier;
    private int row;
    private int col;

    public GridCell(int r, int c) {
        barrier = null;
        hasBarrier = false;
        this.row = r;
        this.col = c;
    }

    public void setBarrier(int type) { //Barrier type
        
        if(type==0) {
            barrier = new SimpleBarrier((col*Constants.CELL_WIDTH) + 16, (row*Constants.CELL_HEIGHT) + 15 + 40, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, row, col);
        }
        else if(type==1) {
            barrier = new ReinforcedBarrier((col*Constants.CELL_WIDTH) + 16, (row*Constants.CELL_HEIGHT) + 15 + 40, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, row, col);
        }
        else if(type==2) {
            barrier = new ExplosiveBarrier((col*Constants.CELL_WIDTH) + 16, (row*Constants.CELL_HEIGHT) + 15 + 40, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, row, col);
        }
        else if(type==3) {
            barrier = new RewardingBarrier((col*Constants.CELL_WIDTH) + 16, (row*Constants.CELL_HEIGHT) + 15 + 40, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, row, col);
        }
        else if(type==4) {
            barrier = new HollowBarrier((col*Constants.CELL_WIDTH) + 16, (row*Constants.CELL_HEIGHT) + 15 + 40, Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT, row, col);
        }

        hasBarrier = true;
    }



    public void setBarrier(Barrier b) { //Barrier type
        
        this.barrier = b;
        this.setHasBarrier(true);
        barrier.setX((col*Constants.CELL_WIDTH) + 16);
        barrier.setY((row*Constants.CELL_HEIGHT) + 15 + 40);
        barrier.setRow(row);
        barrier.setColumn(col);
    }


    public void deleteBarrier() {
        barrier = null;
        hasBarrier = false;
    }



    public boolean hasBarrier() {
        return hasBarrier;
    }

    public void setHasBarrier(boolean hasBarrier) {
        this.hasBarrier = hasBarrier;
    }

    public Barrier getBarrier() {
        return barrier;
    }

    public int getRowNum() {
        return row;
    }

    public int getColNum() {
        return col;
    }

}
