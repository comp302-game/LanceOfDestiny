package tr.edu.ku;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private LogRegPanel logRegPanel;
    private RegisterPanel reg;
    private LoginPanel log;
    
    public LoginFrame() {

        setTitle("Lance Of Destiny - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setResizable(false);

        logRegPanel = new LogRegPanel(this);
        reg = new RegisterPanel(this);
        log = new LoginPanel(this);
         
        getContentPane().add(logRegPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }


    public LogRegPanel getLogRegPanel() {
        return logRegPanel;
    }

    
    public LoginPanel getLoginPanel() {
        return log;
    }

    public RegisterPanel getRegisterPanel() {
        return reg;
    }


}
