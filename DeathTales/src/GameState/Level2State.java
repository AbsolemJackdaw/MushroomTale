package GameState;

import java.awt.Point;

import Entity.enemies.Demon;
import Entity.objects.GameCards;
import Entity.objects.Hearts;
import Entity.objects.PowerUp;
import TileMap.Background;

public class Level2State extends WorldState {

	// populating the map
	private Point[] dms;
	private Point[] hp;
	private Point[] pu;

	Demon demon;
	Hearts hrt;
	PowerUp stache;
	GameCards crd;

	public Level2State(GameStateManager gsm) {
		super(gsm);
		dms = new Point[] { new Point(830, 80),
				new Point(1100, 200), new Point(1850, 200),
				new Point(2000, 80), new Point(2000, 200),
				new Point(2100, 200), new Point(2700, 200),
				new Point(2800, 200), new Point(2900, 200) };

		hp = new Point[] { new Point(760, 50), new Point(1350, 170) };

		pu = new Point[] { new Point(1000, 50) };

	}

	@Override
	public void init() {
		super.init();

	}

	@Override
	protected void setPlayerPosition() {
		player.setPosition(45, 100);
		player.setWorld(this); // used to detect tiles to teleport

	}

	@Override
	protected void loadBackgrounds() {
		addBackGround(new Background("/BackGround/BG_Static.png", 0, true, 0));
		addBackGround(new Background("/BackGround/BG_3.png", 1, false, 10));
		addBackGround(new Background("/BackGround/BG_2.png", 3, false, 5));
	}

	@Override
	protected void loadWorldInfo() {
		loadWorld("/tiles/tiles.png", "/tiles/map_2.map");
	}

	@Override
	public void populateMap() {

		if (dms != null)
			for (int i = 0; i < dms.length; i++) {
				demon = new Demon(tileMap);
				demon.setPosition(dms[i].x, dms[i].y);
				enemies.add(demon);
			}

		if (hp != null)
			for (int i = 0; i < hp.length; i++) {
				hrt = new Hearts(tileMap);
				hrt.setPosition(hp[i].x, hp[i].y);
				hearts.add(hrt);
			}

		if (pu != null)
			for (int i = 0; i < pu.length; i++) {
				stache = new PowerUp(tileMap);
				stache.setPosition(pu[i].x, pu[i].y);
				exp.add(stache);
			}


		crd = new GameCards(tileMap, 1);
		crd.setPosition(200, 150);
		cards.add(crd);
	}
}
