package worldgame.engine.core.threading;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import worldgame.engine.core.InputReadyFrame;

public class GamePortal{
	
	public InputReadyFrame portal_window;
	public JPanel internal_panel;
	private Graphics _panel_g;
	public Graphics image_g;
	public BufferedImage frame_buffer;
	
	public int horizontal_resolution, vertical_resolution;
	private float _aspectRatio;
	
	public GamePortal(String window_name, int horizontal_resolution, int vertical_resolution) {
		frame_buffer = new BufferedImage(horizontal_resolution, vertical_resolution, BufferedImage.TYPE_INT_RGB);
		internal_panel = new JPanel();
		internal_panel.setBackground(Color.BLACK);
		portal_window = new InputReadyFrame();
		portal_window.add(internal_panel);
		portal_window.setSize(100, 100);
		portal_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		portal_window.setVisible(true);
		portal_window.setSize(horizontal_resolution, vertical_resolution);
		this.horizontal_resolution = horizontal_resolution;
		this.vertical_resolution = vertical_resolution;
		_aspectRatio = (float)horizontal_resolution / (float)vertical_resolution;
		_panel_g = internal_panel.getGraphics();
		image_g = frame_buffer.getGraphics();
	}
	
	public void printFrameBuffer() {
		int image_x = 0;
		int image_y = 0;
		int image_width = 0;
		int image_height = 0;
		
		_panel_g = internal_panel.getGraphics();
		
		float current_aspect = (float)internal_panel.getWidth() / (float)internal_panel.getHeight();
		if(current_aspect > _aspectRatio) {
			image_x = (int) (((float)internal_panel.getWidth() * 0.5f) - ((float)internal_panel.getHeight() * _aspectRatio * 0.5f));
			image_y = 0;
			image_width = (int) ((float)internal_panel.getHeight() * _aspectRatio);
			image_height = internal_panel.getHeight();
		}else {
			image_x = 0;
			image_y = (int) (((float)internal_panel.getHeight() * 0.5f) - (0.5f * (float)internal_panel.getWidth() / _aspectRatio));
			image_width = internal_panel.getWidth();
			image_height = (int) ((float)internal_panel.getWidth() / _aspectRatio);
		}
		
		_panel_g.drawImage(frame_buffer, 
				image_x, image_y, 
				image_width, image_height,
				null);
	}

	
}
