package networking.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import networking.Message;
import thread.SharedThreadPool;

public class PlayerConnection {

	private ObjectInputStream input;
	private ObjectOutputStream output;

	public PlayerConnection(Socket socket) throws IOException {
		output = new ObjectOutputStream(socket.getOutputStream());
		output.flush();
		input = new ObjectInputStream(socket.getInputStream());
	}

	public void listen() {
		SharedThreadPool.getES().execute(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Message msg = (Message) input.readObject();
						System.out.println(msg.playerNumber);
						send(msg);
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
