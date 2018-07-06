package GameObjects;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Framework.GameObject;
import Framework.ObjectID;
import Graphics.Window.Handler;

public class KeyInput extends KeyAdapter {

	Handler handler;
	private int spaceClick = 0;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (GameObject G : handler.objects) {
			if (G.getObjID() == ObjectID.Player) {
				Player p = (Player) G;
				if (key == KeyEvent.VK_D)
					p.setvelX(5);
				if (key == KeyEvent.VK_A)
					p.setvelX(-5);
				if (key == KeyEvent.VK_C) {
					p.setCrouch(true);
					p.setHeight(32);
				}
				if (key == KeyEvent.VK_SPACE && !p.isJumping()) {
					if (spaceClick < 2) {
						spaceClick++;
						p.setvelY(-10);
					} else {
						spaceClick = 0;
						p.setJumping(true);
					}
				
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (GameObject G : handler.objects) {
			if (G.getObjID() == ObjectID.Player) {
				Player p = (Player) G;
				if (key == KeyEvent.VK_D)
					p.setvelX(0);
				if (key == KeyEvent.VK_A)
					p.setvelX(0);
				if (key == KeyEvent.VK_C && p.isCrouch()) {
					p.setCrouch(false);
					p.setHeight(64);
				}
			}
		}
	}
}
