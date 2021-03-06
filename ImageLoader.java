

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	public static BufferedImage loadImg(String path) {
		BufferedImage img;
		try {
		     img = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.exit(1);
			return null;
		}
		return img;
	}
}
