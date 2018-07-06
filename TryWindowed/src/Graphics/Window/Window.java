package Graphics.Window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

	public Window(int w, int h, String title, Game game) {
		game.setPreferredSize(new Dimension(w,h));
		game.setMinimumSize(new Dimension(w,h));
		game.setMaximumSize(new Dimension(w,h));
		
		JFrame jf = new JFrame(title);
		jf.add(game);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setVisible(true);
		
		game.start();
	}
}
