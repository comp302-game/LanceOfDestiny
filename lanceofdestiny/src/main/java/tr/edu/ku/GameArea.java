package tr.edu.ku.GameArea;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import tr.edu.ku.Constants;
import tr.edu.ku.AppInterface.ObserverInterface.AttackSpellNumberObs;
import tr.edu.ku.AppInterface.ObserverInterface.SpellAttackObserver;
import tr.edu.ku.AppInterface.ObserverInterface.SpellNumberObs;
import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.FireBall;
import tr.edu.ku.Domain.MagicalStaff;
import tr.edu.ku.Domain.Spell.MultiplayerSpellFac;
import tr.edu.ku.Domain.Spell.SpellAdapter;
import tr.edu.ku.Domain.Spell.SpellController;
import tr.edu.ku.Domain.Spell.SpellFactory;
import tr.edu.ku.Domain.Ymir.DoubleAccel;
import tr.edu.ku.Domain.Ymir.HollowPurple;
import tr.edu.ku.Domain.Ymir.InfiniteVoid;
import tr.edu.ku.Domain.Ymir.Ymir;
import tr.edu.ku.GameEngine.CollisionHandler;
import tr.edu.ku.GameEngine.KeyboardInputHandler;
import tr.edu.ku.GameEngine.MovementHandler;


public class GameArea extends JPanel implements SpellAttackObserver{

	private final Object ObjectLock = new Object(); // Lock object for synchronization
	private Grid grid;
	private ArrayList<Barrier> allBarriers;
	private ArrayList<Barrier> movingBarriers;

	private int HEX_number = 0;
	private int MSE_number = 0;
	private int OFB_number = 0;
	private int FELIX_number = 0;

	//FOR MULTIPLAYER
	private int ACCEL_number = 0;
	private int VOID_number = 0;
	private int HOLLOW_number = 0;

	private MagicalStaff staff;
	private FireBall ball;

	private Ymir ymir;
	private int game_mode;

	private boolean attack = false;
	private String attackSpell;

	private double score = 0;
	private boolean isGameOver = false;
	private int lives = 3;

	private List<AttackSpellNumberObs> observers = new ArrayList<>();
	private List<SpellNumberObs> Num_observers = new ArrayList<>();
	
	private long gameStartingTime = System.currentTimeMillis(); // Record the game starting time

	private MovementHandler movement;
    private CollisionHandler collision;
	
    private int gameState = 1; //1 for resume, -1 for pause. Start at resume state.
	private boolean loaded_flag = false;
	private boolean paused_flag = false; //initialize with not paused



	public GameArea(Grid grid, int game_mode) { //Form a game area using the spesified layout
		this.game_mode = game_mode;
        staff = new MagicalStaff();
        ball = new FireBall((Constants.GAMEPANEL_WIDTH / 2 - Constants.FIREBALL_SIZE / 2), 800.0);

		allBarriers = new ArrayList<>();
		movingBarriers = new ArrayList<>();
        this.grid = grid;

        collision = new CollisionHandler(ball, staff, grid, allBarriers, movingBarriers);
		movement = new MovementHandler(collision);

		
		// Create All barriers List
        for (int i = 0; i < Constants.ROW_NUMBER; i++) {
            for (int j = 0; j < Constants.COLUMN_NUMBER; j++) {
                if(grid.getCells()[i][j].hasBarrier()){
                    allBarriers.add(grid.getCells()[i][j].getBarrier());
                }
            }
        }

		SpellController.getInstance().reset();
        KeyboardInputHandler.setXPressed(false);

		ymir = new Ymir(this);
	}

    

	private void updateGame() {

		int fireball_incident = movement.updateFireBall(ball);
		if(fireball_incident==1) { //fireball has fallen below
			lives--;
			if(lives==0) {
				isGameOver = true;
			}

			ball.reset(); // reset the ball position after fall
			staff.reset();

		} else if (fireball_incident==2) { //Score calculation
			long currentTime = System.currentTimeMillis(); // Get the current time
        	double timeDifference = (currentTime - gameStartingTime) / 1000.0; // Convert milliseconds to seconds
			double scoreIncrement = 300 / timeDifference;
			score = score + scoreIncrement;
		}
		
		
		movement.updateBarriers(movingBarriers, allBarriers, grid);
		movement.updateStaff(staff);

		if(SpellController.getInstance().is_HEX_Active()) {
			movement.updateBullets();
			
			if(collision.checkBulletCollisions()) {
				long currentTime = System.currentTimeMillis(); // Get the current time
        		double timeDifference = (currentTime - gameStartingTime) / 1000.0; // Convert milliseconds to seconds
				double scoreIncrement = 300 / timeDifference;
				score = score + scoreIncrement;
			}
		}

		int staff_col = collision.checkStaffCollisions();

		if(staff_col == 1) {
			lives--;
			if(lives==0) {
				isGameOver = true;
			}
		}

		//create and add spells to inventory
		else if (staff_col == 2) {

			if(game_mode == 0) {
				int i = SpellFactory.getInstance().createSpellIndex();

				if(i==1) {
					HEX_number++;
				}

				else if(i==2) {
					MSE_number++;
				}

				else if(i==3) {
					OFB_number++;
				}

				else if(i==4) {
					FELIX_number++;
				}
			}


			else {
				int i = MultiplayerSpellFac.getInstance().createSpellIndex();

				if(i==1) {
					HEX_number++;
				}

				else if(i==2) {
					MSE_number++;
				}

				else if(i==3) {
					OFB_number++;
				}

				else if(i==4) {
					FELIX_number++;
				}
				
				else if(i==5) {
					ACCEL_number++;
				}

				else if(i==6) {
					VOID_number++;
				}

				else if(i==7) {
					HOLLOW_number++;
				}

			}

			for (SpellNumberObs observer : Num_observers) {
				observer.onSpellNumChange();
			}

			for (AttackSpellNumberObs observer : observers) {
				observer.onAttackSpellNumChange();
            }
		}
	}



