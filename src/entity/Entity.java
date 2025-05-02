package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	public int worldX, worldY; // one thing is the cords on the world and another thing is cords on the screen
	public int speed;
	
	protected BufferedImage left, right, runLeft, runLeft2, runRight, runRight2, upLeft, upRight, standing; // describes an Image with an accessible buffer of image data (to store images)
	
	public String direction = "down"; // inicialize the player to the most basic direction possible so theres now exception for direction being empty at draw() method (my solution)

	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	public boolean collisionOn = false;
	
}
