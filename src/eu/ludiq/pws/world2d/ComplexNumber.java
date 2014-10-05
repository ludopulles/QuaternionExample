package eu.ludiq.pws.world2d;

import org.lwjgl.opengl.GL11;

/**
 * A number is of the form a + b * i, where i * i = -1
 *
 */
public class ComplexNumber {

	private double a, b;

	public ComplexNumber() {
		this(1, 0);
	}

	public ComplexNumber(ComplexNumber z) {
		this(z.a, z.b);
	}

	public ComplexNumber(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public ComplexNumber add(ComplexNumber z) {
		double newA = a + z.a;
		double newB = b + z.b;
		return new ComplexNumber(newA, newB);
	}

	public ComplexNumber subtract(ComplexNumber z) {
		double newA = a - z.a;
		double newB = b - z.b;
		return new ComplexNumber(newA, newB);
	}

	public ComplexNumber multiply(ComplexNumber z) {
		double newA = a * z.a - b * z.b;
		double newB = a * z.b + b * z.a;
		return new ComplexNumber(newA, newB);
	}

	public static ComplexNumber fromPolar(double r, double theta) {
		double a = r * Math.cos(theta);
		double b = r * Math.sin(theta);
		return new ComplexNumber(a, b);
	}

	public void draw() {
		GL11.glVertex2d(a, b);
	}
}
