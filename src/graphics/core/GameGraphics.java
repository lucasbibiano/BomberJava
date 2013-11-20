package graphics.core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import constants.Constants.Movement;

import graphics.input.GameKeyListener;
import graphics.objects.Drawable;
import graphics.objects.MapGraphics;
import core.Game;

public class GameGraphics implements Drawable {
	private Game game;
	private MapGraphics mapGraphics;
	
	private GameKeyListener keyListener;
	
	public GameGraphics(Game game) {
		this.game = game;
		this.mapGraphics = new MapGraphics(game.getMap());
	}

	@Override
	public void draw(Graphics g) {
		mapGraphics.draw(g);
	}
	
	public void update(double delta) {
		if (keyListener.isPressed(KeyEvent.VK_LEFT))
			game.getPlayer().move(Movement.LEFT);
		
		if (keyListener.isPressed(KeyEvent.VK_RIGHT))
			game.getPlayer().move(Movement.RIGHT);
		
		if (keyListener.isPressed(KeyEvent.VK_UP))
			game.getPlayer().move(Movement.UP);
		
		if (keyListener.isPressed(KeyEvent.VK_DOWN))
			game.getPlayer().move(Movement.DOWN);
		
		if (keyListener.isPressed(KeyEvent.VK_SPACE))
			game.getPlayer().placeBomb();
	}
	
	public void setKeyInput(GameKeyListener keyListener) {
		this.keyListener = keyListener;
	}
}
