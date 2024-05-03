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

public class SaveLoadLayout {

    Player player;
    Connection connection = DatabaseConnection.connection;

    public SaveLoadLayout(Player player){
        this.player = player;
    }

    public ArrayList<Integer> getLayouts(){

        ArrayList<Integer> LayoutIDs = new ArrayList<>();

        try{
            String query = "SELECT layout_id FROM Saved_Layouts WHERE player_id = ?";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, player.getPlayerId());
            ResultSet resultset = pStatement.executeQuery();
            // Iterate over the result set and add game IDs to the ArrayList
                while (resultset.next()) {
                    int LayoutID = resultset.getInt("layout_id");
                    LayoutIDs.add(LayoutID);
                }
        }
        catch(Exception ex) {
            System.out.println("could not get saved layouts: " + ex.getMessage());
        }

        return LayoutIDs;
    }

    

    public void SaveLayout(EditingArea editingArea){
        try{
            String query = "INSERT INTO Saved_Layouts (player_id, layout_data) VALUES (?, ?)";
            PreparedStatement pStatement = connection.prepareStatement(query);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            Layout layout = new Layout(editingArea.getSimpleBarriers(), editingArea.getReinforcedBarriers(), editingArea.getExplosiveBarriers());

            oos.writeObject(layout);
            oos.close();
            byte[] serializedObject = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(serializedObject);
            pStatement.setInt(1, player.getPlayerId());
            pStatement.setBinaryStream(2, bais, serializedObject.length);
            pStatement.executeUpdate();
        }
        catch(Exception ex) {
            System.out.println("could not save layout: " + ex.getMessage());
        }
    }



    public Layout LoadLayout(Integer index){

        Layout loadedLayout = null;

        try{
            String query = "SELECT layout_data FROM saved_layouts WHERE player_id = ? AND layout_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getPlayerId());
            preparedStatement.setInt(2, index);
            ResultSet resultset = preparedStatement.executeQuery();
            if(resultset.next()){
                InputStream binaryStream = resultset.getBinaryStream("layout_data");
                ObjectInputStream ois = new ObjectInputStream(binaryStream);
                loadedLayout = (Layout) ois.readObject();
                ois.close();
            }
        }
        catch(Exception ex){
            System.out.println("could not load layout: " + ex.getMessage());
        }

        return loadedLayout;
    }
    
    
}
