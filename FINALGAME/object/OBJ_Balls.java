package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Balls extends SuperObject{
	
	public OBJ_Balls() {
		name = "Balls";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/objects/balls.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
	}
}
