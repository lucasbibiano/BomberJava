package graphics.objects;

import static constants.Constants.TILESIZE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import game_objects.Block;
import game_objects.GameObject;

public class BlockGraphics extends GameObjectGraphics {
	private Block block;
	
	public BlockGraphics(GameObject block) {
		super(block);
		this.block = (Block) block;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(new Color(105,112,96));
		
		g2d.fillRect(block.getX() * TILESIZE, block.getY() * TILESIZE, 32, 32);
		g2d.setColor(new Color(105,112,96));
		
	}
}
