package worldgame.game.block;

import java.awt.image.BufferedImage;

public abstract class Block{
	public abstract String getName();
	public abstract String getDrop();
	public abstract boolean getTransparency();
	public abstract boolean getTraversable();
	public abstract BufferedImage getTexture(int noise, int dimention, char airspace, char otherspace, float lighting);
}
