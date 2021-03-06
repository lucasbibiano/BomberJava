package networking;

import java.io.Serializable;
import java.util.Arrays;

public class GameMessage implements Serializable, Message {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1545345L;
	public boolean[] moves = new boolean[4];
    public boolean placeBomb;
    public boolean placeBlock;
    public int playerNumber;
    
    public String message;
    
    @Override
    public String toString() {
    	return Arrays.toString(moves) + " - " + placeBomb + " - " + placeBlock + " - " + playerNumber;
    }
}
