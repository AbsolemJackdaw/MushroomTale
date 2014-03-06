package Entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.Animation;
import Entity.Enemy;
import TileMap.TileMap;
import content.Images;

public class Demon extends Enemy{

	private BufferedImage [] sprites;

	public Demon(TileMap tm) {
		super(tm);

		health = 3;
		maxSpeed = 0.3;
		moveSpeed = 0.3;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;

		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		//load sprites

		sprites = Images.Demon;

		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(75);

		right = true;
		facingRight = false;
	}

	public void update(){
		super.update();
	}

	public void draw(Graphics2D g){
		super.draw(g);
	}
	
	@Override
	public int getHeartChance() {
		// TODO Auto-generated method stub
		return 50;
	}
	
	@Override
	public int getExpChance() {
		return 60;
	}
}
