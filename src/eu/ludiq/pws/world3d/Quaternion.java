package eu.ludiq.pws.world3d;

public class Quaternion {

	// Een quaternion bestaat uit 4 getallen: a, b, c, d
	// Het quaternion is dan dus gelijk aan: q = a + bi + cj + dk
	private double a, b, c, d;

	public Quaternion() {
		// Als je deze manier kiest van een Quaternion maken, wordt een
		// Quaternion,
		// die de rotatie van 0 graden voorstelt, gemaakt.
		this(1, 0, 0, 0);
	}

	public Quaternion(double a, double b, double c, double d) {
		// Om een quaternion te gebruiken, moet je een beginwaarde voor a, b, c
		// en d geven.
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	// De volgende acties zijn gedefinieerd:

	public Quaternion conjugate() {
		// Geeft de geconjugeerde terug
		return new Quaternion(a, -b, -c, -d);
	}

	public Quaternion multiply(Quaternion q) {
		// Geeft het product van dit quaternion en q terug
		double newA = a * q.a - b * q.b - c * q.c - d * q.d;
		double newB = a * q.b + b * q.a + c * q.d - d * q.c;
		double newC = a * q.c - b * q.d + c * q.a + d * q.b;
		double newD = a * q.d + b * q.c - c * q.b + d * q.a;
		return new Quaternion(newA, newB, newC, newD);
	}

	public Quaternion rotate(Quaternion rotation) {
		// Geeft het beeld terug nadat dit punt gedraaid is volgens rotation.
		// p' = q * p * q^-1
		return rotation.multiply(this).multiply(rotation.conjugate());
	}

	public Point3D toPoint() {
		// Dit is nodig, want bij het draaien van een punt, moet de gedraaide
		// quaternion terug naar een punt worden veranderd.
		return new Point3D(b, c, d);
	}

	public double length() {
		// Geeft de lengte van de Quaternion.
		// Dit wordt gedaan volgens de stelling van Pythagoras
		return Math.sqrt(a * a + b * b + c * c + d * d);
	}

	public void normalize() {
		// Schaalt de quaternion zo, dat de lengte weer precies 1 is.
		// Bij afrondingsfouten kan de lengte steeds verder afwijken van 1,
		// waardoor het roteren niet meer goed gaat.
		double len = length();
		a /= len;
		b /= len;
		c /= len;
		d /= len;
	}
}
