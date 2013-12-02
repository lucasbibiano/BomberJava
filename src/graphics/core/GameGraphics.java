package graphics.core;

import game_objects.GameObject;
import game_objects.Map;
import graphics.input.GameKeyListener;
import graphics.objects.Drawable;
import graphics.objects.MapGraphics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;

import networking.Message;

import static constants.Constants.*;
import core.Game;

public class GameGraphics extends Game implements Drawable {
	private MapGraphics mapGraphics;

	private GameKeyListener keyListener;
	
	public GameGraphics() throws UnknownHostException, IOException {
		super(new Socket(HOST, PORT));
	}

	@Override
	public void draw(Graphics g) {
		mapGraphics.draw(g);

		for (int i = getObjects().size() - 1; i >= 0; --i) {
			GameObject obj = getObjects().get(i);
			Method method;

			try {
				Class<?> clazz = Class.forName("graphics.objects."
						+ obj.getClass().getSimpleName() + "Graphics");
				Constructor<?> constructor = clazz
						.getConstructor(GameObject.class);
				Object instance = constructor.newInstance(obj);

				method = clazz.getDeclaredMethod("draw",
						new Class[] { Graphics.class });
				method.invoke(instance, new Object[] { g });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setMap(Map map) {
		super.setMap(map);
		this.mapGraphics = new MapGraphics(this.getMap());
	}

	public void update(double delta) {
		Message message = new Message();
		
		boolean[] moves = new boolean[4];

		if (keyListener.isPressed(KeyEvent.VK_UP))
			moves[0] = true;
		if (keyListener.isPressed(KeyEvent.VK_DOWN))
			moves[1] = true;
		if (keyListener.isPressed(KeyEvent.VK_LEFT))
			moves[2] = true;
		if (keyListener.isPressed(KeyEvent.VK_RIGHT))
			moves[3] = true;

		this.getPlayer().move(moves);
		
		message.moves = moves;
		message.placeBomb = keyListener.isPressed(KeyEvent.VK_SPACE);
		message.placeBlock = keyListener.isPressed(KeyEvent.VK_SHIFT);
		message.playerNumber = getPlayer().getNumber();

		if (keyListener.isPressed(KeyEvent.VK_SPACE))
			this.getPlayer().placeBomb();
		if (keyListener.isPressed(KeyEvent.VK_SHIFT))
			this.getPlayer().placeBlock();

		try {			System.out.println("hue");

			getClient().send(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		super.update(delta);
	}

	public void setKeyInput(GameKeyListener keyListener) {
		this.keyListener = keyListener;
	}
}
