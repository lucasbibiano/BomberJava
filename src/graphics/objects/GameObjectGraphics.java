package graphics.objects;

import game_objects.GameObject;

import java.awt.Graphics;

public class GameObjectGraphics<T> implements Drawable {

	private T object;
	
	public GameObjectGraphics(T obj) {
		this.object = obj;
	}
	
	@Override
	public void draw(Graphics g) {
	}

	public T getObject() {
		return object;
	}
}
