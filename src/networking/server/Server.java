package networking.server;

import java.io.IOException;
import java.net.ServerSocket;

import thread.SharedThreadPool;

import core.Game;

public class Server {
	private PlayerConnection[] players;
	private int numOfPlayers;

	private ServerSocket server;

	public Server() {
		players = new PlayerConnection[4];
		numOfPlayers = 0;
	}

	public void runServer() {

		SharedThreadPool.getES().execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					server = new ServerSocket(12345, 4);

					while (true) {
						players[numOfPlayers++] = new PlayerConnection(server.accept());
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
				}				
			}
		});
	}
	
	public static void main(String[] args) {
		new Server().runServer();
	}

}
