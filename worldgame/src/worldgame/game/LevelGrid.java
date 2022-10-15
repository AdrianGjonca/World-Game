package worldgame.game;

import java.util.Random;

public class LevelGrid {
	public char[][] grid = new char[1024][1024];
	public byte[][] noise_map = new byte[256][256];
	
	public LevelGrid() {
		Random random = new Random();
		for(int x = 0; x < 1024; x++) {
			for(int y = 0; y < 1024; y++) {
				grid[x][y] = (char) 0;
			}
		}
		
		for(int x = 0; x<256; x++) {
			for(int y = 0; y<256; y++) {
				noise_map[x][y] = (byte) random.nextInt(-128, 127);
			}
		}
	}
}
