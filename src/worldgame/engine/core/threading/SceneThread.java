package worldgame.engine.core.threading;

import java.awt.Graphics;

public abstract class SceneThread {

	public Graphics image_graphics;
	public GamePortal portal;
	
	public SceneThread(Graphics image_graphics, GamePortal portal) {
		this.image_graphics = image_graphics;
		this.portal = portal;
	}
	
	public abstract void update(float deltaTime, Graphics g);

	
}
