package worldgame.game.rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class WorldSpace {
	public float camx = 512f;
	public float camy = 512f;
	
	final int glitch_x = 32;
	final int glitch_y = 32;
	public void drawTileScreenspace(Graphics g, BufferedImage tile, float x, float y) {
		int posx = (int)(((x - camx - glitch_x) * 16f) + 120f) + (16 * glitch_x);
		int posy = (int)((-(y - camy - glitch_y) * 16f) + 72f) - (16 * glitch_y);
		
		if(posx > -16 && posx < 256 && posy > -16 && posy < 160) {
			g.drawImage(tile, posx - 8, posy - 8, null);
		}
	}
}
