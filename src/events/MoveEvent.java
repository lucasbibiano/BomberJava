package events;

import game_objects.GameObject;

public class MoveEvent {
	private GameObject objMoved;
	
	private int lastX;
	private int lastY;

	public MoveEvent(int lastX, int lastY, GameObject objMoved) {
		this.objMoved = objMoved;
		this.lastX = lastX;
		this.lastY = lastY;
	}

	public GameObject getObjMoved() {
		return objMoved;
	}
	
	public int getLastX() {
		return lastX;
	}

	public int getLastY() {
		return lastY;
	}
}
