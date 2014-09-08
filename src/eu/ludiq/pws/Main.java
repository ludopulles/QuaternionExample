package eu.ludiq.pws;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;

import eu.ludiq.pws.world2d.GameWindow2D;
import eu.ludiq.pws.world3d.GameWindow3D;

public class Main {

	public static void main(String[] args) {
		boolean fullscreen = true;

		String message = "Do you want 2D or 3D space?";
		String title = "2D or 3D";
		String options[] = { "2D", "3D" };

		int choice = JOptionPane.showOptionDialog(null, message, title,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[options.length - 1]);
		try {
			GameWindow window;
			if (choice == 0) {
				if (fullscreen) {
					window = new GameWindow2D();
				} else {
					window = new GameWindow2D(840, 600);
				}
			} else {
				if (fullscreen) {
					window = new GameWindow3D();
				} else {
					window = new GameWindow3D(840, 600);
				}
			}
			window.run();
		} catch (Throwable t) {
			StringWriter writer = new StringWriter();
			t.printStackTrace(new PrintWriter(writer));
			JOptionPane.showMessageDialog(null, writer.toString());
		}
	}
}
