package worldgame.engine.core.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TransparencyMask {
	public static BufferedImage applyMask(BufferedImage source, BufferedImage mask) {
		BufferedImage out = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = out.createGraphics();
		g.drawImage(source, 0, 0, null);
		for(int x=0; x<mask.getWidth(); x++) {
			for(int y=0; y<mask.getHeight(); y++) {
				if((mask.getRGB(x, y) & 0xFFFFFF) > 0) {
					out.setRGB(x, y, 0x00);
				}
			}
		}
		return out;
	}
	
	public static BufferedImage fuseMasks(BufferedImage A, BufferedImage B) {
		BufferedImage out = new BufferedImage(A.getWidth(), A.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = out.createGraphics();
		g.drawImage(A, 0, 0, null);
		for(int x=0; x<B.getWidth(); x++) {
			for(int y=0; y<B.getHeight(); y++) {
				if((B.getRGB(x, y) & 0xFFFFFF) > 0) {
					out.setRGB(x, y, 0xFFFFFF);
				}
			}
		}
		return out;
	}
}
