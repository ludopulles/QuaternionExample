package eu.ludiq.pws.world2d;

import org.lwjgl.opengl.GL11;

import eu.ludiq.pws.Color;

public class Square {

	private ComplexNumber center, rotation;

	private ComplexNumber[] points;
	private Color[] colors = new Color[] { Color.RED, Color.GREEN, Color.BLUE,
			Color.WHITE };

	public Square(ComplexNumber center, double size) {
		this.center = center;
		this.rotation = new ComplexNumber();

		this.points = new ComplexNumber[] {
				new ComplexNumber(-size / 2, -size / 2),
				new ComplexNumber(size / 2, -size / 2),
				new ComplexNumber(size / 2, size / 2),
				new ComplexNumber(-size / 2, size / 2) };
	}

	public void addRotation(ComplexNumber rotation) {
		// commutative
		this.rotation = this.rotation.multiply(rotation);
	}

	public void draw() {
		GL11.glBegin(GL11.GL_QUADS);
		for (int i = 0; i < this.points.length; i++) {
			ComplexNumber z = this.points[i].multiply(this.rotation);
			z = z.add(this.center);

			this.colors[i].pick();
			z.draw();
		}
		GL11.glEnd();
	}
}
