package tr.edu.ku.Domain.Ymir;

import java.util.Random;

import tr.edu.ku.Domain.Spell.SpellAdapter;
import tr.edu.ku.GameArea.GameArea;

public class Ymir {
    
    private Random random = new Random();
	private GameArea gameArea;

    private SpellAdapter newSpell;
	private SpellAdapter lastSpell;
    private SpellAdapter secondLastSpell;

    public Ymir(GameArea gameArea) {

		this.gameArea = gameArea;

        // Initialize last two spells with random spells
        lastSpell = generateRandomSpell();
        secondLastSpell = generateRandomSpell();
	}



	public void spawnYmirSpell() {
        // Perform a coin flip (50% chance)
        if (random.nextBoolean()) {
            castSpell();
        }
    }


	private void castSpell() {
		
        do {
            newSpell = generateRandomSpell();
        } while (newSpell.equals(lastSpell) && newSpell.equals(secondLastSpell));  //check if last two spells are equal

        // Cast the spell (this is where you implement the effect of the spell)
        newSpell.activate(15);

        // Update the spell history
        secondLastSpell = lastSpell;
        lastSpell = newSpell;
    }



	private SpellAdapter generateRandomSpell() {
        // Generate a random spell name (replace this with your actual spell generation logic)
        int spellNumber = random.nextInt(3); // Assuming 3 different types of spells
        switch (spellNumber) {
            case 0:
                return new DoubleAccel(gameArea);
            case 1:
                return new InfiniteVoid(gameArea);
            case 2:
                return new HollowPurple(gameArea);
        }

		return lastSpell;
    }
}
