package worldgame.game.rendering;

import java.awt.Graphics;

public abstract class Particle {
	public float position_x;
	public float position_y;
	public boolean retired = false;
	
	public Particle(float position_x, float position_y) {
		this.position_x = position_x;
		this.position_y = position_y;
	}
	
	public abstract void draw(Graphics g, WorldSpace space);
}
