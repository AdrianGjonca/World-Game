package worldgame.game.foliage;

import java.util.Random;

import worldgame.game.LevelGrid;
import worldgame.game.block.BlockTypeManager;

public class FoliageTick {
	static boolean firstTime = true;

	public static void update(LevelGrid grid) {
		int tree_count = 0;
		int air_count = 0;

		Random random = new Random();

		char leaf = (char) BlockTypeManager.getBlockChar("Leaves");
		for (int x = 1; x < grid.grid.length - 2; x++) {
			for (int y = 1; y < grid.grid[0].length - 2; y++) {
				int leaves = 0;
				int air = 0;

				if (grid.grid[x - 1][y] == leaf) {
					leaves++;
				}
				if (grid.grid[x + 1][y] == leaf) {
					leaves++;
				}
				if (grid.grid[x][y - 1] == leaf) {
					leaves++;
				}
				if (grid.grid[x][y + 1] == leaf) {
					leaves++;
				}

				if (grid.grid[x - 1][y] == 0) {
					air++;
				}
				if (grid.grid[x + 1][y] == 0) {
					air++;
				}
				if (grid.grid[x][y - 1] == 0) {
					air++;
				}
				if (grid.grid[x][y + 1] == 0) {
					air++;
				}

				if (grid.grid[x][y] == leaf && (random.nextInt(10) == 2 || firstTime)) {
					if (air > 2) {
						grid.grid[x][y] = 0;
						air_count++;
					}
				}

				if (grid.grid[x][y] == 0 && random.nextInt(100) == 2 && !firstTime) {
					if (leaves > 1) {  
						grid.grid[x][y] = leaf;
						tree_count++;
					}
				}
			}

			firstTime = false;
		}

	}
}
