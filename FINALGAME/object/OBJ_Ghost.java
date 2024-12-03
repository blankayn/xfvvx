package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Ghost extends SuperObject {
    
    private int moveDirection = 1;
    private int moveCounter = 0;
    int speed = 1;

    public OBJ_Ghost() {
        name = "Ghost";
        try {
          
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/ghoste.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true; 
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
      
    	 int screenX = worldX - gp.player.worldX + gp.player.screenX;
    	    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    	    int distanceX = Math.abs(worldX - gp.player.worldX);
    	    int distanceY = Math.abs(worldY - gp.player.worldY);
    	    double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

    	 //ghost visibility
    	    int visibilityRadius = gp.tileSize * 3;

    	  
    	    if (distance <= visibilityRadius) {
    	       g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
       }
    }
    public void update(GamePanel gp) {
       
        if (moveCounter == 0) {
            moveDirection = (int) (Math.random() * 4); 
            moveCounter = 60; 
        }

     
        switch (moveDirection) {
            case 0: // Move up
                worldY -= speed;
                break;
            case 1: // Move down
                worldY += speed;
                break;
            case 2: // Move left
                worldX -= speed;
                break;
            case 3: // Move right
                worldX += speed;
                break;
        }
      

        if (worldX < 0) {
            worldX = 0;
            moveDirection = 3;
        }
        if (worldX > gp.worldWidth - gp.tileSize) {
            worldX = gp.worldWidth - gp.tileSize;
            moveDirection = 2; 
        }
        if (worldY < 0) {
            worldY = 0;
            moveDirection = 1; 
        }
        if (worldY > gp.worldHeight - gp.tileSize) {
            worldY = gp.worldHeight - gp.tileSize;
            moveDirection = 0; 
        }
        if (checkPlayerCollision(gp)) {
            gp.gameState = gp.gameOverState; // Trigger Game Over
        }
       
        moveCounter--;
    }
    private boolean checkPlayerCollision(GamePanel gp) {
        int ghostLeft = worldX;
        int ghostRight = worldX + gp.tileSize;
        int ghostTop = worldY;
        int ghostBottom = worldY + gp.tileSize;

        int playerLeft = gp.player.worldX;
        int playerRight = gp.player.worldX + gp.tileSize;
        int playerTop = gp.player.worldY;
        int playerBottom = gp.player.worldY + gp.tileSize;

        return ghostRight > playerLeft && ghostLeft < playerRight && 
               ghostBottom > playerTop && ghostTop < playerBottom;
    }
}
