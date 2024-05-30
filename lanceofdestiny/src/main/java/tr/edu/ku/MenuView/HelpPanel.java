package tr.edu.ku.View.MenuView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import tr.edu.ku.Constants;


public class HelpPanel extends JPanel {

    public HelpPanel(){
        setPreferredSize(new Dimension(Constants.GAMEPANEL_WIDTH, Constants.GAMEPANEL_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image at the top-left corner of the panel
 
        g.drawImage(Constants.background, 0, 0, 1600, 900, null);
   

        // Barriers
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28));
        g.drawString("Barriers", 10, 25);

        g.drawLine(5, 33, 220, 33);
        
        g.drawImage(Constants.simple_image, 5, 45, 40, 20, null);
  
        g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
        g.drawString("Simple Barrier (Ancient Wall): Can be broken in one hit. When broken, it disappears.", 55, 60);

        g.drawImage(Constants.firm_image, 5, 95, 40, 20, null);
     
        g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
        g.drawString("Reinforced Barrier (Triple Rashomon): These barriers are more difficult to destroy. Each one", 55, 105);
        g.drawString("contains a number written on it (not necessarily just three as the name implies), which", 55, 120);
        g.drawString("corresponds to the number of hits it requires to be destroyed. After every hit it receives, the number", 55, 135);
        g.drawString("decreases by 1, and the barrier disappears once the number reaches zero.", 55, 150);

        g.drawImage(Constants.explosive_image, 5, 170, 40, 20, null);
     
        g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
        g.drawString("Explosive Barrier (Volatile Fence): This barrier has a circular shape and it explodes once it is", 55, 185);
        g.drawString("hit. Once exploded, its remains fall downwards towards the Magical Staff.", 55, 200);
        g.drawString("If the remains touch the Magical Staff, the player loses a chance.", 55, 215);
        
        g.drawImage(Constants.rewarding_image, 5, 230, 40, 20, null);
 
        g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
        g.drawString("Rewarding Barrier (Wish Endower): This barrier can be destroyed in one hit like the simple one.", 55, 245);
        g.drawString("Once destroyed, it drops a box downwards towards the Magical Staff. If the Magical Staff", 55, 260);
        g.drawString(" touches the box, then the box opens and rewards the warrior with  a spell that can be either used", 55, 275);
        g.drawString(" to support the warrior, or to create more challenges and barriers for the other player.", 55, 290);


        //Spells
   
        g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28));
        g.drawString("Spells", 10, 320);

        g.drawLine(5, 328, 220, 328);


        g.drawImage(Constants.felix, 5, 340, 30, 30, null);
       
        g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
        g.drawString("Felix Felicis: This ability increases the player’s chances by 1.", 55, 360);

        g.drawImage(Constants.player2x, 5, 380, 30, 30, null);
        g.drawString("Magical Staff Expansion: This ability doubles the length of the Magical Staff. It is not necessarily", 55, 395);
        g.drawString("once it is received. The player can choose to activate it whenever they want by either pressing", 55, 410);
        g.drawString("M, or pressing its icon on the screen. Once activated, it lasts for only 30 seconds,", 55, 425);
        g.drawString("after which the Magical Staff returns to its original state.", 55, 440);

        g.drawImage(Constants.hex, 5, 460, 30, 30, null);
        g.drawString("Hex: This ability equips the Magical Staff with two magical canons on both of its ends. They can", 55, 475);
        g.drawString("fire magical hexes that can hit the barriers. A hex hit has the same effect as the hit of a", 55, 490);
        g.drawString("Fire Ball. It does not activate immediately, but can be activated by pressingH or pressing", 55, 505);
        g.drawString("its icon on the screen. Once activated it remains active for only 30 seconds and then disappears afterwards.", 55, 520);

        g.drawImage(Constants.fireball2x, 5, 540, 30, 30, null);
        g.drawString("Overwhelming Fire Ball: This ability upgrades the Fire Ball and makes it much more powerful, such", 55, 555);
        g.drawString("that if it hits any barriers, it destroys it and passes through it regardless of its type (even for", 55, 570);
        g.drawString("the firm barriers). This upgrade only lasts 30 seconds after it is activated.", 55, 585);


       //Mulitplayer Spells

       g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28));
       g.drawString("Multiplayer Spells", 10, 615);

       g.drawLine(5, 623, 220, 623);

       g.drawString("1", 5, 645);
       g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
        g.drawString("Time Alter (Double Accel): When a player activates this spell, the speed of the opponent's", 20, 645);
        g.drawString("fireball is reduced by half. Its effect lasts for 15 seconds.Can be activated by pressing 1", 20, 660);
        
        g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28));
        g.drawString("2", 5, 690);
        g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
         g.drawString("Infinite Void: When a player activates this spell, 8 barriers (not destroyed yet) are randomly chosen from the", 20, 690);
         g.drawString("opponent’s map. If fewer than 8 barriers exist, all of them will be chosen. These barriers are", 20, 705);
         g.drawString("frozen for 15 seconds. When frozen, neither the fireball nor the hex spell, will affect them. The fireball will", 20, 720);
         g.drawString("reflect after each hit, and the hex will have no effect. The only way to destroy them is by activating the", 20, 735);
         g.drawString("Overwhelming Fire Ball spell, but note that in this case, the Overwhelming Fire Ball will have the effect", 20, 750);
         g.drawString("of a normal fireball on the frozen barriers. Can be activated by pressing 2", 20, 765);

         g.drawLine(800,70, 800, 750);

         g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28));
         g.drawString("3", 820, 60);

         g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
         g.drawString("Hollow Purple: When a player activates this spell, 8 hollow purple barriers are added randomly to the", 835, 60);
         g.drawString("opponent’s map. A hollow purple barrier is an empty barrier. They can be destroyed with a single hit.", 835, 75);
         g.drawString("However, their destruction does not contribute to the player's score. CAn be activated by pressing 3.", 835, 90);

         g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28));
         g.drawString("Magical Staff", 820, 122);
         g.drawLine(815, 130, 1030, 130);

         g.drawString("Left Arrow:", 820, 160);
         g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
         g.drawString(" Thisbutton is used for moving the Magical Staff to the left.", 940, 160);

         g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28));
         g.drawString("Right Arrow:", 820, 190);
         g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
         g.drawString("This button is used for moving the Magical Staff to the right.", 950, 190);

         g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28));
         g.drawString("A:", 820, 220);
         g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
         g.drawString("This button is used for rotating the Magical Staff in the counter clockwise direction", 860, 220);

         g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28));
         g.drawString("D:", 820, 250);
         g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
         g.drawString("This button is used for rotating the Magical Staff in the clockwise direction", 860, 250);


         g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 28));
         g.drawString("Game Rules", 820, 290);
         g.drawLine(815, 298, 1030, 298);

         g.setFont(new Font("Tw Cen MT Condensed", Font.CENTER_BASELINE, 18));
         g.drawString("The player has control over the Magical Staff, he/she should use the Magical Staffto direct the Fire", 820, 320);
         g.drawString("Ball to destroy as many barriers as possible and at the same time protect the Fire Ball from falling.", 820, 335);
         g.drawString("At the begging of the game the player has 3 chances. However, every time that he/she drops", 820, 350);
         g.drawString("the ball or gets hit by the remains of the Explosive berrier, he/she loses a chance.", 820, 365);
         g.drawString("If the player's chnaces drop to 0, he/she loses the game.", 820, 380);

    }
}
