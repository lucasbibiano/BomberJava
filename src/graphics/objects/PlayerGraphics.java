package graphics.objects;


import game_objects.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import static constants.Constants.*;

public class PlayerGraphics extends GameObjectGraphics<Player> {

	private Player player;
	
	public PlayerGraphics(Player player) {
		super(player);
		this.player = super.getObject();
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);
		
		g2d.fillRect(player.getX() * TILESIZE, player.getY() * TILESIZE, 32, 32);
		g2d.setColor(Color.black);
	}

}
