package tr.edu.ku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException; // Import IOException for handling the exception
import javax.imageio.ImageIO;

public class LanceOfDestiny extends JFrame  {

    private MainApplication mainApp;
    private EditorPanel editorPanel;
    
    private BufferedImage bluegem;
    private BufferedImage firm;
    private BufferedImage redgem;


    public LanceOfDestiny (MainApplication main) {

        this.mainApp = main;
        setTitle("Lance Of Destiny - Editing Mode");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setResizable(true);

        try {
            // Load the image
            bluegem = ImageIO.read(getClass().getResourceAsStream("/Assets/200iconbluegem.png"));
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }

        try {
            // Load the image
            firm = ImageIO.read(getClass().getResourceAsStream("/Assets/200iconfirm.png"));
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }

        try {
            // Load the image
            redgem = ImageIO.read(getClass().getResourceAsStream("/Assets/200iconredgem.png"));
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }


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
        ImageIcon bIcon = new ImageIcon(bluegem); // Change the path to your image file
        JLabel blueLabel = new JLabel(bIcon);
        blueLabel.setPreferredSize(new Dimension(100, 25));

        ImageIcon rIcon = new ImageIcon(firm); // Change the path to your image file
        JLabel rLabel = new JLabel(rIcon);
        rLabel.setPreferredSize(new Dimension(100, 25));

        ImageIcon eIcon = new ImageIcon(redgem); // Change the path to your image file
        JLabel eLabel = new JLabel(eIcon);
        eLabel.setPreferredSize(new Dimension(100, 25));

        //Create text box for barrier numbers
        JTextField simpleField = new JTextField();
        simpleField.setPreferredSize(new Dimension(70, 25));

        //Create text box for barrier numbers
        JTextField reinforcedField = new JTextField();
        reinforcedField.setPreferredSize(new Dimension(70, 25));

        //Create text box for barrier numbers
        JTextField explosiveField = new JTextField();
        explosiveField.setPreferredSize(new Dimension(70, 25));


        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Right-aligned FlowLayout

        buttonPanel.add(eLabel);
        buttonPanel.add(explosiveField);
        buttonPanel.add(rLabel);
        buttonPanel.add(reinforcedField);
        buttonPanel.add(blueLabel);
        buttonPanel.add(simpleField);
        buttonPanel.add(createLayout);
        buttonPanel.add(playLayout);
        buttonPanel.add(saveLayoutButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH); //Add control panel to the frame
        
        editorPanel = new EditorPanel();
        getContentPane().add(editorPanel, BorderLayout.CENTER); //Add editor panel to the frame



        //Button functionalities
        saveLayoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions when the button is clicked
                editorPanel.saveLayout(main.getPlayer());
            }
        });

        playLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions when the button is clicked
                viewLayouts(main.getPlayer());
            }
        });

        createLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int snum = Integer.parseInt(simpleField.getText());
                    int rnum = Integer.parseInt(reinforcedField.getText());
                    int exnum = Integer.parseInt(explosiveField.getText());
                    editorPanel.getEditingArea().createEditingArea(snum, rnum, exnum);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });


        pack();
        setVisible(true);
    }



    //INITIALIZE LAYOUT SCREEN
    private void viewLayouts(Player player) {
        JFrame layoutFrame = new JFrame();
        layoutFrame.setTitle("Layout Viewer");
        layoutFrame.setSize(300, 400);
        layoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        layoutFrame.setLayout(null);
        layoutFrame.setLocationRelativeTo(null);
        layoutFrame.setResizable(false);
  
        DefaultListModel<String> l = new DefaultListModel<>();

        for(Layout layout : player.getLayouts()){
            l.addElement(layout.getName());
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
                    switchToRunningMode(player.getLayouts().get(index));
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
                    editorPanel.getEditingArea().LoadLayout(player.getLayouts().get(index));
                }

            }
        });

        layoutFrame.add(chooseLayout);
        layoutFrame.add(playLayout);
        layoutFrame.add(scrollPane);
        layoutFrame.setVisible(true);
    }



    public void switchToRunningMode(Layout layout) {
        mainApp.switchToRunningMode(layout); // Call the mode switch method from MainApplication
    }

    
    public EditorPanel getEditingPlane() {  
        return editorPanel;
    }

}
