package worldgame.game.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.World;
import worldgame.game.rendering.WorldSpace;
import worldgame.game.rendering.lighting.LightMap;

public class BlockRendering {
	static BufferedImage bob = BlockTypeManager.blocks[1].getTexture(0, 0, (char) 0, 1f);
	static int noise;
	public static void drawBlock(Graphics g, WorldSpace worldspace, World world,LightMap map, int x, int y) {
		int block_id = world.world.grid[x][y];
		noise = world.world.noise_map[x % 256][ y % 256] + 128;
		
		BufferedImage img = null;
		if(x > 0 && y > 0 && x < world.world.grid.length && y < world.world.grid[0].length) {
			char airspace = 0b0000;
			if(world.world.grid[x][y-1] == 0) {
				airspace += 0b1000;
			}
			if(world.world.grid[x][y+1] == 0) {
				airspace += 0b0100;
			}
			if(world.world.grid[x-1][y] == 0) {
				airspace += 0b0010;
			}
			if(world.world.grid[x+1][y] == 0) {
				airspace += 0b0001;
			}
			
			img = BlockTypeManager.blocks[block_id].getTexture(noise, world.dimention, airspace, map.lightMap[x][y]);
			//img = BlockTypeManager.blocks[block_id].getTexture(noise, world.dimention, airspace, 1);
		}else {
			img = BlockTypeManager.blocks[block_id].getTexture(noise, world.dimention, (char) 0,  1);
		}

		if(world.world.grid[x][y] != 0) if(BlockTypeManager.blocks[world.world.grid[x][y]].getTransparency()) {
			worldspace.drawTileScreenspace(g, BlockTypeManager.blocks[0].getTexture(noise, world.dimention, (char) 0, 1f), x, y);
		}
		worldspace.drawTileScreenspace(g, img, x, y);
		
	}
}
