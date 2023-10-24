package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;
import worldgame.game.block.BlockRendering;
import worldgame.game.rendering.lighting.LightingGrader;

public class Water extends Block {

	@Override
	public String getName() {
		return "Water";
	}

	@Override
	public String getDrop() {
		return "Water";
	}

	@Override
	public boolean getTransparency() {
		return true;
	}
	
	@Override
	public boolean getTraversable() {
		return true;
	}

	@Override
	public BufferedImage getTexture(int noise, int dimention, char airspace, char otherspace, float lighting) {
		BufferedImage out = null;
		char compound = (char) (airspace | otherspace);
		switch((int)(((System.currentTimeMillis())/500) % 4)) {
		case 0:
			out = LightingGrader.graded(Water1, lighting);
			break;
		case 1,3:
			out = LightingGrader.graded(Water2, lighting);
			break;
		case 2:
			out = LightingGrader.graded(Water3, lighting);
			break;
		}
		return BlockRendering.tileMask(out, WaterMask, compound);
	}

	public static BufferedImage Water1 = ResourceLoader.easyLoad("/Textures/Water/Water 1.png");
	public static BufferedImage Water2 = ResourceLoader.easyLoad("/Textures/Water/Water 2.png");
	public static BufferedImage Water3 = ResourceLoader.easyLoad("/Textures/Water/Water 3.png");
	public static BufferedImage[] WaterMask = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Water/WaterMask.png"));

}
