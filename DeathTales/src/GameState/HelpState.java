package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Main.GamePanel;
import content.KeyHandler;

public class HelpState extends GameState {

	Font f = new Font("Century Gothic", Font.PLAIN, 13);

	public HelpState(GameStateManager gsm) {
		super();

		this.gsm = gsm;
	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		g.setColor(Color.WHITE);
		g.setFont(f);
		g.drawString("press down to exit to title", 160, 230);
		g.drawString("Arrow Keys to move", 10, 20);
		g.drawString("Space to shoot", 10, 40);
		g.drawString("Collect Shrooms to level up.", 10, 60);
		g.drawString("Each level you will gain speed and jump-height, ", 10, 80);
		g.drawString("as well as health.", 10, 100);

	}

	@Override
	public void init() {
		System.out.println("init");
	}

	@Override
	public void update() {
		if (KeyHandler.isPressed(KeyHandler.DOWN))
			gsm.setState(GameStateManager.MENUSTATE);
	}

}
