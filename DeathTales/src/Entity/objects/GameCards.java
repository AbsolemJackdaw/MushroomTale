package Entity.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.Animation;
import Entity.MapObject;
import TileMap.TileMap;
import content.Images;

public class GameCards extends MapObject {

	private boolean pickedUp;
	private final BufferedImage[] image;
	private int cardNumber;

	public GameCards(TileMap tm, int nr) {
		super(tm);

		cardNumber = nr;

		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;

		maxSpeed = 0;
		moveSpeed = 0;
		fallSpeed = 0.5;
		maxFallSpeed = 10.0;

		image = Images.Cards;

		facingRight = true;

		animation = new Animation();
		animation.setFrames(image);
		animation.setDelay(75);
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void pickUpObject() {
		if (pickedUp)
			return;
		pickedUp = true;
	}

	public void setCardNumber(int nr) {
		cardNumber = nr;
	}

	public void update() {

		super.getNextPosition(); // needed for falling
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		animation.update();
	}

}
