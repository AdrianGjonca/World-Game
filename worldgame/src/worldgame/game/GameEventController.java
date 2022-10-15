package worldgame.game;

import java.awt.event.KeyEvent;

import worldgame.engine.core.IndividualInputObj;
import worldgame.engine.core.InputReadyFrame;
import worldgame.game.particles.DestroyParticle;
import worldgame.game.playerrelated.Inventory;

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
	}
	
	public boolean targeting = false;
	
	long lastClicked = 0;
	public void update() {
		
		if(e.hasBeenPressed && e.hasBeenReleased && e.held) {
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
		if(space.hasBeenPressed) {
			space.hasBeenPressed = false;
			space.hasBeenReleased = false;
			
			if(left.held && !(world.player_facing != 2 && (up.held || down.held))) {
				if(world.checkGround(world.player_x-1, world.player_y)) world.player_x--;
				lastClicked = System.currentTimeMillis();
			}
			
			if(right.held&& !(world.player_facing != 3 && (up.held || down.held))) {
				if(world.checkGround(world.player_x+1, world.player_y)) world.player_x++;
				lastClicked = System.currentTimeMillis();
			}
			
			if(down.held && !(world.player_facing != 1 && (left.held || right.held))) {
				if(world.checkGround(world.player_x, world.player_y-1)) world.player_y--;
				lastClicked = System.currentTimeMillis();
			}
			
			if(up.held && !(world.player_facing != 0 && (left.held || right.held))) {
				if(world.checkGround(world.player_x, world.player_y+1)) world.player_y++;
				lastClicked = System.currentTimeMillis();
			}
		}
		
		if(left.held) {
			world.player_facing = 2;
			targeting = true;
			if(backspace.hasBeenPressed && backspace.hasBeenReleased && backspace.held) {
				destroyBlock(-1, 0);	
				backspace.hasBeenPressed = false;
				backspace.hasBeenReleased = false;
			}
		}
		
		if(right.held) {
			world.player_facing = 3;
			targeting = true;
			if(backspace.hasBeenPressed && backspace.hasBeenReleased && backspace.held) {
				destroyBlock(1, 0);		
				backspace.hasBeenPressed = false;
				backspace.hasBeenReleased = false;
			}
		}
		
		if(down.held) {
			world.player_facing = 1;
			targeting = true;
			if(backspace.hasBeenPressed && backspace.hasBeenReleased && backspace.held) {
				destroyBlock(0, -1);			
				backspace.hasBeenPressed = false;
				backspace.hasBeenReleased = false;
			}
		}
		
		if(up.held) {
			world.player_facing = 0;
			targeting = true;
			if(backspace.hasBeenPressed && backspace.hasBeenReleased && backspace.held) {
				destroyBlock(0, 1);		
				backspace.hasBeenPressed = false;
				backspace.hasBeenReleased = false;
			}
		}
		
		this.targeting = targeting;
	}
	
	public void destroyBlock(int ax, int ay) {
		if(world.world.grid[world.player_x + ax][world.player_y + ay] != 0) {
			inventory.addBlock(world.world.grid[world.player_x + ax][world.player_y + ay]);
			scene.particles.particles.add(new DestroyParticle(world.player_x + ax,world.player_y + ay));
			world.world.grid[world.player_x + ax][world.player_y + ay] = 0;	
		}			
	}
}
