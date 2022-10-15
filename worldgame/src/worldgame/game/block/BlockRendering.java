package worldgame.game.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.World;
import worldgame.game.rendering.WorldSpace;

public class BlockRendering {
	public static BufferedImage[] Tileset = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Tileset.png"));
	
	public static BufferedImage Air_Grass_Base = ResourceLoader.easyLoad("/Textures/Air_Grass/Base.png");
	public static BufferedImage Air_Grass_VarA = ResourceLoader.easyLoad("/Textures/Air_Grass/VarA.png");
	public static BufferedImage Air_Grass_VarB = ResourceLoader.easyLoad("/Textures/Air_Grass/VarB.png");
	public static BufferedImage Air_Stone_Base = ResourceLoader.easyLoad("/Textures/Air_Stone/Base.png");
	public static BufferedImage Air_Stone_VarA = ResourceLoader.easyLoad("/Textures/Air_Stone/VarA.png");
	public static BufferedImage Leaves = ResourceLoader.easyLoad("/Textures/Leaves.png");
	public static BufferedImage Stone = ResourceLoader.easyLoad("/Textures/Stone.png");
	
	static int noise;
	public static void drawBlock(Graphics g, WorldSpace worldspace, World world, int x, int y) {
		int block_id = world.world.grid[x][y];
		if(block_id == 0) {
			if(world.dimention == 0) {
				noise = world.world.noise_map[x % 256][ y % 256] + 128;
				noise = noise >> 5;
				if(noise == 4) {
					worldspace.drawTileScreenspace(g, Air_Grass_VarA, x, y);
				}else if(noise == 3) {
					worldspace.drawTileScreenspace(g, Air_Grass_VarB, x, y);
				}else {
					worldspace.drawTileScreenspace(g, Air_Grass_Base, x, y);
				}
				
			}
			if(world.dimention == 1) {
				noise = world.world.noise_map[x % 256][ y % 256] + 128;
				noise = noise >> 6;
				if(noise == 0) {
					worldspace.drawTileScreenspace(g, Air_Stone_VarA, x, y);
				}else {
					worldspace.drawTileScreenspace(g, Air_Stone_Base, x, y);
				}
			}
		}
		if(block_id == 1) {
			worldspace.drawTileScreenspace(g, Stone, x, y);
		}
		if(block_id == 2) {
			worldspace.drawTileScreenspace(g, Leaves, x, y);
		}
		if(block_id == 3) {
			worldspace.drawTileScreenspace(g, Tileset[2], x, y);
		}
		
	}
}
