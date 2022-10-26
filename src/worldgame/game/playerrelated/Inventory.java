package worldgame.game.playerrelated;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import worldgame.game.block.BlockTypeManager;

public class Inventory {
	public List<ItemStack> items = new LinkedList<ItemStack>();
	public int selected = 0;
	
	
	public Inventory() {
		
	}
	
	public boolean hasSpace(char block) {
		String name = BlockTypeManager.blocks[(int) block].getDrop();
		boolean space = false;
		if(items.size() < 17) {
			return true;
		}else {
			if(name != null) {
				for(ItemStack i : items) {
					if(i.type.equals(name) && i.numberOf < 99) {
						return true;
					}
				}
			}
			return false;
		}
		
	}
	
	public void addBlock(char block) {
		String name = BlockTypeManager.blocks[(int) block].getDrop();
		if(name != null) {
			boolean hasBeenMade = false;
			for(ItemStack i : items) {
				if(i.type.equals(name) && i.numberOf < 99) {
					i.numberOf++;
					hasBeenMade = true;
				}
			}
			if(!hasBeenMade) {
				items.add(new ItemStack(name, 1));
			}
		}
	}
	
}
