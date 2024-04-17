package tr.edu.ku;

import java.awt.*;

//SUPERCLASS FOR BARRIERS
public class Barrier {
  protected int x;
  protected int y;
  protected int width;
  protected int height;
  protected boolean visible;

  public Barrier(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.visible = true;
  }

  public Rectangle getBounds() {
      return new Rectangle(x, y, width, height);
  }

  public boolean isVisible() {
      return visible;
  }

  public void setVisible(boolean visible) {
      this.visible = visible;
  }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
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

	public void setY(int y) {
		this.y = y;
	}

	public boolean intersects(Rectangle rect) {
        return new Rectangle(x, y, width, height).intersects(rect);
    }
}