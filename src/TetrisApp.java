//By Pat Needham

import java.awt.*;
import javax.swing.*;

public class TetrisApp extends JFrame
{
	public TetrisApp(String title)
	{
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		TetrisPanel t = new TetrisPanel();
		setSize(t.getRows() * 25 + 8, 700);
		InfoPanel b = new InfoPanel(t);
		t.addInfoPanel(b);
		add(t, java.awt.BorderLayout.CENTER);
		add(b, java.awt.BorderLayout.SOUTH);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args)
	{
		TetrisApp b = new TetrisApp("Tetris");
	}
}