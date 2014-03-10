package GameState;

import java.awt.Graphics2D;
import java.awt.Point;

import Entity.enemies.Demon;
import Entity.objects.GameCards;
import Entity.objects.Hearts;
import Entity.objects.PowerUp;
import TileMap.Background;

public class Level2State extends WorldState {

	// populating the map
	private final Point[] dms;
	private final Point[] hp;
	private final Point[] pu;
	private boolean check = false;

	Demon demon;
	Hearts hrt;
	PowerUp stache;
	GameCards crd;

	public Level2State(GameStateManager gsm) {
		super(gsm);

		dms = new Point[] { new Point(830, 80), new Point(1100, 200),
				new Point(1850, 200), new Point(2000, 80),
				new Point(2000, 200), new Point(2100, 200),
				new Point(2700, 200), new Point(2800, 200),
				new Point(2900, 200) };

		hp = new Point[] { new Point(760, 50), new Point(1350, 170) };

		pu = new Point[] { new Point(1000, 50) };
		check = true;
		populateMap();
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
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
		if (check) {
			crd = new GameCards(tileMap, 1);
			crd.setPosition(200, 150);
			cards.add(crd);
		}
	}

	@Override
	protected void setPlayerPosition() {
		player.setPosition(45, 100);
		player.setWorld(this); // used to detect tiles to teleport

	}

	@Override
	public void update() {
		super.update();
	}
}
