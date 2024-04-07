package tr.edu.ku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException; // Import IOException for handling the exception
import javax.imageio.ImageIO;
import java.io.File;


public class EditorPanel extends JPanel implements MouseMotionListener, MouseListener {
    
    static final int WIDTH = 1600;
    static final int HEIGHT = 900;

    private EditingArea editingArea = new EditingArea();
    private Renderer renderer = new Renderer();

    private Rectangle reinforcedLabel = new Rectangle(1540, 50, 32, 20);
    private Rectangle simpleLabel = new Rectangle(1540, 20, 32, 20);
    private Barrier selectedBarrier;

    private Point initialClick;
    private boolean isDragging = false;
    
    private BufferedImage background;

    public EditorPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        try {
            // Load the image
            background = ImageIO.read(new File(getClass().getResourceAsStream("/Assets/<png ismi>.png");
        } catch (IOException e) {
            // Handle the IOException (e.g., print an error message)
            e.printStackTrace();
        }

        setFocusable(true);   
        addMouseListener(this);
        addMouseMotionListener(this);

        }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 1600, 900, null);
        renderer.renderEditing((Graphics2D) g, editingArea);
        }

        
    public void updateEditingState() {
        editingArea.updateEditingState();
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
        if (simpleLabel.contains(e.getPoint()))  {
            SimpleBarrier newSBarrier = new SimpleBarrier((int) e.getPoint().getX(), (int) e.getPoint().getY(), 32, 20);
            selectedBarrier = newSBarrier;
            initialClick = e.getPoint();
            editingArea.getSimpleBarriers().add(newSBarrier);
            isDragging = true;
      
        }

        else if (reinforcedLabel.contains(e.getPoint()))  {
            ReinforcedBarrier newRBarrier = new ReinforcedBarrier((int) e.getPoint().getX(), (int) e.getPoint().getY(), 32, 20);
            selectedBarrier = newRBarrier;
            initialClick = e.getPoint();
            editingArea.getReinforcedBarriers().add(newRBarrier);
            isDragging = true;
      
        }
    }


    @Override
    public void mouseReleased(MouseEvent arg0) {
        isDragging = false;
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

    }
