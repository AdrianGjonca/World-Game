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
	
	public boolean contains(String type) {
		for(ItemStack stack : items) {
			if(stack.type.equals(type)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasSpace(char block) {
		String name = BlockTypeManager.blocks[(int) block].getDrop();
		boolean space = false;
		if(items.size() < INVENTORY_SLOTS) {
			return true;
		}else {
			if(name != null) {
				for(ItemStack i : items) {
					if(i.type.equals(name) && i.numberOf < INVENTORY_STACK) {
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
				if(i.type.equals(name) && i.numberOf < INVENTORY_STACK) {
					i.numberOf++;
					hasBeenMade = true;
				}
			}
			if(!hasBeenMade) {
				items.add(new ItemStack(name, 1));
			}
		}
	}
	
	public final int INVENTORY_SLOTS = 17;
	public final int INVENTORY_STACK = 99;
	
}
