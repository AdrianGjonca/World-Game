package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;
import worldgame.game.rendering.lighting.LightingGrader;

public class Torch extends Block {

	@Override
	public String getName() {
		return "Torch";
	}

	@Override
	public String getDrop() {
		return "Torch";
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
		switch((int)(((System.currentTimeMillis()+noise)/200) % 4)) {
		case 0:
			return LightingGrader.graded(Torch1, lighting);
		case 1,3:
			return LightingGrader.graded(Torch2, lighting);
		case 2:
			return LightingGrader.graded(Torch3, lighting);
		}
		return LightingGrader.graded(Torch1, lighting);
	}

	public static BufferedImage Torch1 = ResourceLoader.easyLoad("/Textures/Torch/Torch 1.png");
	public static BufferedImage Torch2 = ResourceLoader.easyLoad("/Textures/Torch/Torch 2.png");
	public static BufferedImage Torch3 = ResourceLoader.easyLoad("/Textures/Torch/Torch 3.png");
}
