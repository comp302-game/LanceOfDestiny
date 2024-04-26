package tr.edu.ku;

public class Launcher {

    public static void main(String[] args) {

        // Enable UI scaling
        System.setProperty("sun.java2d.uiScale.enabled", "true");

        // Set the UI scaling factor
        System.setProperty("sun.java2d.uiScale", "1");

        //Load images
        new Constants();

        //Set connection
        DatabaseConnection.getConnection();

        new LoginFrame();
    }
}
