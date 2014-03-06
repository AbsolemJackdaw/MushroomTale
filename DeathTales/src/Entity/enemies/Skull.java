package Entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Entity.Animation;
import Entity.Enemy;
import TileMap.TileMap;

public class Skull extends Enemy {

	private BufferedImage[] sprites;

	public Skull(TileMap tm) {
		super(tm);

		health = 5;
		maxSpeed = 0.9;
		moveSpeed = 0.5;
		fallSpeed = 0.2;
		maxFallSpeed = 1.0;

		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;

		isFlightEnabled = true;

		// load sprites
		try {

			BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/enemy/enemies.png"));

			sprites = new BufferedImage[8];

			for (int i = 0; i < sprites.length; i++)
				sprites[i] = spritesheet.getSubimage(i * width, 30, width,
						height);
		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(25);

		right = true;
		facingRight = false;
	}

	@Override
	public int getDamageAmount() {
		return 2;
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

	@Override
	public int getHeartChance() {
		return 40;
	}

	@Override
	public int getExpChance() {
		return 50;
	}
	
	@Override
	public int getExpAmount() {
		return 2;
	}
}
