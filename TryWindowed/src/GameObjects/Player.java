package GameObjects;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import Framework.GameObject;
import Framework.ObjectID;

public class Player extends GameObject{
	
	private final static float MAX_SPD = 12;
	
	private float width = 32, height = 64;
	private BufferedImage[] PlayerTexture; //= new BufferedImage((int)width, (int)height, BufferedImage.TYPE_4BYTE_ABGR);
	
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	private float gravity = 0.7f;
	
	private boolean falling = true, jumping = false, crouch = false;

	public Player(float x, float y) {
		super(x, y, ObjectID.Player);
		try {
			PlayerTexture = new BufferedImage[2];
			PlayerTexture[0] = ImageIO.read(new File("Textures/TestBackGroundTexture.png"));
			PlayerTexture[1] = ImageIO.read(new File("Textures/PlayerCrouchTexture.png"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void tick(LinkedList<GameObject> objects) {
		x+=velX;
		y+=velY;
		if(falling||jumping) {
			velY+=gravity;
			if(velY >= MAX_SPD) {
				velY=MAX_SPD;
			}
		}
		Collision(objects);
	}

	@Override
	public void render(Graphics g) {
		
		if(!crouch)
			g.drawImage(PlayerTexture[0], (int)x, (int)y, (int)width, (int)height, null);
		else
			g.drawImage(PlayerTexture[1], (int)x, (int)y, (int)width, (int)height, null);
			
		
		/*g.setColor(Color.BLACK);
		g.fillRect((int) x+5, (int)y, (int) width-10, (int) height/2-position);
		g.setColor(Color.WHITE);
		g.drawRect((int)x+5, (int)(y+height/2), (int)width-10, (int)height/2);
		g.setColor(Color.RED);
		g.drawRect((int) (x+ width -5 ), (int)y+5, 5, (int) height-10);
		g.setColor(Color.BLUE);
		g.drawRect((int) x, (int)y+5, 5, (int) height-10);*/
	}

	
	private void Collision(LinkedList<GameObject> objects) {
		for(GameObject G: objects) {
			if(G.getObjID() == ObjectID.SimpleBlock) {
				// Top
				if(getBoundsTop().intersects(G.getBounds())) {
					y = G.getY()+33;
					velY = 0;
					if(!crouch)
						crouch = true;
					return;
				}
				// Down
				if(getBounds().intersects(G.getBounds())) {
					if (!crouch)
						y = G.getY() - height + 1;
					if (crouch)
						y = G.getY() - height - 1;
					velY = 0;
					falling = false;
					jumping = false;
					return;
				}
				// Right
				if(getBoundsRight().intersects(G.getBounds())) {
					x = G.getX() - width;
					return;
				}
				// Left
				if(getBoundsLeft().intersects(G.getBounds())) {
					x = G.getX()+width;
					return;
				}
				else{
					falling = true;
				}
			}
			if(G.getObjID() == ObjectID.Final) {
				if(getBoundsTop().intersects(G.getBounds())|| getBounds().intersects(G.getBounds()))
					((MapFinal)G).setReached(true);
			}
		}
	}

	public boolean isCrouch() {
		return crouch;
	}

	public void setCrouch(boolean crouch) {
		this.crouch = crouch;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x+5, (int)(y+height/2), (int)width-10, (int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x+5, (int)y, (int) width-10, (int) height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) (x+ width -5 ), (int)y+5, 5, (int) height-10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int)y+5, 5, (int) height-10);
	}
}
