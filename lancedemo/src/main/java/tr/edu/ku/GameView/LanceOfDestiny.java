package tr.edu.ku.GameView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import tr.edu.ku.Constants;
import tr.edu.ku.Main.MainApplication;
import tr.edu.ku.Database.SaveLoadLayout;
import tr.edu.ku.GameArea.Grid;


public class LanceOfDestiny extends JFrame  {

    private MainApplication mainApp;
    private EditorPanel editorPanel;
    private SaveLoadLayout layout_manager;


    public LanceOfDestiny (MainApplication main) {

        layout_manager = new SaveLoadLayout(main.getPlayer());
        this.mainApp = main;
        setTitle("Lance Of Destiny - Editing Mode");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setResizable(false);


        //Play Layout Button
        JButton playLayout = new JButton("LAYOUTS");
        playLayout.setPreferredSize(new Dimension(110, 25));
		playLayout.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));

        //Save Layout Button
        JButton saveLayoutButton = new JButton("SAVE LAYOUT");
        saveLayoutButton.setPreferredSize(new Dimension(110, 25));
		saveLayoutButton.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));

        //Create Layout Button
        JButton createLayout = new JButton("CREATE LAYOUT");
        createLayout.setPreferredSize(new Dimension(120, 25));
		createLayout.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));

        //Create icons for barriers
        ImageIcon bIcon = new ImageIcon(Constants.bluegem);
        JLabel blueLabel = new JLabel(bIcon);
        blueLabel.setPreferredSize(new Dimension(100, 25));

        ImageIcon rIcon = new ImageIcon(Constants.firm);
        JLabel rLabel = new JLabel(rIcon);
        rLabel.setPreferredSize(new Dimension(100, 25));

        ImageIcon eIcon = new ImageIcon(Constants.redgem); 
        JLabel eLabel = new JLabel(eIcon);
        eLabel.setPreferredSize(new Dimension(100, 25));

        ImageIcon wIcon = new ImageIcon(Constants.greengem); 
        JLabel wLabel = new JLabel(wIcon);
        wLabel.setPreferredSize(new Dimension(100, 25));

        //Create text box for barrier numbers
        JTextField simpleField = new JTextField();
        simpleField.setPreferredSize(new Dimension(70, 25));

        //Create text box for barrier numbers
        JTextField reinforcedField = new JTextField();
        reinforcedField.setPreferredSize(new Dimension(70, 25));

        //Create text box for barrier numbers
        JTextField explosiveField = new JTextField();
        explosiveField.setPreferredSize(new Dimension(70, 25));

        //Create text box for barrier numbers
        JTextField rewardingField = new JTextField();
        rewardingField.setPreferredSize(new Dimension(70, 25));

        //Empty JLabel for allignment
        JLabel emptyLabel = new JLabel();
        emptyLabel.setPreferredSize(new Dimension(60, 25));

        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Right-aligned FlowLayout
        buttonPanel.setBackground(new Color(74, 179, 217));

        buttonPanel.add(wLabel);
        buttonPanel.add(rewardingField);
        buttonPanel.add(eLabel);
        buttonPanel.add(explosiveField);
        buttonPanel.add(rLabel);
        buttonPanel.add(reinforcedField);
        buttonPanel.add(blueLabel);
        buttonPanel.add(simpleField);
        buttonPanel.add(createLayout);
        buttonPanel.add(playLayout);
        buttonPanel.add(saveLayoutButton);
        buttonPanel.add(emptyLabel);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH); //Add control panel to the frame
        
        editorPanel = new EditorPanel();
        editorPanel.setBackground(new Color(74, 179, 217));
        getContentPane().add(editorPanel, BorderLayout.CENTER); //Add editor panel to the frame


        //Button functionalities
        saveLayoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions when the button is clicked
                editorPanel.saveLayout(layout_manager);
            }
        });

        playLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions when the button is clicked
                viewLayouts();
            }
        });

        createLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int snum = Integer.parseInt(simpleField.getText());
                    int rnum = Integer.parseInt(reinforcedField.getText());
                    int exnum = Integer.parseInt(explosiveField.getText());
                    int rwnum = Integer.parseInt(rewardingField.getText());
                    editorPanel.getEditingArea().createEditingArea(snum, rnum, exnum, rwnum);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });


        pack();
        setVisible(true);
    }



    //INITIALIZE LAYOUT SCREEN
    private void viewLayouts() {
        JFrame layoutFrame = new JFrame();
        layoutFrame.setTitle("Layout Viewer");
        layoutFrame.setSize(300, 400);
        layoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        layoutFrame.setLayout(null);
        layoutFrame.setLocationRelativeTo(null);
        layoutFrame.setResizable(false);
  
        DefaultListModel<String> l = new DefaultListModel<>();

        for(Integer layout_id : layout_manager.getLayouts()){
            String layout_name = "Layout: "+layout_id;
            l.addElement(layout_name);
        }

        JList<String> list = new JList<>(l);
        list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(75, 20, 150, 250);

        JButton playLayout = new JButton("PLAY");
        playLayout.setBounds(75, 300, 70, 25);
		playLayout.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));

        playLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(list.getSelectedIndex() != -1){
                    layoutFrame.dispose();  //close layout screen
                    int index = list.getSelectedIndex();
                    Grid grid = layout_manager.LoadLayout(layout_manager.getLayouts().get(index));
                    switchToRunningMode(grid);
                }

            }
        });


        JButton chooseLayout = new JButton("EDIT");
        chooseLayout.setBounds(150, 300, 70, 25);
		chooseLayout.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 14));

        chooseLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(list.getSelectedIndex() != -1){
                    layoutFrame.dispose();  //close layout screen
                    int index = list.getSelectedIndex();
                    Grid grid = layout_manager.LoadLayout(layout_manager.getLayouts().get(index));
                    editorPanel.getEditingArea().LoadLayout(grid);
                }

            }
        });

        layoutFrame.add(chooseLayout);
        layoutFrame.add(playLayout);
        layoutFrame.add(scrollPane);
        layoutFrame.setVisible(true);
    }



    public void switchToRunningMode(Grid grid) {
        mainApp.switchToRunningMode(grid); // Call the mode switch method from MainApplication
    }

    
    public EditorPanel getEditingPlane() {  
        return editorPanel;
    }

}
