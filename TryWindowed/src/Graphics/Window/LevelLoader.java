package Graphics.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.LinkedList;

import GameObjects.MapFinal;
import GameObjects.Player;
import GameObjects.SimpleBlock;

public class LevelLoader {

	private static int CurrentChosen = 0;
	private LinkedList<String> levelPaths;

	public LevelLoader() {
		fillLevelPaths();
	}

	private void fillLevelPaths() {
		levelPaths = new LinkedList<String>();
		levelPaths.add("Levels\\TestMap3.txt");
		levelPaths.add("Levels\\TestMap3.txt");
		levelPaths.add("Levels\\TestMap3.txt");
	}

	@SuppressWarnings("finally")
	public Handler LoadLevel() {

		InputStream in = null;
		Reader r = null;
		Reader buffer = null;

		Handler Handler = new Handler();

		try {
			File f = new File(levelPaths.get(CurrentChosen));
			in = new FileInputStream(f);
			r = new InputStreamReader(in, Charset.defaultCharset());
			buffer = new BufferedReader(r);

			int objectFromMap, line = 0, column = 0;
			while ((objectFromMap = buffer.read()) != -1) {
				if (column == 55) {
					line++;
					column = 0;
				}
				column++;

				if (objectFromMap == 'a')
					Handler.AddObject(new SimpleBlock(column * 32, line * 32));
				if (objectFromMap == 'p')
					Handler.AddObject(new Player(column * 32, line * 32));
				if (objectFromMap == 'f')
					Handler.AddObject(new MapFinal(column * 32, line * 32));
			}
		} catch (NullPointerException e) {
			System.exit(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			try {
				buffer.close();
				r.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			CurrentChosen++;
			return Handler;
		}
	}
}
