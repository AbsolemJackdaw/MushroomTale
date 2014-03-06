package com.neet.Entity.Artfact;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.neet.Entity.MapObject;
import com.neet.TileMap.TileMap;

public class TopLeftPiece extends MapObject {

	private BufferedImage[] sprites;

	public TopLeftPiece(TileMap tm) {
		super(tm);
		try {
			final BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/Sprites/Other/Artifact.gif"));
			sprites = new BufferedImage[1];
			width = height = 4;
			sprites[0] = spritesheet.getSubimage(0, 0, 10, 10);
			animation.setFrames(sprites);
			animation.setDelay(-1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

	public void update() {
		x += dx;
		y += dy;
		animation.update();
	}

}
