package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
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
import Entity.player.Lore;
import Entity.player.Player;
import Entity.player.PlayerAttributes;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import content.KeyHandler;

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
	private boolean isDisplayingLore;
	private Lore gamestory;
	private BufferedImage guiLore;

	//	private boolean drawBoxes;

	public WorldState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {
		
		System.out.println(" init");
		
		gamestory = new Lore();
		loreToDisplay = new String[]{};
		explosion = new ArrayList<Explosion>();
		enemies = new ArrayList<Enemy>();
		hearts = new ArrayList<Hearts>();
		background = new ArrayList<Background>();
		exp = new ArrayList<PowerUp>();
		cards = new ArrayList<GameCards>();

		populateMap();

		//load background after background is initialized
		loadBackgrounds();

		tileMap = new TileMap(30);

		//load world after tilemap is set
		loadWorldInfo();

		//load player after world
		player = new Player(tileMap);
		PlayerAttributes.setPlayerAttributes(player);

		hud = new HUD(player);

		setPlayerPosition();


		try {
			guiLore = ImageIO.read(getClass().getResourceAsStream("/player/cardGui.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to load Lore Gui");
		}
	}

	protected void loadWorldInfo() {}
	protected void loadBackgrounds(){}

	/**WARNING
	 * Should only be used to set the player's position when spawning it into a map*/
	protected void setPlayerPosition(){}


	protected void loadWorld(String pathTiles, String pathWorld){

		tileMap.loadTiles(pathTiles);
		tileMap.loadMap(pathWorld);
		tileMap.setPosition(0, 0);
	}


	/**The order in which you add the backgrounds is important !
	 * they will be drawn in the same order as you register them.*/
	protected void addBackGround(Background bg) {
		background.add(bg);
	}

	/**All maps have different layouts.
	 * This method HAS TO BE Overwritten, or else you wont have any mobs.
	 * */
	public void populateMap() {
		System.out.println("Filled map");
	}

	public void update() {

		// check keys
		handleInput();

		//update player. requiered for all maps
		player.update();

		//popping up death screen
		if(player.isDead()){
			//			//			player.setDead(false);
			////			reset();
			////			player.setPosition(100, 200);
			//			gsm.setState(GameStateManager.MENUSTATE);
		}

		tileMap.setPosition(
				GamePanel.WIDTH/2 - player.getx(),
				GamePanel.HEIGHT/2 - player.gety()
				);

		for(int i = 0; i < background.size(); i++){
			Background b = background.get(i);

			if(!b.isStatic){
				b.setPosition(tileMap.getx()/b.speed, tileMap.gety()/b.speed);
			}
		}

		//pick up objects
		player.checkObjects(hearts, exp);

		//pickup and read a card
		//		player.readCard(cards);
		readCard();

		//attack ennemies
		player.checkAttack(enemies);


		// update hearts
		for(int i = 0; i < hearts.size(); i ++){
			Hearts hrt = hearts.get(i);
			hrt.update();
			if(hrt.isPickedUp()){
				hearts.remove(i);
				i--;
			}
		}

		//update exp
		for(int i = 0; i < exp.size(); i ++){
			PowerUp p = exp.get(i);
			p.update();
			if(p.isPickedUp()){
				exp.remove(i);
				i--;
			}
		}

		//update cards
		for(int i = 0; i < cards.size(); i ++){
			GameCards card = cards.get(i);
			card.update();
			if(card.isPickedUp()){
				cards.remove(i);
				i--;
			}
		}

		//updating all enemies
		for(int i = 0; i < enemies.size(); i++){

			Enemy e = enemies.get(i);
			e.update();
			//e.setWorld(this);
			if(e.isDead()){
				enemies.remove(i);
				i--;
				explosion.add(new Explosion(e.getx(), e.gety(), e));
			}
		}

		//update explosions
		for(int i = 0; i < explosion.size(); i++){

			Explosion e = explosion.get(i);
			e.update();
			if(e.shouldRemove()){
				explosion.remove(i);	
				dropLoot(e);
			}
		}
		
		System.out.println(enemies);
	}

	private void readCard() {

		for(GameCards c : cards){
			if(player.intersects(c)){
				c.pickUpObject();
				int k = c.getCardNumber();
				loreToDisplay = gamestory.getLore().get(k);
				isDisplayingLore = true;
			}
		}
	}

	private void dropLoot(Explosion e){
		int c = new Random().nextInt(99)+1; // +1 to prevent 0

		if(c  < e.getEnemy().getHeartChance()){
			for(int i = 0; i < e.getEnemy().getHeartAmount();i ++){
				Hearts hrt = new Hearts(tileMap);
				hearts.add(hrt);
				hrt.setPosition(e.getx()+(i*3), e.gety());
			}
		}

		if(c < e.getEnemy().getExpChance()){
			for(int i = 0; i < e.getEnemy().getExpAmount();i ++){
				PowerUp p = new PowerUp(tileMap);
				exp.add(p);
				p.setPosition(e.getx()+(i*3), e.gety());
			}			
		}
	}

	public void draw(Graphics2D g) {

		//background could be set somewhere else ...
		for(int i = 0; i < background.size(); i++){
			background.get(i).draw(g);
		}

		// clear screen
		//		g.setColor(Color.CYAN);
		//		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		// draw tilemap
		tileMap.draw(g);

		//draw player
		player.draw(g);

		//draw explosions
		for(int i = 0; i < explosion.size(); i++){
			explosion.get(i).setMapPosition((int)tileMap.getx(), (int)tileMap.gety());
			explosion.get(i).draw(g);
		}

		//draw visible enemies
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}

		//draw hearts
		for(int i = 0; i < hearts.size(); i++){
			hearts.get(i).draw(g);
		}

		//draw exp
		for(int i =0; i < exp.size(); i++){
			exp.get(i).draw(g);
		}

		//draw cards
		for(int i =0; i < cards.size(); i++){
			cards.get(i).draw(g);
		}

		hud.draw(g);

		if(loreToDisplay.length > 0){
			displayLoreGui(g);
		}
	}


	/**Display a gui when reading lore*/
	private void displayLoreGui(Graphics2D g) {
		g.drawImage(guiLore, 0, 0, null);

		for(int c = 0; c < loreToDisplay.length; c++){				
			//			g.setColor(Color.DARK_GRAY);
			//			g.setFont(new Font("Arial", Font.PLAIN, 10));
			//			g.drawString(loreToDisplay[c], 50 + 1, 45+10+(c*10)+1);

			g.setColor(new Color(0.2f , 0.2f , 0.3f));
			g.setFont(new Font("Arial", Font.PLAIN, 10));
			g.drawString(loreToDisplay[c], 50, 45+10+(c*10));

		}		
	}

	public void keyPressed(int k) {

		//		System.out.println(isDisplayingLore);
		if(isDisplayingLore == false){
			if(!player.isDead()){
				if(k == KeyEvent.VK_LEFT)
					player.setLeft(true);
				if(k == KeyEvent.VK_RIGHT)
					player.setRight(true);
				if(k == KeyEvent.VK_UP){		
					player.setUp(true);
					player.setJumping(true);
				}

				if(k == KeyEvent.VK_DOWN)
					player.setDown(true);
				if(k == KeyEvent.VK_SPACE)
					player.setAttacking();
			}
		}
	}


	public void handleInput() {
//		if(KeyHandler.isPressed(KeyHandler.ESCAPE)) gsm.setPaused(true);
		if( player.getHealth() == 0) return;
		player.setLeft(KeyHandler.keyState[KeyHandler.LEFT]);
		player.setRight(KeyHandler.keyState[KeyHandler.RIGHT]);
		player.setJumping(KeyHandler.keyState[KeyHandler.UP]);
		if(KeyHandler.isPressed(KeyHandler.SPACE)) player.setAttacking();
	}

	//	public void keyReleased(int k) {
	//
	//		if(k == KeyEvent.VK_LEFT)
	//			player.setLeft(false);
	//		if(k == KeyEvent.VK_RIGHT)
	//			player.setRight(false);
	//		if(k == KeyEvent.VK_UP)	{	
	//			player.setUp(false);
	//			player.setJumping(false);
	//		}
	//		if(k == KeyEvent.VK_DOWN)
	//			player.setDown(false);
	//
	//		if(k == KeyEvent.VK_SPACE && !isDisplayingLore)
	//			player.setAttacking();
	//
	//		if(k == KeyEvent.VK_ENTER){
	//			loreToDisplay = new String[]{};
	//			isDisplayingLore = false;
	//		}
	//
	//		//		if(k == KeyEvent.VK_B){
	//		//			drawBoxes = drawBoxes ? false : true;
	//		//		}
	//
	//		if(k == KeyEvent.VK_G){
	//			if(gsm.getCurrentState() < gsm.maxLevels)
	//				gsm.setState(gsm.getCurrentState()+1);
	//
	//			System.out.println("changed to " + gsm.getCurrentState());
	//		}
	//
	//		if(k == KeyEvent.VK_H)
	//			player.damagePlayer(1);
	//
	//		if(k == KeyEvent.VK_A)
	//			player.damagePlayer(-1);
	//
	//		if(k == KeyEvent.VK_E)
	//			System.out.println(player.getExp());
	//
	//		if(k == KeyEvent.VK_Y)
	//			player.setStaches(player.getExp() +1);
	//
	//		if(k == KeyEvent.VK_U)
	//			player.setExpStub(player.getExpStub() +1);
	//	}
}












