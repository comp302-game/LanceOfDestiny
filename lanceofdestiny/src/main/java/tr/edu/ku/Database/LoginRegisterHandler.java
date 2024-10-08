package tr.edu.ku.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;



public class LoginRegisterHandler {


    public int Register(String name, String username, String password, String email){
        String query = "INSERT INTO players (username, password, name, email) VALUES (?, ?, ?, ?)";
        if(!isPlayerExist(username)){
        	try {
                ValidateName(name);
                ValidatePassword(password);
                ValidateEmail(email);

                try{
                    PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, name);
                    preparedStatement.setString(4, email);
                    preparedStatement.executeUpdate();
                    return 1;
                }
                catch (Exception e ){
                    e.printStackTrace();
                }
        	}catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return 0;
        }
        else {
            return -1;
        }
    }



    public int Login(String username, String password){
        String query = "SELECT password FROM players WHERE username = ? AND password = ?";
        if(isPlayerExist(username)){
            ;
            try {
                PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    // connect game
                    // call game initializer and create player object
                	return 1;
                }
                else {
                    return 0;
                }
            }
            catch (Exception e ){
                e.printStackTrace();
            }
        }
        
        return -1;  
    }



    public int getPlayerID(String username, String password){
        int player_id = 0;
        String query = "SELECT player_id FROM players WHERE username = ? AND password = ?";
        if(isPlayerExist(username)){
            try {
                PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    player_id = resultSet.getInt("player_id");
                }
            }
            catch (Exception e ){
                e.printStackTrace();
            }
        }

        return player_id;
    }




    private boolean isPlayerExist(String username){
        String query = "SELECT * FROM players WHERE username = ?";
        try{
            PreparedStatement preparedStatement = DatabaseConnection.connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        return false;
    }




    
    public void ValidatePassword(String password) throws Exception{
        // rules: Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character:
        
    	String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()) {
            throw new Exception("Password is invalid! Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character.");
        }
    }


    public void ValidateEmail(String email) throws Exception{

        // basic email validator for @gmail, etc something
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) {
            throw new Exception("Email is invalid!");
        }
    }


    public void ValidateName(String Name) throws Exception{
        String regex = "^[a-zA-Z][a-zA-Z ',.-]{0,99}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Name);
        if(!matcher.matches()) {
            throw new Exception("Name is invalid!");
        }
    }

}
