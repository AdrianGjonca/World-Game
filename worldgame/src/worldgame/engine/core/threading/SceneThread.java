package worldgame.engine.core.threading;

import java.awt.Graphics;

public abstract class SceneThread implements Runnable {

	private Graphics image_graphics;
	
	public GamePortal portal;
	public SceneThread(Graphics image_graphics, GamePortal portal) {
		this.image_graphics = image_graphics;
		this.portal = portal;
	}
	
	boolean active = false;
	boolean retired = false;
	@Override
	public void run() {
		long last = System.nanoTime();
		float delta = 0.2f;
		while(!retired) {
			if(active) {
				update(delta, image_graphics);
				portal.printFrameBuffer();
				delta = (float)(System.nanoTime() - last) / 1000000000f;
				last = System.nanoTime();
			}
		}
	}
	
	public abstract void update(float deltaTime, Graphics g);

	
	public void pause() {
		active = false;
	}
	
	public void play() {
		active = true;
	}
	
	public void retire() {
		retired = true;
		while(!retired);
	}
	
	Thread t1;
	public void initThread() {
		t1 = new Thread(this); 
		t1.start();
	}
}
