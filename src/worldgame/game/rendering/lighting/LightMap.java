package worldgame.game.rendering.lighting;

import worldgame.game.LevelGrid;
import worldgame.game.block.BlockTypeManager;

public class LightMap {
	public float[][] lightMap;
	public float ambient = 0;
	public LightMap() {
		lightMap = new float[1024][1024];
	}
	
	public void process(LevelGrid grid) {
		for(int x = 0; x<1024; x++) {
			for(int y = 0; y<1024; y++) {
				lightMap[x][y] = ambient;
			}
		}
		
		char torch = (char) BlockTypeManager.getBlockChar("Torch");
		char[][] tiles = grid.grid;
		for(int x = 0; x<tiles.length; x++) {
			for(int y = 0; y<tiles[0].length; y++) {
				if(tiles[x][y] == torch) {
					kernel(x,y,1,5);
				}
			}
		}
		
		for(int x = 0; x<1024; x++) {
			for(int y = 0; y<1024; y++) {
				if(lightMap[x][y] > 1) {
					lightMap[x][y] = 1;
				}
				if(lightMap[x][y] < 0) {
					lightMap[x][y] = 0;
				}
			}
		}
	}
	
	public void kernel(int x, int y, float a, int r) {
		float c;
		for(int u = -r; u<r; u++) {
			for(int v = -r; v<r; v++) {
				c = 1f - (float)(Math.sqrt(u*u + v*v)/r);
				if(c < 0) c = 0;
				add(x+u, y+v, c);
			}
		}
	}
	
	public void set(int x, int y, float a) {
		if(x < 0) return;
		if(x > 1023) return;
		if(y < 0) return;
		if(y > 1023) return;
		
		lightMap[x][y] = a;
	}
	public void add(int x, int y, float a) {
		if(x < 0) return;
		if(x > 1023) return;
		if(y < 0) return;
		if(y > 1023) return;
		
		lightMap[x][y] += a;
	}
}
