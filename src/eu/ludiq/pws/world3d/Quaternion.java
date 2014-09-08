package eu.ludiq.pws.world3d;

public class Quaternion {

	private float a, b, c, d;

	public Quaternion() {
		this(1, 0, 0, 0);
	}

	public Quaternion(float a, float b, float c, float d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public Quaternion(Quaternion q) {
		this(q.a, q.b, q.c, q.d);
	}

	public Quaternion conjugate() {
		return new Quaternion(a, -b, -c, -d);
	}

	public Quaternion multiply(Quaternion q) {
		float newA = a * q.a - b * q.b - c * q.c - d * q.d;
		float newB = a * q.b + b * q.a + c * q.d - d * q.c;
		float newC = a * q.c - b * q.d + c * q.a + d * q.b;
		float newD = a * q.d + b * q.c - c * q.b + d * q.a;
		return new Quaternion(newA, newB, newC, newD);
	}

	public Quaternion rotate(Quaternion rotation) {
		return rotation.multiply(this).multiply(rotation.conjugate());
	}

	public Point3D toPoint() {
		return new Point3D(b, c, d);
	}

	public float lengthSquared() {
		return a * a + b * b + c * c + d * d;
	}

	public void normalize() {
		float lengthSquared = lengthSquared();
		float length = (float) Math.sqrt(lengthSquared);
		a /= length;
		b /= length;
		c /= length;
		d /= length;
	}

	@Override
	public String toString() {
		return "(" + a + ", " + b + ", " + c + ", " + d + ")";
	}
}
