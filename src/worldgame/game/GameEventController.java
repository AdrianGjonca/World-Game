package worldgame.game;

import java.awt.event.KeyEvent;

import worldgame.engine.core.IndividualInputObj;
import worldgame.engine.core.InputReadyFrame;
import worldgame.game.block.BlockTypeManager;
import worldgame.game.foliage.FoliageTick;
import worldgame.game.particles.DestroyParticle;
import worldgame.game.particles.ErrorParticle;
import worldgame.game.playerrelated.Inventory;
import worldgame.game.playerrelated.ItemStack;

public class GameEventController {
	InputReadyFrame frame;
	World world;
	public GameScene scene;
	
	Inventory inventory;
	
	IndividualInputObj left;
	IndividualInputObj right;
	IndividualInputObj up;
	IndividualInputObj down;
	IndividualInputObj space;
	IndividualInputObj backspace;
	IndividualInputObj e;
	
	IndividualInputObj upArrow;
	IndividualInputObj downArrow;
	IndividualInputObj returnKey;
	
	public GameEventController(InputReadyFrame frame, World world, Inventory inventory) {
		this.frame = frame;
		this.world = world;
		this.inventory = inventory;
		
		IndividualInputObj left = new IndividualInputObj(KeyEvent.VK_A);
		frame.listened_to_buttons.add(left);
		this.left = left;
		this.left.hasBeenReleased = true;
		
		IndividualInputObj right = new IndividualInputObj(KeyEvent.VK_D);
		frame.listened_to_buttons.add(right);
		this.right = right;
		this.right.hasBeenReleased = true;
		
		IndividualInputObj up = new IndividualInputObj(KeyEvent.VK_W);
		frame.listened_to_buttons.add(up);
		this.up = up;
		this.up.hasBeenReleased = true;
		
		IndividualInputObj down = new IndividualInputObj(KeyEvent.VK_S);
		frame.listened_to_buttons.add(down);
		this.down = down;
		this.down.hasBeenReleased = true;
		
		IndividualInputObj space = new IndividualInputObj(KeyEvent.VK_SPACE);
		frame.listened_to_buttons.add(space);
		this.space = space;
		this.space.hasBeenReleased = true;
		
		IndividualInputObj backspace = new IndividualInputObj(KeyEvent.VK_BACK_SPACE);
		frame.listened_to_buttons.add(backspace);
		this.backspace = backspace;
		this.backspace.hasBeenReleased = true;
		
		IndividualInputObj e = new IndividualInputObj(KeyEvent.VK_E);
		frame.listened_to_buttons.add(e);
		this.e = e;
		this.e.hasBeenReleased = true;
		
		IndividualInputObj upArrow = new IndividualInputObj(KeyEvent.VK_UP);
		frame.listened_to_buttons.add(upArrow);
		this.upArrow = upArrow;
		this.upArrow.hasBeenReleased = true;
		
		IndividualInputObj downArrow = new IndividualInputObj(KeyEvent.VK_DOWN);
		frame.listened_to_buttons.add(downArrow);
		this.downArrow = downArrow;
		this.downArrow.hasBeenReleased = true;
		
		//returnKey
		IndividualInputObj returnKey = new IndividualInputObj(KeyEvent.VK_ENTER);
		frame.listened_to_buttons.add(returnKey);
		this.returnKey = returnKey;
		this.returnKey.hasBeenReleased = true;
		
	}
	
	public boolean targeting = false;
	public boolean strolling = false;
	public long lastTimeReleased = 0;
	public long lastTimePressed = 0;
	
