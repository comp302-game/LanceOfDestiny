package tr.edu.ku.Domain;

public class HollowBarrier extends Barrier {

    public HollowBarrier(int x, int y, int width, int height, int row, int column) {
        super(x, y, width, height, row, column);
        this.setIsDynamic(false);
    }
    
}
