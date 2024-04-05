package tr.edu.ku;
import javax.swing.JFrame;
public class Launcher {

    public static void main(String[] args) {
        GamePanel gamepanel = new GamePanel();
        JFrame frame = new LanceOfDestiny(gamepanel);
        new Thread(new GameLoop(gamepanel)).start();
    }
}