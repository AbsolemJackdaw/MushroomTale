package Entity.player;

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

	public static ArrayList<String[]> lore = new ArrayList<String[]>();

	public Lore() {
		lore.add(cart1);
		lore.add(cart2);
		lore.add(cart3);
	}

	public ArrayList<String[]> getLore() {
		return lore;
	}
}
