package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;
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
	public BufferedImage getTexture(int noise, int dimention, char airspace, float lighting) {
		BufferedImage out;
		if(airspace == 0b0000) {
			out = Leaves;
		}else if(airspace == 0b1000) {
			out = LeafEdges[0];
		}else if(airspace == 0b0100) {
			out = LeafEdges[1];
		}else if(airspace == 0b0010) {
			out = LeafEdges[2];
		}else if(airspace == 0b0001) {
			out = LeafEdges[3];
		}else if(airspace == 0b1010) {
			out = LeafEdges[4];
		}else if(airspace == 0b0110) {
			out = LeafEdges[5];
		}else if(airspace == 0b1001) {
			out = LeafEdges[6];
		}else if(airspace == 0b0101) {
			out = LeafEdges[7];
		}else {
			out = LeafEdges[8];
		}
		//return out;
		return LightingGrader.graded(out, lighting);
	}
	
	public static BufferedImage Leaves = ResourceLoader.easyLoad("/Textures/Leaves/Leaves.png");
	public static BufferedImage[] LeafEdges = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Leaves/LeafEdges.png"));
}
