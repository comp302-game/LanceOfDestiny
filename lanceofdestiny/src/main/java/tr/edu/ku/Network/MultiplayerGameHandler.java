package tr.edu.ku.Network;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import tr.edu.ku.AppInterface.ObserverInterface.GameStateObserver;
import tr.edu.ku.AppInterface.ObserverInterface.SpellAttackObserver;


public class MultiplayerGameHandler {
    
    private GameHost host;
    private GameClient client;
    private boolean isHost;
    private List<GameStateObserver> observers = new ArrayList<>();
    private List<SpellAttackObserver> attackObservers = new ArrayList<>();


    public void addObserver(GameStateObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameStateObserver observer) {
        observers.remove(observer);
    }


    public void addAttackObserver(SpellAttackObserver observer) {
        attackObservers.add(observer);
    }

    public void removeAttackObserver(SpellAttackObserver observer) {
        attackObservers.remove(observer);
    }


    public MultiplayerGameHandler(GameHost host) {
        this.host = host;
        this.isHost = true;
    }

    public MultiplayerGameHandler(GameClient client) {
        this.client = client;
        this.isHost = false;
    }



    public void receiveUpdate(String update) {
        if (update.startsWith("state:")) {
            String[] parts = update.substring(6).split(",");
            String uname = parts[0];
            int score = Integer.parseInt(parts[1]);
            int barriers = Integer.parseInt(parts[2]);
            int lives = Integer.parseInt(parts[3]);

            for (GameStateObserver observer : observers) {  //OBSERVER PATTERN
                observer.onOpponentStateUpdate(uname, score, barriers, lives);
            }

            if(barriers == 0) {
                if(isHost) {
                    host.getApp().exitGame();
                } else { 
                    client.getApp().exitGame();
                }

                JOptionPane.showMessageDialog(null, "You lost!");
            }
        }
        
        else if(update.equals("ACCEL")) {
            for (SpellAttackObserver observer : attackObservers) {  //OBSERVER PATTERN
                observer.onSpellAttack(update);
            }
        }

        else if(update.equals("VOID")) {
            for (SpellAttackObserver observer : attackObservers) {  //OBSERVER PATTERN
                observer.onSpellAttack(update);
            }
        }

        else if(update.equals("HOLLOW")) {
            for (SpellAttackObserver observer : attackObservers) {  //OBSERVER PATTERN
                observer.onSpellAttack(update);
            }
        }
    }
}
