package tr.edu.ku.Network;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import tr.edu.ku.AppInterface.IClient;
import tr.edu.ku.AppInterface.ObjectTransferListener;
import tr.edu.ku.GameArea.Grid;
import tr.edu.ku.Main.MainApplication;
import tr.edu.ku.View.MenuView.WaitingForHostView;

public class GameClient implements IClient {
    
    private Socket socket;
    private int playerID;
    private WaitingForHostView lobby_frame;
    private ObjectTransferListener listener;
    private MultiplayerGameHandler gameHandler;
    private ArrayList<Integer> state_list = new ArrayList<>();
    private MainApplication mainApp;

    private ObjectOutputStream objectOut; // Add this member variable for object input stream
    private ObjectInputStream objectIn; // Add this member variable for object input stream


    public GameClient(MainApplication mainApp, String serverAddress, int serverPort, int playerID, ObjectTransferListener listener) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);
        this.playerID = playerID;
        this.listener = listener;
        this.gameHandler = new MultiplayerGameHandler(this);
        this.mainApp = mainApp;
        
        this.objectOut = new ObjectOutputStream(socket.getOutputStream()); // Initialize ObjectOutputStream here
        this.objectIn = new ObjectInputStream(socket.getInputStream()); // Initialize ObjectOutputStream here

        // Create the lobby view
        lobby_frame = new WaitingForHostView();

        new Thread(new Listener()).start();
    }



    private class Listener implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Object received_object = objectIn.readObject();
                    if (listener != null && received_object instanceof Grid) {
                        listener.onGridReceived((Grid) received_object);
                    }
                    else if(listener != null && received_object instanceof String) {
                        gameHandler.receiveUpdate((String)received_object);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                
            }
        }
    }


    public void exitLobby() {
        lobby_frame.dispose();
    }

    





    

//USER INTERFACE
    @Override
    public void sendUpdate(String update) {
        try {
            objectOut.writeObject(update);
            objectOut.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainApp.getMultiRunningFrame(), "Host has leaved, Player 2 wins!");
            mainApp.exitGame();
        }
    }
    @Override
    public void close() throws IOException {
        if (socket != null) socket.close();
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
        return playerID;
    }
    @Override
    public MainApplication getApp() {
        return mainApp;
    }
}
