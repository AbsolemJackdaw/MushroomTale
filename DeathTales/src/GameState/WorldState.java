package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import Entity.Enemy;
import Entity.enemies.Explosion;
import Entity.objects.GameCards;
import Entity.objects.Hearts;
import Entity.objects.PowerUp;
import Entity.player.HUD;
import Entity.player.Player;
import Entity.player.PlayerAttributes;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import content.KeyHandler;
import content.Lore;

public class WorldState extends GameState {

	protected TileMap tileMap;

	public HUD hud;
	protected Player player;

	protected ArrayList<Enemy> enemies;
	protected ArrayList<Explosion> explosion;
	public ArrayList<Hearts> hearts;
	public ArrayList<PowerUp> exp;
	public ArrayList<GameCards> cards;

	protected ArrayList<Background> background;

	private String[] loreToDisplay;
	public boolean isDisplayingLore;
	private Lore gamestory;
	private BufferedImage guiLore;

	private boolean startTimer = false;

	// private boolean drawBoxes;

	public WorldState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	/**
	 * The order in which you add the backgrounds is important ! they will be
	 * drawn in the same order as you register them.
	 */
	protected void addBackGround(Background bg) {
		background.add(bg);
	}

	/** Display a gui when reading lore */
	public void displayLoreGui(Graphics2D g) {
		g.drawImage(guiLore, 0, 0, null);

		for (int c = 0; c < loreToDisplay.length; c++) {

			g.setColor(new Color(0.2f, 0.2f, 0.3f));
			g.setFont(new Font("Arial", Font.PLAIN, 10));
			g.drawString(loreToDisplay[c], 50, 45 + 10 + (c * 10));

		}
	}

	@Override
	public void draw(Graphics2D g) {

		// background could be set somewhere else ...
		for (int i = 0; i < background.size(); i++)
			background.get(i).draw(g);

		// clear screen
		// g.setColor(Color.CYAN);
		// g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		// draw tilemap
		tileMap.draw(g);

		// draw player
		player.draw(g);
		// draw explosions
		for (int i = 0; i < explosion.size(); i++) {
			explosion.get(i).setMapPosition((int) tileMap.getx(),
					(int) tileMap.gety());
			explosion.get(i).draw(g);
		}

		// draw visible enemies
		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).draw(g);

		// draw hearts
		for (int i = 0; i < hearts.size(); i++)
			hearts.get(i).draw(g);

		// draw exp
		for (int i = 0; i < exp.size(); i++)
			exp.get(i).draw(g);

		// draw cards
		for (int i = 0; i < cards.size(); i++)
			cards.get(i).draw(g);

		hud.draw(g);

