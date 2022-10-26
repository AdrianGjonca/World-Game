package worldgame.game.block.blocks;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.Block;

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
	public BufferedImage getTexture(int noise, int dimention, char airspace) {
		noise = noise >> 5;
		switch (dimention) {
			case 0:
				if(noise == 4) {
					return Air_Grass_VarA;
				}else if(noise == 3) {
					return Air_Grass_VarB;
				}else {
					return Air_Grass_Base;
				}
			case 1:
				if(noise == 0) {
					return Air_Stone_VarA;
				}else {
					return Air_Stone_Base;
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
