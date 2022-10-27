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
	public BufferedImage getTexture(int noise, int dimention, char airspace, float lighting) {
		return LightingGrader.graded(Torch, lighting);
	}

	public static BufferedImage Torch = ResourceLoader.easyLoad("/Textures/Torch/Torch.png");
}
