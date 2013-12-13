package networking;

import game_objects.Map;

import java.io.Serializable;

public class SVConfigMessage implements Serializable, Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = 10434234L;
	
	public int nPlayers;
	public Map map;
	
	public int yourNumber;
	
	public String toString() {
		return String.valueOf(nPlayers);
	}
}
