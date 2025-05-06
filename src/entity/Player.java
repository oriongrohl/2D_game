package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH=keyH;
		
		this.screenX=gp.screenWidth/2 - (gp.tileSize/2);
		this.screenY=gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(8,16,32,32); // x, y, width, height
		
		setDefaultValues();
		getPlayerImage();
	}
	
	private void setDefaultValues() {
		//default players position
		worldX=gp.tileSize * 23;
		worldY=gp.tileSize * 21;
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
			runRight2 = ImageIO.read(getClass().getResourceAsStream("/player/girl_run_right_2.png"));
			standing = ImageIO.read(getClass().getResourceAsStream("/player/girl_standing.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		direction = "standing";
		//directionAux = "straight";
		if(keyH.anyKeyPressed) {
			if(keyH.upArrowPressed) {
				direction = "up";
				directionAuxY="up";
				worldY -= speed;
			}
			else if(keyH.downArrowPressed) {
				direction="down";
				directionAuxY="down";				
				worldY += speed;
			}
			if(keyH.leftArrowPressed) { // si le ponemos un else no puede andar en diagonal!!!
				direction="left";
				directionAuxX="left";
				worldX -= speed;
			}
			else if(keyH.rightArrowPressed) {
				direction="right";
				directionAuxX="right";
				worldX += speed;
			}
			// else if(!keyH.rightArrowPressed||!keyH.leftArrowPressed){
			 	//directionAux="straight";}
			
			// CHECKS TILE COLLITION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// IF COLLISION IS TRUE OVERLAP SPEED
			if(collisionOn == true) {
				if(direction.equals("up")) {
					/*if(directionAuxX.equals("right")) {
						worldX -= speed;
					}
					if(directionAuxX.equals("left")) {
						worldX += speed;
					}*/
					worldY += speed;		
				}
				else if(direction.equals("down")) {
					worldY -= speed;
				}
				if(direction.equals("left")) {
					if(directionAuxY.equals("up") && keyH.upArrowPressed) {
						worldY += speed;
					}
					if(directionAuxY.equals("down") && keyH.downArrowPressed) {
						worldY -= speed;
					}
					//else if (!directionAuxY.equals("up")){
						//worldY += speed;		
					//}
					worldX += speed;

				}
				else if(direction.equals("right")) {
					if(directionAuxY.equals("up") && keyH.upArrowPressed) {
						worldY += speed;
					}
					if(directionAuxY.equals("down") && keyH.downArrowPressed) {
						worldY -= speed;
					}
					worldX -= speed;
				}
			}
			// IF COLLITION IS FALSE, PLAYER MOVES
			/*if(collisionOn == false) {
				if(direction.equals("up")) {
					worldY -= speed;		
				}
				else if(direction.equals("down")) {
					worldY += speed;
					if(directionAux.equals("left")&&keyH.leftArrowPressed) {
						worldX -= speed; // no ENTRA
						System.out.println("ENTRA left");
					}
					else if(directionAux.equals("right")&&keyH.rightArrowPressed) {
						worldX += speed;
						System.out.println("ENTRA right");
					}
				}
				if(direction.equals("left")) {
					worldX -= speed;
				}
				else if(direction.equals("right")) {
					worldX += speed;
				}
			}*/
			
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
			if(spriteNum==1) {
				image = runRight;
			}
			if(spriteNum==2) {
				image=runRight2;
			}
			break;
		case "standing":
			image=standing;
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}
	
	
	
}