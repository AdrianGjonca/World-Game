package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;
import worldgame.game.rendering.lighting.LightingGrader;

public class Coal extends Block {

	@Override
	public String getName() {
		return "Coal";
	}

	@Override
	public String getDrop() {
		return "Coal";
	}

	@Override
	public BufferedImage getTexture(int noise, int dimention, char airspace, float lighting) {
		BufferedImage[] CoalEdges = CoalEdgesO;
		BufferedImage out;
		if(dimention == 1) CoalEdges = CoalEdgesU;
		if(airspace == 0b0000) {
			out =  Coal;
		}else if(airspace == 0b1000) {
			out = CoalEdges[0];
		}else if(airspace == 0b0100) {
			out = CoalEdges[1];
		}else if(airspace == 0b0010) {
			out = CoalEdges[2];
		}else if(airspace == 0b0001) {
			out = CoalEdges[3];
		}else if(airspace == 0b1010) {
			out = CoalEdges[4];
		}else if(airspace == 0b0110) {
			out = CoalEdges[5];
		}else if(airspace == 0b1001) {
			out = CoalEdges[6];
		}else if(airspace == 0b0101) {
			out = CoalEdges[7];
		}else {
			out = CoalEdges[8];
		}
		return LightingGrader.graded(out, lighting);
	}

	public static BufferedImage Coal = ResourceLoader.easyLoad("/Textures/Coal/Coal.png");
	public static BufferedImage[] CoalEdgesU = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Coal/CoalEdgesU.png"));
	public static BufferedImage[] CoalEdgesO = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Textures/Coal/CoalEdgesO.png"));
}
