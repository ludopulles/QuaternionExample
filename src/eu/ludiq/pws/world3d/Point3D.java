package eu.ludiq.pws.world3d;

import org.lwjgl.opengl.GL11;

public class Point3D {

	private float x, y, z;

	public Point3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point3D add(Point3D p) {
		return new Point3D(this.x + p.x, this.y + p.y, this.z + p.z);
	}

	public Point3D subtract(Point3D p) {
		return new Point3D(this.x - p.x, this.y - p.y, this.z - p.z);
	}

	public Quaternion toQuaternion() {
		return new Quaternion(0, x, y, z);
	}

	public Quaternion createQuaternion(float theta) {
		float cos = (float) Math.cos(theta);
		float sin = (float) Math.sin(theta);
		return new Quaternion(cos, sin * x, sin * y, sin * z);
	}

	public void draw() {
		GL11.glVertex3f(this.x, this.y, this.z);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
