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

public class GameClient implements Drawable {
	private MapGraphics mapGraphics;

	private GameKeyListener keyListener;
	
	private MessageListener messageListener;
	
	private Game game;
	private int playerNumber;
	
	public GameClient(Game game) {
		this.game = game;
	}
	
	@Override
	public void draw(Graphics g) {
		if (!game.canStart()) {
			g.drawString("Waiting for players", 100, 100);
			return;
		}
		
		if (mapGraphics != null)
			mapGraphics.draw(g);

		for (int i = 0; i < game.getNPlayers(); i++) {
			Player p = game.getPlayers()[i];
			PlayerGraphics pg = new PlayerGraphics(p);
			
			pg.draw(g);
		}
		
		for (int i = 0 ; i < game.getBombs().size(); i++) {
			Bomb b = game.getBombs().get(i);
			BombGraphics bg = new BombGraphics(b);
			bg.draw(g);
		}
		
		for (int i = 0; i < game.getExplosions().size(); i++) {
			Explosion e = game.getExplosions().get(i);
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

	public void setMap(Map map) {
		game.setMap(map);
		this.mapGraphics = new MapGraphics(game.getMap());
	}

	public void update() {
		GameMessage message = new GameMessage();
		
		System.out.println(message);
		
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
		message.playerNumber = playerNumber;
		
		if (moves[0] || moves[1] || moves[2] || moves[3] || message.placeBlock || message.placeBomb)
			System.out.println(message);

		if (messageListener != null) {
			messageListener.messageReceived(message);
		}
		
		game.update();
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

	public void newPlayer(Player player) {
		game.newPlayer(player);
	}

	public Game getGame() {
		return game;
	}

	public void setPlayerNumber(int yourNumber) {
		playerNumber = yourNumber;
	}
}
