package networking.client;

import game_objects.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.core.GameClient;

import behavior.MessageListener;

import networking.GameMessage;
import networking.SVConfigMessage;
import thread.SharedThreadPool;

public class PlayerClient implements MessageListener {
	private ObjectInputStream input;
	private ObjectOutputStream output;

	private GameClient game;

	public PlayerClient(Socket socket, GameClient game) throws IOException {
		output = new ObjectOutputStream(socket.getOutputStream());
		output.flush();
		input = new ObjectInputStream(socket.getInputStream());
		this.game = game;
	}

	public void listen() {
		SharedThreadPool.getES().execute(new Runnable() {

			@Override
			public void run() {

				try {
					SVConfigMessage configMsg = (SVConfigMessage) input.readObject();
					game.setMap(configMsg.map);
					game.newPlayer(new Player(game, 10, 10, configMsg.nPlayers + 1));
					System.out.println(configMsg);
					
					while (true) {
						GameMessage msg = (GameMessage) input.readObject();
						Thread.yield();
					}
				} catch (IOException | ClassNotFoundException e) {
				}
			}
		});
	}

	public void send(GameMessage msg) throws IOException {
		output.writeObject(msg);
		output.flush();
	}

	@Override
	public void messageReceived(GameMessage msg) {
		try {
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}