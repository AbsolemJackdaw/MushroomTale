package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.GamePanel;
import TileMap.Background;
import content.KeyHandler;

public class MenuState extends GameState {

	private Background bg;

	private int currentChoice = 0;
	private String[] options = { "Start", "Help", "Quit" };

	private Color titleColor;
	private Font titleFont;

	private Font font;

	public MenuState(GameStateManager gsm) {

		this.gsm = gsm;

		try {

			bg = new Background("/BackGround/bg.png", 1, false, 0);
			bg.setVector(-0.1, 0);

			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);

			font = new Font("Arial", Font.PLAIN, 12);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init() {
	}

	@Override
	public void update() {

		if (KeyHandler.isPressed(KeyHandler.ENTER))
			select();
		if (KeyHandler.isPressed(KeyHandler.UP)) {
			currentChoice--;
			if (currentChoice == -1)
				currentChoice = options.length - 1;
		}
		if (KeyHandler.isPressed(KeyHandler.DOWN)) {
			currentChoice++;
			if (currentChoice == options.length)
				currentChoice = 0;
		}
	}

	@Override
	public void draw(Graphics2D g) {

		// draw bg
		bg.draw(g);

		// draw title

		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Mushroom Tale", 60, GamePanel.HEIGHT / 3);

		Color clr = new Color(0x9d300f);
		// Draw menu square
		Rectangle rect = new Rectangle(GamePanel.WIDTH / 2 - 20,
				127 + currentChoice * 15, 35, 15);
		g.setColor(clr);
		g.draw(rect);

		// draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice)
				g.setColor(Color.BLACK);
			else
				g.setColor(Color.RED);
			g.drawString(options[i], 145, 140 + i * 15);
		}

	}

	private void select() {
		if (currentChoice == 0)
			gsm.setState(GameStateManager.LEVEL1STATE);
		if (currentChoice == 1) {
			// help
		}
		if (currentChoice == 2)
			System.exit(0);
	}

	public void keyPressed(int k) {

	}

	public void keyReleased(int k) {
	}

}
