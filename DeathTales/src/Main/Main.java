package Main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		final JFrame window = new JFrame("Death's Tale");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);

	}

}