	public void update() {

		if(gameState == 1 && KeyboardInputHandler.getX_Pressed() == true && isGameOver() == false && isGameFinished() == false) { //If resuming, update the game state

			
			synchronized(ObjectLock){
            	updateGame();
			}

			if(paused_flag == true) {
				paused_flag = false;

				if (SpellController.getInstance().is_HEX_Active()){
					SpellController.getInstance().getCurrentHEX().resume();
				}
				
				else if (SpellController.getInstance().is_OFB_Active()){
					SpellController.getInstance().getCurrentOFB().resume();
				}

				else if (SpellController.getInstance().is_MSE_Active()){
					SpellController.getInstance().getCurrentMSE().resume();
				}

				else if (SpellController.getInstance().is_VOID_Active()){
					SpellController.getInstance().getCurrentVOID().resume();
				}

				else if (SpellController.getInstance().is_ACCEL_Active()){
					SpellController.getInstance().getCurrentACCEL().resume();
				}
			}
        }

		//additional pauser mechanic for all spells active
		else if (paused_flag == false) {
			paused_flag = true;

			if (SpellController.getInstance().is_HEX_Active()){
				SpellController.getInstance().getCurrentHEX().spellPaused();
			}

			else if (SpellController.getInstance().is_OFB_Active()){
				SpellController.getInstance().getCurrentOFB().spellPaused();
			} 

			else if (SpellController.getInstance().is_MSE_Active()){
				SpellController.getInstance().getCurrentMSE().spellPaused();
			}
			
			else if (SpellController.getInstance().is_VOID_Active()){
				SpellController.getInstance().getCurrentVOID().spellPaused();
			}

			else if (SpellController.getInstance().is_ACCEL_Active()){
				SpellController.getInstance().getCurrentACCEL().spellPaused();
			}
		}
	}


	//Adapter Pattern is used. Even If activate function changes or a new spell is added, this part of the code won't be edited again.
	public void spellUsed(String s) {
		
		SpellAdapter activated_spell = SpellFactory.getInstance().createSpell(s, this);

		if(s == "HEX") {
			HEX_number--;
		}

		if(s == "FELIX") {
			FELIX_number--;
		}

		if(s == "MSE") {
			MSE_number--;
		}

		if(s == "OFB") {
			OFB_number--;
		}

		for (SpellNumberObs observer : Num_observers) {
			observer.onSpellNumChange();
		}

		activated_spell.activate(30);
	}


	public void ymirHit() { //Hits every 30 seconds (if coin flip is succesful)
		ymir.spawnYmirSpell();
	}


	public void attackOpponent(String s) {   //Opponent attacks you with ymir spells.
		attackSpell = s;
		attack = true;

		if(s == "ACCEL") {
			ACCEL_number--;
		}

		else if(s == "VOID") {
			VOID_number--;
		}

		else if(s == "HOLLOW") {
			HOLLOW_number--;
		}

		for (AttackSpellNumberObs observer : observers) {
			observer.onAttackSpellNumChange();
		}
	}
    

    
	//Method to load every barrier into gamearea barrier lists. This method loads the saved game into  gamearea.
	//It resets all the game objects and clone the saved ones. Including every game info (lives, score ...)
    public void LoadSavedGame(Game game) { 
		
        reset();

		setGrid(game.getGrid());
		setAllBarriers(game.getAllBarriers());
		setMovingBarriers(game.getMovingBarriers());

		synchronized(ObjectLock) {
			for(Barrier barrier: allBarriers) {
				if(barrier.isFrozen()) {
					barrier.setIsFrozen(false);
				}
			}
		}

		setMSE(game.getMSE());
		setFELIX(game.getFELIX());
		setHEX(game.getHEX());
		setOFB(game.getOFB());

		setStaff(game.getStaff());
		staff.resetSize();
		setBall(game.getBall());
		ball.resetSize();

		setScore(game.getScore());
		setLives(game.getLives());
		setGameOver(game.isGameOver());

		ymir.setLastSpells(game.getlastSpell(), game.getSecondLastSpell());
			
        collision = new CollisionHandler(ball, staff, grid, allBarriers, movingBarriers);
		movement = new MovementHandler(collision);
		loaded_flag = true;
    }



