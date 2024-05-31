package tr.edu.ku.Domain;

import java.awt.Rectangle;
import java.io.Serializable;

import tr.edu.ku.Domain.BarrierStrategy.DynamicBehavior;


//SUPERCLASS (Abstract) FOR ALL TYPES OF BARRIERS
public abstract class Barrier implements Serializable {

	private static final long serialVersionUID = 3L;

	private double x;
  	private double y;
  	private int width;
  	private int height;
  	private boolean visible;
  	private Boolean collideable;
  	private boolean isMoving;
	private boolean isDynamic;
	private int grid_row;
	private int grid_column;
	private boolean is_frozen;

	//Strategy Pattern
	DynamicBehavior dynamicBehavior;


  	public Barrier(int x, int y, int width, int height, int row, int column) {
    	this.x = x;
    	this.y = y;
    	this.width = width;
    	this.height = height;
    	this.visible = true;
		this.collideable = true;
		this.isMoving = false;
		this.grid_row = row;
		this.grid_column = column;
		this.is_frozen=false;
	}


	//Only calling this function is enough to control all barrier movements - Strategy Pattern
	public void MoveBarrier() {
		if(dynamicBehavior != null){
			dynamicBehavior.move(this);
		}
	}


//GETTER SETTER
  	public Rectangle getBounds() {
      	return new Rectangle((int) x, (int) y, width, height);
  	}

  	public boolean isVisible() {
      	return visible;
  	}

  	public void setVisible(boolean visible) {
      	this.visible = visible;
  	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean intersects(Rectangle rect) {
        return new Rectangle((int) x, (int) y, width, height).intersects(rect);
    }

	public boolean getCollideable() {
		return collideable;
	}

	public void setCollideable(Boolean b) {
		this.collideable = b;
	}

	public Rectangle getPath() {
		return new Rectangle((int) x-40, (int) y, 112, 20);
	}

	public double getCenterX() {
		return x + width/2;
	}

	public double getCenterY() {
		return y + height/2;
	}

	public DynamicBehavior getDynamicBehavior() {
		return dynamicBehavior;
	}

	public void setDynamicBehavior(DynamicBehavior db) {
		this.dynamicBehavior = db;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setIsMoving(boolean b) {
		this.isMoving = b;
	}

	public boolean isDynamic() {
		return isDynamic;
	}

	public void setIsDynamic(boolean b) {
		this.isDynamic = b;
	}

	public boolean isFrozen() {
		return is_frozen;
	}

	public void setIsFrozen(boolean b) {
		this.is_frozen = b;
	}


	public int getRow() {
		return grid_row;
	}

	public int getColumn() {
		return grid_column;
	}

	public void setRow(int r) {
		this.grid_row = r;
	}

	public void setColumn(int c) {
		this.grid_column = c;
	}
}