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
	final int size = 6;
	Clip[] clips = new Clip[size];
	public SoundEffect(String resourcePath) {
		try {
			for(int i = 0; i<size; i++) {
				clips[i] = AudioSystem.getClip();
				clips[i].open(AudioSystem.getAudioInputStream(ResourceLoader.class.getResource(resourcePath).toURI().toURL()));
			}
		} catch (UnsupportedAudioFileException | IOException | URISyntaxException | LineUnavailableException e) {
			e.printStackTrace();
		}  
	}
	
	int n = 0;
	public void play() {
		System.out.print("");
		clips[n].setFramePosition(0);
		clips[n].start();
		n++;
		if(n>=size)n=0;
	}
}
