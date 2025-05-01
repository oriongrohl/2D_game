package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH=keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	private void setDefaultValues() {
		//default players position
		x=200;
		y=200;
		speed=4;
	}
	
	private void getPlayerImage() {
		try {
			upLeft = ImageIO.read(getClass().getResourceAsStream("/player/girl_up_left.png"));
			upRight = ImageIO.read(getClass().getResourceAsStream("/player/girl_up_right.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/player/girl_left.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/player/girl_right.png"));
			runLeft = ImageIO.read(getClass().getResourceAsStream("/player/girl_run_left.png"));
			runLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/girl_run_left_2.png"));
			runRight = ImageIO.read(getClass().getResourceAsStream("/player/girl_run_right.png"));
			runRight = ImageIO.read(getClass().getResourceAsStream("/player/girl_run_right.png"));
			standing = ImageIO.read(getClass().getResourceAsStream("/player/girl_standing.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		direction = "standing";
		if(keyH.anyKeyPressed) {
			if(keyH.upArrowPressed) {
				direction = "up";
				y -= speed;	
			}
			else if(keyH.downArrowPressed) {
				direction="down";
				y += speed;
			}
			if(keyH.leftArrowPressed) { // si le ponemos un else no puede andar en diagonal!!!
				direction="left";
				x -= speed;
			}
			else if(keyH.rightArrowPressed) {
				direction="right";
				x += speed;
			}
			
			// sprite
			
			spriteCounter++;
			if(spriteCounter > 12) { //changes every n frames
				if(spriteNum == 1) {
					spriteNum=2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize); // x,y,width,height
		
		BufferedImage image = null;
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = upLeft;
			}
			if(spriteNum == 2) {
				image = upRight;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = left;
			}
			if(spriteNum==2) {
				image=right;
			}
			break;
		case "left":
			if(spriteNum==1) {
				image = runLeft;
			}
			if(spriteNum==2) {
				image=runLeft2;
			}
			break;
		case "right":
			image=runRight;
			break;
		case "standing":
			image=standing;
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
	}
	
	
	
}