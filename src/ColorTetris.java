//By Pat Needham

import java.awt.*;

public abstract class ColorTetris
{
	protected ColorRectangle r1, r2, r3, r4;
	private int x, y, row, col;
	private TetrisPanel t;
	private Color tetrisColor;
	
	public ColorTetris(int x1, int y1, int r, int c, Color color, TetrisPanel t1)
	{
		x = x1;
		y = y1;
		row = r;
		col = c;
		t = t1;
		tetrisColor = color;
		Color b = Color.BLACK;
		r1 = new ColorRectangle(b, color);
		r1.setSize(25, 25);
		r2 = new ColorRectangle(b, color);
		r2.setSize(25, 25);
		r3 = new ColorRectangle(b, color);
		r3.setSize(25, 25);
		r4 = new ColorRectangle(b, color);
		r4.setSize(25, 25);
		setLocation(x, y);
	}
	
	public abstract void setLocation(int x, int y);
	
	public abstract boolean nextSpotEmpty();
	
	public abstract void move();
	
	public abstract ColorTetris rotate();
	
	public abstract boolean canRotate();
	
	public abstract void addEachRectangle();
	
	public Color getColor()
	{
		return tetrisColor;
	}
	
	public void moveLeft()
	{
		setLocation(x - 25, y);
		x -= 25;
		setRow(getRow() - 1);
		t.repaint();
	}
	
	public void moveRight()
	{
		setLocation(x + 25, y);
		x += 25;
		setRow(getRow() + 1);
		t.repaint();
	}
	
	public void fill(Graphics2D g)
	{
		r1.fill(g);
		r2.fill(g);
		r3.fill(g);
		r4.fill(g);
	}
	
	public void draw(Graphics2D g)
	{
		r1.draw(g);
		r2.draw(g);
		r3.draw(g);
		r4.draw(g);
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x1)
	{
		x = x1;
		t.repaint();
	}
	
	public void setY(int y1)
	{
		y = y1;
		t.repaint();
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public void setRow(int r)
	{
		row = r;
	}
	
	public void setCol(int c)
	{
		col = c;
	}
	
	public TetrisPanel getTetrisPanel()
	{
		return t;
	}
}