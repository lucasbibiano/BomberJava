package networking.server;

import game_objects.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import core.Game;

import networking.GameMessage;
import networking.SVConfigMessage;
import thread.SharedThreadPool;

public class PlayerConnection {

	private ObjectInputStream input;
	private ObjectOutputStream output;

	private Game game;

	public PlayerConnection(Socket socket, Game game) throws IOException {
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
					SVConfigMessage configMsg = new SVConfigMessage();
					configMsg.map = game.getMap();
					configMsg.nPlayers = game.getNPlayers();

					game.newPlayer(new Player(game, 10, 10, 1));

					output.writeObject(configMsg);
					output.flush();

					while (true) {
						GameMessage msg = (GameMessage) input.readObject();
						game.process(msg);
					}

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void send(final GameMessage msg) throws IOException {
		output.writeObject(msg);
		output.flush();
	}
}
