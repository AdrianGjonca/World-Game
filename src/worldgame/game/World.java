package worldgame.game;

import worldgame.game.block.BlockTypeManager;

public class World {
	public LevelGrid world;
	public LevelGrid underground;
	public LevelGrid overground;
	public int player_x = 512;
	public int player_y = 512;
	
	public int dimention = 0;
	//up, down, left, right
	public int player_facing = 1;
	public World() {
		underground = new LevelGrid();
		overground = new LevelGrid();
		dimention = 0;
		world = overground;
	}
	
	public void dimentionCheck() {
		if(dimention == 0) {
			world = overground;
		}else if(dimention == 1) {
			world = underground;
		}
	}
	
	public boolean checkGround(int x, int y) {
		return BlockTypeManager.blocks[world.grid[x][y]].getTraversable();
	}
}
