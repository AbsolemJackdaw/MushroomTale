package GameState;

import java.awt.Point;

import Entity.enemies.Demon;
import Entity.objects.GameCards;
import Entity.objects.Hearts;
import Entity.objects.PowerUp;
import TileMap.Background;

public class Level1State extends WorldState {
	// populating the map
	private Point[] dms;
	private Point[] hp;
	private Point[] pu;
	private Point[] c;

	Demon demon;
	Hearts hrt;
	PowerUp stache;
	GameCards crd;

	public Level1State(GameStateManager gsm) {
		super(gsm);

		dms = new Point[] { new Point(880, 200), new Point(1700, 200),
				new Point(1800, 80) };
		hp = new Point[] { new Point(1420, 130) };

		//		pu = new Point[] { new Point(200, 200),new Point(250, 200),new Point(250, 200),};
		pu = new Point[] { new Point(1480 , 200) };

		c = new Point[] { new Point( 200, 200) } ;

	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	protected void setPlayerPosition() {
		player.setPosition(100, 150);
		player.setWorld(this);
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

		if(pu != null)
			for(int i = 0; i < pu.length; i++){
				stache = new PowerUp(tileMap);
				stache.setPosition(pu[i].x, pu[i].y);
				exp.add(stache);
			}

		if(c != null)
			for(int i = 0; i < c.length; i ++){
				crd = new GameCards(tileMap, 0);
				crd.setPosition(c[i].x, c[i].y);
				cards.add(crd);

			}
	}

	@Override
	public void update() {
		super.update();

	}
}
