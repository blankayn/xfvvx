package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.OBJ_Ghost;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	//SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int gameOverState = 4;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//WorldSET
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	//fps
	int FPS = 60;
	//GAME STATE
	public final int titleState = 0;
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialougeState = 3;
	
	KeyHandler keyH = new KeyHandler(this);
	TileManager tileM = new TileManager(this,keyH);
	Sound music = new Sound();
	Sound se = new Sound();
	
	public CollisionChecker cChecker = new CollisionChecker(this);
	public static final int instructionState = 3;
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public Player player = new Player(this,keyH, tileM);
	Thread gameThread;
	
	
	
	public SuperObject obj[] = new SuperObject[20];
	
	
	//set player default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	    obj = new SuperObject[10];
	    
	}
	
	public void setupGame() {
		aSetter.setObject();
		
		playMusic(0);   
		
		
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}


	@Override
	public void run() {
			double drawinterval = 1000000000/FPS;
			double delta = 0;
			long lastTime = System.nanoTime();
			long currentTime;		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawinterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--; 
			}
			
		}
		
	}
	public void update() {
		player.update();
		
		for (int i = 0; i < obj.length; i++) {
		    if (obj[i] != null && obj[i] instanceof OBJ_Ghost) {
		        ((OBJ_Ghost) obj[i]).update(this);
		    }
		}
		
		
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D) g;

	    if (gameState == titleState) {
	        ui.draw(g2);
	    } else {
	        tileM.draw(g2);

	        for (int i = 0; i < obj.length; i++) {
	            if (obj[i] != null) {
	                obj[i].draw(g2, this); 
	            }
	        }

	        if (gameState == gameOverState) {
	        	 g2.setColor(Color.black);
	             g2.fillRect(0, 0, screenWidth, screenHeight);

	             // Display"Game Over" 
	             g2.setColor(Color.red);
	             g2.setFont(g2.getFont().deriveFont(50f));
	            g2.setColor(Color.red);
	            g2.setFont(g2.getFont().deriveFont(50f));
	            String text = "Game Over!";
	            int x = this.screenWidth / 2 - g2.getFontMetrics().stringWidth(text) / 2; 
	            int y = this.screenHeight / 2; 
	            g2.drawString(text, x, y);
	            
	            String tryAgainText = "Press R to Try Again";
	            x = this.screenWidth / 2 - g2.getFontMetrics().stringWidth(tryAgainText) / 2;
	            y = this.screenHeight / 2 + 50;
	            g2.setColor(Color.white);
	            g2.drawString(tryAgainText, x, y);
	            return;
	        }
 
	        player.draw(g2);

	        ui.draw(g2);

	        g2.dispose();
	    }
	}

	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
		
	}

	public void resetGame() {
	
		    // Reset game variables and objects
		    player.worldX = tileSize * 24; // Reset player position
		    player.worldY = tileSize * 25;
		    gameState = playState;

		    // Reset any other game objects or logic
		    for (int i = 0; i < obj.length; i++) {
		        if (obj[i] instanceof OBJ_Ghost) {
		            obj[i].worldX = tileSize * (i + 5); // Example position reset
		            obj[i].worldY = tileSize * (i + 5);
		        
		    }
		}

		
	}
	
	
	
	
}
