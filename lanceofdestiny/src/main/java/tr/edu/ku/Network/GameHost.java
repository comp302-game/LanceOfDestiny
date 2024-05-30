package tr.edu.ku.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import tr.edu.ku.AppInterface.IClient;
import tr.edu.ku.GameArea.Grid;
import tr.edu.ku.Main.MainApplication;
import tr.edu.ku.View.MenuView.WaitingForPlayerView;

public class GameHost implements IClient {
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int hostID;
    private MultiplayerGameHandler gameHandler;
    private WaitingForPlayerView lobby_frame;
    private ArrayList<Integer> state_list = new ArrayList<>();
    private MainApplication mainApp;

    private ObjectOutputStream objectOut; // Add this member variable for object output stream
    private ObjectInputStream objectIn; // Add this member variable for object output stream


    public GameHost(MainApplication mainApp, int port, int playerID) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.hostID = playerID;
        this.gameHandler = new MultiplayerGameHandler(this);
        this.mainApp = mainApp;
    }


    public void waitForClient() throws IOException {
        
        lobby_frame = new WaitingForPlayerView(mainApp);
        lobby_frame.getIPLabel().setText(InetAddress.getLocalHost().getHostAddress());

        try {
            this.clientSocket = serverSocket.accept();

            this.objectOut = new ObjectOutputStream(clientSocket.getOutputStream()); // Initialize ObjectOutputStream here
            this.objectIn = new ObjectInputStream(clientSocket.getInputStream()); // Initialize ObjectOutputStream here

            JOptionPane.showMessageDialog(null, "Player 2 has connected!", "Notification", JOptionPane.INFORMATION_MESSAGE);

            lobby_frame.dispose();
            new Thread(new Listener()).start();
        } catch (IOException e) {
            throw e;
        }
    }



    private class Listener implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Object received_object = objectIn.readObject();
                    
                    if(received_object instanceof String) {
                        gameHandler.receiveUpdate((String)received_object);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {

            }
        }
    }


    public void startGame(Grid grid) {
        try {
            objectOut.writeObject(grid); // Use the ObjectOutputStream to send the Grid object
            objectOut.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainApp.getMultiRunningFrame(), "Network Problem: Couldn't start the game.");
            mainApp.exitGame();
        }
    }

    
    





    

//USER INTERFACE
    @Override
    public void close() throws IOException {
        if (clientSocket != null) clientSocket.close();
        if (serverSocket != null) serverSocket.close();
    }
    @Override
    public void sendUpdate(String update) {
        try {
            objectOut.writeObject(update);
            objectOut.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainApp.getMultiRunningFrame(), "Player 2 has leaved, Host wins!");
            mainApp.exitGame();
        }
    }
    @Override
    public MultiplayerGameHandler getMultiGame() {
        return gameHandler;
    }
    @Override
    public ArrayList<Integer> getStateOpponent() {
        return state_list;
    }
    @Override
    public int getPlayerID() {
        return hostID;
    }
    @Override
    public MainApplication getApp() {
        return mainApp;
    }

}
