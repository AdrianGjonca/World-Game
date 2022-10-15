package worldgame.engine.core.graphics.modes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import worldgame.engine.core.threading.GamePortal;
import worldgame.engine.core.threading.SceneThread;

public class DualLayerMode extends SceneThread{

	public BufferedImage background;
	public BufferedImage foreground;
	
	public DualLayerMode(Graphics image_graphics, GamePortal portal) {
		super(image_graphics, portal);
		background = new BufferedImage(portal.horizontal_resolution, portal.vertical_resolution, BufferedImage.TYPE_INT_RGB);
		foreground = new BufferedImage(portal.horizontal_resolution, portal.vertical_resolution, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
	public void update(float deltaTime, Graphics g) {
		g.clearRect(0, 0, portal.horizontal_resolution, portal.vertical_resolution);
		g.drawImage(background, 0, 0, null);
		g.drawImage(foreground, 0, 0, null);
	}

}
