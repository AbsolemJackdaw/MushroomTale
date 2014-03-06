package GameState;

import java.awt.Point;

import Entity.enemies.Demon;
import Entity.objects.GameCards;
import Entity.objects.Hearts;
import Entity.objects.PowerUp;
import TileMap.Background;
import content.Music;

public class Level1State extends WorldState {
	// populating the map
	private final Point[] dms;
	private final Point[] hp;
	private final Point[] pu;
	private final Point[] c;

	Demon demon;
	Hearts hrt;
	PowerUp stache;
	GameCards crd;

	public Level1State(GameStateManager gsm) {
		super(gsm);

		dms = new Point[] { new Point(880, 200), new Point(1700, 200),
				new Point(1800, 80) };
		hp = new Point[] { new Point(1420, 130) };

		// pu = new Point[] { new Point(200, 200),new Point(250, 200),new
		// Point(250, 200),};
		pu = new Point[] { new Point(1480, 200) };

		c = new Point[] { new Point(200, 200) };

		populateMap();
		Music.load("/Music/track_1.mp3", "BackgroundMusic");
		Music.loop("BackgroundMusic");

	}

	@Override
	protected void loadBackgrounds() {
		addBackGround(new Background("/BackGround/BG_Static.png", 0, true, 0));
		addBackGround(new Background("/BackGround/BG_3.png", 1, false, 10));
		addBackGround(new Background("/BackGround/BG_2.png", 3, false, 5));
	}

	@Override
	protected void loadWorldInfo() {
		loadWorld("/tiles/tiles.png", "/tiles/map_1.map");
	}

	@Override
	public void populateMap() {

		if (dms != null)
			for (final Point dm : dms) {
				demon = new Demon(tileMap);
				demon.setPosition(dm.x, dm.y);
				enemies.add(demon);
			}

		if (hp != null)
			for (final Point element : hp) {
				hrt = new Hearts(tileMap);
				hrt.setPosition(element.x, element.y);
				hearts.add(hrt);
			}

		if (pu != null)
			for (final Point element : pu) {
				stache = new PowerUp(tileMap);
				stache.setPosition(element.x, element.y);
				exp.add(stache);
			}

		if (c != null)
			for (final Point element : c) {
				crd = new GameCards(tileMap, 0);
				crd.setPosition(element.x, element.y);
				cards.add(crd);
			}

	}

	@Override
	protected void setPlayerPosition() {
		player.setPosition(100, 150);
		player.setWorld(this);
	}

	@Override
	public void update() {
		super.update();

	}
}