	long nextFoliageTick = 0;
	public void update() {
		
		if(System.currentTimeMillis() > nextFoliageTick) {
			FoliageTick.update(world.overground);
			nextFoliageTick = System.currentTimeMillis() + 1000 * 10;
			System.out.println("tree");
		}
		if(upArrow.hasBeenPressed && upArrow.held && upArrow.hasBeenReleased) {
			if(inventory.selected > 0) {
				inventory.selected--;
			}else {
				inventory.selected = inventory.items.size() - 1;
			}
			
			upArrow.hasBeenPressed = false;
			upArrow.hasBeenReleased = false;
		}
		
		if(downArrow.hasBeenPressed && downArrow.held && downArrow.hasBeenReleased) {
			if(inventory.selected < inventory.items.size() - 1) {
				inventory.selected++;
			}else {
				inventory.selected = 0;
			}
			
			downArrow.hasBeenPressed = false;
			downArrow.hasBeenReleased = false;
		}
		
		if(e.hasBeenPressed && e.held && e.hasBeenReleased) {
			if(world.dimention == 0) {
				world.dimention = 1;
			}else if(world.dimention == 1) {
				world.dimention = 0;
			}
			world.dimentionCheck();
			e.hasBeenPressed = false;
			e.hasBeenReleased = false;
		}
		
		
		boolean targeting = false;
		int targetblockx = 0;
		int targetblocky = 0;
		
		if(left.held) {
			world.player_facing = 2;
			targeting = true;
			targetblockx = -1;
			targetblocky = 0;
		}
		
		if(right.held) {
			world.player_facing = 3;
			targeting = true;
			targetblockx = 1;
			targetblocky = 0;
		}
		
		if(down.held) {
			world.player_facing = 1;
			targeting = true;
			targetblockx = 0;
			targetblocky = -1;
		}
		
		if(up.held) {
			world.player_facing = 0;
			targeting = true;
			targetblockx = 0;
			targetblocky = 1;
		}
		
		if(backspace.hasBeenPressed && backspace.hasBeenReleased && backspace.held && targeting) {
			destroyBlock(targetblockx, targetblocky);			
			backspace.hasBeenPressed = false;
			backspace.hasBeenReleased = false;
		}
		
		if((!space.held && space.hasBeenReleased == true) || !targeting) {
			lastTimeReleased = System.currentTimeMillis();
		}
		
		boolean spacetap = (space.hasBeenPressed && space.hasBeenReleased && space.held && targeting);
		
		int gap = 300;
		if((System.currentTimeMillis() - lastTimeReleased) > 900) {
			gap = 200;
		}
		if((System.currentTimeMillis() - lastTimeReleased) > 1200) {
			gap = 100;
		}
		if((System.currentTimeMillis() - lastTimeReleased) > 1500) {
			gap = 50;
		}
		boolean canhold = (System.currentTimeMillis() - lastTimePressed) > gap && space.held;
		if(spacetap || canhold) {
			if(world.checkGround(world.player_x+targetblockx, world.player_y+targetblocky)) {
				world.player_x += targetblockx;
				world.player_y += targetblocky;
			}else {
				lastTimeReleased = System.currentTimeMillis();
			}
			space.hasBeenPressed = false;
			space.hasBeenReleased = false;
			lastTimePressed = System.currentTimeMillis();
		}
		
		if(returnKey.hasBeenPressed && returnKey.held && returnKey.hasBeenReleased) {
			if(inventory.items.size() > 0) {
				ItemStack stack = inventory.items.get(inventory.selected);
				if(targeting && world.checkGround(world.player_x+targetblockx, world.player_y+targetblocky)) {
					int a = BlockTypeManager.getBlockChar(stack.type);
					if(a > 0) world.world.grid[world.player_x + targetblockx][world.player_y + targetblocky] = (char) a;
					stack.numberOf --;
					if(stack.numberOf <= 0) {
						inventory.selected--;
						if(inventory.selected < 0) inventory.selected = 0;
						inventory.items.remove(stack);
					}
				}
			}
			
			returnKey.hasBeenPressed = false;
			returnKey.hasBeenReleased = false;
		}
		
		this.targeting = targeting;
		this.strolling = (System.currentTimeMillis() - lastTimeReleased) > 900;
	}
	
	public void destroyBlock(int ax, int ay) {
		if(world.world.grid[world.player_x + ax][world.player_y + ay] != 0) {
			if(inventory.hasSpace(world.world.grid[world.player_x + ax][world.player_y + ay])) {
				inventory.addBlock(world.world.grid[world.player_x + ax][world.player_y + ay]);
				scene.particles.particles.add(new DestroyParticle(world.player_x + ax,world.player_y + ay));
				world.world.grid[world.player_x + ax][world.player_y + ay] = 0;	
			}else {
				scene.particles.particles.add(new ErrorParticle(world.player_x + ax,world.player_y + ay));
			}
		}			
	}
}
