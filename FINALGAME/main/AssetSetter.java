package main;

import object.OBJ_Balls;
import object.OBJ_Door;
import object.OBJ_Ghost;
import object.OBJ_Granny;
import object.OBJ_Stairs;
import object.OBJ_Torch;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
		
	}
	
	public void setObject() {
//		gp.obj[0] = new OBJ_Balls();
//		gp.obj[0].worldX = 2 * gp.tileSize;
//		gp.obj[0].worldY = 3 * gp.tileSize;
		
		gp.obj[0] = new OBJ_Balls();
		gp.obj[0].worldX = 26 * gp.tileSize;
		gp.obj[0].worldY = 25 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Door();
		gp.obj[1].worldX = 27 * gp.tileSize;
		gp.obj[1].worldY = 47 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Stairs();
		gp.obj[2].worldX = 24 * gp.tileSize;
		gp.obj[2].worldY = 48 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Granny();
		gp.obj[3].worldX = 48 * gp.tileSize;
		gp.obj[3].worldY = 21 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Torch();
		gp.obj[4].worldX = 23 * gp.tileSize;
		gp.obj[4].worldY = 24 * gp.tileSize;
		
		//GHOST AHHH
		 gp.obj[5] = new OBJ_Ghost();
		 gp.obj[5].worldX = 48 * gp.tileSize;
		 gp.obj[5].worldY = 21 * gp.tileSize;
		  
		gp.obj[2] = new OBJ_Ghost();
		gp.obj[2].worldX = 26 * gp.tileSize;
		gp.obj[2].worldY = 25 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Ghost();
		gp.obj[2].worldX = 32 * gp.tileSize;
		gp.obj[2].worldY = 36 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Ghost();
		gp.obj[2].worldX = 46 * gp.tileSize;
		gp.obj[2].worldY = 14 * gp.tileSize;
		
			
	
	}
	
	
}
