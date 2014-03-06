package com.neet.Entity;

import java.awt.Graphics2D;

import com.neet.TileMap.TileMap;

public abstract class EnemyProjectile extends MapObject {

	protected boolean hit;
	protected boolean remove;
	protected int damage;

	public EnemyProjectile(TileMap tm) {
		super(tm);
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

	public int getDamage() {
		return damage;
	}

	public abstract void setHit();

	public boolean shouldRemove() {
		return remove;
	}

	public abstract void update();

}
