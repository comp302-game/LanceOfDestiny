package tr.edu.ku.Domain.Ymir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import tr.edu.ku.Constants;
import tr.edu.ku.Domain.Spell.SpellAdapter;
import tr.edu.ku.GameArea.GameArea;
import tr.edu.ku.GameArea.GridCell;

public class HollowPurple implements SpellAdapter {

    private Random random = new Random();
    private GameArea gameArea;

    public HollowPurple(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    @Override
    public void activate(int time) {

        ArrayList<GridCell> grids = new ArrayList<>();

        for (int i = 0; i < Constants.ROW_NUMBER; i++) {
            for (int j = 0; j < Constants.COLUMN_NUMBER; j++) {
                grids.add(gameArea.getGrid().getCells()[i][j]);
            }
        }

        Collections.shuffle(grids, random);

        int number = 0;

        for(int i = 0; i < grids.size(); i++){
            if(!grids.get(i).hasBarrier()){
                grids.get(i).setBarrier(4);
                gameArea.getAllBarriers().add(grids.get(i).getBarrier());
                number++;
            }

            if(number>=8) {
                break;
            }
        }
    }
    

    @Override
    public void deactivate() {
        //to timer task
    }

    
}
