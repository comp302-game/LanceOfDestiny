package tr.edu.ku;

import java.util.ArrayList;

public class Player {
    private String username;
    private int playerID;
    private int currentSession;
    private ArrayList<Layout> layouts;
    private ArrayList<SavedGame> savedGames;

    public Player() {
        layouts = new ArrayList<>();
        savedGames = new ArrayList<>();
    }

    
    public ArrayList<Layout> getLayouts() {
        return layouts;
    }

    public ArrayList<SavedGame> getSavedGames() {
        return savedGames;
    }


}
