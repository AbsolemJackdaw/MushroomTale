package GameState;

import java.awt.Graphics2D;

import Entity.objects.GameCards;
import TileMap.Background;
import content.Music;

public class Level5State extends WorldState {

	GameCards crd;

	public Level5State(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

	@Override
	protected void loadBackgrounds() {
		addBackGround(new Background("/BackGround/BG_END.png", 0, true, 0));

		Music.stop("mazemusic");
		Music.load("/Music/heartBeat.mp3", "HeartBeat");
		Music.loop("HeartBeat");

	}

	@Override
	protected void loadWorldInfo() {
		loadWorld("/tiles/tiles.png", "/tiles/map_5.map");
	}

	@Override
	public void populateMap() {
		crd = new GameCards(tileMap, 4);
		crd.setPosition(280, 200);
		cards.add(crd);
	}

	@Override
	protected void setPlayerPosition() {
		player.setPosition(330, 200);
		player.setWorld(this);
		player.setLeft(true);
	}

	@Override
	public void update() {
		super.update();
	}
}
