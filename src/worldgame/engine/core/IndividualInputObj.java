package worldgame.engine.core;

public class IndividualInputObj {
	public IndividualInputObj(int key_code) {
		this.key_code = key_code;
	}
	
	public int key_code;
	
	public boolean held = false;
	
	public boolean hasBeenPressed = false;
	
	public boolean hasBeenReleased = false;
}
