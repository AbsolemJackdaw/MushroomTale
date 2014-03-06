package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class FumeBall extends MapObject {


	private boolean hit;
	private boolean remove; // dead ?

	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;


	public FumeBall(TileMap tm, boolean right) {
		super(tm);
		
		facingRight = true;

		moveSpeed = 3.8;
		if(right)dx = moveSpeed;
		else dx = -moveSpeed;

		width = 30;
		height = 30;
		cwidth = 14;
		cheight = 14;

		//load sprite

		try {

			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/player/fumeball.png"));

			sprites = new BufferedImage[8];

			for(int i = 0; i < sprites.length; i++){
				sprites[i] = spritesheet.getSubimage(
						i*width,
						0,
						width,
						height						
						);
			}

			hitSprites = new BufferedImage[5];

			for(int i2 = 0; i2 < hitSprites.length; i2++){
				hitSprites[i2] = spritesheet.getSubimage(
						i2*width,
						height,
						width,
						height
						);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(20);
	}

	public void setHit() {
		if(hit) return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(5);
		dx = 0;
	}

	public boolean shouldRemove(){ return remove;}


	public void update() {

		//System.out.println("IM ALIVE");

		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		if(dx == 0 && !hit){
			setHit();
		}
		
		animation.update();
		if(hit && animation.hasPlayedOnce()){
			remove = true;
		}
	}

	public void draw(Graphics2D g) {

			super.draw(g);
			//drawSprite(g, animation);

//		setMapPosition();
//
//		// draw 
//
//		if(facingRight) {
//			g.drawImage(
//					animation.getImage(),
//					(int)(x + xmap - width / 2),
//					(int)(y + ymap - height / 2),
//					null
//					);
//		}
//		else {
//			g.drawImage(
//					animation.
//					getImage(),
//					(int)(x + xmap - width / 2 + width),
//					(int)(y + ymap - height / 2),
//					-width,
//					height,
//					null
//					);
//
//		}
	}
}
