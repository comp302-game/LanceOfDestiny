package tr.edu.ku.Domain.Ymir;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tr.edu.ku.AppInterface.ObserverInterface.YmirSpellObserver;
import tr.edu.ku.Domain.Spell.SpellAdapter;
import tr.edu.ku.GameArea.GameArea;

public class Ymir implements Serializable {
    
    private Random random = new Random();
	private GameArea gameArea;

    private SpellAdapter newSpell;
	private SpellAdapter lastSpell;
    private SpellAdapter secondLastSpell;
    private List<YmirSpellObserver> observers = new ArrayList<>();

    public Ymir(GameArea gameArea) {

		this.gameArea = gameArea;

        // Initialize last two spells with random spells
        lastSpell = generateRandomSpell();
        secondLastSpell = generateRandomSpell();
	}



	public void spawnYmirSpell() {
        // Perform a coin flip (50% chance)
        
        castSpell();
        
    }


	private void castSpell() {
		
        do {
            newSpell = generateRandomSpell();
        } while (lastSpell.getClass() == secondLastSpell.getClass() && newSpell.getClass() == lastSpell.getClass());  //check if last two spells are equal so that 3rd one is not the same 

        // Cast the spell (this is where you implement the effect of the spell)
        newSpell.activate(15);

        String s = "";
        if(newSpell instanceof HollowPurple) {
            s = "Hollow Barrier";
        }
        else if(newSpell instanceof InfiniteVoid) {
            s = "Infinite Void";
        }
        else if(newSpell instanceof DoubleAccel) {
            s = "Double Accel";
        }

        for(YmirSpellObserver observer : observers) {   //Notify the observers
            observer.onYmirSpellActivation(s);
        }

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
            default:
                return new DoubleAccel(gameArea);
        }
    }


    public void setLastSpells(int s1, int s2){

        if(s1==0){
            lastSpell = new DoubleAccel(gameArea);
        }
        else if(s1==1){
            lastSpell = new HollowPurple(gameArea);
        }
        else if(s1==2){
            lastSpell = new InfiniteVoid(gameArea);
        }
        else if(s1==-1){
            lastSpell = null;
        }


        if(s2==0){
            secondLastSpell = new DoubleAccel(gameArea);
        }
        else if(s2==0){
            secondLastSpell = new HollowPurple(gameArea);
        }
        else if(s2==0){
            secondLastSpell = new InfiniteVoid(gameArea);
        }
        else if(s2==-1){
            secondLastSpell = null;
        }
    }


    public SpellAdapter getLastSpelll() {
        return lastSpell;
    }

    public SpellAdapter getSecondLastSpelll() {
        return secondLastSpell;
    }

    public void addObserver(YmirSpellObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(YmirSpellObserver observer) {
        observers.remove(observer);
    }
}
