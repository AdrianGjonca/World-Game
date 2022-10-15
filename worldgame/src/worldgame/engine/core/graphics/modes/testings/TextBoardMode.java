package worldgame.engine.core.graphics.modes.testings;

import java.awt.Graphics;

import worldgame.engine.core.threading.GamePortal;
import worldgame.engine.core.threading.SceneThread;

public class TextBoardMode extends SceneThread {

	public String text;
	public TextBoardMode(Graphics image_graphics, GamePortal portal) {
		super(image_graphics, portal);
	}

	@Override
	public void update(float deltaTime, Graphics g) {
		g.clearRect(0, 0, portal.horizontal_resolution, portal.vertical_resolution);
		
		g.drawString(text, 1, 10);
		g.drawString("["+ portal.portal_window.typing_buffer +"]", 1, 41);
		
	}
}
