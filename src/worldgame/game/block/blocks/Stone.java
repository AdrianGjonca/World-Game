package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;
import worldgame.game.block.BlockRendering;
import worldgame.game.rendering.lighting.LightingGrader;

public class Stone extends Block {

	@Override
	public String getName() {
		return "Stone";
	}

	@Override
	public String getDrop() {
		return "Stone";
	}

	@Override
	public boolean getTransparency() {
		return true;
	}
	
	@Override
	public boolean getTraversable() {
		return false;
	}
	
	@Override
	public BufferedImage getTexture(int noise, int dimention, char airspace, float lighting) {
		BufferedImage out;
		if(airspace == 0b0000) {
			out = Stone;
		}else {
			out = BlockRendering.tileMask(Stone, StoneMask, airspace);
		}
		//return out;
		return LightingGrader.graded(out, lighting);
	}

	public static BufferedImage Stone = ResourceLoader.easyLoad("/Textures/Stone/Stone.png");
	public static BufferedImage[] StoneEdges = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Stone/StoneEdges.png"));
	public static BufferedImage[] StoneMask = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Stone/StoneMask.png"));
}
