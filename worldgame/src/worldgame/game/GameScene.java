package worldgame.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import worldgame.engine.core.threading.GamePortal;
import worldgame.engine.core.threading.SceneThread;
import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.BlockRendering;
import worldgame.game.rendering.ParticleManager;
import worldgame.game.rendering.ScreenSpace;
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
	BufferedImage golden_cursor;
	
	public GameScene(Graphics image_graphics, GamePortal portal, World world, GameEventController controller) {
		super(image_graphics, portal);
		controller.scene = this;
		this.world = world;
		position = new WorldSpace();
		ground = new BufferedImage(240, 144, BufferedImage.TYPE_INT_RGB);
		g2 = ground.createGraphics();
		
		playersprite = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Player.png"));
		cursor = ResourceLoader.easyLoad("/Cursor.png");
		golden_cursor = ResourceLoader.easyLoad("/GoldenCursor.png");
		this.controller = controller;
		
		particles = new ParticleManager(world, position);
	}

	@Override
	public void update(float deltaTime, Graphics g) {
		System.out.println(1f/deltaTime);
		controller.update();
		if(position.camx < world.player_x) position.camx += deltaTime * 2f + (Math.abs(position.camx - world.player_x) * 0.05f);
		if(position.camx > world.player_x) position.camx -= deltaTime * 2f + (Math.abs(position.camx - world.player_x) * 0.05f);
		if(position.camy < world.player_y) position.camy += deltaTime * 2f + (Math.abs(position.camy - world.player_y) * 0.05f);
		if(position.camy > world.player_y) position.camy -= deltaTime * 2f + (Math.abs(position.camy - world.player_y) * 0.05f);
		
		drawBlocks();
		position.drawTileScreenspace(g2, playersprite[world.player_facing], world.player_x, world.player_y);
		if(controller.targeting) {
			switch (world.player_facing){
				case 0:
					position.drawTileScreenspace(g2, golden_cursor, world.player_x, world.player_y + 1);
					break;
				case 1:
					position.drawTileScreenspace(g2, golden_cursor, world.player_x, world.player_y - 1);
					break;
				case 2:
					position.drawTileScreenspace(g2, golden_cursor, world.player_x - 1, world.player_y);
					break;
				case 3:
					position.drawTileScreenspace(g2, golden_cursor, world.player_x + 1, world.player_y);
					break;
			
			}
		}
		g.drawImage(ground, 0, 0, null);
		ScreenSpace.drawTileScreenspace(g, cursor, 0, 0);
		
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
