package worldgame.game.particles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.rendering.Particle;
import worldgame.game.rendering.WorldSpace;

public class DestroyParticle extends Particle{

	public static BufferedImage[] sprite_stages = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/DestroyParticles.png"));
	
	public long lastStage = 0;
	int stage;
	public DestroyParticle(float position_x, float position_y) {
		super(position_x, position_y);
		stage = 0;
		lastStage = System.currentTimeMillis();
	}

	
	@Override
	public void draw(Graphics g, WorldSpace space) {
		if(System.currentTimeMillis() - lastStage > 40) {
			lastStage = System.currentTimeMillis();
			stage++;
		}
		if(stage > 5) {
			retired = true;
			return;
		}
		space.drawTileScreenspace(g, sprite_stages[stage], position_x, position_y);
	}

}
