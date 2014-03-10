package GameState;

import Main.GamePanel;
import content.Music;

public class GameStateManager {

	private final GameState[] gameStates;
	private int currentState;

	public static final int NUMGAMESTATES = 9;

	public static final int DEATH = 0;
	public static final int MENUSTATE = 1;
	public static final int HELP = 2;
	public static final int LEVEL1STATE = 3;
	public static final int LEVEL2STATE = 4;
	public static final int LEVEL3STATE = 5;
	public static final int LEVEL4STATE = 6;
	public static final int LEVEL5STATE = 7;
	public static final int STUB = 8;

	public GameStateManager() {

		Music.init();

		gameStates = new GameState[NUMGAMESTATES];

		currentState = MENUSTATE;
		loadState(currentState);

	}

	public void draw(java.awt.Graphics2D g) {
		if (gameStates[currentState] != null)
			gameStates[currentState].draw(g);
		else {
			g.setColor(java.awt.Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
	}

	public int getCurrentState() {
		return currentState;
	}

	private void loadState(int state) {
		if (state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		else if (state == LEVEL1STATE)
			gameStates[state] = new Level1State(this);
		else if (state == LEVEL2STATE)
			gameStates[state] = new Level2State(this);
		else if (state == LEVEL3STATE)
			gameStates[state] = new Level3State(this);
		else if (state == LEVEL4STATE)
			gameStates[state] = new Level4State(this);
		else if (state == LEVEL5STATE)
			gameStates[state] = new Level5State(this);
		else if (state == HELP)
			gameStates[state] = new HelpState(this);
		else if (state == DEATH)
			gameStates[state] = new DeadScreen(this);
		else if (state == STUB)
			gameStates[state] = new LevelEND(this);
	}

	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}

	private void unloadState(int state) {
		gameStates[state] = null;
	}

	public void update() {

		if (gameStates[currentState] != null)
			gameStates[currentState].update();
	}

}