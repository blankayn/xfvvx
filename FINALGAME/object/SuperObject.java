package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
//		if(worldX + gp.tileSize  > gp.player.worldX - gp.player.screenX && 
//		   worldX - gp.tileSize  < gp.player.worldX + gp.player.screenX &&
//		   worldY + gp.tileSize  > gp.player.worldY - gp.player.screenY &&
//		   worldY - gp.tileSize  < gp.player.worldY + gp.player.screenY) {
//			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//				
//		}
		
		//TORCH
		if(gp.player.hasTorch == true) {
			if(worldX - (gp.tileSize * 4) > gp.player.worldX - gp.player.screenX && 
					   worldX + (gp.tileSize * 4) < (gp.player.worldX - 2) + gp.player.screenX &&
					   worldY - (gp.tileSize * 3) > gp.player.worldY - gp.player.screenY &&
					   worldY + (gp.tileSize * 3) < gp.player.worldY + gp.player.screenY) {
						g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
							
					}
		}
		
		else {
		switch(gp.player.direction) {
		case "up":
			if(worldX - (gp.tileSize * 6) > gp.player.worldX - gp.player.screenX && 
					   worldX + (gp.tileSize * 6) < gp.player.worldX + gp.player.screenX &&
					   worldY - (gp.tileSize * 2) > gp.player.worldY - gp.player.screenY &&
					   worldY + (gp.tileSize * 5) < gp.player.worldY + gp.player.screenY) {
						g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
						
//						for(int i = 0; i < gp.obj.length; i++) {
//							if(gp.obj[i] != null) {
//								gp.obj[i].draw(g2, gp);
//							}
//						}
							
					}
			break;
		case "down":
			if(worldX - (gp.tileSize * 6) > gp.player.worldX - gp.player.screenX && 
					   worldX + (gp.tileSize * 6) < gp.player.worldX + gp.player.screenX &&
					   worldY - (gp.tileSize * 5) > gp.player.worldY - gp.player.screenY &&
					   worldY + (gp.tileSize * 2) < gp.player.worldY + gp.player.screenY) {
						g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
							
					}
			break;
		case "left":
			if(worldX - (gp.tileSize * 2) > gp.player.worldX - gp.player.screenX && 
					   worldX + (gp.tileSize * 7) < gp.player.worldX + gp.player.screenX &&
					   worldY - (gp.tileSize * 4) > gp.player.worldY - gp.player.screenY &&
					   worldY + (gp.tileSize * 4) < gp.player.worldY + gp.player.screenY) {
						g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
							
					}
			break;
		case "right":
			if(worldX - (gp.tileSize * 7) > gp.player.worldX - gp.player.screenX && 
					   worldX + (gp.tileSize * 2) < gp.player.worldX + gp.player.screenX &&
					   worldY - (gp.tileSize * 4) > gp.player.worldY - gp.player.screenY &&
					   worldY + (gp.tileSize * 4) < gp.player.worldY + gp.player.screenY) {
						g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
							
					}
			break;
			}
		}
	
		
	}

	

}
