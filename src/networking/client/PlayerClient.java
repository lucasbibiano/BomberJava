package networking.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import networking.Message;
import thread.SharedThreadPool;
import core.Game;

public class PlayerClient {
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private Game game;

	public PlayerClient(Socket socket, Game game) throws IOException {
		output = new ObjectOutputStream(socket.getOutputStream());
		output.flush();
		input = new ObjectInputStream(socket.getInputStream());
		this.game = game;
		System.out.println("hue");
	}

	public void listen() {
		SharedThreadPool.getES().execute(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Message msg = (Message) input.readObject();
						game.process(msg);
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void send(Message msg) throws IOException {
		output.writeObject(msg);
		output.flush();
	}
}
