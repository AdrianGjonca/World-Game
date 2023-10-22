package worldgame.engine;

import java.awt.event.KeyEvent;

import worldgame.engine.core.IndividualInputObj;
import worldgame.engine.core.audio.SoundEffect;
import worldgame.engine.core.threading.GamePortal;
import worldgame.engine.core.threading.SceneManager;
import worldgame.game.GameEventController;
import worldgame.game.GameScene;
import worldgame.game.World;
import worldgame.game.playerrelated.Inventory;
import worldgame.game.playerrelated.InventoryScene;
import worldgame.game.worldgeneration.GenWorld;

public class Main {

	public static void main(String[] args) {
		GamePortal port = new GamePortal("Title", 240, 144);
		
		SceneManager scene_manager = new SceneManager();
		
		World world = new World();		
		Inventory inventory = new Inventory();
		GenWorld.generateWorld(world);
		GameEventController ev = new GameEventController(port.portal_window, world, inventory);
		GameScene game = new GameScene(port.image_g, port, world, ev);
		InventoryScene inventscene = new InventoryScene(port.image_g, port, inventory);
		IndividualInputObj itemsIn = new IndividualInputObj(KeyEvent.VK_I);
		port.portal_window.listened_to_buttons.add(itemsIn);
		
		scene_manager.initThread();
		scene_manager.active_scene = game;
		scene_manager.play();
		
		boolean inInventory = false;
		while(true) {
			if(itemsIn.hasBeenPressed && itemsIn.hasBeenReleased && !itemsIn.held) {
				inInventory = !inInventory;
				ui_sound.play();
				
				if(inInventory) {
					scene_manager.active_scene = inventscene;
				}else {
					scene_manager.active_scene = game;
				}
				itemsIn.hasBeenReleased = false;
				itemsIn.hasBeenPressed = false;
			}
			
			System.out.print("");
		}
		
	}
	static SoundEffect ui_sound = new SoundEffect("/Sound/FX/ui.wav");

}
