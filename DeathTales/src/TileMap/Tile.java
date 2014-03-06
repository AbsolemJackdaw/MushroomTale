package TileMap;

import java.awt.image.BufferedImage;

public class Tile {

	private final BufferedImage image;
	private final int type;

	// private int Nr;

	// tile types
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;

	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getType() {
		return type;
	}

	// public int getBlockType() { return Nr;}
	// public void setBlockType(int i){ Nr = i; }
	//
	// public boolean isDangerous() { if (getBlockType() == 26 || getBlockType()
	// == 27) return true; return false;}

}
