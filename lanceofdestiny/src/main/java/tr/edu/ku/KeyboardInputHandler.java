package tr.edu.ku;

import java.awt.event.KeyEvent;

public class KeyboardInputHandler  {
    
    private static boolean leftPressed = false;
    private static boolean rightPressed = false;
    private static boolean A_pressed = false;
    private static boolean D_pressed = false;


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        } else if (key == KeyEvent.VK_A) {
            A_pressed = true;
        } else if (key == KeyEvent.VK_D) {
            D_pressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        } else if (key == KeyEvent.VK_A) {
            A_pressed = false;
        } else if (key == KeyEvent.VK_D) {
            D_pressed = false;
        }
    }


    public static boolean getLeftPressed() {
		return leftPressed;
	}

	public static boolean getRightPressed() {
		return rightPressed;
	}

    public static boolean getA_Pressed() {
		return A_pressed;
	}

	public static boolean getD_Pressed() {
		return D_pressed;
	}



}