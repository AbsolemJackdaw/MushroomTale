package com.neet.Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.neet.GameState.GameStateManager;
import com.neet.Handlers.Keys;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

	// dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;

	// game thread
	private Thread thread;
	private boolean running;
	private final int FPS = 60;
	private final long targetTime = 1000 / FPS;

	// image
	private BufferedImage image;
	private Graphics2D g;

	// game state manager
	private GameStateManager gsm;

	// other
	private boolean recording = false;
	private int recordingCount = 0;
	private boolean screenshot;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	private void draw() {
		gsm.draw(g);
	}

	private void drawToScreen() {
		final Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
		if (screenshot) {
			screenshot = false;
			try {
				final java.io.File out = new java.io.File("screenshot "
						+ System.nanoTime() + ".gif");
				javax.imageio.ImageIO.write(image, "gif", out);
			} catch (final Exception e) {
			}
		}
		if (!recording)
			return;
		try {
			final java.io.File out = new java.io.File("C:\\out\\frame"
					+ recordingCount + ".gif");
			javax.imageio.ImageIO.write(image, "gif", out);
			recordingCount++;
		} catch (final Exception e) {
		}
	}

	private void init() {

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		/*
		 * g.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING,
		 * RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
		 */

		running = true;

		gsm = new GameStateManager();

	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.isControlDown()) {
			if (key.getKeyCode() == KeyEvent.VK_R) {
				recording = !recording;
				return;
			}
			if (key.getKeyCode() == KeyEvent.VK_S) {
				screenshot = true;
				return;
			}
		}
		Keys.keySet(key.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent key) {
	}

	@Override
	public void run() {
		init();

		long start;
		long elapsed;
		long wait;

		// game loop
		while (running) {

			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;

			wait = targetTime - (elapsed / 1000000);
			if (wait < 0)
				wait = 5;

			try {
				Thread.sleep(wait);
			} catch (final Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void update() {
		gsm.update();
		Keys.update();
	}

}