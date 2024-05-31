package tr.edu.ku.Main;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import tr.edu.ku.AppInterface.IClient;
import tr.edu.ku.AppInterface.ModeSwitchListener;
import tr.edu.ku.AppInterface.ObjectTransferListener;
import tr.edu.ku.GameArea.Grid;
import tr.edu.ku.Network.GameClient;
import tr.edu.ku.Network.GameHost;
import tr.edu.ku.View.GameView.LanceOfDestiny;
import tr.edu.ku.View.GameView.MultiplayerRunning;
import tr.edu.ku.View.GameView.RunningFrame;
import tr.edu.ku.View.MenuView.StarterMenuView;

public class MainApplication implements ModeSwitchListener, ObjectTransferListener {
    
    private LanceOfDestiny editingFrame;
    private RunningFrame runningFrame;
    private MultiplayerRunning multiRunning;
    private Player player;
    private GameLoop gloop;
    private int gameMode;
    private Thread gameThread;
    private boolean multiGameReady = false;

    //For networking
    private GameHost host;
    private GameClient client;
    private IClient user;
    

    public MainApplication(Player user, int gameMode) {
        this.player = user;
        this.gameMode = gameMode;
        this.editingFrame = new LanceOfDestiny(this);
        this.gloop = new GameLoop(editingFrame.getEditingPlane(), this);
        this.gameThread = new Thread(gloop);
        
        switch (gameMode) {
            case 0:
                //SinglePlayer
                editingFrame.setVisible(true);
                gameThread.start();
                break;

            case 1:
                startHosting(); // Host Game
                break;
            case 2:
                String hostAddress = JOptionPane.showInputDialog("Enter host address:");
                joinGame(hostAddress); // Join Game
                break;

            default:
                break;
        }
    }



    // Method to start hosting a game
    public void startHosting() {
        try {
            host = new GameHost(this, 12345, player.getPlayerId());
            user = host;
            
            new Thread(() -> {
                try {
                    host.waitForClient();

                    editingFrame.setVisible(true);
                    gameThread.start();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Server closed.", "Notification", JOptionPane.INFORMATION_MESSAGE);
                }
            }).start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Server closed.", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    // Method to join a hosted game
    public void joinGame(String hostAddress) {
        try {
            client = new GameClient(this, hostAddress, 12345, player.getPlayerId(), this);
            user = client;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Connection timed out. Unable to connect to the host.", "Notification", JOptionPane.INFORMATION_MESSAGE);
            exitGame();
        }
    }


    public void launchMulti(Grid grid) {
        switchToMultiRunningMode(grid);
        host.startGame(grid);
        multiGameReady = true;
    }



    public void switchToRunningMode(Grid grid) {
        if (runningFrame == null) {
            runningFrame = new RunningFrame(grid, this);
            gloop.setRunningPanel(runningFrame.getGamePanel());

        } else {
            runningFrame.LoadGamePanel(grid); //Load gamePanel's game area with layout
            runningFrame.setVisible(true);
        }

        editingFrame.setVisible(false);
        gloop.setMode(1);
    }



    public void switchToMultiRunningMode(Grid grid) {
        
        multiRunning = new MultiplayerRunning(grid, this);
        gloop.setRunningPanel(multiRunning.getGamePanel());
        user.getMultiGame().addObserver(multiRunning);
        user.getMultiGame().addAttackObserver(multiRunning.getGamePanel().getGameArea());
        editingFrame.setVisible(false);
        gloop.setMode(1);
    }



    public void switchToEditingMode() {
        editingFrame.setVisible(true);
        if (runningFrame != null) {
            gloop.setMode(0);
            runningFrame.setVisible(false);
        }
    }



    @Override
    public void onGridReceived(Grid grid) {
        client.exitLobby();
        gameThread.start();
        switchToMultiRunningMode(grid);
        multiGameReady = true;
    }


    public void sendMultiInfo(int score, int barriers, int lives) {
        user.sendUpdate("state:" + player.getUsername() + "," + score + "," + barriers + "," + lives);
    }

    public void sendAttack(String s) {
        user.sendUpdate(s);
    }



    public void exitGame() {
        // Clean up the current game state
        if (editingFrame != null ) {
            editingFrame.dispose();
        }
        if (runningFrame != null) {
            runningFrame.dispose();
        }
        if (multiRunning != null) {
            multiRunning.dispose();
        }

        if (gameThread != null && gameThread.isAlive()) {
            gloop.stop(); // Assuming GameLoop has a stop method to stop the loop
            gameThread.interrupt();
        }
        
        if (host != null) {
            try {
                host.close();
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (user != null) {
            try {
                user.close();
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
        

        SwingUtilities.invokeLater(() -> {
            new StarterMenuView(player);
        });
    }





//GETTER SETTER 
    public Player getPlayer() {
        return player;
    }

    public int getGameMode() {
        return gameMode;
    }

    public boolean getMultiGameReady() {
        return multiGameReady;
    }

    public void setGameMode(int mode) {
        this.gameMode = mode;
    }

    public ArrayList<Integer> getOpponentState() {
        return user.getStateOpponent();
    }

    public LanceOfDestiny getEditingFrame() {
        return editingFrame;
    }

    public MultiplayerRunning getMultiRunningFrame() {
        return multiRunning;
    }

}
