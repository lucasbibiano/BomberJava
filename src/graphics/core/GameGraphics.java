package graphics.core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;

import graphics.objects.Drawable;
import core.Game;

public class GameGraphics implements Drawable {
	private Game game;
	
	private KeyListener keyListener;
	
	public GameGraphics(Game game) {
		this.game = game;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.fillRect(game.getPlayer().getX(), game.getPlayer().getY(), 50, 50);
	}
	
	public void update(double delta) {
		
	}
	
	public void setKeyInput(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
}
