package tr.edu.ku.Domain.Spell;

import java.util.Random;

import tr.edu.ku.GameArea.GameArea;

public class MultiplayerSpellFac {
    
    
    //singleton & factory pattern for creating spells
    private static MultiplayerSpellFac instance;

    private int progression = 0;
	private SpellAdapter new_Spell;
    private Random random = new Random();

    private MultiplayerSpellFac() {}


    //singleton pattern (only one instance)
    public static MultiplayerSpellFac getInstance() {
		if (instance == null) {
			instance = new MultiplayerSpellFac();
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
			else if (progression == 4) {
				i = 5;
				progression++;
			}
			else if (progression == 5) {
				i = 6;
				progression++;
			}
			else if (progression == 6) {
				i = 7;
				progression++;
			}

            else {
                // Generate a random number between 0 and 1
                double randomNumber = random.nextDouble();

 	            // Create a new spell with %25 probability each
                if (randomNumber <= 0.143) {
		            i = 1;
    	        }

                else if (0.143 < randomNumber && randomNumber <= 0.286) {
		            i = 2;
    	        }

                else if (0.286 < randomNumber && randomNumber <= 0.429) {
		            i = 3;
    	        }

                else if (0.429 < randomNumber && randomNumber <= 0.572) {
		            i = 4;
    	        }

				else if (0.572 < randomNumber && randomNumber <= 0.715) {
		            i = 5;
    	        }

				else if (0.175 < randomNumber && randomNumber <= 0.858) {
		            i = 6;
    	        }

				else if (0.858 < randomNumber && randomNumber <= 1) {
		            i = 7;
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
