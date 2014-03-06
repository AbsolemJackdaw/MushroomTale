package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import TileMap.Background;
import content.KeyHandler;

public class DeadScreen extends GameState {

	private final Background bg;

	private final Color clr = new Color(0.5f, 0, 0);
	private final String endScreenTitle = "You Could Not Save The Forest ... ";
	private final Font fnt = new Font("Century Gothic", Font.PLAIN, 20);

	public DeadScreen(GameStateManager gsm) {

		this.gsm = gsm;

		bg = new Background("/BackGround/death.png", 1, true, 0);

	}

	@Override
	public void draw(Graphics2D g) {

		bg.draw(g);

		g.setColor(Color.DARK_GRAY);
		g.setFont(fnt);
		g.drawString(endScreenTitle, 14, 54);

		g.setColor(Color.BLACK);
		g.setFont(fnt);
		g.drawString(endScreenTitle, 12, 52);

		g.setColor(clr);
		g.setFont(fnt);
		g.drawString(endScreenTitle, 10, 50);

	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		if (KeyHandler.isPressed(KeyHandler.DOWN))
			gsm.setState(GameStateManager.MENUSTATE);

		bg.update();
	}
}
