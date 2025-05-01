package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	//SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 tile
	
	final int scale = 3;// scale the original tile size to match modern 1080px resolution (16x3=48, most common)
	
	public final int tileSize = originalTileSize * scale; // 48x48 tiles
	
	public final int maxScreenCol = 16; // max number of collums of tiles per screen
	public final int maxScreenRow = 12; // max number of rows of tiles per screen
	public final int screenWidth = tileSize * maxScreenCol; // 768 px
	public final int screenHeight = tileSize * maxScreenRow; //576 px
	
	
	int fps = 60; // FPS
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public Player player = new Player(this,keyH);
	
	
	
	public GamePanel(Color color) {
		this.setPreferredSize( // sets the size of this GamePanel
				new Dimension(screenWidth, screenHeight)
		);
		this.setBackground(color); // (this. is not necessary)
		this.setDoubleBuffered(true); // activates double buffer (avoids jumps on the drawing when screen updates) prepara los graficos en un segundo buffer de memoria antes de mostrarlos en pantalla
		this.addKeyListener(keyH);
		this.setFocusable(true); // gamePanel focuses on receiving a key input
	}

	public void startGameThread() {
		gameThread = new Thread(this); // instanciate the thread
		gameThread.start(); // start
	}
	
	public void update() {
		player.update(); // updates player from player method
	}
	
	public void paintComponent(Graphics g) {// paint component is a java method
		super.paintComponent(g); // 
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2); 
		player.draw(g2); // the order of drawing layers it 
		g2.dispose(); // releases memory (not needed but good practice)
	} 
	
	
//	@Override // FIRST WAY TO MAKE A GAME LOOP
//	public void run() { // it automatically calls gameThread
//		
//		double drawInterval = 1000000000/fps; // 1 billion nano seconds/60 fps meaning 16 million nano seconds 
//		double nextDrawTime = System.nanoTime() + drawInterval; // current system time + interval (16 million nano seconds after current tyime)
//		
//		while(gameThread != null) { // as long as gameThread exists
//			// CREATE A GAME LOOP
//			  
//			
//			System.out.println("Game loop is running");
//			// STEP 1: update information
//			update();
//			// STEP 2: paint the screen
//			repaint(); // calls paintComponent method 
//			
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000; //sleeps requires parameter in milliseconds not nano so we convert it
//				
//				if(remainingTime<0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval; // next draw time
//				
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			
//		}
//	}
	
	public void run() { // SECOND WAY TO CREATE A GAME LOOP DELTA
		double drawInterval = 1000000000/fps;
		double delta = 0;
		double lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: "+drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
		
	}
	
	
}
