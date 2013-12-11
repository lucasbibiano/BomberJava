package networking.server;

import game_objects.Map;

import java.io.IOException;
import java.net.ServerSocket;

import core.Game;

import thread.SharedThreadPool;

public class Server {
	private PlayerConnection[] players;
	private int numOfPlayers;

	private ServerSocket server;
	
	private Game game;

	public Server(Game game) {
		players = new PlayerConnection[4];
		numOfPlayers = 0;
		this.game = game;
	}

	public void runServer() {

		SharedThreadPool.getES().execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					server = new ServerSocket(12345, 4);

					while (true) {
						players[numOfPlayers++] = new PlayerConnection(server.accept(), game);
						players[numOfPlayers - 1].listen();
						Thread.yield();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
				}				
			}
		});
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		Map map = new Map(game);
		game.setMap(map);
		
		new Server(game).runServer();
	}

}
