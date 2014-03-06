package Entity.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.Animation;
import Entity.MapObject;
import TileMap.TileMap;
import content.Images;

public class Hearts extends MapObject {

	private boolean pickedUp;

	private final BufferedImage[] image;

	public Hearts(TileMap tm) {
		super(tm);

		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;

		maxSpeed = 0;
		moveSpeed = 0;
		fallSpeed = 0.5;
		maxFallSpeed = 10.0;

		image = Images.Heart;

		animation = new Animation();
		animation.setFrames(image);
		animation.setDelay(0);
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void pickUpObject() {
		if (pickedUp)
			return;
		pickedUp = true;
	}

	public void update() {

		super.getNextPosition(); // needed for falling
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
	}
}
