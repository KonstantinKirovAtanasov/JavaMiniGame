package Graphics.Window;

import GameObjects.Player;

public class Camera {

	private float x, y;

	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public void tick(Player p) {
		x = - p.getX() + Game.WIDTH/2;
		y = - p.getY() + Game.HEIGHT/2;
	}


	public float getX() {
		return x;
	} 

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
