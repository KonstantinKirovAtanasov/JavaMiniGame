package Graphics.Window;

import java.awt.Graphics;
import java.util.LinkedList;

import Framework.GameObject;

public class Handler {

	public LinkedList<GameObject> objects = new LinkedList<GameObject>();

	public void tick() {
		for (GameObject gObj : objects) {
			gObj.tick(objects);
		}
	}

	public void render(Graphics g) {
		for (GameObject gObj : objects) {
			gObj.render(g);
		}
	}

	public void AddObject(GameObject go) {
		this.objects.add(go);
	}

	public void RemoveObject(GameObject go) {
		this.objects.remove(go);
	}
}
