package graphics.core;

import game_objects.GameObject;
import graphics.input.GameKeyListener;
import graphics.objects.Drawable;
import graphics.objects.MapGraphics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import constants.Constants.Movement;
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
			Method method;
			
			try {
				Class<?> clazz = Class.forName("graphics.objects." + obj.getClass().getSimpleName() + "Graphics");
				Constructor<?> constructor = clazz.getConstructor(GameObject.class);
				Object instance = constructor.newInstance(obj);
				
				method = clazz.getDeclaredMethod("draw", new Class[] { Graphics.class });
				method.invoke(instance, new Object[] { g });
			} catch (Exception e) {
				e.printStackTrace();
			}
			
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
