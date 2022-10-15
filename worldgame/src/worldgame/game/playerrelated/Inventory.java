package worldgame.game.playerrelated;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
	List<ItemStack> items = new LinkedList<ItemStack>();
	
	
	public Inventory() {
		
	}
	
	final static String[] itemNames = {"Air", "Stone", "Leaves", "Iron"};
	public void addBlock(char block) {
		String name = itemNames[block];
		boolean hasBeenMade = false;
		for(ItemStack i : items) {
			if(i.type.equals(name)) {
				i.numberOf++;
				hasBeenMade = true;
			}
		}
		if(!hasBeenMade) {
			items.add(new ItemStack(name, 1));
		}
	}
}
