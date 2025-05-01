package main;

import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame(); // creates a new window (where the game is going to appear)
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes when user clicks X
		window.setResizable(false); // not letting the windows change size format
		window.setTitle("Untitled");
		
		GamePanel gamePanel = new GamePanel(Color.DARK_GRAY);
		window.add(gamePanel);
		
		window.pack(); // causes window to fit sizes defined on GamePanel
		
		window.setLocationRelativeTo(null); // position of the screen relative to a point (in case null, window centered)
		window.setVisible(true); // to see the window (?
		
		gamePanel.startGameThread();
		
	}

}
