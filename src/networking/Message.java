package networking;

import java.io.Serializable;

public class Message implements Serializable {
    public boolean[] moves = new boolean[4];
    public boolean placeBomb;
    public boolean placeBlock;
    public int playerNumber;
}
