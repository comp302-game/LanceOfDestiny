package tr.edu.ku.AppInterface;

import java.io.IOException;
import java.util.ArrayList;

import tr.edu.ku.Main.MainApplication;
import tr.edu.ku.Network.MultiplayerGameHandler;

public interface IClient {
    ArrayList<Integer> getStateOpponent();
    void sendUpdate(String update);
    MultiplayerGameHandler getMultiGame();
    void close() throws IOException;
    MainApplication getApp();
    int getPlayerID();
}