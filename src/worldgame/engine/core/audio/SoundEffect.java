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
	Clip clip1;
	Clip clip2;
	Clip clip3;
	Clip clip4;
	public SoundEffect(String resourcePath) {
	    AudioInputStream audioIn1 = null;
	    AudioInputStream audioIn2 = null;
	    AudioInputStream audioIn3 = null;
	    AudioInputStream audioIn4 = null;
		try {
			audioIn1 = AudioSystem.getAudioInputStream(ResourceLoader.class.getResource(resourcePath).toURI().toURL());
			clip1 = AudioSystem.getClip();
			audioIn2 = AudioSystem.getAudioInputStream(ResourceLoader.class.getResource(resourcePath).toURI().toURL());
			clip2 = AudioSystem.getClip();
			audioIn3 = AudioSystem.getAudioInputStream(ResourceLoader.class.getResource(resourcePath).toURI().toURL());
			clip3 = AudioSystem.getClip();
			audioIn4 = AudioSystem.getAudioInputStream(ResourceLoader.class.getResource(resourcePath).toURI().toURL());
			clip4 = AudioSystem.getClip();
			clip1.open(audioIn1);
			clip2.open(audioIn2);
			clip3.open(audioIn3);
			clip4.open(audioIn4);
		} catch (UnsupportedAudioFileException | IOException | URISyntaxException | LineUnavailableException e) {
			e.printStackTrace();
		}  
	}
	
	public void play() {
		System.out.print("");
		clip1.setFramePosition(0);
		clip1.start();
		
		Clip clipT = clip4;
		clip4 = clip3;
		clip3 = clip2;
		clip2 = clip1;
		clip1 = clipT;
	}
}
