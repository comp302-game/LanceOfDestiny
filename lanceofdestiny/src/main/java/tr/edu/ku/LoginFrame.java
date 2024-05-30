package tr.edu.ku.View.LoginView;

import java.awt.BorderLayout;

import javax.swing.JFrame;

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


    public void switchLogin() {
        getContentPane().removeAll();
        log.resetLogin();
        getContentPane().add(log, BorderLayout.CENTER);
        pack();
    }


    public void switchRegister() {
        getContentPane().removeAll();
        reg.resetLogin();
        getContentPane().add(reg, BorderLayout.CENTER);
        pack();
    }


    public void switchMain() {
        getContentPane().removeAll();
        getContentPane().add(logRegPanel, BorderLayout.CENTER);
        pack();
    }
}
