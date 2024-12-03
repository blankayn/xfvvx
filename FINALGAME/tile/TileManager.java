package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class TileManager {
	GamePanel gp;
	KeyHandler keyH;
	public Tile[] tile;
	public int mapTileNum[][];
	boolean hasTorch;
	
	public TileManager(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/res/maps/mapkey.txt");
		
		
		
	}
	
	public void getTileImage () {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/walls.png"));
			tile[0].collision = true;
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/ground.png"));
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/planks.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String FilePath) {
		try {
			
			InputStream is = getClass().getResourceAsStream(FilePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
					
			}
			br.close();
			
			
		}catch(Exception e){
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//FULL VIEW
//			if(worldX - (gp.tileSize - 1) > gp.player.worldX - gp.player.screenX && 
//					   worldX + (gp.tileSize + 1) < gp.player.worldX + gp.player.screenX &&
//					   worldY - (gp.tileSize - 1) > gp.player.worldY - gp.player.screenY &&
//					   worldY + (gp.tileSize + 1) < gp.player.worldY + gp.player.screenY) {
//						g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//							
//					}
			//TORCH
			if(gp.player.hasTorch == true) {
				if(worldX - (gp.tileSize * 4) > gp.player.worldX - gp.player.screenX && 
						   worldX + (gp.tileSize * 4) < (gp.player.worldX - 2) + gp.player.screenX &&
						   worldY - (gp.tileSize * 3) > gp.player.worldY - gp.player.screenY &&
						   worldY + (gp.tileSize * 3) < gp.player.worldY + gp.player.screenY) {
							g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
								
						}
			}
			

			else {
				switch(gp.player.direction) {
				case "up":
					if(worldX - (gp.tileSize * 6) > gp.player.worldX - gp.player.screenX && 
							   worldX + (gp.tileSize * 6) < gp.player.worldX + gp.player.screenX &&
							   worldY - (gp.tileSize * 2) > gp.player.worldY - gp.player.screenY &&
							   worldY + (gp.tileSize * 5) < gp.player.worldY + gp.player.screenY) {
								g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
								
//								for(int i = 0; i < gp.obj.length; i++) {
//									if(gp.obj[i] != null) {
//										gp.obj[i].draw(g2, gp);
//									}
//								}
									
							}
					break;
				case "down":
					if(worldX - (gp.tileSize * 6) > gp.player.worldX - gp.player.screenX && 
							   worldX + (gp.tileSize * 6) < gp.player.worldX + gp.player.screenX &&
							   worldY - (gp.tileSize * 5) > gp.player.worldY - gp.player.screenY &&
							   worldY + (gp.tileSize * 2) < gp.player.worldY + gp.player.screenY) {
								g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
									
							}
					break;
				case "left":
					if(worldX - (gp.tileSize * 2) > gp.player.worldX - gp.player.screenX && 
							   worldX + (gp.tileSize * 7) < gp.player.worldX + gp.player.screenX &&
							   worldY - (gp.tileSize * 4) > gp.player.worldY - gp.player.screenY &&
							   worldY + (gp.tileSize * 4) < gp.player.worldY + gp.player.screenY) {
								g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
									
							}
					break;
				case "right":
					if(worldX - (gp.tileSize * 7) > gp.player.worldX - gp.player.screenX && 
							   worldX + (gp.tileSize * 2) < gp.player.worldX + gp.player.screenX &&
							   worldY - (gp.tileSize * 4) > gp.player.worldY - gp.player.screenY &&
							   worldY + (gp.tileSize * 4) < gp.player.worldY + gp.player.screenY) {
								g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
									
							}
					break;
			}
			}
			
			
			
				
				
			
			
			worldCol++;
		
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
			
		}
		
		

		
	}
	
	
	
	
	
}
