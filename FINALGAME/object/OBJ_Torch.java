package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Torch extends SuperObject{
	public OBJ_Torch() {
		name = "Torch";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/objects/torch.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
