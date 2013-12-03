package behavior;

import networking.Message;

public interface MessageListener {
	public void messageReceived(Message msg);
}
