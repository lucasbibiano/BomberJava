package game_objects;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

import static constants.Constants.*;
import core.Game;

public class Map implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4341L;
	
	private int width = WIDTH;
	private int height = HEIGHT;
	
	private char[][] matrix;
	
	private transient HashMap<Point, PowerUp> powerups;
	
	private transient Game game;
	
	public Map(Game game) {
		matrix = new char[height][width];
		this.game = game;
		powerups = new HashMap<Point, PowerUp>();
		game.setMap(this);
		
		Random r = new Random();
		
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				matrix[j][i] = (char) r.nextInt(4); 
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public PowerUp[] getPowerUps() {
		return powerups.values().toArray(new PowerUp[powerups.size()]);
	}
	
	public boolean isMovableSpace(int x, int y){
		return matrix[y][x] == EMPTY_TILE;
	}
	
	public boolean isValid(int x, int y) {
		return (-1 < y && y < getHeight() && -1 < x && x < getWidth() && isMovableSpace(x, y));
	}
	
	public char getType(int x, int y) {
		return matrix[y][x];
	}
	
	public void loadMap(String file) {
		//TODO
	}

	public void checkExplosion(Explosion explosion) {
		char tile_type = matrix[explosion.getY()][explosion.getX()];
		
		if (tile_type == EXPLODABLE_TILE)
			matrix[explosion.getY()][explosion.getX()] = EMPTY_TILE;
		else if (tile_type == EXPLODABLE_POWER_UP_TILE) {
			matrix[explosion.getY()][explosion.getX()] = EMPTY_TILE;
			powerups.put(new Point(explosion.getX(), explosion.getY()), new PowerUp(this.game, explosion.getX(), explosion.getY()) {
				
				@Override
				public void update(double delta) {
					
				}
				
				@Override
				public void execute(Player player) {
					System.out.println("Power up pego");
				}
			});
		}
	}
}
