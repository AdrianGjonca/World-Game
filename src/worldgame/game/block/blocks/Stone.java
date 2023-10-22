package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;
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
			out =  Stone;
		}else if(airspace == 0b1000) {
			out = StoneEdges[0];
		}else if(airspace == 0b0100) {
			out = StoneEdges[1];
		}else if(airspace == 0b0010) {
			out = StoneEdges[2];
		}else if(airspace == 0b0001) {
			out = StoneEdges[3];
		}else if(airspace == 0b1010) {
			out = StoneEdges[4];
		}else if(airspace == 0b0110) {
			out = StoneEdges[5];
		}else if(airspace == 0b1001) {
			out = StoneEdges[6];
		}else if(airspace == 0b0101) {
			out = StoneEdges[7];
		}else {
			out = StoneEdges[8];
		}
		//return out;
		return LightingGrader.graded(out,lighting);
	}

	public static BufferedImage Stone = ResourceLoader.easyLoad("/Textures/Stone/Stone.png");
	public static BufferedImage[] StoneEdges = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Stone/StoneEdges.png"));
}
