package worldgame.engine.core.graphics.modes.testings;

import java.awt.Graphics;

import worldgame.engine.core.threading.GamePortal;
import worldgame.engine.core.threading.SceneThread;

public class DebugMode extends SceneThread{
	
	public DebugMode(Graphics image_graphics, GamePortal portal) {
		super(image_graphics, portal);
	}

	@Override
	public void update(float deltaTime, Graphics g) {
		g.clearRect(0, 0, portal.horizontal_resolution, portal.vertical_resolution);
		
		g.drawString("This is a debug" + System.currentTimeMillis(), 1, 10);
		g.drawString("["+ portal.portal_window.typing_buffer +"]", 1, 21);
		
	}
	
}
