package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import object.OBJ_Balls;

public class UI {

    private static final Color WHITE = null;
    private static final Color YELLOW = null;
    private static final int screenWidth = 0;
    GamePanel gp;
    Font arial_40;
    Font arial_80;
    BufferedImage keyImage;
    BufferedImage av;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public boolean noAvMess = false;
    public int commandNum = 0;

    int x;
    int y;

    int width;
    int height;
    private Graphics2D g2;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 30);
        arial_80 = new Font("Arial", Font.BOLD, 70);

        OBJ_Balls key = new OBJ_Balls();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
       
        if (gp.gameState == gp.playState || gp.gameState == gp.dialougeState) {
            drawGameplayUI();
        }

        if (gameFinished) {
            drawGameFinishedScreen();
        }
    }

    private void drawGameplayUI() {
        x = 10;
        y = 10;
        width = gp.tileSize * 4;
        height = gp.tileSize * 2;
        Color c = new Color(0, 0, 0);

        g2.setFont(arial_40);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);

        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

        g2.drawImage(keyImage, gp.tileSize, gp.tileSize / 2, gp.tileSize + 11, gp.tileSize + 11, null);
        g2.drawString(" x " + gp.player.hasKey, 100, 65);

        // Message handling
        if (messageOn) {
            x = (gp.tileSize * 2) - 20;
            y = (gp.tileSize * 8) + 20;
            width = gp.tileSize * 10;
            height = gp.tileSize * 3;
            av = gp.player.avatar;

            c = new Color(0, 0, 0, 122);

            g2.setFont(arial_40);
            g2.setColor(c);
            g2.fillRoundRect(x, y, width, height, 35, 35);

            c = new Color(255, 255, 255);

            g2.setColor(c);
            g2.setStroke(new BasicStroke(5));

            g2.drawRoundRect(x + 50, y + 5, width - 10, height - 10, 25, 25);

            for (String line : message.split("\n")) {
                g2.drawString(line, x + 150, y + 60);
                y += 40;
            }

            g2.drawImage(av, gp.tileSize - 30, (gp.tileSize * 8) - 20, gp.tileSize * 4, gp.tileSize * 4, null);

            messageCounter++;

            if (messageCounter > 240) {
                messageCounter = 0;
                noAvMess = false;
                messageOn = false;
            }
        }
    }

    private void drawGameFinishedScreen() {
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        String text;
        int textLength;
        int x;
        int y;
     if(gameFinished == true) {
    	 
        text = "You found one of your Balls";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = (gp.tileSize * 4) + 25;
        y = gp.screenHeight / 2 - (gp.tileSize * 3);
        g2.drawString(text, x, y);

        g2.setFont(arial_80);
        g2.setColor(Color.white);
        text = "Congratulations";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.tileSize * 3;
        y = gp.screenHeight / 2 + (gp.tileSize * 3);
        g2.drawString(text, x, y);

        gp.gameThread = null;
     }
    }
    public void drawTitleScreen() {
        if (g2 != null) {
            // Set Background
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());

            g2.setColor(Color.BLACK);
            String text = "GOLDEN BALL";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,56F));
    	
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 2;

            // Draw Shadow
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);

            // Draw Main Title
            g2.setColor(Color.ORANGE);
            g2.drawString(text, x, y);

            // Start Game
            text = "START GAME";
            x = getXforCenteredText(text);
            g2.setColor(Color.WHITE);
            y += gp.tileSize * 5;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
            	g2.drawString(">",x-gp.tileSize,y);
            }
            
            
            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
            	g2.drawString(">",x-gp.tileSize,y);
            }
        }
    }

    public int getXforCenteredText(String text) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth / 2) - (textLength / 2);
    }
}
