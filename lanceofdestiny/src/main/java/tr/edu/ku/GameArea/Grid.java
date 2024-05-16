package tr.edu.ku.GameArea;
import java.io.Serializable;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Barrier;

public class Grid implements Serializable {

    private GridCell[][] cells;

    public Grid() {
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
        return cells[row][col].hasBarrier();
    }


    public void setBarrier(int row, int col, int type) {
        cells[row][col].setBarrier(type);
    }


    public void deleteFromGrid(Barrier barrier) {
        cells[barrier.getRow()][barrier.getColumn()].deleteBarrier();
    }


    public GridCell[][] getCells() {
        return cells;
    }

    public void reset() {
        cells = new GridCell[Constants.ROW_NUMBER][Constants.COLUMN_NUMBER];
        initializeGrid();
    }

}
