package tr.edu.ku.GameView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tr.edu.ku.Database.SaveLoadGame;
import tr.edu.ku.GameArea.Game;
import tr.edu.ku.GameArea.Grid;
import tr.edu.ku.Main.MainApplication;

public class RunningFrame extends JFrame {

    private MainApplication mainApp;
    private GamePanel gamePanel;
    private SaveLoadGame game_manager;

    public RunningFrame(Grid grid, MainApplication main) {

        game_manager = new SaveLoadGame(main.getPlayer());
        this.mainApp = main;
        setTitle("Lance Of Destiny - Running Mode");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setResizable(false);

        //Create game panel
        gamePanel = new GamePanel(grid);
    
        //Pause Button
        JButton pause = new JButton("PAUSE");
        pause.setPreferredSize(new Dimension(100, 25));
		pause.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));

        //Return Editing Mode Button
        JButton returnEditing = new JButton("RETURN EDITING");
        returnEditing.setPreferredSize(new Dimension(125, 25));
		returnEditing.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));

        //Save Button
        JButton save = new JButton("SAVE");
        save.setPreferredSize(new Dimension(100, 25));
		save.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));

        //Load Button
        JButton load = new JButton("SAVED GAMES");
        save.setPreferredSize(new Dimension(100, 25));
		save.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));


        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Right-aligned FlowLayout
        buttonPanel.add(load);
        buttonPanel.add(save);
        buttonPanel.add(pause);
        buttonPanel.add(returnEditing);
        

        getContentPane().add(buttonPanel, BorderLayout.SOUTH); //Add control panel to the frame
        getContentPane().add(gamePanel, BorderLayout.CENTER);


        //Button functionalities
        returnEditing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToEditingMode();
                gamePanel.requestFocus();
            }
        });

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.getGameArea().setGameState(); //Revert the game state 
                if (gamePanel.getGameArea().getGameState() == -1) {pause.setText("RESUME");}
                else if (gamePanel.getGameArea().getGameState() == 1) {pause.setText("PAUSE");}
                gamePanel.requestFocus();
            }
        });


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //call save game function of game panel.
                gamePanel.saveGame(game_manager);
                gamePanel.requestFocus();
            }
        });


        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.getGameArea().setGameState();    //revert the game state
                pause.setText("RESUME");
                viewSavedGames();
                gamePanel.requestFocus();
            }
        });
 
        
        gamePanel.requestFocus();
        pack();
        setVisible(true);
    }


   //INITIALIZE SAVED GAMES SCREEN
    private void viewSavedGames() {
        JFrame savedGamesFrame = new JFrame();
        savedGamesFrame.setTitle("Saved Games Viewer");
        savedGamesFrame.setSize(300, 400);
        savedGamesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        savedGamesFrame.setLayout(null);
        savedGamesFrame.setLocationRelativeTo(null);
        savedGamesFrame.setResizable(false);
  
        DefaultListModel<String> l = new DefaultListModel<>();

        for(Integer game_id : game_manager.getGames()){
            String game_name = "Game: "+game_id;
            l.addElement(game_name);
        }

        JList<String> list = new JList<>(l);
        list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(75, 20, 150, 250);

        JButton playSavedGame = new JButton("PLAY");
        playSavedGame.setBounds(75, 300, 70, 25);
		playSavedGame.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));

        playSavedGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(list.getSelectedIndex() != -1){
                    savedGamesFrame.dispose();  //close saved games screen
                    int index = list.getSelectedIndex();
                    Game game = game_manager.LoadGame(game_manager.getGames().get(index)); //FIX THE PROBLEM WITH INDEXES
                    gamePanel.getGameArea().LoadSavedGame(game);
                    gamePanel.requestFocus();
                }
            }
        });

        savedGamesFrame.add(playSavedGame);
        savedGamesFrame.add(scrollPane);
        savedGamesFrame.setVisible(true);
    }


    public void switchToEditingMode() {
        mainApp.switchToEditingMode(); // Call the mode switch method from MainApplication
    }

    
    public void LoadGamePanel(Grid grid) {
        gamePanel.setGameArea(grid);
    }


    public GamePanel getGamePanel() {
        return gamePanel;
    }

}
