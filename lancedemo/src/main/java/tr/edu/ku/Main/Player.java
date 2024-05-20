package tr.edu.ku.Main;


public class Player {
    private String username;
    private int playerID;

    public Player(int id, String uname) {
        this.username = uname;
        this.playerID = id;
    }

    public int getPlayerId() {
        return playerID;
    }

    public String getUsername() {
        return username;
    }

}
