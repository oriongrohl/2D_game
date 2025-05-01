package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean leftArrowPressed, rightArrowPressed, upArrowPressed, downArrowPressed;
	
	public boolean anyKeyPressed;
	
	@Override
	public void keyTyped(KeyEvent e) {	} // only for alphanumeric keys

	@Override
	public void keyPressed(KeyEvent e) { // any keys
		int code = e.getKeyCode(); // gets what key got pressed
		
		// WASD
		if(code == KeyEvent.VK_W){
			upArrowPressed = true;
		}
		if(code == KeyEvent.VK_S){
			downArrowPressed = true;
		}
		if(code == KeyEvent.VK_A){
			leftArrowPressed=true;
		}
		if(code == KeyEvent.VK_D){
			rightArrowPressed=true;
		}
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_A || code == KeyEvent.VK_S || code == KeyEvent.VK_D) {
			anyKeyPressed=true;
		}
		// ARROWS
		/*if(code == KeyEvent.VK_KP_UP){
			upArrowPressed = true;
		}
		if(code == KeyEvent.VK_KP_DOWN){
			downArrowPressed = true;
		}
		if(code == KeyEvent.VK_KP_LEFT){
			leftArrowPressed=true;
		}
		if(code == KeyEvent.VK_KP_RIGHT){
			rightArrowPressed=true;
		}*/
		
	}

	@Override
	public void keyReleased(KeyEvent e) { // user releases key (stops pressing it)
		int code = e.getKeyCode(); // gets what key got pressed
		
		//WASD
		if(code == KeyEvent.VK_W){
			upArrowPressed = false;
		}
		if(code == KeyEvent.VK_S){
			downArrowPressed = false;
		}
		if(code == KeyEvent.VK_A){
			leftArrowPressed=false;
		}
		if(code == KeyEvent.VK_D){
			rightArrowPressed=false;
		}
		if(code == KeyEvent.VK_W & code == KeyEvent.VK_A & code == KeyEvent.VK_S & code == KeyEvent.VK_D) { 
			anyKeyPressed=false;
		}
		
		// ARROWS
		/*if(code == KeyEvent.VK_KP_UP){
			upArrowPressed = false;
		}
		if(code == KeyEvent.VK_KP_DOWN){
			downArrowPressed = false;
		}
		if(code == KeyEvent.VK_KP_LEFT){
			leftArrowPressed=false;
		}
		if(code == KeyEvent.VK_KP_RIGHT){
			rightArrowPressed=false;
		}*/
		
	}

}