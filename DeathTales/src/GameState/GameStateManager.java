package GameState;

import content.Music;


public class GameStateManager {

	private GameState[] gameStates;
	private int currentState;

	public static final int DEATH = 0;
	public static final int MENUSTATE = 1;
	public static final int LEVEL1STATE = 2;
	public static final int LEVEL2STATE = 3;
	public static final int LEVEL3STATE = 4;

	public final int maxLevels = 5;

	public GameStateManager() {

		Music.init();
		
		gameStates = new GameState[maxLevels];

		currentState = MENUSTATE;
		loadState(currentState);

	}

	private void loadState(int state) {
		if(state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		else if(state == LEVEL1STATE)
			gameStates[state] = new Level1State(this);
		else if(state == LEVEL2STATE)
			gameStates[state] = new Level2State(this);
		else if(state == LEVEL3STATE)
			gameStates[state] = new Level3State(this);
	}

	private void unloadState(int state) {
		gameStates[state] = null;
	}

	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}	

	public int getCurrentState(){return currentState;}

	public void update() {
		try {
			gameStates[currentState].update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void keyPressed(int k) {
//		gameStates[currentState].keyPressed(k);
//	}
//
//	public void keyReleased(int k) {
//		gameStates[currentState].keyReleased(k);
//	}

}









