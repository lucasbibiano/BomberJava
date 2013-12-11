package behavior;

import networking.GameMessage;

public interface MessageListener {
	public void messageReceived(GameMessage msg);
}
