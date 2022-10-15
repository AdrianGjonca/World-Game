package worldgame.game.rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ScreenSpace {
	final static int glitch_x = 32;
	final static int glitch_y = 32;
	//240, 144
	public static void drawTileScreenspace(Graphics g, BufferedImage tile, float x, float y) {
		int posx = (int)(((x+glitch_x) * 16f) + 120f) - (16*glitch_x);
		int posy = (int)((-(y+glitch_y) * 16f) + 72f) + (16*glitch_y);
		
		g.drawImage(tile, posx - 8, posy - 8, null);
	}
	
}
