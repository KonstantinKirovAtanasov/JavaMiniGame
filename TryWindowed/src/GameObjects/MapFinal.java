package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import Framework.GameObject;
import Framework.ObjectID;

public class MapFinal extends GameObject{

	private boolean reached = false;
	
	public MapFinal(float x, float y) {
		super(x, y, ObjectID.Final);
	}

	@Override
	public void tick(LinkedList<GameObject> obj) {
		for(GameObject G:obj) {
			if(G.getObjID() == ObjectID.Final) {
				/// change the level of the game
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16, 16);
	}	
	public boolean isReached() {
		return reached;
	}

	public void setReached(boolean reached) {
		this.reached = reached;
	}

}
