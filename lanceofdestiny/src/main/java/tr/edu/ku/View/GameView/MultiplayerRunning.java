package tr.edu.ku.View.GameView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tr.edu.ku.Constants;
import tr.edu.ku.AppInterface.ObserverInterface.AttackSpellNumberObs;
import tr.edu.ku.AppInterface.ObserverInterface.GameStateObserver;
import tr.edu.ku.AppInterface.ObserverInterface.SpellNumberObs;
import tr.edu.ku.GameArea.Grid;
import tr.edu.ku.GameEngine.KeyboardInputHandler;
import tr.edu.ku.Main.MainApplication;


public class MultiplayerRunning extends JFrame implements GameStateObserver, AttackSpellNumberObs, SpellNumberObs {

    private MainApplication mainApp;
    private GamePanel gamePanel;
    private JLabel opponentField;
    private JLabel attackField;

    // Load spell images from Constants
    private  ImageIcon spellIcon1 = new ImageIcon(Constants.hex);
    private  ImageIcon spellIcon2 = new ImageIcon(Constants.felix);
    private  ImageIcon spellIcon3 = new ImageIcon(Constants.fireball2x);
    private  ImageIcon spellIcon4 = new ImageIcon(Constants.player2x);

    private  ImageIcon atspellIcon1 = new ImageIcon(Constants.accel);
    private  ImageIcon atspellIcon2 = new ImageIcon(Constants.voids);
    private  ImageIcon atspellIcon3 = new ImageIcon(Constants.hollow);

    private JLabel spellLabel1;
    private JLabel spellLabel2;
    private JLabel spellLabel3;
    private JLabel spellLabel4;

    private JLabel atspellLabel1;
    private JLabel atspellLabel2;
    private JLabel atspellLabel3;

    private CountdownWindow countdownWindow;

    public MultiplayerRunning(Grid grid, MainApplication main) {

        this.mainApp = main;
        setTitle("Lance Of Destiny - Multiplayer Mode");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setResizable(false);

        //Create game panel
        gamePanel = new GamePanel(grid, mainApp.getGameMode());
        gamePanel.getGameArea().addObserver(this);      //Add the observer for the attack spell counter
        gamePanel.getGameArea().addNumObserver(this);   //Add the observer for the rewarding spell counter
    

        // Create labels with icons and spell numbers
        spellLabel1 = createSpellLabel(spellIcon1);
        spellLabel2 = createSpellLabel(spellIcon2);
        spellLabel3 = createSpellLabel(spellIcon3);
        spellLabel4 = createSpellLabel(spellIcon4);

        spellLabel1.setPreferredSize(new Dimension(60, 25));
        spellLabel2.setPreferredSize(new Dimension(60, 25));
        spellLabel3.setPreferredSize(new Dimension(60, 25));
        spellLabel4.setPreferredSize(new Dimension(60, 25));


        // Create attack labels with icons and spell numbers
        atspellLabel1 = createSpellLabel(atspellIcon1);
        atspellLabel2 = createSpellLabel(atspellIcon2);
        atspellLabel3 = createSpellLabel(atspellIcon3);

        atspellLabel1.setPreferredSize(new Dimension(60, 25));
        atspellLabel2.setPreferredSize(new Dimension(60, 25));
        atspellLabel3.setPreferredSize(new Dimension(270, 25));


        //Ymir Panel
        attackField = new JLabel("\uD83D\uDE08");
        attackField.setFont(new Font("SansSerif", Font.BOLD, 16));
        attackField.setPreferredSize(new Dimension(450 ,25));


        //Pause Button
        JButton exit = new JButton("LEAVE");
        exit.setPreferredSize(new Dimension(100, 25));
		exit.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));


        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Right-aligned FlowLayout

        opponentField = new JLabel("OPPONENT STATE");
        opponentField.setPreferredSize(new Dimension(350, 30));
        opponentField.setFont(new Font("SansSerif", Font.PLAIN, 14));


        buttonPanel.add(spellLabel1);
        buttonPanel.add(spellLabel2);
        buttonPanel.add(spellLabel3);
        buttonPanel.add(spellLabel4);

        buttonPanel.add(atspellLabel1);
        buttonPanel.add(atspellLabel2);
        buttonPanel.add(atspellLabel3);

        buttonPanel.add(attackField);
        buttonPanel.add(opponentField);
        buttonPanel.add(exit);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH); //Add control panel to the frame
        getContentPane().add(gamePanel, BorderLayout.CENTER);


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainApp.exitGame();
            }
        });

        
        gamePanel.requestFocus();
        pack();

        KeyboardInputHandler.setXPressed(true);
        gamePanel.getGameArea().setGameState(); //pause

        countdownWindow = new CountdownWindow(gamePanel.getGameArea());
        countdownWindow.startCountdown();
        setVisible(true);
    }

    
    @Override
    public void onOpponentStateUpdate(String uname, int score, int barriers, int lives) { //OBSERVER PATTERN
        // Update the opponent state label with the new values
        opponentField.setText(String.format("%s \uD83D\uDD79  Score: %d, Barrier Count: %d, Lives: %d", uname, score, barriers, lives));
    }

    @Override
    public void onAttackSpellNumChange() {
        atspellLabel1.setText(": "+gamePanel.getGameArea().getACCEL());
        atspellLabel2.setText(": "+gamePanel.getGameArea().getVOID());
        atspellLabel3.setText(": "+gamePanel.getGameArea().getHOLLOW());
    }

    @Override
    public void onSpellNumChange() {
        spellLabel1.setText(": "+gamePanel.getGameArea().getHEX());
        spellLabel2.setText(": "+gamePanel.getGameArea().getFELIX());
        spellLabel3.setText(": "+gamePanel.getGameArea().getOFB());
        spellLabel4.setText(": "+gamePanel.getGameArea().getMSE());
    }


    @Override
    public void onReceivedAttack(String s) {
        attackField.setText("\uD83D\uDE08 : Player attacked with "+s);
    }


    private static JLabel createSpellLabel(ImageIcon icon) {
        JLabel label = new JLabel();
        label.setIcon(icon);
        label.setText(": 0");
        return label;
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
