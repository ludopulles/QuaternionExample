package eu.ludiq.pws.world3d;

import org.lwjgl.opengl.GL11;

import eu.ludiq.pws.Color;

public class Cube {

	public enum Axis {
		X(0), Y(1), Z(2);

		private int index;

		private Axis(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}

	private Point3D center;
	private Quaternion rotation;

	private Point3D[] points;
	private Color[] faceColors;
	private int[][] faces = new int[][] { // 6 faces
	{ 0, 1, 2, 3 }, // bottom
			{ 0, 1, 5, 4 }, // front
			{ 1, 2, 6, 5 }, // right
			{ 2, 3, 7, 6 }, // back
			{ 3, 0, 4, 7 }, // left
			{ 4, 5, 6, 7 } // top
	};

	private Point3D[] rotationAxes;
	private Color[] rotationColors;

	public Cube(Point3D center, double size) {
		this.center = center;
		this.rotation = new Quaternion();

		this.points = new Point3D[] {
				new Point3D(-size / 2, -size / 2, -size / 2),
				new Point3D(+size / 2, -size / 2, -size / 2),
				new Point3D(+size / 2, +size / 2, -size / 2),
				new Point3D(-size / 2, +size / 2, -size / 2),
				new Point3D(-size / 2, -size / 2, +size / 2),
				new Point3D(+size / 2, -size / 2, +size / 2),
				new Point3D(+size / 2, +size / 2, +size / 2),
				new Point3D(-size / 2, +size / 2, +size / 2) };

		this.faceColors = new Color[] { Color.YELLOW, Color.PURPLE, Color.RED,
				Color.GREEN, Color.CYAN, Color.BLUE };

		this.rotationAxes = new Point3D[] { new Point3D(1, 0, 0),
				new Point3D(0, 1, 0), new Point3D(0, 0, 1) };
		this.rotationColors = new Color[] { Color.CYAN, Color.PURPLE,
				Color.YELLOW };
	}

	public void addRotation(Axis axis, double rotationSpeed) {
		Point3D rotationAxis = this.rotationAxes[axis.getIndex()];
		rotationAxis = rotationAxis.toQuaternion().rotate(this.rotation)
				.toPoint();
		Quaternion rotation2 = rotationAxis.createQuaternion(rotationSpeed);

		// q'=  q_2 * q_1
		// in which q' corresponds to the rotation q_1 followed by the rotation q_2.
		this.rotation = rotation2.multiply(this.rotation);
		
		// length must be one
		this.rotation.normalize();
	}

	public void draw() {
		// draw rotation axes
		GL11.glBegin(GL11.GL_LINES);
		for (int i = 0; i < this.rotationAxes.length; i++) {
			Point3D axis = rotationAxes[i].toQuaternion().rotate(this.rotation)
					.toPoint();

			this.rotationColors[i].pick();
			this.center.draw();
			this.center.add(axis).draw();

			this.rotationColors[i].negative().pick();
			this.center.draw();
			this.center.subtract(axis).draw();
		}
		GL11.glEnd();

		// draw cube
		GL11.glBegin(GL11.GL_QUADS);
		for (int i = 0; i < this.faces.length; i++) {
			this.faceColors[i].pick();
			for (int j = 0; j < this.faces[i].length; j++) {
				Point3D p = this.points[this.faces[i][j]];
				Quaternion h = p.toQuaternion();

				// h' = q * h * q^-1
				h = h.rotate(this.rotation);

				p = h.toPoint().add(center);
				
				p.draw();
			}
		}
		GL11.glEnd();
	}
}
