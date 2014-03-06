package Entity.player;


public class PlayerAttributes {

	private static int health = 3;
	private static int maxHealth = 3;
	private static int moustaches = 0;
	private static int levels = 0;
	private static int expStub = 0;
	private static double maxSpeed = 1.0;
	private static double fallSpeed = 0.15;

	//TODO save position and map

	public static void setAttributes(int hp, int maxHp, int exp, int lvl, int stub, double maxSp, double fallSp){
		health = hp;
		maxHealth = maxHp;
		moustaches = exp;
		levels = lvl;		
		expStub = stub;
		maxSpeed = maxSp;
		fallSpeed = fallSp;
	}

	public static void setPlayerAttributes(Player p){
		p.setHealth(health);
		p.setMaxHealth(maxHealth);
		p.setExpStub(expStub);
		p.setStaches(moustaches);
		p.setLevels(levels);
		p.setMaxSpeed(maxSpeed);
		p.setFallSpeed(fallSpeed);
		
	}

}
