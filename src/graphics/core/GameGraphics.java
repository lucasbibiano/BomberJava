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

import behavior.MessageListener;

import networking.Message;

import static constants.Constants.*;
import core.Game;

public class GameGraphics extends Game implements Drawable {
	private MapGraphics mapGraphics;

	private GameKeyListener keyListener;
	
	private MessageListener messageListener;
	
	public GameGraphics() throws UnknownHostException, IOException {
		super();
	}

	@Override
	public void draw(Graphics g) {
		mapGraphics.draw(g);

/*		for (int i = getObjects().size() - 1; i >= 0; --i) {
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
		}*/
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

		message.moves = moves;
		message.placeBomb = keyListener.isPressed(KeyEvent.VK_SPACE);
		message.placeBlock = keyListener.isPressed(KeyEvent.VK_SHIFT);
		message.playerNumber = 1;

		if (messageListener != null) {
			messageListener.messageReceived(message);
			//super.update(delta);
		}
	}

	public void setKeyInput(GameKeyListener keyListener) {
		this.keyListener = keyListener;
	}

	public MessageListener getMessageListener() {
		return messageListener;
	}

	public void setMessageListener(MessageListener messageListener) {
		this.messageListener = messageListener;
	}
}
