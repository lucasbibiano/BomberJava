package client.core;

import game_objects.Bomb;
import game_objects.Explosion;
import game_objects.Map;
import game_objects.Player;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;

import client.input.GameKeyListener;
import client.objects.BombGraphics;
import client.objects.Drawable;
import client.objects.ExplosionGraphics;
import client.objects.MapGraphics;
import client.objects.PlayerGraphics;

import networking.GameMessage;
import behavior.MessageListener;
import core.Game;

public class GameClient extends Game implements Drawable {
	private MapGraphics mapGraphics;

	private GameKeyListener keyListener;
	
	private MessageListener messageListener;
	
	public GameClient() {
		super();
	}

	@Override
	public void draw(Graphics g) {
		if (mapGraphics != null)
			mapGraphics.draw(g);

		for (int i = 0; i < nPlayers; i++) {
			Player p = getPlayers()[i];
			PlayerGraphics pg = new PlayerGraphics(p);
			
			pg.draw(g);
		}
		
		Iterator<Bomb> i = bombs.iterator();
		while (i.hasNext()) {
			Bomb b = i.next();
			BombGraphics bg = new BombGraphics(b);
			bg.draw(g);
		}
		
		Iterator<Explosion> j = explosions.iterator();
		while (j.hasNext()) {
			Explosion e = j.next();
			ExplosionGraphics eg = new ExplosionGraphics(e);
			eg.draw(g);
		}
		
		
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
		GameMessage message = new GameMessage();
		
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
