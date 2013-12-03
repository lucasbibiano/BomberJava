package networking.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import behavior.MessageListener;

import networking.Message;
import thread.SharedThreadPool;
import core.Game;

public class PlayerClient implements MessageListener {
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private Game game;

	public PlayerClient(Socket socket, Game game) throws IOException {
		output = new ObjectOutputStream(socket.getOutputStream());
		output.flush();
		input = new ObjectInputStream(socket.getInputStream());
		this.game = game;
	}

	public void listen() {
		SharedThreadPool.getES().execute(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Message msg = (Message) input.readObject();
						System.out.println(msg);
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

	@Override
	public void messageReceived(Message msg) {
		try {
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
