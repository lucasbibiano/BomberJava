package graphics.input;

import graphics.core.GameGraphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class GameKeyListener implements KeyListener {

	private HashMap<Integer, Boolean> keys;
	
	public GameKeyListener() {
		keys = new HashMap<Integer, Boolean>();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys.put(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//nothing
	}
	
	public boolean isPressed(int key) {
		return keys.get(key);
	}
}
