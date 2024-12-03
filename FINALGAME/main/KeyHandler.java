package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.UI;
import main.GamePanel;
public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, shift, torch, tf,R;
	
	
	  GamePanel gp;

	    public KeyHandler(GamePanel gp) {
	        this.gp = gp;
	    }
	    
	@Override
	public void keyTyped(KeyEvent e) {
	
		return;
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
	    int code = e.getKeyCode();

	    if (gp.gameState == gp.titleState) {
	        // Navigate up
	        if (code == KeyEvent.VK_W) {
	            gp.ui.commandNum--;
	            if (gp.ui.commandNum < 1) {
	                gp.ui.commandNum = 2;
	            }
	        }

	        // Navigate down
	        if (code == KeyEvent.VK_S) {
	            gp.ui.commandNum++;
	            if (gp.ui.commandNum > 2) {
	                gp.ui.commandNum = 1; 
	            }
	        }

	       
	        if (code == KeyEvent.VK_ENTER) {
	            if (gp.ui.commandNum == 1) {
	                // Start Game
	                gp.gameState = gp.playState;
	            } else if (gp.ui.commandNum == 2) {
	                // Quit Game
	                System.exit(0); 
	            }
	        }
	        
	    }
	    if (gp.gameState == gp.gameOverState) {
	        if (code == KeyEvent.VK_R) {
	            gp.resetGame(); 
	        }
	    }

	    if (gp.gameState == gp.playState) {
	        if (code == KeyEvent.VK_W) {
	            upPressed = true;
	        }
	    }
	    //control sprite
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_SHIFT) {
			shift = true;
		}
		
		if(torch == false) {
			if(code == KeyEvent.VK_E) {
				torch = true;
			}
		}
		else if(torch == true) {
			if(code == KeyEvent.VK_E) {
				torch = false;
			}
		}
		
		if(tf == false) {
			if(code == KeyEvent.VK_T) {
				tf = true;
			}
		}
		else if(tf == true) {
			if(code == KeyEvent.VK_T) {
				tf = false;
			}
		}
		 
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_SHIFT) {
			shift = false;
		}
		
		
		
	}

}
