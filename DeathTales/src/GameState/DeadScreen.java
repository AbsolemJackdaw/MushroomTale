package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import TileMap.Background;

public class DeadScreen extends GameState{

	private Background bg;
	
	private Color clr = new Color(0.5f,0,0);
	private String endScreenTitle = "You Could Not Save The Forest ... ";
	private Font fnt = new Font("Century Gothic",Font.PLAIN, 20);
	
	public DeadScreen(GameStateManager gsm) {

		this.gsm = gsm;

		try {

			bg = new Background("/BackGround/death.png", 1, false, 0);
			bg.setVector(-0.1, 0);

		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics2D g) {
		
		bg.draw(g);
		
		g.setColor(clr);
		g.setFont(fnt);
		g.drawString(endScreenTitle,GamePanel.WIDTH/2 , GamePanel.HEIGHT/3);
	}

//	@Override
//	public void keyPressed(int k) {
//
//		if(k == KeyEvent.VK_ENTER){
//			gsm.setState(GameStateManager.MENUSTATE);
//		}
//	}
//
//	@Override
//	public void keyReleased(int k) {
//
//	}

}
