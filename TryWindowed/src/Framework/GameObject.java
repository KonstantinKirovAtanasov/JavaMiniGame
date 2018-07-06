package Framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected float velX = 0, velY = 0;
	protected ObjectID id;

	public GameObject(float x, float y, ObjectID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> obj);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	///////////////// Position ////////////////////////
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setX(float x) {
		this.x =x;
	}
	public void setY(float y) {
		this.y=y;
	}
	
	//////////////////// Velocity ///////////////////////
	public float getvelX() {
		return velX;
	}
	public float getvelY() {
		return velY;
	}
	public void setvelX(float x) {
		this.velX =x;
	}
	public void setvelY(float y) {
		this.velY=y;
	}
	
	//////////////////// ObjectID //////////////////////////
	
	public ObjectID getObjID() {
		return id;
	}
}
