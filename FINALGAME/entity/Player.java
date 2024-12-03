package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	TileManager tileM;
	int defaultspeed;
	public int canTransform = 0;
	boolean transform;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	public boolean hasTorch;
	public int torch = 0;
	private BufferedImage transformA;
	private boolean showTransformA = false;
	private int transformACounter = 0;
	private BufferedImage dialogueAvatar;
	
	
	
	
	public Player(GamePanel gp, KeyHandler keyH, TileManager tileM) {
		this.gp = gp;
		this.keyH = keyH;
		this.tileM = tileM;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		
		setDefaultValues(); 
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 25;
//		worldX = gp.tileSize * 2;
//		worldY = gp.tileSize * 3;
		defaultspeed = 2;
		speed = 2;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			System.out.print("YAWa");
			up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/top1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/top2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/bottom1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/bottom2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/right2.png"));
			avatar = ImageIO.read(getClass().getResourceAsStream("/res/player/Avatar .png"));
			
			
			//transform
			tup1 = ImageIO.read(getClass().getResourceAsStream("/res/player/ttop1.png"));
			tup2 = ImageIO.read(getClass().getResourceAsStream("/res/player/ttop2.png"));
			tdown1 = ImageIO.read(getClass().getResourceAsStream("/res/player/tbottom1.png"));
			tdown2 = ImageIO.read(getClass().getResourceAsStream("/res/player/tbottom2.png"));
			tleft1 = ImageIO.read(getClass().getResourceAsStream("/res/player/tleft1.png"));
			tleft2 = ImageIO.read(getClass().getResourceAsStream("/res/player/tleft2.png"));
			tright1 = ImageIO.read(getClass().getResourceAsStream("/res/player/tright1.png"));
			tright2 = ImageIO.read(getClass().getResourceAsStream("/res/player/tright2.png"));
			transformA = ImageIO.read(getClass().getResourceAsStream("/res/player/TurboGranny.png"));
			
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}
	
	
	public void update() {
		if(torch > 0) {
			if(keyH.torch == true) {
				hasTorch = true;
			}
			else if(keyH.torch == false) {
				hasTorch = false;
			}
		}
		
		if(canTransform > 0) {	
			if(keyH.tf == true) {
				transform = true;
			}
			
			else if(keyH.tf == false) {
				transform = false;
			}
			
			if(transform == true) {
				
				try {
					tileM.tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/walls2.png"));
					tileM.tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/ground2.png"));
					tileM.tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/planks2.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			else {
				try {
					
					tileM.tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/walls.png"));
					tileM.tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/ground.png"));
					tileM.tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/planks.png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
				
			}
			
			else if(keyH.downPressed == true) {
				direction = "down";
				
			}
			
			else if(keyH.leftPressed == true) {
				direction = "left";
				
			}
			
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			//checkCollison
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			int objIndex =  gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			
			//if collison is false.player can move
			if(collisionOn == false) {
				if(transform == false) {
				if(keyH.shift == true) {
					speed = 4;
				}else {
					speed = defaultspeed;
				}
				}
				else {
					speed = 6;
				}

				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
		}
		
		
		
	}
	
	
	
	public void pickUpObject(int i) {
		
	
		
		if(i != 999) {
		    String objectName = gp.obj[i].name;
		    System.out.println("Interacting with object: " + objectName); 

		    switch(objectName) {
		        case "Balls":
		            gp.playSE(1);
		            hasKey++;
		            gp.obj[i] = null;
		            gp.ui.showMessage("Finally, I found one of my \nBalls! I can leave now");
		            System.out.println("Found a Ball, hasKey: " + hasKey); 
		            break;
		        case "Door":
		            if(hasKey > 0) {
		                gp.playSE(3);
		                gp.obj[i] = null;
		                hasKey--;
		                gp.ui.showMessage("The door's unlocked. \n Ugh So boring");
		                System.out.println("Door unlocked, hasKey: " + hasKey); 
		                gp.ui.gameFinished = true;
		            } else {
		                gp.ui.showMessage("I can't leave yet. I need to \nfind one of my balls first");
		                System.out.println("Can't unlock the door, hasKey: " + hasKey);
		            }
		            break;

		        case "Granny": 
		            gp.playSE(2);
		            canTransform++;
		            gp.ui.showMessage("Ugh, such a bummer...");
		            showTransformA = true;
		            transformACounter = 60; 
		            avatar = transformA;
		            gp.obj[i] = null; 
		        
		            break;
			case "Stairs":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				break;
			case "Torch":
				torch++;
				gp.obj[i] = null;
				gp.ui.showMessage("I found a torch");
				break;
			}
		}
		
		
	}
	
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		
		BufferedImage image = null;
		if(transform == false) {
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}
		else if(transform == true) {
			switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = tup1;
				}
				if(spriteNum == 2) {
					image = tup2;
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = tdown1;
				}
				if(spriteNum == 2) {
					image = tdown2;
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = tleft1;
				}
				if(spriteNum == 2) {
					image = tleft2;
				}
				break;
			case "right":
				if(spriteNum == 1) {
					image = tright1;
				}
				if(spriteNum == 2) {
					image = tright2;
				}
				break;
			}
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			
		}
		
	}
}
