package Entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.Animation;
import Entity.Enemy;
import content.Images;


public class Explosion {


	private int x;
	private int y;
	private int xmap;
	private int ymap;

	private int width;
	private int height;

	private Animation animation;
	private BufferedImage [] imgs;

	private boolean remove;

	private Enemy enemy;

	public Explosion ( int x, int y, Enemy e2){

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

	public void update(){
		animation.update();
		if(animation.hasPlayedOnce()){
			remove = true;
		}
	}

	public boolean shouldRemove(){ return remove; }

	public void setMapPosition(int x, int y){
		xmap = x;
		ymap = y;
	}

	public void draw(Graphics2D g){
		g.drawImage(
				animation.getImage(),
				x + xmap - width/2,
				y + ymap - height/2,
				null

				);
	}

	public int getx(){return x;	}
	public int gety() { return y; }

	public Enemy getEnemy(){
		return enemy;
	}

}
