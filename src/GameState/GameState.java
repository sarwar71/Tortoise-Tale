package GameState;

import java.awt.Graphics2D;

public abstract class GameState {

    protected GameStateManager gsm;

    public abstract void init();
    public abstract void update();
    public abstract void draw(Graphics2D g);
    public abstract void KeyPressed(int k);
    public abstract void KeyReleased(int k);
}
