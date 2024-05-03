package tr.edu.ku;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SaveLoadGame {
    Player player;
    Connection connection = DatabaseConnection.connection;

    public SaveLoadGame(Player player){
        this.player = player;
    }


    public ArrayList<Integer> getGames(){

        ArrayList<Integer> gameIDs = new ArrayList<>();

        try{
            String query = "SELECT game_id FROM saved_games WHERE player_id = ?";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, player.getPlayerId());
            ResultSet resultset = pStatement.executeQuery();
            // Iterate over the result set and add game IDs to the ArrayList
                while (resultset.next()) {
                    int gameId = resultset.getInt("game_id");
                    gameIDs.add(gameId);
                }
        }
        catch(Exception ex) {
            System.out.println("could not get saved games: " + ex.getMessage());
        }

        return gameIDs;
    }




    public void SaveGame(GameArea gameArea){
        try{
            String query = "INSERT INTO saved_games (player_id, game_data) VALUES (?, ?)";
            PreparedStatement pStatement = connection.prepareStatement(query);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            Game sglh = new Game(gameArea.getSimpleBarriers(), gameArea.getReinforcedBarriers(), gameArea.getExplosiveBarriers(), gameArea.getPaddle(), gameArea.getBall(), gameArea.getScore(), gameArea.isGameOver(), gameArea.getLives());
    
            oos.writeObject(sglh);
            oos.close();
            byte[] serializedObject = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(serializedObject);
            pStatement.setInt(1, player.getPlayerId());
            pStatement.setBinaryStream(2, bais, serializedObject.length);
            pStatement.executeUpdate();
        }
        catch(Exception ex) {
            System.out.println("could not save game: " + ex.getMessage());
        }
    }



    public Game LoadGame(Integer index){
        Game loadedGame = null;
        try{
            String query = "SELECT game_data FROM Saved_Games WHERE player_id = ? AND game_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getPlayerId());
            preparedStatement.setInt(2, index);
            ResultSet resultset = preparedStatement.executeQuery();
            if(resultset.next()){
                InputStream binaryStream = resultset.getBinaryStream("game_data");
                ObjectInputStream ois = new ObjectInputStream(binaryStream);
                loadedGame = (Game) ois.readObject();
                ois.close();
            }
        }
        catch(Exception ex){
            System.out.println("could not load game: " + ex.getMessage());
        }

        return loadedGame;
    }
    
}
