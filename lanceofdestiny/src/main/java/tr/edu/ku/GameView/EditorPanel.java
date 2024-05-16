package tr.edu.ku.GameView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tr.edu.ku.Constants;
import tr.edu.ku.Database.SaveLoadLayout;
import tr.edu.ku.Domain.Barrier;
import tr.edu.ku.Domain.ExplosiveBarrier;
import tr.edu.ku.Domain.ReinforcedBarrier;
import tr.edu.ku.Domain.RewardingBarrier;
import tr.edu.ku.Domain.SimpleBarrier;
import tr.edu.ku.GameArea.GameArea.EditingArea;
import tr.edu.ku.Render.Renderer;


public class EditorPanel extends JPanel implements MouseMotionListener, MouseListener {
    
    private EditingArea editingArea = new EditingArea();
    private Renderer renderer = new Renderer();

    private Rectangle reinforcedLabel = new Rectangle(1605, 70, 32, 20);
    private Rectangle simpleLabel = new Rectangle(1605, 20, 32, 20);
    private Rectangle explosiveLabel = new Rectangle(1605, 120, 32, 20);
    private Rectangle rewardingLabel = new Rectangle(1605, 170, 32, 20);

    private Rectangle boundary = new Rectangle(0, 40, 1600, 610); //Allowable barrier placement area

    private Barrier selectedBarrier;
    
    private Point initialClick;
    private boolean isDragging = false;
    private boolean isEditing = false;
    private double prev_x;
    private double prev_y;


    public EditorPanel() {

        setPreferredSize(new Dimension(Constants.EDITORPANEL_WIDTH, Constants.EDITORPANEL_HEIGHT));
        setFocusable(true);   
        addMouseListener(this);
        addMouseMotionListener(this);
        }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Constants.background, 0, 0, 1600, 900, null);

        if (!(selectedBarrier == null) && selectedBarrier.isVisible()) { //If player has selected a barrier and dragging it print the selected barrier
            if(selectedBarrier instanceof SimpleBarrier) {
                g.drawImage(Constants.simple_image, (int) selectedBarrier.getX(), (int) selectedBarrier.getY(), selectedBarrier.getWidth(), selectedBarrier.getHeight(), null);}
            else if(selectedBarrier instanceof ReinforcedBarrier) {
                g.drawImage(Constants.firm_image, (int) selectedBarrier.getX(), (int) selectedBarrier.getY(), selectedBarrier.getWidth(), selectedBarrier.getHeight(), null);}
            else if(selectedBarrier instanceof ExplosiveBarrier) {
                g.drawImage(Constants.explosive_image, (int) selectedBarrier.getX(), (int) selectedBarrier.getY(), selectedBarrier.getWidth(), selectedBarrier.getHeight(), null);}
            else if(selectedBarrier instanceof RewardingBarrier) {
                g.drawImage(Constants.rewarding_image, (int) selectedBarrier.getX(), (int) selectedBarrier.getY(), selectedBarrier.getWidth(), selectedBarrier.getHeight(), null);}
        }


        // Create dashed line
        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
        g2d.setStroke(dashed);
        g2d.setColor(Color.WHITE);
        
        // Draw dashed lines
        int startX = 0;
        int startY = 650;
        int endX = 1600;
        int endY = 650;
        g2d.drawLine(startX, startY, endX, endY);

        startX = 0;
        startY = 40;
        endX = 1600;
        endY = 40;
        g2d.drawLine(startX, startY, endX, endY);

        //Display the numbers of barriers placed
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18)); // Font name, style, size
        g.drawString(""+editingArea.getSimpleBarriers().size(), 1645, 35);
        g.drawString(""+editingArea.getReinforcedBarriers().size(), 1645, 85);
        g.drawString(""+editingArea.getExplosiveBarriers().size(), 1645, 135);
        g.drawString(""+editingArea.getRewardingBarriers().size(), 1645, 185);

        renderer.renderEditing((Graphics2D) g, editingArea);
        }

        
    public void updateEditingState() {
        editingArea.updateEditingState();
    }

    
    
    //Save layout to players layout list
    public void saveLayout(SaveLoadLayout layout_manager) {
        try {
            editingArea.checkCorrectNum();
            layout_manager.SaveLayout(editingArea);
            JOptionPane.showMessageDialog(this, "Layout saved to player's layout saves.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid action: "+e.getMessage());
        } 
    }


    
