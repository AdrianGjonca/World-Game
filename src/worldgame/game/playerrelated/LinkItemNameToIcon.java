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
	public static BufferedImage leaves = mini("/Textures/Leaves.png");
	public static BufferedImage stone = mini("/Textures/Stone.png");
	public static BufferedImage wood = mini("/Textures/Wood.png");
	public static BufferedImage coal = mini("/Textures/Coal/Coal.png");
	public static BufferedImage torch = mini("/Textures/Torch/Torch.png");
	
	public static BufferedImage icon(String name) {
		if(name.equals("Leaves")) {
			return leaves;
		}else if(name.equals("Stone")) {
			return stone;
		}else if(name.equals("Wood")) {
			return wood;
		}else if(name.equals("Coal")) {
			return coal;
		}else if(name.equals("Torch")) {
			return torch;
		}
		return null;
	}
	
	
}
