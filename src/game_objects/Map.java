package game_objects;

import core.Game;

import events.MoveEvent;

import behavior.MoveListener;

public class Map implements MoveListener {
	private int width = 20;
	private int height = 20;
	
	private boolean[][] matrix;
	
	private Game game;
	
	public Map(Game game) {
		matrix = new boolean[height][width];
		this.game = game;
		game.setMap(this);
		
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				matrix[j][i] = true; 
	}
	
	public void addObject(GameObject obj) {
		matrix[obj.getY()][obj.getX()] = obj.isTrepassable();
	}
	
	public void removeObject(GameObject obj) {
		GameObject last = objAt(obj.getX(), obj.getY());
		matrix[obj.getY()][obj.getX()] = last == null || last.isTrepassable();
	}
	
	public GameObject objAt(int x, int y) {
		for (GameObject obj: game.getObjects()) {
			if (obj.getX() == x && obj.getY() == y)
				return obj;
		}
		
		return null;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean isMovableSpace(int x, int y){
		return matrix[y][x];
	}
	
	public boolean isValid(int x, int y) {
		return (-1 < y && y < getHeight() && -1 < x && x < getWidth() && isMovableSpace(x, y));
	}

	@Override
	public void objectMoved(MoveEvent e) {
		int lastY = e.getLastY();
		int lastX = e.getLastX();
		GameObject objMoved = e.getObjMoved();
		
		matrix[lastY][lastX] = objAt(lastX, lastY) == null;
		matrix[objMoved.getY()][objMoved.getX()] = objMoved.isTrepassable();
	}
}
