package tr.edu.ku.Domain;

import tr.edu.ku.GameArea.GameArea;

public class FelixFelicis extends Spell {

    public FelixFelicis() {
        super("Felix Felicis", 30);
    }

    public void activate(GameArea garea) {
        garea.setLives(garea.getLives() + 1);
    }
    
}
