package Entity.player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entity.Animation;
import Entity.Enemy;
import Entity.FumeBall;
import Entity.MapObject;
import Entity.objects.Hearts;
import Entity.objects.PowerUp;
import TileMap.TileMap;

public class Player extends MapObject {

	private int health;
	private int maxHealth;
	// private int fire; this is from tutorial

	private int moustaches; // collectable pink moustaches. it's like exp. used
	// to grow up the player
	private int levels; // gained trough collecting moustaches
	public int levelUps[] = { 2, 5, 10, 17, 28, 35, 49, 60, 75 }; // moustaches
	// needed to
	// level up.
	// you start
	// with lvl
	// 1

	private int expStub;

	private final ArrayList<FumeBall> fumeBalls;

	private boolean attacking;
	private int attackDamage;
	// private int attackRange;

	private boolean dead = false;

	private boolean flinching;
	private long flinchTimer;

	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
			// for every animation, add the number of frames here
			// sample : 2,5,8,4 (2 for idle 0, 5 for walking 1, etc.
			30, 4, 2, 2, 4 };

	// animation action
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int ATTACKING = 4;

	public Player(TileMap tm) {
		super(tm);

		width = 30;
		height = 30;

		cwidth = 20;
		cheight = 20;

		moveSpeed = 0.3; // inital walking speed. you speed up as you walk
		maxSpeed = 1.0; // change to jump farther and walk faster
		stopSpeed = 0.4;
		fallSpeed = 0.15; // affects falling and jumping
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;

		facingRight = true;

		levels = 0;
		moustaches = 0;
		maxHealth = levels + 3;
		health = maxHealth;

		fumeBalls = new ArrayList<FumeBall>();

		// load sprites
		try {

			final BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/player/playerSheet.png"));

			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < numFrames.length; i++) {

				final BufferedImage[] bi = new BufferedImage[numFrames[i]];

				for (int j = 0; j < numFrames[i]; j++)
					bi[j] = spritesheet.getSubimage(j * width, i * height,
							width, height);
				sprites.add(bi);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(100);
	}

	public void checkAttack(ArrayList<Enemy> enemies) {

		for (int j = 0; j < enemies.size(); j++) {
			final Enemy e = enemies.get(j);

			for (int i = 0; i < fumeBalls.size(); i++)
				if (fumeBalls.get(i).intersects(e)) {
					e.hit(attackDamage);
					fumeBalls.get(i).setHit();
					break;
				}

			if (intersects(e))
				damagePlayer(e.getDamageAmount());
		}
	}

	public void checkObjects(ArrayList<Hearts> hearts, ArrayList<PowerUp> pu) {

		for (int i = 0; i < hearts.size(); i++) {
			final Hearts hrt = hearts.get(i);

			if (intersects(hrt) && (health < maxHealth)) {
				hrt.pickUpObject();
				health += 1;
			}
		}

		for (int i = 0; i < pu.size(); i++) {
			final PowerUp p = pu.get(i);

			if (intersects(p)) {
				p.pickUpObject();
				moustaches += 1;
				expStub++;
			}
		}
	}

	@Override
	public void checkTileMapCollision() {

		super.checkTileMapCollision();

		if ((currCol < tileMap.getNumCols())
				&& (currRow < tileMap.getNumRows()))
			if (tileMap.isDangerTile(currCol, currRow) && !flinching)
				tileHurtsPlayer = true;
		// System.out.println("auw");

		if ((currCol < tileMap.getNumCols())
				&& (currRow < tileMap.getNumRows()))
			if (tileMap.canTeleport(getWorld(), currCol, currRow))
				getWorld().gsm.setState(getWorld().gsm.getCurrentState() + 1);
	}

	public void damagePlayer(int damage) {
		if (flinching)
			return;
		health -= damage;
		if (health < 0)
			health = 0;
		if (health == 0)
			dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}

	@Override
	public void draw(Graphics2D g) {

		// draw player
		if (flinching) {
			final long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (((elapsed / 100) % 2) == 0)
				return;
		}

		for (int i = 0; i < fumeBalls.size(); i++)
			fumeBalls.get(i).draw(g);

		super.draw(g);
	}

	public int expLeftOver() {
		int k = 0;

		if (levels == 0)
			k = 2;
		else
			k = levelUps[getLvl()] - levelUps[getLvl() - 1];

		return k;
	}

	public int expToNextLevel() {

		int c = 0;
		for (int i = 0; i < levelUps.length; i++) {

			c = levelUps[getLvl()] - moustaches;

			if ((i + 1) > getLvl())
				break;
		}

		return c;
	}

	/** Updates level, jump and speed */
	private void gainLevel() {

		if (levels < levelUps.length)
			if (expStub >= expLeftOver()) {
				moveSpeed += 0.9f;
				maxSpeed += 0.11f;
				fallSpeed -= 0.015;
				expStub = expStub - expLeftOver();
				maxHealth++;
				levels++;
			}
	}

	public int getExp() {
		return moustaches;
	}

	public int getExpStub() {
		return expStub;
	}

	public int getHealth() {
		return health;
	}

	public int getLvl() {
		return levels;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public void getNextPosition() {

		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed)
				dx = -maxSpeed;
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed)
				dx = maxSpeed;
		} else if (dx > 0) {
			dx -= stopSpeed;
			if (dx < 0)
				dx = 0;
		} else if (dx < 0) {
			dx += stopSpeed;
			if (dx > 0)
				dx = 0;
		}

		// cannot move while attacking, except in air
		if ((currentAction == ATTACKING) && !(jumping || falling))
			dx = 0;

		// jumping
		if (jumping && !falling) {
			dy = jumpStart;
			falling = true;
		}

		// falling
		if (falling) {
			dy += fallSpeed;

			if (dy > 0)
				jumping = false;
			if ((dy < 0) && !jumping)
				dy += stopJumpSpeed;

			if (dy > maxFallSpeed)
				dy = maxFallSpeed;

		}

	}

	public boolean isDead() {
		return dead;
	}

	public void setAttacking() {
		attacking = true;
	}

	public void setDead(boolean b) {
		dead = b;
	}

	public void setExpStub(int i) {
		expStub = i;
	}

	public void setFallSpeed(double d) {
		fallSpeed = d;
	}

	public void setHealth(int i) {
		health = i;
	}

	@Override
	public void setJumping(boolean b) {
		jumping = b;
	}

	public void setLevels(int i) {
		levels = i;
	}

	public void setMaxHealth(int i) {
		maxHealth = i;
	}

	public void setMaxSpeed(double d) {
		maxSpeed = d;
	}

	public void setStaches(int i) {
		moustaches = i;
	}

	public void update() {

		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		// update attack damage
		attackDamage = 1 + (levels / 2);
		// TODO put attack damage into the PlayerAttributes

		// check attack to stop
		if (currentAction == ATTACKING)
			if (animation.hasPlayedOnce())
				attacking = false;

		// fumeball attack

		if (attacking && (currentAction != ATTACKING)) {
			final FumeBall fb = new FumeBall(tileMap, facingRight);
			fb.setPosition(x, y);
			fumeBalls.add(fb);
		}

		// check done flinching
		if (flinching) {
			final long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 1200)
				flinching = false;
		}

		// update for tiles hurting player
		if (!flinching && tileHurtsPlayer) {
			damagePlayer(1);
			tileHurtsPlayer = false;
		}

		for (int i = 0; i < fumeBalls.size(); i++) {
			fumeBalls.get(i).update();

			if (fumeBalls.get(i).shouldRemove()) {
				fumeBalls.remove(i);
				i--;
			}
		}

		// set animation
		if (attacking) {
			if (currentAction != ATTACKING) {
				currentAction = ATTACKING;
				animation.setFrames(sprites.get(ATTACKING));
				animation.setDelay(75);
				width = 30;
			}
		} else if (dy > 0) {
			if (currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		} else if (dy < 0) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		} else if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		} else if (currentAction != IDLE) {
			currentAction = IDLE;
			animation.setFrames(sprites.get(IDLE));
			animation.setDelay(100);
			width = 30;
		}

		animation.update();

		// set direction
		if (currentAction != ATTACKING) {
			if (right)
				facingRight = true;
			if (left)
				facingRight = false;
		}

		// if(moustaches == levelUps[levels])
		gainLevel();

		// has to be the very last to update everything correctly to the save
		if (!dead)
			writePlayerToUniverse();

	}

	private void writePlayerToUniverse() {
		PlayerAttributes.setAttributes(health, maxHealth, moustaches, levels,
				expStub, maxSpeed, fallSpeed);
	}
}
