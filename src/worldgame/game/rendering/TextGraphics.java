package worldgame.game.rendering;

import java.awt.image.BufferedImage;

import worldgame.engine.resourcecontroll.ResourceLoader;

public class TextGraphics {
	static BufferedImage alphabet = ResourceLoader.easyLoad("/Alphabet/Alphabet.png");
	static BufferedImage[] tiles = ResourceLoader.retrieveTileset(alphabet);
	
	static BufferedImage numbers = ResourceLoader.easyLoad("/Alphabet/Numbers.png");
	static BufferedImage[] numtiles = ResourceLoader.retrieveTileset(numbers);
	
	static BufferedImage[] inv_numbers = coloredLetters(numtiles, 0xFF332E27);
	static BufferedImage[] inv_letters = coloredLetters(tiles, 0xFF332E27);
	
	
	public static BufferedImage[] coloredLetters(BufferedImage[] input, int rgb) {
		BufferedImage[] output = input.clone();
		for (BufferedImage tile : output) {
			for(int x = 0; x < 8; x++) {
				for(int y = 0; y < 8; y++) {
					if(tile.getRGB(x, y) < 0) {
						tile.setRGB(x, y, rgb);
					}
				}
			}
		}
		return output;
	}
	public static BufferedImage getLetter(char let) {
		if(let >= 48 && let <= 57) {
			return inv_numbers[let - 48];
		}
		if(let >= 65) {
			return inv_letters[let - 65];
		}
		return null;
		
	}
}
