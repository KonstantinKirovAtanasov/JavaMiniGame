package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import Framework.GameObject;
import Framework.ObjectID;

public class SimpleBlock extends GameObject{
	
	private BufferedImage SimpleBlockTexture;

	public SimpleBlock(float x, float y) {
		super(x, y, ObjectID.SimpleBlock);
		try {
			SimpleBlockTexture = ImageIO.read(new File("Textures\\SimpleBlockTexture.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick(LinkedList<GameObject> obj) {
			
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(SimpleBlockTexture, (int)x,(int)y,32,32,null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}

}
