package worldgame.game.foliage;

import worldgame.game.World;

public class FoliageThread extends Thread{
	
	public FoliageThread(World world){
		super();
		this.world = world;
	}
	
	World world;
	long nextFoliageTick = 0;
	public void run() {
		if(System.currentTimeMillis() > nextFoliageTick) {
			FoliageTick.update(world.overground);
			nextFoliageTick = System.currentTimeMillis() + 1000 * 10;
		}
	}
}
