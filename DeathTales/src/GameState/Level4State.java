package GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Entity.objects.GameCards;
import TileMap.Background;
import content.Music;

public class Level4State extends WorldState {

	GameCards crd;
	public static BufferedImage ThirdEyeBlind;

	public Level4State(GameStateManager gsm) {
		super(gsm);

		try {
			ThirdEyeBlind = ImageIO.read(getClass().getResourceAsStream(
					"/BackGround/ThirdEyeBlind.png"));
		} catch (final Exception e) {
			// TODO: handle exception
		}
		Music.stop("musicLvL3");
		Music.stop("BackgroundMusic");
		Music.load("/Music/track_3.mp3", "mazemusic");
		Music.loop("mazemusic");

	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

		g.drawImage(ThirdEyeBlind, 0, 0, 320, 240, null);

		if (isDisplayingLore)
			displayLoreGui(g);

		hud.draw(g);

	}

	@Override
	protected void loadBackgrounds() {
		addBackGround(new Background("/BackGround/BG_5.png", 0, true, 0));
	}

	@Override
	protected void loadWorldInfo() {
		loadWorld("/tiles/tiles.png", "/tiles/map_4.map");
	}

	@Override
	public void populateMap() {
		crd = new GameCards(tileMap, 3);
		crd.setPosition(1500, 90);
		cards.add(crd);
	}

	@Override
	protected void setPlayerPosition() {
		player.setPosition(1550, 70);
		player.setWorld(this);
	}

	@Override
	public void update() {
		super.update();
	}
}
