package content;

import java.util.ArrayList;

public class Lore {

	// beginning
	private static final String[] cart1 = {
			"Yesterday, the forest was lively, green.",
			"Today everything is pulsating, infected, dead.", "",
			"What happened after I left ... ?", "", "", "", "", "", "", "", "",
			"", "", "Press enter to continue." };

	// end level 1
	private static final String[] cart2 = {
			"I have never seen such creatures in the forest!",
			"Dead karkasses roam my once fertile soil...",
			"The ground is pulsating softly and breaths.",
			"Something infected my forest and its feeding ", "itself on it...",
			"", "", "", "", "", "", "", "", "", "*Press enter to continue.*" };

	private static final String[] cart3 = { "I feel I'm getting closer... ",
			"", "I should hurry.. The forest suffers.", "I suffer ...", "", "",
			"", "", "", "", "", "", "", "", "Press enter to continue." };

	private static final String[] cart4 = { "Shivering...",
			"It's getting so cold ...", "",
			"The problem comes from down below.",
			"I need to find my way to the source...", "", "", "", "", "", "",
			"", "", "", "", "Press enter to continue" };

	private static final String[] cart5 = { "This infects my lands ... !",
			"This infects my existence and fills my green",
			"lands with death and sorrow. ",
			"This acking heart grows upon and over me.",
			"I need to get rid of it ...", "", "", "", "", "", "", "", "", "",
			"Press enter to sacrifice yourself",

	};

	public static final String[] endCredits = { "MUSHROOM TALE",
			"an allegorie of a depression", "", "", "Game Developped by",
			"Subaraki S.", "Axel Cornelis", "", "", "Music written",
			"and performed by", "Axel Cornelis", "", "Aid with code",
			"Darkhax", "ShadowChild", "", "Beta testers", "bl4ckscor3",
			"ShadowChild", "Mega377", "", "", "", "Special Thanks to",
			"ForeignGuyMike","http://www.youtube.com/user/ForeignGuyMike", "",
			"", "This was an artproject submitted",
			"by an artstudent that tries to", "express his state of mind.", "",
			"", "", "", "", "", "", "THE END"

	};

	public static ArrayList<String[]> lore = new ArrayList<String[]>();

	public Lore() {
		lore.add(cart1);
		lore.add(cart2);
		lore.add(cart3);
		lore.add(cart4);
		lore.add(cart5);
	}

	public ArrayList<String[]> getLore() {
		return lore;
	}
}
