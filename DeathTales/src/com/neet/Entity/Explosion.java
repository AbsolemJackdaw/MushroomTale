package com.neet.Entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.neet.Handlers.Content;
import com.neet.TileMap.TileMap;

public class Explosion extends MapObject {

	private final BufferedImage[] sprites;

	private boolean remove;

	private final Point[] points;
	private final int speed;
	private final double diagSpeed;

	public Explosion(TileMap tm, int x, int y) {

		super(tm);

		this.x = x;
		this.y = y;

		width = 30;
		height = 30;

		speed = 2;
		diagSpeed = 1.41;

		sprites = Content.Explosion[0];

		animation.setFrames(sprites);
		animation.setDelay(6);

		points = new Point[8];
		for (int i = 0; i < points.length; i++)
			points[i] = new Point(x, y);

	}

	@Override
	public void draw(Graphics2D g) {
		setMapPosition();
		for (final Point point : points)
			g.drawImage(animation.getImage(),
					(int) ((point.x + xmap) - (width / 2)),
					(int) ((point.y + ymap) - (height / 2)), null);
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void update() {
		animation.update();
		if (animation.hasPlayedOnce())
			remove = true;
		points[0].x += speed;
		points[1].x += diagSpeed;
		points[1].y += diagSpeed;
		points[2].y += speed;
		points[3].x -= diagSpeed;
		points[3].y += diagSpeed;
		points[4].x -= speed;
		points[5].x -= diagSpeed;
		points[5].y -= diagSpeed;
		points[6].y -= speed;
		points[7].x += diagSpeed;
		points[7].y -= diagSpeed;

	}

}