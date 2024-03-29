package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;
import worldgame.game.rendering.lighting.LightingGrader;

public class Wood extends Block {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Wood";
	}

	@Override
	public String getDrop() {
		// TODO Auto-generated method stub
		return "Wood";
	}

	@Override
	public boolean getTransparency() {
		return false;
	}
	
	@Override
	public boolean getTraversable() {
		return false;
	}
	
	@Override
	public BufferedImage getTexture(int noise, int dimention, char airspace, char otherspace, float lighting) {
		return LightingGrader.graded(Wood, lighting);
	}

	public static BufferedImage Wood = ResourceLoader.easyLoad("/Textures/Wood.png");
}
