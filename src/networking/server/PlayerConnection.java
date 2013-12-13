package networking.server;

import game_objects.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import core.Game;

import networking.GameMessage;
import networking.Message;
import networking.SVConfigMessage;
import thread.SharedThreadPool;

public class PlayerConnection {

	private ObjectInputStream input;
	private ObjectOutputStream output;

	private Game game;

	private Server server;
	
	public PlayerConnection(Socket socket, Server server) throws IOException {
		output = new ObjectOutputStream(socket.getOutputStream());
		output.flush();
		input = new ObjectInputStream(socket.getInputStream());
		this.server = server;
		this.game = server.getGame();
	}

	public void listen() {
		SharedThreadPool.getES().execute(new Runnable() {

			@Override
			public void run() {
				try {					
					SVConfigMessage configMsg = new SVConfigMessage();
					configMsg.map = game.getMap();
					configMsg.nPlayers = game.getNPlayers() + 1;
					configMsg.yourNumber = game.getNPlayers();

					server.broadcast(configMsg);
					
					game.newPlayer(new Player(game, game.getNPlayers()));

					while (true) {
						GameMessage msg = (GameMessage) input.readObject();
						game.process(msg);
						game.update();
					}

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void send(final Message msg) throws IOException {
		output.writeObject(msg);
		output.flush();
	}
}
