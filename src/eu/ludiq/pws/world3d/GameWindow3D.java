package eu.ludiq.pws.world3d;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import eu.ludiq.pws.GameWindow;
import eu.ludiq.pws.world3d.Cube.Axis;

public class GameWindow3D extends GameWindow {

	private static final double ROTATION_SPEED = Math.PI / 60;
	private Cube cube;

	public GameWindow3D() throws LWJGLException {
		super();
	}

	public GameWindow3D(int width, int height) throws LWJGLException {
		super(width, height);
	}

	@Override
	protected void init(int width, int height) {
		// init cube
		double size = 1d / Math.sqrt(2);
		this.cube = new Cube(new Point3D(0, 0, 0), size);

		// init opengl 3D
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(30f, ((float) width) / height, 0.001f, 100f);
		GLU.gluLookAt(3f, 3f, 3f, 0f, 0f, 0f, 0f, 1f, 0f);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		// GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
	}

	@Override
	protected void render() {
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		cube.draw();
	}

	/**
	 * 
	 * @param axis
	 *            the axis to rotate over
	 * @param keyPositive
	 *            the key which has to be pressed to rotate in the positive
	 *            direction
	 * @param keyNegative
	 *            the key which has to be pressed to rotate in the negative
	 *            direction
	 */
	private void processRotationOnAxis(Axis axis, int keyPositive,
			int keyNegative) {
		boolean isPositive = Keyboard.isKeyDown(keyPositive);
		boolean isNegative = Keyboard.isKeyDown(keyNegative);
		if (isPositive && !isNegative) {
			cube.addRotation(axis, ROTATION_SPEED);
		} else if (isNegative && !isPositive) {
			cube.addRotation(axis, -ROTATION_SPEED);
		}
	}

	@Override
	protected void logic() {
		processRotationOnAxis(Axis.X, Keyboard.KEY_S, Keyboard.KEY_W);
		processRotationOnAxis(Axis.Y, Keyboard.KEY_D, Keyboard.KEY_A);
		processRotationOnAxis(Axis.Z, Keyboard.KEY_Q, Keyboard.KEY_E);
	}
}
