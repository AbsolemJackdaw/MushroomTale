package GameState;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import TileMap.Background;
import content.Lore;

public class LevelEND extends WorldState {

	BufferedImage img_prpl;
	BufferedImage img_gr;

	float alpha = 1f;
	float alpha2 = 0.0f;

	float dY = 250f;

	public LevelEND(GameStateManager gsm) {
		super(gsm);

		try {
			img_prpl = ImageIO.read(getClass().getResourceAsStream(
					"/BackGround/BG_FILLED.png"));
			img_gr = ImageIO.read(getClass().getResourceAsStream(
					"/BackGround/BG_FILLED_GR.png"));

		} catch (final Exception e) {
			e.printStackTrace();
			System.out
			.println("Failed to load purple fade over background image ");
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// super.draw(g);

		System.out.println(alpha2);
		if(alpha2 == 1.0f){
			g.setComposite(AlphaComposite.SrcOver.derive(1.0f - alpha));
			g.drawImage(img_gr, 0, 0, img_gr.getWidth(), img_prpl.getHeight(), null);

			g.setComposite(AlphaComposite.SrcOver.derive(alpha));
			g.drawImage(img_prpl, 0, 0, img_prpl.getWidth(), img_prpl.getHeight(), null);
		}

		if(alpha2 <1.0f){
			g.setComposite(AlphaComposite.SrcOver.derive(alpha2));
			g.drawImage(img_prpl, 0, 0, img_prpl.getWidth(), img_prpl.getHeight(), null);
		}
		if (alpha == 0.0f) {

			g.setComposite(AlphaComposite.SrcOver.derive(1.0f));

			for (int c = 0; c < Lore.endCredits.length; c++) {

				g.setColor(new Color(0f, 0f, 0f));
				g.setFont(new Font("Arial", Font.PLAIN, 10));
				g.drawString(Lore.endCredits[c], 50, dY + (10 * c));

			}

			// System.out.println("writing");
		}
	}

	@Override
	protected void loadBackgrounds() {
		addBackGround(new Background("/BackGround/BG_END.png", 0, true, 0));
	}

	@Override
	protected void loadWorldInfo() {
		loadWorld("/tiles/tiles.png", "/tiles/map_stub.map");
	}

	@Override
	protected void setPlayerPosition() {
		player.setPosition(0, 200);
	}

	@Override
	public void update() {
		// super.update();

		if(alpha2 < 0.95f)
			alpha2 += 0.002f;
		else
			alpha2 = 1.0f;

		if (alpha > 0.05f)
			alpha -= 0.001f;
		else
			alpha = 0.0f;

		if (alpha == 0.0f)
			dY -= 0.25;

		System.out.println(dY);

		if (dY < -400.0f)
			System.exit(0);
	}

}
