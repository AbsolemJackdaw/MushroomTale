package Entity.player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HUD {

	private Player player;
	private BufferedImage img;

	public HUD(Player p){

		player = p;

		try{
			img = ImageIO.read(	getClass().getResourceAsStream(
					"/player/hud.png"
					));


		}catch(Exception e){
			e.printStackTrace();
		}


	}

	public void draw(Graphics2D g){

		//		int v = 0;
		//		int v2 = 0;
		//		if(player.getLvl() > 5)
		//			v = 20; v2 = 0;
		//heart containers
		for(int i = 0; i < player.getMaxHealth(); i++){

			g.drawImage(img.getSubimage(30, 0, 30, 30),
					i > 5 ? -20+(30*(i-5))  :10 +(30*i) ,
							i > 5 ? 35 : 10 ,
									null);

		}

		//hearts
		for(int i = 0; i < player.getHealth(); i++){
			g.drawImage(img.getSubimage(00, 0, 30, 30),
					i > 5 ? -20+(30*(i-5))  :10 +(30*i) ,
							i > 5 ? 35 : 10 ,
									null);
		}

		//amount of exp gotten for this level
		float c;
		c = 30* ((float)player.getExpStub()/(float)player.expLeftOver());

		if(30-(int)c >= 0 && 40-(int)c >= 0)
			g.drawImage(img.getSubimage(90, 30-(int)c, 30, 30), 280 , 40-(int)c , null);

		//ball that contains exp
		g.drawImage(img.getSubimage(60, 0, 30, 30), 280 , 10 , null);

	}

}
