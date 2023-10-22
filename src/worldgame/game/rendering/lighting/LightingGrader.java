package worldgame.game.rendering.lighting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class LightingGrader {

	public static BufferedImage graded(BufferedImage in, float grade) {
		/*
		 * float gradeProper = (float) Math.sqrt(grade);
		 * 
		 * if(g == null) g = buffer.createGraphics(); g.setBackground(new
		 * Color(0,0,0,0)); int r =0; int g = 0; int b = 0; int rgb = 0; for(int x = 0;
		 * x < in.getWidth(); x++) { for(int y = 0; y < in.getHeight(); y++) { rgb =
		 * in.getRGB(x, y); r = (rgb & 0xFF0000) >> 16; g = (rgb & 0x00FF00) >> 8; b =
		 * (rgb & 0x0000FF); r *= gradeProper; g *= gradeProper; b *= gradeProper;
		 * buffer.setRGB(x, y, (r << 16) + (g << 8) + (b)); } } return
		 * buffer.getSubimage(0, 0, in.getWidth(), in.getHeight());
		 */

		//This method is chosen as it seems to be better performance wise.
		//I'd imagine it's utilising the GPU.
		
		
		float gradeProper = (float) Math.sqrt(grade);

		BufferedImage buffer = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = buffer.createGraphics();
		
		g.drawImage(in, 0, 0, null);
		g.setColor(new Color(0, 0, 0, (int) (255f * (1f - grade))));
		g.fillRect(0, 0, in.getWidth(), in.getHeight());

		return buffer.getSubimage(0, 0, in.getWidth(), in.getHeight());

	}

}
