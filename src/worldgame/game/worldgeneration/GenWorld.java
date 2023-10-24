package worldgame.game.worldgeneration;

import java.util.Random;

import worldgame.game.LevelGrid;
import worldgame.game.World;
import worldgame.game.block.BlockTypeManager;

public class GenWorld {
	public static void generateWorld(World world) {
		fillwith(world.overground, (char)2);
		fillwith(world.underground, (char)1);
		populate(world.overground, (char)0);
		populate(world.underground, (char)0);
		populateMini(world.underground, (char)BlockTypeManager.getBlockChar("Coal"));
		populateMini(world.overground, (char)BlockTypeManager.getBlockChar("Water"));
	}
	
	public static void fillwith(LevelGrid grid, char block) {
		for(int x = 0; x < 1024; x++) {
			for(int y = 0; y < 1024; y++) {
				grid.grid[x][y] = block;
			}
		}
	}
	
	public static void populate(LevelGrid grid, char tile) {
		Random rand = new Random();
		for(int x = 100; x < 900; x++) {
			for(int y = 100; y < 900; y++) {
				if(rand.nextInt(60) == 5) {
					int ax = 0;
					int ay = 0;
					while (rand.nextInt(10) != 5) {
						grid.grid[x+ax][y+ay] = tile;
						grid.grid[x+ax+1][y+ay] = tile;
						grid.grid[x+ax-1][y+ay] = tile;
						grid.grid[x+ax][y+ay+1] = tile;
						grid.grid[x+ax][y+ay-1] = tile;
						if(rand.nextInt(2) == 1) {
							ax++;
						}else {
							ax--;
						}
						if(rand.nextInt(2) == 1) {
							ay++;
						}else {
							ay--;
						}
					}
				}
			}
		}
	}
	
	public static void populateMini(LevelGrid grid, char tile) {
		Random rand = new Random();
		for(int x = 100; x < 900; x++) {
			for(int y = 100; y < 900; y++) {
				if(rand.nextInt(120) == 5) {
					int ax = 0;
					int ay = 0;
					while (rand.nextInt(6) != 5) {
						grid.grid[x+ax][y+ay] = tile;
						grid.grid[x+ax+1][y+ay] = tile;
						grid.grid[x+ax-1][y+ay] = tile;
						grid.grid[x+ax][y+ay+1] = tile;
						grid.grid[x+ax][y+ay-1] = tile;
						if(rand.nextInt(2) == 1) {
							ax++;
						}else {
							ax--;
						}
						if(rand.nextInt(2) == 1) {
							ay++;
						}else {
							ay--;
						}
					}
				}
			}
		}
	}
}
