//By Pat Needham

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InfoPanel extends JPanel
{
	private TetrisPanel tetris;
	private JButton play;
	private JLabel score;
	private boolean started;
	private int scoreAmount;
	
	public InfoPanel(TetrisPanel p)
	{
		super();
		setBackground(Color.WHITE);
		JLabel instructions = new JLabel("Instructions: Use the arrow keys");
		score = new JLabel("Score: 0");
		scoreAmount = 0;
		play = new JButton("Play");
		JPanel subPanel1 = new JPanel();
		JPanel subPanel2 = new JPanel();
		JPanel subPanel3 = new JPanel();
		subPanel1.add(instructions);
		subPanel2.add(play);
		subPanel3.add(score);
		add(subPanel1);
		add(subPanel2);
		add(subPanel3);
		tetris = p;
		started = false;
		play.addActionListener(new PlayListener());
		
		addKeyListener(new KeyListener()
		{
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e)
			{
				if (started)
					tetris.setFocusable(true);
			}
			public void keyTyped(KeyEvent e)
			{
				if (started)
					tetris.setFocusable(true);
			}
		});	
	}
	
	public void addToScore(int amt)
	{
		scoreAmount += amt;
		score.setText("Score: " + scoreAmount);
		repaint();
	}
	
	public boolean started()
	{
		return started;
	}
	
	private class PlayListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			started = true;
			tetris.start();
			tetris.setFocusable(true);
			play.setEnabled(false);
		}
	}
}