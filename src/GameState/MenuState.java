package GameState;


import TileMap.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {"Start", "Help", "Quit"};
    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;

        try {

            bg = new Background("/Backgrounds/cover.jpg", 1);
            //bg.setVector(-0.1, 0);

            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic", font.PLAIN, 28);
            font = new Font("Arial", font.PLAIN, 12);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
    }

    public void update() {
        bg.update();
    }

    public void draw(Graphics2D g) {

        // draw background
        bg.draw(g);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Tortoise Tale", 80, 70);

        // draw menu options
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.white);
            }
            g.drawString(options[i], 145, 140 + i * 15);
        }

    }

    public void select() {
        if (currentChoice == 0) {
            // start
            gsm.setState(GameStateManager.LEVEL_1_STATE);
        }

        if (currentChoice == 1) {
            // help
           
        }

        if (currentChoice == 2) {
            // exit
            System.exit(0);
        }
    }

   
    public void KeyPressed(int k) {

        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    public void KeyReleased(int k) {
    }

}
