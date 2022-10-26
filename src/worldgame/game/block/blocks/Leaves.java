package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;

public class Leaves extends Block {

	@Override
	public String getName() {
		return "Leaves";
	}

	@Override
	public String getDrop() {
		return "Wood";
	}

	@Override
	public BufferedImage getTexture(int noise, int dimention, char airspace) {
		if(airspace == 0b0000) {
			return Leaves;
		}else if(airspace == 0b1000) {
			return LeafEdges[0];
		}else if(airspace == 0b0100) {
			return LeafEdges[1];
		}else if(airspace == 0b0010) {
			return LeafEdges[2];
		}else if(airspace == 0b0001) {
			return LeafEdges[3];
		}else if(airspace == 0b1010) {
			return LeafEdges[4];
		}else if(airspace == 0b0110) {
			return LeafEdges[5];
		}else if(airspace == 0b1001) {
			return LeafEdges[6];
		}else if(airspace == 0b0101) {
			return LeafEdges[7];
		}else {
			return LeafEdges[8];
		}
	}
	
	public static BufferedImage Leaves = ResourceLoader.easyLoad("/Textures/Leaves/Leaves.png");
	public static BufferedImage[] LeafEdges = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Leaves/LeafEdges.png"));
}
