package worldgame.engine.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class InputReadyFrame extends JFrame implements KeyListener{
	private static final long serialVersionUID = -759672415048105591L;
	
	public List<IndividualInputObj> listened_to_buttons = new ArrayList<IndividualInputObj>();
	
	public InputReadyFrame() {
		addKeyListener(this);
	}
	
	public String typing_buffer = "";
	@Override
	public void keyTyped(KeyEvent e) {
		if(Character.isLetterOrDigit(e.getKeyChar()) || e.getKeyChar() == ' ' ) {
			typing_buffer += e.getKeyChar();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && typing_buffer.length() > 0) {
			typing_buffer = typing_buffer.substring(0, typing_buffer.length() - 1);
		}
		
		for(IndividualInputObj o : listened_to_buttons) {
			if(o.key_code == e.getKeyCode()) {
				o.hasBeenPressed = true;
				o.held = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for(IndividualInputObj o : listened_to_buttons) {
			if(o.key_code == e.getKeyCode()) {
				o.hasBeenReleased = true;
				o.held = false;
			}
		}
	}

}
