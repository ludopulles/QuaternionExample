package eu.ludiq.pws.world2d;

import org.lwjgl.opengl.GL11;

/**
 * A number is of the form a + b * i, where i * i = -1
 *
 */
public class ComplexNumber {

	private float a, b;

	public ComplexNumber() {
		this(1, 0);
	}

	public ComplexNumber(ComplexNumber z) {
		this(z.a, z.b);
	}

	public ComplexNumber(float a, float b) {
		this.a = a;
		this.b = b;
	}

	public ComplexNumber add(ComplexNumber z) {
		float newA = this.a + z.a;
		float newB = this.b + z.b;
		return new ComplexNumber(newA, newB);
	}

	public ComplexNumber subtract(ComplexNumber z) {
		float newA = this.a - z.a;
		float newB = this.b - z.b;
		return new ComplexNumber(newA, newB);
	}

	public ComplexNumber multiply(ComplexNumber z) {
		float newA = this.a * z.a - this.b * z.b;
		float newB = this.a * z.b + this.b * z.a;
		return new ComplexNumber(newA, newB);
	}

	public void draw() {
		GL11.glVertex2f(this.a, this.b);
	}

	public static ComplexNumber fromPolar(float r, float theta) {
		float a = (float) (r * Math.cos(theta));
		float b = (float) (r * Math.sin(theta));
		return new ComplexNumber(a, b);
	}
}
