package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Granny extends SuperObject{
	
	public OBJ_Granny() {
		name = "Granny";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/objects/granny.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
