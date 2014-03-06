package Entity.player;

import java.util.ArrayList;


public class Lore {


	//beginning
	private static final String[] cart1 = {
		"What happened to my forest ? Everything got ",
		" dread and wasted... The lively, green",
		"forest I used to know has turned to a bloody",
		"wasteland ! I need to find out what's going on.",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"Press enter to continue."
	};

	//end level 1
	private static final String[] cart2 = {
		"I have never seen such creatures in the forest!",
		"Why are dead, twiching, walking karkasses",
		"roaming the forest ?! Luckily my Mushroom ",
		"Digestion allows me to consume the dead.",
		"The soil is strangely pulsating though...",
		"Something infected my forest and its feeding ",
		"itself on it.",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"Press enter to continue."
	};


	private static final String[] cart3 = {
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"Press enter to continue."
	};


	public static ArrayList<String[]> lore = new ArrayList<String[]>();

	public Lore(){
		lore.add(cart1);
		lore.add(cart2);
		lore.add(cart3);
	}

	public ArrayList<String[]> getLore(){
		return lore;
	}
}
