package worldgame.engine.resourcecontroll;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ResourceLoader {
	
	public static BufferedImage easyLoad(String resourcePath) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(ResourceLoader.class.getResource(resourcePath));
		} catch (IOException e) {
		}
		return img;
	}
	
	public static BufferedImage[] retrieveTileset(BufferedImage Tileset) {
		List<BufferedImage> set = new ArrayList<BufferedImage>();
		int height = Tileset.getHeight();
		int tiles = Tileset.getWidth() / height;
		
		for (int i = 0; i < tiles; i++) {
			set.add(Tileset.getSubimage(i * height, 0, height, height));
		}
		
		BufferedImage[] out = new BufferedImage[tiles];
		for(int i = 0; i < tiles; i++) {
			out[i] = set.get(i);
		}
		return out;
	}
}
