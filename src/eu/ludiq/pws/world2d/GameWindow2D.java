package eu.ludiq.pws.world2d;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import eu.ludiq.pws.GameWindow;

public class GameWindow2D extends GameWindow {

	private static final float ROTATION_SPEED = (float) (Math.PI / 60);

	private Square square;

	public GameWindow2D() throws LWJGLException {
		super();
	}

	public GameWindow2D(int width, int height) throws LWJGLException {
		super(width, height);
	}

	@Override
	protected void init(int width, int height) {
		// init square
		ComplexNumber squareCenter = new ComplexNumber(width / 2, height / 2);
		float squareSize = Math.min(width, height) / 2;
		this.square = new Square(squareCenter, squareSize);

		// init opengl
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	@Override
	protected void render() {
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		square.draw();
	}

	@Override
	protected void logic() {
		boolean isLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
		boolean isRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
		if (isLeft && !isRight) {
			square.addRotation(ComplexNumber.fromPolar(1, ROTATION_SPEED));
		} else if (isRight && !isLeft) {
			square.addRotation(ComplexNumber.fromPolar(1, -ROTATION_SPEED));
		}
	}
}
