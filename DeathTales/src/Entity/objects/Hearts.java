package Entity.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Animation;
import Entity.MapObject;
import TileMap.TileMap;

public class Hearts extends MapObject{

	private boolean pickedUp;

	private BufferedImage[] image;
	
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
		
		image = new BufferedImage[1];
		
		animation = new Animation();
		try {
			BufferedImage img = ImageIO.read(getClass().getResource("/player/hud.png"));
			for(int i = 0; i < image.length; i++){
				image[i] = img.getSubimage(i*width, 0, width, height);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		animation.setFrames(image);
		animation.setDelay(0);
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
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
