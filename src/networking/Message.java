package networking;

import java.io.Serializable;
import java.util.Arrays;

public class Message implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean[] moves = new boolean[4];
    public boolean placeBomb;
    public boolean placeBlock;
    public int playerNumber;
    
    @Override
    public String toString() {
    	return Arrays.toString(moves) + " - " + placeBomb + " - " + placeBlock + " - " + playerNumber;
    }
}
