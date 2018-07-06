package Graphics.Window;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import Framework.GameObject;
import Framework.ObjectID;
import GameObjects.KeyInput;
import GameObjects.MapFinal;
import GameObjects.Player;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static int WIDTH, HEIGHT;

	private boolean isRunning = false;
	private Thread thread;

	private Handler Handler;
	private Camera Camera;

	private void init() throws IOException {
		WIDTH = getWidth();
		HEIGHT = getHeight();

		this.Handler = new LevelLoader().LoadLevel();

		Camera = new Camera(0f, 0f);
		this.addKeyListener(new KeyInput(Handler));
	}

	public synchronized void start() {
		if (isRunning)
			return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();

	}

	@Override
	public void run() {
		try {
			init();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = Integer.MAX_VALUE / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(frames);
				System.out.println(updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick() {
		Handler.tick();
		for (GameObject G : Handler.objects) {
			if (G.getObjID() == ObjectID.Player)
				Camera.tick((Player) G);
			if (G.getObjID() == ObjectID.Final && ((MapFinal) G).isReached()) {
				Handler = new LevelLoader().LoadLevel();
				this.addKeyListener(new KeyInput(Handler));
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics gs = bs.getDrawGraphics();
		Graphics2D G2D = (Graphics2D) gs;
		///////////////////////////////////
		// gs.setColor(Color.BLACK);
		gs.fillRect(0, 0, getWidth(), getHeight());

		G2D.translate(Camera.getX(), Camera.getY());

		Handler.render(gs);

		G2D.translate(-Camera.getX(), -Camera.getY());

		///////////////////////////////////
		gs.dispose();
		bs.show();
	}

	public static void main(String args[]) {
		new Window(1560, 800, "Title", new Game());
	}
}
