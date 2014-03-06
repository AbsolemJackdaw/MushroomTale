package com.neet.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.neet.Handlers.Keys;
import com.neet.Main.GamePanel;

public class PauseState extends GameState {

	private final Font font;

	public PauseState(GameStateManager gsm) {

		super(gsm);

		// fonts
		font = new Font("Century Gothic", Font.PLAIN, 14);

	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Game Paused", 90, 90);
	}

	@Override
	public void handleInput() {
		if (Keys.isPressed(Keys.ESCAPE))
			gsm.setPaused(false);
		if (Keys.isPressed(Keys.BUTTON1)) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		handleInput();
	}

}
