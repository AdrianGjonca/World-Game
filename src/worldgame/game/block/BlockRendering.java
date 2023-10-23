package worldgame.game.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import worldgame.engine.core.graphics.TransparencyMask;
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
			if(world.world.grid[x-1][y+1] == 0) {
				airspace += 0b10000000;
			}
			if(world.world.grid[x+1][y+1] == 0) {
				airspace += 0b01000000;
			}
			if(world.world.grid[x+1][y-1] == 0) {
				airspace += 0b00100000;
			}
			if(world.world.grid[x-1][y-1] == 0) {
				airspace += 0b00010000;
			}
			if(world.world.grid[x][y+1] == 0) {
				airspace += 0b00001000;
			}
			if(world.world.grid[x+1][y] == 0) {
				airspace += 0b00000100;
			}
			if(world.world.grid[x][y-1] == 0) {
				airspace += 0b00000010;
			}
			if(world.world.grid[x-1][y] == 0) {
				airspace += 0b00000001;
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
	
	public static BufferedImage tileMask(BufferedImage source, BufferedImage[] mask, char airspace) {
		BufferedImage out = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
		out.createGraphics().drawImage(source, 0, 0, null);
		if((airspace & 0b10000000) > 0) {
			out = TransparencyMask.applyMask(out, mask[0]);
		}
		if((airspace & 0b01000000) > 0) {
			out = TransparencyMask.applyMask(out, mask[1]);
		}
		if((airspace & 0b00100000) > 0) {
			out = TransparencyMask.applyMask(out, mask[2]);
		}
		if((airspace & 0b00010000) > 0) {
			out = TransparencyMask.applyMask(out, mask[3]);
		}
		if((airspace & 0b00001000) > 0) {
			out = TransparencyMask.applyMask(out, mask[4]);
		}
		if((airspace & 0b00000100) > 0) {
			out = TransparencyMask.applyMask(out, mask[5]);
		}
		if((airspace & 0b00000010) > 0) {
			out = TransparencyMask.applyMask(out, mask[6]);
		}
		if((airspace & 0b00000001) > 0) {
			out = TransparencyMask.applyMask(out, mask[7]);
		}
		
		return out;
		
	}
}
