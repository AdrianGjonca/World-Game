package worldgame.game.playerrelated;

import java.awt.Graphics;

import worldgame.engine.core.threading.GamePortal;
import worldgame.engine.core.threading.SceneThread;

public class InventoryScene extends SceneThread {

	Inventory inventory;
	public InventoryScene(Graphics image_graphics, GamePortal portal, Inventory inventory) {
		super(image_graphics, portal);
		this.inventory = inventory;
	}

	@Override
	public void update(float deltaTime, Graphics g) {
		g.clearRect(0, 0, 240, 144);
		int i = 0;
		for(ItemStack item : inventory.items) {
			g.drawString(item.type + " x" + item.numberOf, 0, 10 + 10 * i);
			i++;
		}
	}

}
