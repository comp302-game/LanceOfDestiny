package tr.edu.ku.AppInterface.ObserverInterface;

public interface GameStateObserver {
    void onOpponentStateUpdate(String uname, int score, int barriers, int spells);
}