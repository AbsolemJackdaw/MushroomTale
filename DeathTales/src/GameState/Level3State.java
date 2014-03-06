package GameState;

import java.awt.Point;

import Entity.enemies.Demon;
import Entity.enemies.Skull;
import Entity.objects.GameCards;
import Entity.objects.Hearts;
import Entity.objects.PowerUp;
import TileMap.Background;

public class Level3State extends WorldState {

	//populating the map
	private Point[] dms;
	private Point[] skulls;
	private Point[] hp;
	private Point[] pu;

	Demon demon;
	Skull skl;
	Hearts hrt;
	PowerUp stache;
	GameCards crd;

	public Level3State(GameStateManager gsm) {
		super(gsm);

		dms = new Point[] { new Point(200, 130), new Point(380,200), new Point(3300, 170),
				new Point(3500, 200), new Point(3700, 200), new Point(2400, 130), new Point(1930, 200),
				new Point (1100, 200), new Point(1000, 200), new Point(1700, 200), new Point(1800, 200)};

		skulls = new Point[] { new Point(2460, 50), new Point(2600, 200), new Point(2700, 500), new Point(2900, 500),
				new Point(3000, 500), new Point(4100, 70), new Point(1700, 100),
				new Point(1500, 200)};
		hp = new Point[] { new Point(1600, 400) };
		
		pu = new Point[] { new Point(2200, 300), new Point(1540, 400) };
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	protected void setPlayerPosition() {
		player.setPosition(60, 200);
		player.setWorld(this);
	}

	@Override
	protected void loadBackgrounds() {
		addBackGround(new Background("/BackGround/BG_Static.png", 0, true, 0));
		addBackGround(new Background("/BackGround/BG_3.png", 1, false, 10));
		addBackGround(new Background("/BackGround/BG_4.png", 3, false, 5));
	}

	@Override
	protected void loadWorldInfo() {
		loadWorld("/tiles/tiles.png", "/tiles/map_3.map");
	}

	@Override
	public void populateMap() {

		if (dms != null)
			for (int i = 0; i < dms.length; i++) {
				demon = new Demon(tileMap);
				demon.setPosition(dms[i].x, dms[i].y);
				enemies.add(demon);
			}

		if (skulls != null)
			for (int i = 0; i < skulls.length; i++) {
				skl = new Skull(tileMap);
				skl.setPosition(skulls[i].x, skulls[i].y);
				enemies.add(skl);
			}


		if (hp != null)
			for (int i = 0; i < hp.length; i++) {
				hrt = new Hearts(tileMap);
				hrt.setPosition(hp[i].x, hp[i].y);
				hearts.add(hrt);
			}

		if(pu != null)
			for(int i = 0; i < pu.length; i++){
				stache = new PowerUp(tileMap);
				stache.setPosition(pu[i].x, pu[i].y);
				exp.add(stache);
			}

		crd = new GameCards(tileMap, 2);
		crd.setPosition(200, 150);
		cards.add(crd);
	}

	@Override
	public void update() {
		super.update();

	}

}
