package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;

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
	public BufferedImage getTexture(int noise, int dimention, char airspace) {
		BufferedImage[] StoneEdges = StoneEdgesO;
		if(dimention == 1) StoneEdges = StoneEdgesU;
		if(airspace == 0b0000) {
			return Stone;
		}else if(airspace == 0b1000) {
			return StoneEdges[0];
		}else if(airspace == 0b0100) {
			return StoneEdges[1];
		}else if(airspace == 0b0010) {
			return StoneEdges[2];
		}else if(airspace == 0b0001) {
			return StoneEdges[3];
		}else if(airspace == 0b1010) {
			return StoneEdges[4];
		}else if(airspace == 0b0110) {
			return StoneEdges[5];
		}else if(airspace == 0b1001) {
			return StoneEdges[6];
		}else if(airspace == 0b0101) {
			return StoneEdges[7];
		}else {
			return StoneEdges[8];
		}
	}

	public static BufferedImage Stone = ResourceLoader.easyLoad("/Textures/Stone/Stone.png");
	public static BufferedImage[] StoneEdgesU = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Stone/StoneEdgesU.png"));
	public static BufferedImage[] StoneEdgesO = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Stone/StoneEdgesO.png"));
}
