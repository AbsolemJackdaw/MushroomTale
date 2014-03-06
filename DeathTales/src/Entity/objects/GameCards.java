package Entity.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Animation;
import Entity.MapObject;
import TileMap.TileMap;

public class GameCards extends MapObject {

	private boolean pickedUp;
	private BufferedImage[] image;
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

		image = new BufferedImage[24];

		facingRight = true;

		animation = new Animation();
		try {
			BufferedImage img = ImageIO.read(getClass().getResource("/items/lorecard.png"));
			for(int i = 0; i < image.length; i++){
				image[i] = img.getSubimage(i*width, 0, width, height);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		animation.setFrames(image);
		animation.setDelay(75);
	}

	public boolean isPickedUp(){ return pickedUp;}

	public void pickUpObject(){
		if(pickedUp) return;
		pickedUp = true;
	}

	public void update() {

		super.getNextPosition(); // needed for falling
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		animation.update();
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int nr) {
		this.cardNumber = nr;
	}

}
