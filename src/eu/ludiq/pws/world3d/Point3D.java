package eu.ludiq.pws.world3d;

import org.lwjgl.opengl.GL11;

public class Point3D {

	// een punt bestaat uit 3 getallen: x, y en z.
	private double x, y, z;

	public Point3D(double x, double y, double z) {
		// Om een punt te gebruiken, moet je een beginwaarde voor x, y en z
		// geven.
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// De volgende acties zijn gedefinieerd:

	public Point3D add(Point3D p) {
		// Dit geeft een punt terug met waarde ('dit punt' + 'p')
		return new Point3D(x + p.x, y + p.y, z + p.z);
	}

	public Point3D subtract(Point3D p) {
		// Dit geeft een punt terug met waarde ('dit punt' - 'p')
		return new Point3D(x - p.x, y - p.y, z - p.z);
	}

	public Quaternion toQuaternion() {
		// Dit is nodig, want bij het draaien van een punt, moet het een
		// quaternion zijn.
		return new Quaternion(0, x, y, z);
	}

	public Quaternion createQuaternion(double theta) {
		// Maakt een quaternion, met dit punt als de as, en theta als de hoek
		double cos = Math.cos(theta / 2);
		double sin = Math.sin(theta / 2);
		return new Quaternion(cos, sin * x, sin * y, sin * z);
	}

	public void draw() {
		// tekent het punt op het scherm
		GL11.glVertex3d(x, y, z);
	}
}
