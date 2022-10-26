package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;

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
	public BufferedImage getTexture(int noise, int dimention, char airspace) {
		return Wood;
	}

	public static BufferedImage Wood = ResourceLoader.easyLoad("/Textures/Wood.png");
}