		if (isDisplayingLore)
			displayLoreGui(g);
	}

	private void dropLoot(Explosion e) {
		final int c = new Random().nextInt(99) + 1; // +1 to prevent 0

		if (c < e.getEnemy().getHeartChance())
			for (int i = 0; i < e.getEnemy().getHeartAmount(); i++) {
				final Hearts hrt = new Hearts(tileMap);
				hearts.add(hrt);
				hrt.setPosition(e.getx() + (i * 3), e.gety());
			}

		if (c < e.getEnemy().getExpChance())
			for (int i = 0; i < e.getEnemy().getExpAmount(); i++) {
				final PowerUp p = new PowerUp(tileMap);
				exp.add(p);
				p.setPosition(e.getx() + (i * 3), e.gety());
			}
	}

	public void handleInput() {
		// if(KeyHandler.isPressed(KeyHandler.ESCAPE)) gsm.setPaused(true);
		if (player.getHealth() == 0)
			return;
		player.setLeft(KeyHandler.keyState[KeyHandler.LEFT]);
		player.setRight(KeyHandler.keyState[KeyHandler.RIGHT]);
		player.setJumping(KeyHandler.keyState[KeyHandler.UP]);
		if (KeyHandler.isPressed(KeyHandler.SPACE))
			player.setAttacking();

		if (KeyHandler.isPressed(KeyHandler.G))
			gsm.setState(gsm.getCurrentState() + 1);

		if (KeyHandler.isPressed(KeyHandler.U)) {
			player.setExpStub(player.getExpStub() + 1);
			player.setStaches(player.getExp() + 1);
		}
	}

	@Override
	public void init() {

		System.out.println(" init");

		gamestory = new Lore();
		loreToDisplay = new String[] {};
		explosion = new ArrayList<Explosion>();
		enemies = new ArrayList<Enemy>();
		hearts = new ArrayList<Hearts>();
		background = new ArrayList<Background>();
		exp = new ArrayList<PowerUp>();
		cards = new ArrayList<GameCards>();

		// load background after background is initialized
		loadBackgrounds();

		tileMap = new TileMap(30);

		populateMap();

		// load world after tilemap is set
		loadWorldInfo();

		// load player after world
		player = new Player(tileMap);
		PlayerAttributes.setPlayerAttributes(player);

		hud = new HUD(player);

		setPlayerPosition();

		try {
			guiLore = ImageIO.read(getClass().getResourceAsStream(
					"/player/cardGui.png"));
		} catch (final IOException e) {
			e.printStackTrace();
			System.out.println("Failed to load Lore Gui");
		}
	}

	protected void loadBackgrounds() {
	}

	protected void loadWorld(String pathTiles, String pathWorld) {

		tileMap.loadTiles(pathTiles);
		tileMap.loadMap(pathWorld);
		tileMap.setPosition(0, 0);
	}

	protected void loadWorldInfo() {
	}

	/**
	 * All maps have different layouts. This method HAS TO BE Overwritten, or
	 * else you wont have any mobs.
	 * */
	public void populateMap() {
		System.out.println("Filled map");
	}

	private void readCard() {

		for (final GameCards c : cards)
			if (player.intersects(c)) {
				c.pickUpObject();
				final int k = c.getCardNumber();
				loreToDisplay = gamestory.getLore().get(k);
				isDisplayingLore = true;
			}
	}

	private void reInitKeys() {
		if (player.getHealth() == 0)
			return;
		player.setLeft(false);
		player.setRight(false);
		player.setJumping(false);
	}

	/**
	 * WARNING Should only be used to set the player's position when spawning it
	 * into a map
	 */
	protected void setPlayerPosition() {
	}

	@Override
	public void update() {

		// startTimer = true;

		// check keys
		if (!isDisplayingLore)
			handleInput();
		else if (KeyHandler.isPressed(KeyHandler.ENTER)) {
			isDisplayingLore = false;
			if (player.getWorld() instanceof Level5State)
				startTimer = true;

		} else
			reInitKeys();

		// update player. requiered for all maps
		player.update();

		// if(startTimer && !player.isDead()){
		// player.damagePlayer(1);
		// }

		// popping up death screen
		if (player.isDead())
			if (startTimer)
				gsm.setState(gsm.getCurrentState() + 1);
			else {
				init();
				// TODO needs a proper reset button
				gsm.setState(GameStateManager.DEATH);
			}

		tileMap.setPosition((GamePanel.WIDTH / 2) - player.getx(),
				(GamePanel.HEIGHT / 2) - player.gety());

		for (int i = 0; i < background.size(); i++) {
			final Background b = background.get(i);

			if (!b.isStatic)
				b.setPosition(tileMap.getx() / b.speed, tileMap.gety()
						/ b.speed);
		}

		// pick up objects
		player.checkObjects(hearts, exp);

		// pickup and read a card
		// player.readCard(cards);
		readCard();

		// attack ennemies
		player.checkAttack(enemies);

		// update hearts
		for (int i = 0; i < hearts.size(); i++) {
			final Hearts hrt = hearts.get(i);
			hrt.update();
			if (hrt.isPickedUp()) {
				hearts.remove(i);
				i--;
			}
		}

		// update exp
		for (int i = 0; i < exp.size(); i++) {
			final PowerUp p = exp.get(i);
			p.update();
			if (p.isPickedUp()) {
				exp.remove(i);
				i--;
			}
		}

		// update cards
		for (int i = 0; i < cards.size(); i++) {
			final GameCards card = cards.get(i);
			card.update();
			if (card.isPickedUp()) {
				cards.remove(i);
				i--;
			}
		}

		// updating all enemies
		for (int i = 0; i < enemies.size(); i++) {

			final Enemy e = enemies.get(i);
			e.update();
			if (e.isDead()) {
				enemies.remove(i);
				i--;
				explosion.add(new Explosion(e.getx(), e.gety(), e));
			}
		}

		// update explosions
		for (int i = 0; i < explosion.size(); i++) {

			final Explosion e = explosion.get(i);
			e.update();
			if (e.shouldRemove()) {
				explosion.remove(i);
				dropLoot(e);
			}
		}

		if (startTimer) {
			player.damagePlayer(1);

			System.out.println("");
		}

	}
}