	private void reset() {
		allBarriers.clear();
		movingBarriers.clear();
        grid.reset();
		HEX_number = 0;
		MSE_number = 0;
		OFB_number = 0;
		FELIX_number = 0;
		ball = null;
		staff = null;

		SpellController.getInstance().reset();
		KeyboardInputHandler.setXPressed(false);
		gameState = 1;
	}

	
	public boolean isGameFinished() {
		if(allBarriers.isEmpty()) return true; else return false;
	}


	@Override
	public void onSpellAttack(String s) {
		opponentHit(s);
	}


	private void opponentHit(String s) {   //Opponent attacks you with ymir spells.
		
		if(s.equals("HOLLOW")) {
			HollowPurple spell = new HollowPurple(this);
			spell.activate(15);
		}

		else if(s.equals("VOID")) {

			if(SpellController.getInstance().is_VOID_Active()) {  // if already active, destroy it, start a new one
				SpellController.getInstance().getCurrentVOID().deactivate();
			}

			InfiniteVoid spell = new InfiniteVoid(this);
			spell.activate(15);
		}

		else if(s.equals("ACCEL")) {

			if(SpellController.getInstance().is_ACCEL_Active()) {  // if already active, destroy it, start a new one
				SpellController.getInstance().getCurrentACCEL().deactivate();
			}

			DoubleAccel spell = new DoubleAccel(this);
			spell.activate(15);
		}

		for (AttackSpellNumberObs observer : observers) {
			observer.onReceivedAttack(s);
		}
	}







    //GETTERS AND SETTERS
	public Object getLock() {
        return ObjectLock;
    }

	public String getAttackSpell() {
		return attackSpell;
	}

	public int getOFB() {
		return OFB_number;
	}

	public int getMSE() {
		return MSE_number;
	}

	public int getFELIX() {
		return FELIX_number;
	}

	public int getHEX() {
		return HEX_number;
	}

	public int getVOID() {
		return VOID_number;
	}

	public int getHOLLOW() {
		return HOLLOW_number;
	}

	public int getACCEL() {
		return ACCEL_number;
	}


	public void setHEX(int i) {
		this.HEX_number = i;
	}

	public void setMSE(int i) {
		this.MSE_number = i;
	}

	public void setOFB(int i) {
		this.OFB_number = i;
	}

	public void setFELIX(int i) {
		this.FELIX_number = i;
	}

	public void setHOLLOW(int i) {
		this.HOLLOW_number = i;
	}

	public void setVOID(int i) {
		this.VOID_number = i;
	}

	public void setACCEL(int i) {
		this.ACCEL_number = i;
	}



	public MagicalStaff getStaff() {
		return staff;
	}

	public void setStaff(MagicalStaff staff) {
		this.staff = staff;
	}


	public FireBall getBall() {
		return ball;
	}

	public void setBall(FireBall fireball) {
		this.ball = fireball;
	}

	public double getScore() {
		return score;
	}

	public int getLives() {
		return lives;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setScore(double new_score) {
		score = new_score;
	}

	public void setGameOver(Boolean state) {
		isGameOver = state;
	}

	public void setLives(int new_life) {
		lives = new_life;
	}

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid g) {
		this.grid = g;
	}

	public ArrayList<Barrier> getAllBarriers() {
        return allBarriers;
    }

    public void setAllBarriers(ArrayList<Barrier> list) {
        this.allBarriers = list;
    }


	public ArrayList<Barrier> getMovingBarriers() {
        return movingBarriers;
    }

    public void setMovingBarriers(ArrayList<Barrier> list) {
        this.movingBarriers = list;
    }

	public int getGameState() {
        return gameState;
    }

    public void setGameState() {
        gameState = gameState*-1;
    }

	public Ymir getYmir() {
		return ymir;
	}

	public void setYmir(Ymir new_ymir) {
		ymir = new_ymir;
	}

	public boolean getAttackFlag() {
		return attack;
	}

	public void setAttackFlag(boolean b) {
		attack = b;
	}

	public void addObserver(AttackSpellNumberObs observer) {
        observers.add(observer);
    }

    public void removeObserver(AttackSpellNumberObs observer) {
        observers.remove(observer);
    }

	public void addNumObserver(SpellNumberObs observer) {
        Num_observers.add(observer);
    }

    public void removeNumObserver(SpellNumberObs observer) {
        Num_observers.remove(observer);
    }

	public boolean getLoadedFlag() {
		return loaded_flag;
	}

	public void setLoadedFlag(boolean b) {
		loaded_flag = b;
	}

}