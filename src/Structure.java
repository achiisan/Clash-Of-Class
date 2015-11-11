import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Structure {
	float Strength = 100;
	String name;
	String imgloc;
	
	public Structure(float Strength, String name, String imgloc){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(imgloc));
		} catch (IOException e) {
		}
	}
}
