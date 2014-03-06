package com.neet.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.neet.Handlers.Content;
import com.neet.TileMap.TileMap;

public class EnergyParticle extends MapObject {

	private int count;
	private boolean remove;

	private final BufferedImage[] sprites;

	public static int UP = 0;
	public static int LEFT = 1;
	public static int DOWN = 2;
	public static int RIGHT = 3;

	public EnergyParticle(TileMap tm, double x, double y, int dir) {
		super(tm);
		this.x = x;
		this.y = y;
		final double d1 = (Math.random() * 2.5) - 1.25;
		final double d2 = -Math.random() - 0.8;
		if (dir == UP) {
			dx = d1;
			dy = d2;
		} else if (dir == LEFT) {
			dx = d2;
			dy = d1;
		} else if (dir == DOWN) {
			dx = d1;
			dy = -d2;
		} else {
			dx = -d2;
			dy = d1;
		}

		count = 0;
		sprites = Content.EnergyParticle[0];
		animation.setFrames(sprites);
		animation.setDelay(-1);
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void update() {
		x += dx;
		y += dy;
		count++;
		if (count == 60)
			remove = true;
	}

}
