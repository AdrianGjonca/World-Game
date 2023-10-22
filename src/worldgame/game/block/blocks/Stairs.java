package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;
import worldgame.game.rendering.lighting.LightingGrader;

public class Stairs extends Block {

	@Override
	public String getName() {
		return "Stairs";
	}

	@Override
	public String getDrop() {
		return "Stairs";
	}

	@Override
	public boolean getTransparency() {
		return false;
	}
	
	@Override
	public boolean getTraversable() {
		return true;
	}

	@Override
	public BufferedImage getTexture(int noise, int dimention, char airspace, float lighting) {
		return LightingGrader.graded(Stairs, lighting);
	}
	
	public static BufferedImage Stairs = ResourceLoader.easyLoad("/Textures/Stairs.png");

}
