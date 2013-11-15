package game_objects;

import java.util.ArrayList;

public class Map {
	private ArrayList<GameObject> objects;
	
	private int xLimit = 100;
	private int yLimit = 100;
	
	private GameObject[][] matrix;
	
	public Map() {
		matrix = new GameObject[100][100];
		objects = new ArrayList<GameObject>();
	}
	
	public void addObject(GameObject obj) {
		objects.add(obj);
		matrix[obj.getX()][obj.getY()] = obj;
	}
	
	public GameObject objAt(int x, int y) {
		if (!(x >= 0 && x < 100 && y >= 0 && y < 100))
			return null;
		
		return matrix[x][y];
	}
	
	public void moveObject(GameObject obj, int x, int y){
		matrix[obj.getX()][obj.getY()] = null;
		matrix[x][y] = obj;
	}
	
	public int getxLimit(){
		return xLimit;
	}
	
	public int getyLimit(){
		return yLimit;
	}
	
	public boolean isMovableSpace(int x, int y){
		return ((objAt(x, y) != null && objAt(x, y).isTrepassable()) || objAt(x, y) == null);
	}
}
