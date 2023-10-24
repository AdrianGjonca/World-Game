package worldgame.game.playerrelated;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.BlockTypeManager;

public class LinkItemNameToIcon {
	private static BufferedImage mini(String loc) {
		BufferedImage big = ResourceLoader.easyLoad(loc);
		BufferedImage after = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x < 8; x ++) {
			for(int y = 0; y < 8; y ++) {
				after.setRGB(x, y, big.getRGB(x*2, y*2));
			}
		}
		return after;
	}
	private static BufferedImage mini(BufferedImage big) {
		BufferedImage after = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x < 8; x ++) {
			for(int y = 0; y < 8; y ++) {
				after.setRGB(x, y, big.getRGB(x*2, y*2));
			}
		}
		return after;
	}
	public static BufferedImage leaves = mini("/Textures/Leaves/Leaves.png");
	public static BufferedImage stone = mini("/Textures/Stone/Stone.png");
	public static BufferedImage wood = mini("/Textures/Wood.png");
	public static BufferedImage coal = mini("/Textures/Coal/Coal.png");
	public static BufferedImage torch = mini("/Textures/Torch/Torch.png");
	
	public static BufferedImage icon(String name) {
		return mini(BlockTypeManager.blocks[BlockTypeManager.getBlockChar(name)].getTexture(0, 0, (char) 0, (char) 0, 1f));
	}
	
	
}
