//By Pat Needham
//Looks like: * 
//			  **
//			  *

import java.awt.*;

public class Tetris2B extends ColorTetris
{
	public Tetris2B(int x1, int y1, int row, int col, Color c, TetrisPanel t)
	{
		super(x1, y1, row, col, c, t);
		getTetrisPanel().enterGrid(row, col);
		getTetrisPanel().enterGrid(row, col + 1);
		getTetrisPanel().enterGrid(row + 1, col + 1);
		getTetrisPanel().enterGrid(row, col + 1);
	}
	
	public void setLocation(int x, int y)
	{
		r1.setLocation(x, y);
		r2.setLocation(x, y + 25);
		r3.setLocation(x + 25, y + 25);
		r4.setLocation(x, y + 50);
	}
	
	public void move()
	{
		setY(getY() + 25);
		setCol(getCol() + 1);
		setLocation(getX(), getY());
		
		getTetrisPanel().enterGrid(getRow(), getCol() + 2);
		getTetrisPanel().enterGrid(getRow() + 1, getCol() +1);
		
		getTetrisPanel().exitGrid(getRow(), getCol() - 1);
		getTetrisPanel().exitGrid(getRow() + 1, getCol());
	}
	
	public void moveLeft()
	{
		if (!getTetrisPanel().checkLocation(getRow() - 1, getCol())
			&& !getTetrisPanel().checkLocation(getRow() - 1, getCol() + 1)
			&& !getTetrisPanel().checkLocation(getRow() - 1, getCol() + 2))
		{
			getTetrisPanel().enterGrid(getRow() - 1, getCol());
			getTetrisPanel().enterGrid(getRow() - 1, getCol() + 1);
			getTetrisPanel().enterGrid(getRow() - 1, getCol() + 2);
			getTetrisPanel().exitGrid(getRow(), getCol());
			getTetrisPanel().exitGrid(getRow() + 1, getCol() + 1);
			getTetrisPanel().exitGrid(getRow(), getCol() + 2);
			super.moveLeft();
		}
	}
	
	public void moveRight()
	{
		if (!getTetrisPanel().checkLocation(getRow() + 1, getCol())
			&& !getTetrisPanel().checkLocation(getRow() + 2, getCol() + 1)
			&& !getTetrisPanel().checkLocation(getRow() + 1, getCol() + 2))
		{
			getTetrisPanel().enterGrid(getRow() + 1, getCol());
			getTetrisPanel().enterGrid(getRow() + 2, getCol() + 1);
			getTetrisPanel().enterGrid(getRow() + 1, getCol() + 2);
			getTetrisPanel().exitGrid(getRow(), getCol());
			getTetrisPanel().exitGrid(getRow(), getCol() + 1);
			getTetrisPanel().exitGrid(getRow(), getCol() + 2);
			super.moveRight();
		}
	}
	
	public boolean nextSpotEmpty()
	{
		boolean a = getTetrisPanel().checkLocation(getRow(), getCol() + 3);
		boolean b = getTetrisPanel().checkLocation(getRow() + 1, getCol() + 2);
		return (!(a || b));
	}
	
	public ColorTetris rotate()
	{
		removeFromGrid();
		ColorTetris t = 
		new Tetris2(getX(), getY(), getRow(), getCol(), getColor(), getTetrisPanel());
		return t;
	}
	
	public boolean canRotate()
	{
		return false;
		/*
		if (!getTetrisPanel().checkLocation(getRow(), getCol() - 1)
			&& !getTetrisPanel().checkLocation(getRow(), getCol() - 2)
			&& !getTetrisPanel().checkLocation(getRow(), getCol() - 3))
		{
			return true;
		}
		else
		{
			return false;
		}
		*/
	}
	
	public void removeFromGrid()
	{
		getTetrisPanel().exitGrid(getRow(), getCol());
		getTetrisPanel().exitGrid(getRow(), getCol() + 1);
		getTetrisPanel().exitGrid(getRow() + 1, getCol() + 1);
		getTetrisPanel().exitGrid(getRow(), getCol() + 2);
		r1 = null;
		r2 = null;
		r3 = null;
		r4 = null;
	}
	
	public void addEachRectangle()
	{
		getTetrisPanel().addToRGrid(r1, getRow(), getCol());
		getTetrisPanel().addToRGrid(r2, getRow(), getCol() + 1);
		getTetrisPanel().addToRGrid(r3, getRow() + 1, getCol() + 1);
		getTetrisPanel().addToRGrid(r4, getRow(), getCol() + 2);
	}
}