package events;

public class ExplodeEvent {
	private int playerNumber;
	
	public ExplodeEvent(int playerNumber) {
		this.setPlayerNumber(playerNumber);
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
}
