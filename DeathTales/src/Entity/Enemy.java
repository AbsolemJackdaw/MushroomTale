package Entity;

import java.awt.Graphics2D;

import TileMap.TileMap;

public class Enemy extends MapObject{

	protected int health;
	protected int maxHealth;
	protected boolean dead;
	protected int damage;

	public Enemy(TileMap tm) {
		super(tm);
	}

	public boolean isDead() { return dead; }

	public int getDamage() { return damage; }

	public void hit(int damage){
		if(dead) return;
		health -= damage;
		if(health < 0 ) health = 0;
		if(health == 0) dead = true;

	}	

	public void update() {

		super.getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		//if a wall is hit, turn around
		//or if the end/beginning of the map is reached, turn around

		if(right && dx == 0 || right && this.currCol == tileMap.getNumCols()-1){
			right =   false;
			left = true;
			facingRight =true;
		}
		else if(left && dx ==0 || left && this.currCol == 1){
			right  = true;
			left = false;
			facingRight = false;
		}



		animation.update();
	}

	public void draw(Graphics2D g) {
		//if(notOnScreen()) return;
		super.draw(g);		
	}

	/**Amount of exp dropped*/
	public int getExpAmount(){
		return 1;
	}
	
	/**Amount of hearts dropped */
	public int getHeartAmount(){
		return 1;
	}

	/**how many percent chance to drop a heart*/
	public int getHeartChance(){
		return 50;
	}

	/**how many percent chance to drop exp*/
	public int getExpChance(){
		return 50;
	}
	
	/**How many hearts the entity damages the player*/
	public int getDamageAmount(){
		return 1;
	}

	
}
