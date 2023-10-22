package worldgame.engine.core.audio;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import worldgame.engine.resourcecontroll.ResourceLoader;

public class SoundEffect {
	Clip clip;
	public SoundEffect(String resourcePath) {
	    AudioInputStream audioIn = null;
		try {
			audioIn = AudioSystem.getAudioInputStream(ResourceLoader.class.getResource(resourcePath).toURI().toURL());
		} catch (UnsupportedAudioFileException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}  
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			clip.open(audioIn);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		System.out.print("");
		clip.setFramePosition(0);
		clip.start();
		
	}
}
