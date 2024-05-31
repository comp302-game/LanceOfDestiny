import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;

import tr.edu.ku.Database.DatabaseConnection;
import tr.edu.ku.Database.SaveLoadLayout;
import tr.edu.ku.GameArea.Grid;
import tr.edu.ku.Main.Player;

public class GameLoadTest {
    Connection connection;
    Player player_exist;
    Player player_nonexist;
    SaveLoadLayout saveLoadLayout;
    
    @Before
    // setup database properly and create required objects for testing load method for layout objects
    public void setUp() {
        connection = DatabaseConnection.getConnection();
        player_exist = new Player(1, "Avci");
        player_nonexist = new Player(3, "test");
        saveLoadLayout = new SaveLoadLayout(player_exist);

    }

    // test for connection to database not null
    @Test
    public void testConnectionDatabase() {
        // requires 
        // checks for database connected properly and connection object is not null.
        assertNotNull(connection);
    }
    @Test
    public void testExistingGameDatabase(){
        try{
            // requires 
            //a database is valid and properly connected. 
            String query = "SELECT layout_id FROM saved_layouts WHERE player_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            // modifies
            // Executes a database query to retrieve the layout ID for a specific player ID.
            stmt.setInt(1, player_exist.getPlayerId());
            ResultSet rs = stmt.executeQuery();
            assertTrue(rs.next());
            // effects
            // Asserts that the ResultSet 'rs' contains at least one row, indicating an existing game database entry for the player.
        }

        catch(SQLException e){
            System.out.println(e);
        }
    }
    @Test
    public void testLayoutAccessDatabase(){
    

        try{
            // requires 
            //A database is valid and properly connected. 
            String query = "SELECT layout_data FROM saved_layouts WHERE player_id = ? AND layout_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player_exist.getPlayerId());
            preparedStatement.setInt(2, 2);
            // modifies
            // Executes a database query to retrieve the layout data for a specific player ID and layout ID.
            // Modifies the PreparedStatement object 'preparedStatement' by setting the player ID and layout ID parameters.
            ResultSet resultset = preparedStatement.executeQuery();
            Grid loadedGrid = null;
            if(resultset.next()){
                InputStream binaryStream = resultset.getBinaryStream("layout_data");
                ObjectInputStream ois = new ObjectInputStream(binaryStream);
                loadedGrid = (Grid) ois.readObject();
                ois.close();
            }
            // Effects
            // Asserts that the 'loadedGrid' object is not null, indicating successful retrieval of layout data from the database.
            assertNotNull("Layout should not be null", loadedGrid);
            
        }
        catch(Exception e){
            System.out.println( e.toString());

        }
    }
    @Test
    public void testNonExistingGameDatabase(){
        try{
            // requires 
            //A database is valid and properly connected. 
            //The table "saved_layouts" with columns "layout_id" and "player_id" must exist in the database.
            String query = "SELECT layout_id FROM saved_layouts WHERE player_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            // modifies 
            //Executes a database query to retrieve the layout ID for a specific player ID.
            // modifies
            // Modifies the PreparedStatement object 'stmt' by setting the player ID parameter.
            stmt.setInt(1, player_nonexist.getPlayerId());
            ResultSet rs = stmt.executeQuery();

            // effects 
            // Asserts that the ResultSet 'rs' does not contain any rows, indicating no existing game database entry for the player.
            // If the assertion fails (i.e., if there is a row in the ResultSet), a test failure will be reported.
            assertFalse(rs.next());
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    @Test
    public void testLayoutAccessDatabasewithAllFields(){
        // requires
        // A valid database connection must be established.
        // The table "saved_layouts" with columns "layout_data", "player_id", and "layout_id" must exist in the database.
        try{
            // modifies 
            // Executes a database query to retrieve the layout data for a specific player ID and layout ID.
            // Modifies the PreparedStatement object 'preparedStatement' by setting the player ID and layout ID parameters.
            // effects
            // Initializes a 'loadedGrid' object to null.
            Grid loadedGrid = null;
            String query = "SELECT layout_data FROM saved_layouts WHERE player_id = ? AND layout_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player_exist.getPlayerId());
            int layout_id = 7;
            preparedStatement.setInt(2, layout_id);
            ResultSet resultset = preparedStatement.executeQuery();
            if(resultset.next()){
                // effects 
                // Retrieves the layout data from the database result set and deserializes it into a 'Grid' object.
                // Modifies the 'loadedGrid' object by assigning it the deserialized grid data.
                InputStream binaryStream = resultset.getBinaryStream("layout_data");
                ObjectInputStream ois = new ObjectInputStream(binaryStream);
                loadedGrid = (Grid) ois.readObject();
                ois.close();
            }
            // effects
            //Asserts that the 'loadedGrid' object is not null, indicating successful retrieval of layout data from the database.
            //If the assertion fails, a test failure will be reported.
            assertNotNull("Layout should not be null", loadedGrid);
        }

        catch(Exception e){
            System.out.println("Layout could not be accessed" + e);

        }
    }

}