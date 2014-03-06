package Entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.Animation;
import Entity.Enemy;
import content.Images;

public class Explosion {

	private final int x;
	private final int y;
	private int xmap;
	private int ymap;

	private final int width;
	private final int height;

	private final Animation animation;
	private final BufferedImage[] imgs;

	private boolean remove;

	private final Enemy enemy;

	public Explosion(int x, int y, Enemy e2) {

		enemy = e2;

		this.x = x;
		this.y = y;

		width = 30;
		height = 30;

		imgs = Images.Explosion;

		animation = new Animation();
		animation.setFrames(imgs);
		animation.setDelay(40);
	}

	public void draw(Graphics2D g) {
		g.drawImage(animation.getImage(), (x + xmap) - (width / 2), (y + ymap)
				- (height / 2), null

		);
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public void setMapPosition(int x, int y) {
		xmap = x;
		ymap = y;
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void update() {
		animation.update();
		if (animation.hasPlayedOnce())
			remove = true;
	}

}
