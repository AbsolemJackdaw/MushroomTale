package com.neet.GameState;

import com.neet.Audio.JukeBox;
import com.neet.Main.GamePanel;

public class GameStateManager {

	private final GameState[] gameStates;
	private int currentState;

	private final PauseState pauseState;
	private boolean paused;

	public static final int NUMGAMESTATES = 16;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1ASTATE = 2;
	public static final int LEVEL1BSTATE = 3;
	public static final int LEVEL1CSTATE = 4;
	public static final int ACIDSTATE = 15;

	public GameStateManager() {

		JukeBox.init();

		gameStates = new GameState[NUMGAMESTATES];

		pauseState = new PauseState(this);
		paused = false;

		currentState = MENUSTATE;
		loadState(currentState);

	}

	public void draw(java.awt.Graphics2D g) {
		if (paused) {
			pauseState.draw(g);
			return;
		}
		if (gameStates[currentState] != null)
			gameStates[currentState].draw(g);
		else {
			g.setColor(java.awt.Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
	}

	private void loadState(int state) {
		if (state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		else if (state == LEVEL1ASTATE)
			gameStates[state] = new Level1AState(this);
		else if (state == LEVEL1BSTATE)
			gameStates[state] = new Level1BState(this);
		else if (state == LEVEL1CSTATE)
			gameStates[state] = new Level1CState(this);
		else if (state == ACIDSTATE)
			gameStates[state] = new AcidState(this);
	}

	public void setPaused(boolean b) {
		paused = b;
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
		if (paused) {
			pauseState.update();
			return;
		}
		if (gameStates[currentState] != null)
			gameStates[currentState].update();
	}

}