package worldgame.engine;

import java.awt.event.KeyEvent;

import worldgame.engine.core.IndividualInputObj;
import worldgame.engine.core.threading.GamePortal;
import worldgame.game.GameEventController;
import worldgame.game.GameScene;
import worldgame.game.World;
import worldgame.game.playerrelated.Inventory;
import worldgame.game.playerrelated.InventoryScene;
import worldgame.game.worldgeneration.GenWorld;

public class Main {

	public static void main(String[] args) {
		GamePortal port = new GamePortal("Title", 240, 144);
		World world = new World();		
		Inventory inventory = new Inventory();
		GenWorld.generateWorld(world);
		GameEventController ev = new GameEventController(port.portal_window, world, inventory);
		GameScene game = new GameScene(port.image_g, port, world, ev);
		InventoryScene inventscene = new InventoryScene(port.image_g, port, inventory);
		IndividualInputObj itemsIn = new IndividualInputObj(KeyEvent.VK_I);
		//itemsIn.hasBeenReleased = true;
		port.portal_window.listened_to_buttons.add(itemsIn);
		
		inventscene.initThread();
		
		game.initThread();
		game.play();
		
		boolean inInventory = false;
		while(true) {
			if(itemsIn.hasBeenPressed && itemsIn.hasBeenReleased && !itemsIn.held) {
				inInventory = !inInventory;
				
				if(inInventory) {
					game.pause();
					inventscene.play();
				}else {
					inventscene.pause();
					game.play();
				}
				itemsIn.hasBeenReleased = false;
				itemsIn.hasBeenPressed = false;
			}
			
			System.out.print("");
		}
		
	}

}
