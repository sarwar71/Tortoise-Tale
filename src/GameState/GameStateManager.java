package GameState;

import java.awt.Graphics2D;

public class GameStateManager {

   

    private GameState[] gameState;
    private int currentState;

    private static final int NUMGAMESTATE = 3;
    public static final int MENU_STATE = 0;
    public static final int LEVEL_1_STATE = 1;
    public static final int LEVEL_2_STATE = 2;
    public static int Help;

    public GameStateManager() {

        gameState = new GameState[NUMGAMESTATE];
        currentState = MENU_STATE;
        loadState(currentState);

    }

    private void loadState(int state) {
        if (state == MENU_STATE) {
            gameState[state] = new MenuState(this);
        }
        if (state == LEVEL_1_STATE) {
            gameState[state] = new Level_1_State(this);
        }
        if (state == LEVEL_2_STATE) {
            gameState[state] = new Level_2_State(this);
        }
        
    }

    private void unloadState(int state) {
        gameState[state] = null;
    }

    public void setState(int state) {

        unloadState(currentState);
        currentState = state;
        loadState(currentState);
        //gameState[currentState].init();
    }

    public void update() {
        try {
            gameState[currentState].update();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g) {
        try {
            gameState[currentState].draw(g);
        } catch (Exception e) {

        }
    }

    public void KeyPresesd(int k) {
        gameState[currentState].KeyPressed(k);
    }

    public void KeyReleased(int k) {
        gameState[currentState].KeyReleased(k);
    }

}
