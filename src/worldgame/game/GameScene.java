package worldgame.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import worldgame.engine.core.threading.GamePortal;
import worldgame.engine.core.threading.SceneThread;
import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.BlockRendering;
import worldgame.game.playerrelated.ItemStack;
import worldgame.game.playerrelated.LinkItemNameToIcon;
import worldgame.game.rendering.ParticleManager;
import worldgame.game.rendering.ScreenSpace;
import worldgame.game.rendering.TextGraphics;
import worldgame.game.rendering.WorldSpace;

public class GameScene extends SceneThread {

	WorldSpace position;
	World world;
	ParticleManager particles;
	BufferedImage ground;
	
	Graphics g2;
	GameEventController controller;
	
	BufferedImage[] playersprite;
	BufferedImage cursor;
	BufferedImage placement_cursor;
	BufferedImage destroy_cursor;
	BufferedImage selectedBlockUI;
	
	public GameScene(Graphics image_graphics, GamePortal portal, World world, GameEventController controller) {
		super(image_graphics, portal);
		controller.scene = this;
		this.world = world;
		position = new WorldSpace();
		ground = new BufferedImage(240, 144, BufferedImage.TYPE_INT_RGB);
		g2 = ground.createGraphics();
		
		playersprite = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Player.png"));
		cursor = ResourceLoader.easyLoad("/Cursor.png");
		placement_cursor = ResourceLoader.easyLoad("/Sprites/PlaceCursor.png");
		destroy_cursor = ResourceLoader.easyLoad("/Sprites/DestroyCursor.png");
		
		selectedBlockUI = ResourceLoader.easyLoad("/Sprites/UI/SelectedBlock.png");
		this.controller = controller;
		
		particles = new ParticleManager(world, position);
	}

	@Override
	public void update(float deltaTime, Graphics g) {
		//System.out.println(1f/deltaTime);
		controller.update();
		if(position.camx < world.player_x) position.camx += deltaTime * 2f + (Math.abs(position.camx - world.player_x) * 0.05f);
		if(position.camx > world.player_x) position.camx -= deltaTime * 2f + (Math.abs(position.camx - world.player_x) * 0.05f);
		if(position.camy < world.player_y) position.camy += deltaTime * 2f + (Math.abs(position.camy - world.player_y) * 0.05f);
		if(position.camy > world.player_y) position.camy -= deltaTime * 2f + (Math.abs(position.camy - world.player_y) * 0.05f);
		
		drawBlocks();
		position.drawTileScreenspace(g2, playersprite[world.player_facing], world.player_x, world.player_y);
		g2.drawImage(selectedBlockUI, 240 - 16 - 4, 144 - 16 - 4, null);
		
		if(controller.inventory.items.size() > 0) {
			ItemStack stack = controller.inventory.items.get(controller.inventory.selected);
			g2.drawImage(LinkItemNameToIcon.icon(stack.type), 240 - 16 - 4 + 1, 144 - 16 - 4 + 1, null);
			
			String toWrite = "" + controller.inventory.items.get(controller.inventory.selected).numberOf;
			for(int x = 0; x < toWrite.length(); x++) {
				g2.drawImage(TextGraphics.getLetter(toWrite.charAt(x)), 240 - 16 - 2 + (6 * x), 144 - 16 - 4 + 8, null);
			}
			
		}
		
		int xoffs = 0;
		int yoffs = 0;
		if(!controller.strolling) {
			if(controller.targeting) {
				switch (world.player_facing){
					case 0:
						yoffs = 1;
						break;
					case 1:
						yoffs = -1;
						break;
					case 2:
						xoffs = -1;
						break;
					case 3:
						xoffs = 1;
						break;
				}
				
				BufferedImage cursorType;
				if(world.checkGround(world.player_x + xoffs, world.player_y + yoffs)) {
					cursorType = placement_cursor;
				}else {
					cursorType = destroy_cursor;
				}
				position.drawTileScreenspace(g2, cursorType, world.player_x + xoffs, world.player_y+yoffs);
				
			}
			ScreenSpace.drawTileScreenspace(g2, cursor, 0, 0);
		}else {
			
		}
		g.drawImage(ground, 0, 0, null);
		
		if(Math.abs(position.camx - world.player_x) < 1f/16f) {
			position.camx = world.player_x;
		}
		if(Math.abs(position.camy - world.player_y) < 1f/16f) {
			position.camy = world.player_y;
		}
		
		particles.draw(g);
	}
	
	
	public void drawBlocks() {
		for(int x = world.player_x - 8; x<world.player_x + 9; x++) {
			for(int y = world.player_y - 5; y<world.player_y + 6; y++) {
				BlockRendering.drawBlock(g2, position, world, x, y);
			}
		}
	}

}