//INPUT
    @Override
    public void mouseClicked(MouseEvent arg0) {
    }


    @Override
    public void mouseEntered(MouseEvent arg0) {
    }


    @Override
    public void mouseExited(MouseEvent arg0) {
    }


    @Override
    public void mousePressed(MouseEvent e) {
        //Check if labels are clicked
        if (simpleLabel.contains(e.getPoint()))  {
            SimpleBarrier newSBarrier = new SimpleBarrier((int) e.getPoint().getX(), (int) e.getPoint().getY(), Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
            selectedBarrier = newSBarrier;
            initialClick = e.getPoint();
            isDragging = true;
    
        }

        else if (reinforcedLabel.contains(e.getPoint()))  {
            ReinforcedBarrier newRBarrier = new ReinforcedBarrier((int) e.getPoint().getX(), (int) e.getPoint().getY(), Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
            selectedBarrier = newRBarrier;
            initialClick = e.getPoint();
            isDragging = true;
      
        }

        else if (explosiveLabel.contains(e.getPoint()))  {
            ExplosiveBarrier newEBarrier = new ExplosiveBarrier((int) e.getPoint().getX(), (int) e.getPoint().getY(), Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
            selectedBarrier = newEBarrier;
            initialClick = e.getPoint();
            isDragging = true;
      
        }

        else if (rewardingLabel.contains(e.getPoint()))  {
            RewardingBarrier newRBarrier = new RewardingBarrier((int) e.getPoint().getX(), (int) e.getPoint().getY(), Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
            selectedBarrier = newRBarrier;
            initialClick = e.getPoint();
            isDragging = true;
        }



        //Check if already existing barriers are clicked
        else {
            for(SimpleBarrier sbarrier : editingArea.getSimpleBarriers()){
                if(sbarrier.getBounds().contains(e.getPoint())){
                    selectedBarrier = sbarrier;
                    initialClick = e.getPoint();
                    isDragging = true;
                    isEditing = true;
                    prev_x = sbarrier.getX();
                    prev_y = sbarrier.getY();
                }
            }

            for(ReinforcedBarrier rbarrier : editingArea.getReinforcedBarriers()){
                if(rbarrier.getBounds().contains(e.getPoint())){
                    selectedBarrier = rbarrier;
                    initialClick = e.getPoint();
                    isDragging = true;
                    isEditing = true;
                    prev_x = rbarrier.getX();
                    prev_y = rbarrier.getY();
                }
            }

            for(ExplosiveBarrier eBarrier : editingArea.getExplosiveBarriers()){
                if(eBarrier.getBounds().contains(e.getPoint())){
                    selectedBarrier = eBarrier;
                    initialClick = e.getPoint();
                    isDragging = true;
                    isEditing = true;
                    prev_x = eBarrier.getX();
                    prev_y = eBarrier.getY();
                }
            }


            for(RewardingBarrier wBarrier : editingArea.getRewardingBarriers()){
                if(wBarrier.getBounds().contains(e.getPoint())){
                    selectedBarrier = wBarrier;
                    initialClick = e.getPoint();
                    isDragging = true;
                    isEditing = true;
                    prev_x = wBarrier.getX();
                    prev_y = wBarrier.getY();
                }
            }
        }
    }


    @Override
    public void mouseReleased(MouseEvent arg0) {

        if (selectedBarrier != null){
        isDragging = false;
    
        if(boundary.contains(selectedBarrier.getBounds()) && isEditing == false) {

             try {
                editingArea.checkValidPlacement(selectedBarrier);

                if (selectedBarrier instanceof SimpleBarrier) {
                    editingArea.getSimpleBarriers().add((SimpleBarrier) selectedBarrier);
                }

                else if (selectedBarrier instanceof ReinforcedBarrier) {
                    editingArea.getReinforcedBarriers().add((ReinforcedBarrier) selectedBarrier);
                }

                else if (selectedBarrier instanceof ExplosiveBarrier) {
                    editingArea.getExplosiveBarriers().add((ExplosiveBarrier) selectedBarrier);
                }

                else if (selectedBarrier instanceof RewardingBarrier) {
                    editingArea.getRewardingBarriers().add((RewardingBarrier) selectedBarrier);
                }

            } catch (Exception e) {
                System.out.println("Invalid action: "+e.getMessage());
            }
        }

        else if(boundary.contains(selectedBarrier.getBounds()) == false && isEditing == false) {
            selectedBarrier.setVisible(false);
        }
    
        
        else if(boundary.contains(selectedBarrier.getBounds()) && isEditing) {
            
            try {
                editingArea.checkValidPlacement(selectedBarrier);
            } catch (Exception e) {
                System.out.println("Invalid action: "+e.getMessage());
                selectedBarrier.setX(prev_x);
                selectedBarrier.setY(prev_y); 
            }
        }


        else if(boundary.contains(selectedBarrier.getBounds()) == false && isEditing) {
            selectedBarrier.setX(prev_x);
            selectedBarrier.setY(prev_y);  
        }

        selectedBarrier = null;
        isEditing = false;
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDragging && selectedBarrier != null){
            int dx = e.getX() - initialClick.x;
            int dy = e.getY() - initialClick.y;
            selectedBarrier.setX(selectedBarrier.getX() + dx);
            selectedBarrier.setY(selectedBarrier.getY() + dy);
            initialClick = e.getPoint();
        }
    }


    @Override
    public void mouseMoved(MouseEvent arg0) {
    }


    public EditingArea getEditingArea() {
        return this.editingArea;
    }

}
