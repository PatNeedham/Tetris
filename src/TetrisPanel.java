//By Pat Needham

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;


import java.awt.event.*;

//rows: 28, columns: 25
public class TetrisPanel extends JPanel implements Mover
{
	private MoveTimer t;
	private ArrayList<ColorTetris> tetrices;
	private ColorTetris current;
	private int speed, initialSpeed, score;
	private boolean[][] occupancyGrid;
	private ColorRectangle[][] rectangleGrid;
	private InfoPanel p;
	
	public TetrisPanel()
	{
		setFocusable(false);
		addKeyListener(new KeyListener()
		{
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					move(-1);
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					move(1);
				else if (e.getKeyCode() == KeyEvent.VK_UP)
				{
					if (current.canRotate())
					{
						ColorTetris temp = current.rotate();
						current = temp;
						repaint();
					}
						
				}
				else if (e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					if (speed == initialSpeed)
						setSpeed(1);
					repaint();
				}
			}
			public void keyTyped(KeyEvent e) {}
		});
		tetrices = new ArrayList<ColorTetris>();
		rectangleGrid = new ColorRectangle[28][25];
		occupancyGrid = new boolean[28][25];
		
		initialSpeed = 100;
		speed = 100;
		t = new MoveTimer(speed, this);
	}
	
	public void start()
	{
		setFocusable(true);
		current = getRandomTetris();
		t.start();
	}
	
	public void move()
	{
		if (current.nextSpotEmpty())
			current.move();
		else
		{
			p.addToScore(50);
			int row = (int)(Math.random() * 25);
			int xPosition = row * 25;
			current.addEachRectangle();
			current = getRandomTetris();
			tetrices.add(current);
			setSpeed(initialSpeed);
			boolean temp;
			for (int i = 0; i < 25; i++)
			{
				temp = checkColumn(i);
				
				if (temp)
				{
					removeColumn(i);
					p.addToScore(100);
				}
					
			}
		}
		this.repaint();
	}
	
	public Color getRandomColor()
	{
		int r = (int)(Math.random() * 255);
		int g = (int)(Math.random() * 255);
		int b = (int)(Math.random() * 255);
		return new Color(r, g, b);
	}
	
	public ColorTetris getRandomTetris()
	{
		int i = (int)(Math.random() * 4);
		int row = (int)(Math.random() * (getRows() - 3));
		int xPosition = row * 25;
		
		if (i == 0)
			return new Tetris1(xPosition, 0, row, 0, getRandomColor(), this);
		if (i == 1)
			return new Tetris2(xPosition, 0, row, 0, getRandomColor(), this);
		if (i == 2)
			return new Tetris1(xPosition, 0, row, 0, getRandomColor(), this);
		else
			return new Tetris1(xPosition, 0, row, 0, getRandomColor(), this);
	}
	
	public void move(int amount)
	{
		if (amount < 0)
		{
			current.moveLeft();
		}
		else
		{
			current.moveRight();
		}
		repaint();
	}
	
	public void rotate()
	{
		current.rotate();
	}
	
	public void enterGrid(int r, int c)
	{
		occupancyGrid[r][c] = true;
	}
	
	public void exitGrid(int r, int c)
	{
		occupancyGrid[r][c] = false;
	}
	
	public boolean checkLocation(int r, int c)
	{
		if (r > getRows() - 1 || r < 0 || c > getCols() - 1 || c < 0)
			return true;
		return occupancyGrid[r][c];
	}
	
	public void addToRGrid(ColorRectangle rect, int r, int c)
	{
		rectangleGrid[r][c] = rect;
	}
	
	public boolean checkColumn(int col)
	{
		for (int i = 0; i < getRows(); i++)
			if (!checkLocation(i, col))
				return false;
		return true;
	}
	
	public void removeColumn(int col)
	{
		for (int i = 0; i < getRows(); i++)
		{
			rectangleGrid[i][col] = null;
			occupancyGrid[i][col] = false;
		}
		
		for (int i = 0; i < getRows(); i++)
		{
			if (rectangleGrid[i][col - 1] != null)
			{
				shiftDown(i, col - 1);
			}
		}
	}
	
	public void shiftDown(int row, int col)
	{
		if (col < 0)
			return;
		
		ColorRectangle tempRect = rectangleGrid[row][col];
		while(col > 0 && tempRect != null)
		{
			tempRect.setLocation((int)tempRect.getX(),(int)tempRect.getY() + 25);
			rectangleGrid[row][col] = null;
			occupancyGrid[row][col] = false;
			rectangleGrid[row][col + 1] = tempRect;
			occupancyGrid[row][col + 1] = true;
			col -= 1;
			tempRect = rectangleGrid[row][col];
		}
	}
	
	public void setSpeed(int i)
	{
		t.setDelay(i);
		speed = i;
	}
	
	public void addInfoPanel(InfoPanel pa)
	{
		p = pa;
	}
	
	public void paintComponent(java.awt.Graphics aBrush)
	{
		super.paintComponent(aBrush);
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		
		if (current != null)
		{
			current.fill(betterBrush);
			current.draw(betterBrush);
		}
		
		for (int row = 0; row < getRows(); row++)
		{
			for (int col = 0; col < getCols(); col++)
			{
				if (rectangleGrid[row][col] != null)
				{
					rectangleGrid[row][col].fill(betterBrush);
					rectangleGrid[row][col].draw(betterBrush);
				}
			}
		}
    }
	
	public int getRows()
	{
		return 16;
	}
	
	public int getCols()
	{
		return 25;
	}
}