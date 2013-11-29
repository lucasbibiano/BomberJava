package graphics.core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import constants.Constants.Movement;

import game_objects.Bomb;
import game_objects.GameObject;
import game_objects.Player;
import graphics.input.GameKeyListener;
import graphics.objects.BombGraphics;
import graphics.objects.Drawable;
import graphics.objects.GameObjectGraphics;
import graphics.objects.MapGraphics;
import graphics.objects.PlayerGraphics;
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
		
		for (GameObject obj: game.getObjects()) {
			GameObjectGraphics objGraphics = null;
			
			if (obj instanceof Player)
				objGraphics = new PlayerGraphics(obj);
			else if (obj instanceof Bomb)
				objGraphics = new BombGraphics(obj);
			
			if (objGraphics != null)
				objGraphics.draw(g);
		}
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
		
		game.update(delta);
	}
	
	public void setKeyInput(GameKeyListener keyListener) {
		this.keyListener = keyListener;
	}
}
