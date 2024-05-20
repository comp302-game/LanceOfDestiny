package tr.edu.ku.GameArea;
import java.io.Serializable;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Barrier;

public class Grid implements Serializable {
    
    //OVERVIEW: Grid is a data structure that is formed by 12x25 Grid Cells

    private GridCell[][] cells;

    public Grid() {
        //EFFECTS: Initialize it to be hold empty grid cells.
        cells = new GridCell[Constants.ROW_NUMBER][Constants.COLUMN_NUMBER];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < Constants.ROW_NUMBER; i++) {
            for (int j = 0; j < Constants.COLUMN_NUMBER; j++) {
                cells[i][j] = new GridCell(i,j);
            }
        }
    }

    public boolean hasBarrier(int row, int col) {
        //EFFECTS: If the cell contains a barrier, return true, else false.
        return cells[row][col].hasBarrier();
    }


    public void setBarrier(int row, int col, int type) {
        //EFFECTS: Set the barrier of the cell
        cells[row][col].setBarrier(type);
    }


    public void deleteFromGrid(Barrier barrier) {
        //EFFECTS: Deletes the barrier from the grid which it is assigned to.
        cells[barrier.getRow()][barrier.getColumn()].deleteBarrier();
    }


    public GridCell[][] getCells() {
        //EFFECTS: Returns the contained cells
        return cells;
    }

    public void reset() {
        //EFFECTS: Resets the grid cells 
        //MODIFIES: this.cells
        cells = new GridCell[Constants.ROW_NUMBER][Constants.COLUMN_NUMBER];
        initializeGrid();
    }



    public boolean repOk() {
        if (cells == null) {
            return false;
        }
        if (cells.length != Constants.ROW_NUMBER) {
            return false;
        }
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == null || cells[i].length != Constants.COLUMN_NUMBER) {
                return false;
            }
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
