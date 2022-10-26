package worldgame.game.block;

import worldgame.game.block.blocks.Air;
import worldgame.game.block.blocks.Leaves;
import worldgame.game.block.blocks.Stone;
import worldgame.game.block.blocks.Wood;

public class BlockTypeManager {
	public static Block[] blocks = {
		new Air(),
		new Stone(),
		new Leaves(),
		new Wood()
	};
	
	public static int getBlockChar(String drop) {
		int id = 0;
		for(Block b : blocks) {
			if(b.getName() == drop) {
				return id;
			}
			id++;
		}
		return -1;
		
	}
}
