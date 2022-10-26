package worldgame.game.playerrelated;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import worldgame.engine.core.threading.GamePortal;
import worldgame.engine.core.threading.SceneThread;
import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.rendering.TextGraphics;

public class InventoryScene extends SceneThread {

	Inventory inventory;
	static BufferedImage inventoryBackground = ResourceLoader.easyLoad("/Sprites/UI/InventoryBack.png");
	
	public InventoryScene(Graphics image_graphics, GamePortal portal, Inventory inventory) {
		super(image_graphics, portal);
		this.inventory = inventory;
	}

	@Override
	public void update(float deltaTime, Graphics g) {
		g.drawImage(inventoryBackground, 0, 0, null);
		int i = 0;
		for(ItemStack item : inventory.items) {
			g.drawImage(LinkItemNameToIcon.icon(item.type), 1, 8 * i + 1, null);
			String toWrite = item.type.toUpperCase() + " X " + item.numberOf;
			for(int x = 0; x < toWrite.length(); x++) {
				g.drawImage(TextGraphics.getLetter(toWrite.charAt(x)), 11 + (x * 8), 8 * i + 1, null);
			}
			i++;
		}
	}

}
