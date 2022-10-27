package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;
import worldgame.game.rendering.lighting.LightingGrader;

public class Air extends Block {

	@Override
	public String getName() {
		return "Air";
	}

	@Override
	public String getDrop() {
		return null;
	}

	@Override
	public BufferedImage getTexture(int noise, int dimention, char airspace, float lighting) {
		noise = noise >> 5;
		switch (dimention) {
			case 0:
				if(noise == 4) {
					return LightingGrader.graded(Air_Grass_Base, lighting);
				}else if(noise == 3) {
					return LightingGrader.graded(Air_Grass_VarB, lighting);
				}else {
					return LightingGrader.graded(Air_Grass_Base, lighting);
				}
			case 1:
				if(noise == 0) {
					return LightingGrader.graded(Air_Stone_VarA, lighting);
				}else {
					return LightingGrader.graded(Air_Stone_Base, lighting);
				}
		}
		return null; 
	}

	public static BufferedImage Air_Grass_Base = ResourceLoader.easyLoad("/Textures/Air_Grass/Base.png");
	public static BufferedImage Air_Grass_VarA = ResourceLoader.easyLoad("/Textures/Air_Grass/VarA.png");
	public static BufferedImage Air_Grass_VarB = ResourceLoader.easyLoad("/Textures/Air_Grass/VarB.png");
	public static BufferedImage Air_Stone_Base = ResourceLoader.easyLoad("/Textures/Air_Stone/Base.png");
	public static BufferedImage Air_Stone_VarA = ResourceLoader.easyLoad("/Textures/Air_Stone/VarA.png");

}
