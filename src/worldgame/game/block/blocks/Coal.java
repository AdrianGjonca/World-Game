package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;
import worldgame.game.block.BlockRendering;
import worldgame.game.rendering.lighting.LightingGrader;

public class Coal extends Block {

	@Override
	public String getName() {
		return "Coal";
	}

	@Override
	public String getDrop() {
		return "Coal";
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
	public BufferedImage getTexture(int noise, int dimention, char airspace, char otherspace, float lighting) {
		BufferedImage out;
		if(airspace == 0b0000) {
			out = Coal;
		}else {
			out = BlockRendering.tileMask(Coal, CoalMask, airspace);
		}
		//return out;
		return LightingGrader.graded(out, lighting);
	}

	public static BufferedImage Coal = ResourceLoader.easyLoad("/Textures/Coal/Coal.png");
	public static BufferedImage[] CoalEdges = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Coal/CoalEdges.png"));
	public static BufferedImage[] CoalMask = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Coal/CoalMask.png"));
}
