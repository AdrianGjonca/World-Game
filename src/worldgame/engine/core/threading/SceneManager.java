package worldgame.engine.core.threading;

import java.awt.Graphics;
import java.util.List;

public class SceneManager implements Runnable {
	public SceneThread active_scene = null;
	
	private Thread t1;
	private boolean active = false;
	private boolean retired = false;
	
	public SceneManager() {
	}
	
	@Override
	public void run() {
		long last = System.nanoTime();
		float delta = 0.2f;
		while(!retired) {
			if(active) {
				if(active_scene != null) active_scene.update(delta, active_scene.image_graphics);
				active_scene.portal.printFrameBuffer();
				delta = (float)(System.nanoTime() - last) / 1000000000f;
				last = System.nanoTime();
			}
		}
	}
	
	public void pause() {
		System.out.println("BYE");
		active = false;
	}
	public void play() {
		System.out.println("HI");
		active = true;
	}
	public void retire() {
		retired = true;
	}
	public void initThread() {
		t1 = new Thread(this); 
		t1.start();
	}
}
