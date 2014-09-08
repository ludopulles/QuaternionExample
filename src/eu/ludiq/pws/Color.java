package eu.ludiq.pws;

import org.lwjgl.opengl.GL11;

public class Color {

	public static final Color RED = new Color(1f, 0f, 0f);
	public static final Color GREEN = new Color(0f, 1f, 0f);
	public static final Color BLUE = new Color(0f, 0f, 1f);

	public static final Color YELLOW = new Color(1f, 1f, 0f);
	public static final Color CYAN = new Color(0f, 1f, 1f);
	public static final Color PURPLE = new Color(1f, 0f, 1f);

	public static final Color BLACK = new Color(0f, 0f, 0f);
	public static final Color GREY = new Color(0.5f, 0.5f, 0.5f);
	public static final Color WHITE = new Color(1f, 1f, 1f);

	public final float red, green, blue, alpha;

	public Color(float red, float green, float blue) {
		this(red, green, blue, 1.0f);
	}

	public Color(float red, float green, float blue, float alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	public Color negative() {
		return new Color(1 - this.red, 1 - this.green, 1 - this.blue,
				this.alpha);
	}

	public void pick() {
		GL11.glColor4f(this.red, this.green, this.blue, this.alpha);
	}
}
