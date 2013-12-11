package client.objects;

import game_objects.GameObject;

public abstract class GameObjectGraphics implements Drawable {

	private GameObject object;
	
	public GameObjectGraphics(GameObject obj) {
		this.object = obj;
	}

	public GameObject getObject() {
		return object;
	}
}
