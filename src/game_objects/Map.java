package game_objects;

import java.util.ArrayList;

public class Map {
	private ArrayList<GameObject> objects;
	
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
}
