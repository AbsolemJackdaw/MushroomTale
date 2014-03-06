package content;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Images {

	public static BufferedImage[] Explosion = load("/enemy/enemy_Death.png", 30, 0, 10);

	public static BufferedImage[] Demon = load("/enemy/enemies.png", 30, 0, 8);
	public static BufferedImage[] Skull = load("/enemy/enemies.png", 30, 30, 10);

	public static BufferedImage[] Heart = load("/player/hud.png", 30, 0,  1);
	public static BufferedImage[] Shroom = load("/items/powerup.png", 30, 0,  10);
	public static BufferedImage[] Cards = load("/items/lorecard.png", 30, 0,  24);

	
	public static BufferedImage[] load(String s, int x, int y, int subImages) {
		BufferedImage[] ret;
		try {
			BufferedImage spritesheet = ImageIO.read(Images.class.getResourceAsStream(s));
			
			ret = new BufferedImage[subImages];
			
			for(int i = 0; i < subImages; i++) {
				ret[i] = spritesheet.getSubimage(i * x, y, x, x);
			}
			return ret;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}

}
