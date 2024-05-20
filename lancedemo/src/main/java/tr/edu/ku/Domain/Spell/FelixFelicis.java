package tr.edu.ku.Domain.Spell;

import tr.edu.ku.GameArea.GameArea;

public class FelixFelicis implements SpellAdapter {

    private GameArea gameArea; 

    public FelixFelicis(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    @Override
    public void activate(int time) {
        gameArea.setLives(gameArea.getLives() + 1);
    }

    @Override
    public void deactivate() {
        //cant deactivate felix felicis
    }
    
}