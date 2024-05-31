package tr.edu.ku.View.MenuView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class HelpView extends JFrame {
    
    JButton ret = new JButton("EXIT");
    
    public HelpView() {
        setTitle("Help Menu");
        getContentPane().setLayout(new BorderLayout(0,0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        HelpPanel hPanel = new HelpPanel();
        getContentPane().add(hPanel, BorderLayout.CENTER);

        ret.setSize(new Dimension(100, 25));
        ret.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
        getContentPane().add(ret, BorderLayout.SOUTH);

        ret.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
        });


        pack();
        setVisible(true);
    }
    
}
