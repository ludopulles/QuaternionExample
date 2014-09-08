package eu.ludiq.pws;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public abstract class GameWindow {

	public GameWindow() throws LWJGLException {
		DisplayMode displayMode = Display.getDesktopDisplayMode();
		Display.setDisplayMode(displayMode);
		Display.setFullscreen(true);
		Display.setVSyncEnabled(true);
		Display.create();

		init(displayMode.getWidth(), displayMode.getHeight());
	}

	public GameWindow(int width, int height) throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.create();

		init(width, height);
	}

	protected abstract void init(int width, int height);

	protected abstract void render();

	protected abstract void logic();

	public void run() throws LWJGLException {
		while (!Display.isCloseRequested()) {
			GL11.glClearColor(0f, 0f, 0f, 0f);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			render();

			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				break;
			}
			logic();

			Display.sync(60);
			Display.update();
		}
		Display.destroy();
	}
}
