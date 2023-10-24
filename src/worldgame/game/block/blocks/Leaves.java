package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.core.graphics.TransparencyMask;
import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;
import worldgame.game.block.BlockRendering;
import worldgame.game.rendering.lighting.LightingGrader;

public class Leaves extends Block {

	@Override
	public String getName() {
		return "Leaves";
	}

	@Override
	public String getDrop() {
		return "Leaves";
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
		char compound = (char) (airspace | otherspace);
		if(compound == 0b0000) {
			out = Leaves;
		}else {
			out = BlockRendering.tileMask(Leaves, LeafMask, airspace);
		}
		//return out;
		return LightingGrader.graded(out, lighting);
	}
	
	public static BufferedImage Leaves = ResourceLoader.easyLoad("/Textures/Leaves/Leaves.png");
	public static BufferedImage[] LeafEdges = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Leaves/LeafEdges.png"));
	public static BufferedImage[] LeafMask = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Leaves/LeafMask.png"));
}
