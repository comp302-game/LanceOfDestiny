package tr.edu.ku.Domain.Spell;

import java.util.Random;

import tr.edu.ku.GameArea.GameArea;

public class SpellFactory {

    //singleton & factory pattern for creating spells
    private static SpellFactory instance;

    private int progression = 0;
	private SpellAdapter new_Spell;
    private Random random = new Random();

    private SpellFactory() {}


    //singleton pattern (only one instance)
    public static SpellFactory getInstance() {
		if (instance == null) {
			instance = new SpellFactory();
		}
		return instance;
	}


    public int createSpellIndex() {

			int i = 0;

		    if (progression == 0) {
				i = 1;
				progression++;
			}
			else if(progression == 1) {
				i = 2;
				progression++;
			}
			else if (progression == 2) {
				i = 3;
				progression++;
			}
			else if (progression == 3) {
				i = 4;
				progression++;
			}

            else {
                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();

 	            // Create a new spell with %25 probability each
                if (randomNumber <= 0.25) {
		            i = 1;
    	        }

                else if (0.25 < randomNumber && randomNumber <= 0.50) {
		            i = 2;
    	        }

                else if (0.50 < randomNumber && randomNumber <= 0.75) {
		            i = 3;
    	        }

                else if (0.75 < randomNumber && randomNumber <= 1) {
		            i = 4;
    	        }
            }

		return i;
	}


	public SpellAdapter createSpell(String s, GameArea gameArea) {

		if(s == "HEX") {
			new_Spell = new Hex(gameArea);
		}

		else if(s == "MSE") {
			new_Spell = new StaffExpansion(gameArea);
		}

		else if(s == "OFB") {
			new_Spell = new EnlargementSpell(gameArea);
		}

		else if(s == "FELIX") {
			new_Spell = new FelixFelicis(gameArea);
		}

		return new_Spell;
	}
}
