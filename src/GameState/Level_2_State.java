package GameState;

import Audio.AudioPlayer;
import Entity.Enemies.Slugger2;
import Entity.Enemy;
import Entity.Explosion;
import Entity.HUD;
import Entity.Player;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level_2_State extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;

    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosion;

    private HUD hud;

    private AudioPlayer bgMusic;

    public Level_2_State(GameStateManager gsm) {
        this.gsm = gsm;
        init();

    }

    public void init() {

        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset1.png");
        tileMap.loadMap("/Maps/level2.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        bg = new Background("/Backgrounds/state.jpg", 0.1);

        bgMusic = new AudioPlayer("/Music/Underworld bpm 75.mp3");
        bgMusic.play();

        player = new Player(tileMap, gsm, bgMusic);

        //player.setPosition(110, 680);
        player.setPosition(800, 750);
        populateEnemies();

        explosion = new ArrayList<Explosion>();

        hud = new HUD(player);

    }

    private void populateEnemies() {
        enemies = new ArrayList<Enemy>();

        Slugger2 s;
        Point[] points = new Point[]{
            new Point(200, 760),
            new Point(300, 760),
            new Point(250, 760),
            new Point(270, 600),
            new Point(180, 600),
            new Point(100, 550),
            new Point(1000, 730),
            new Point(1050, 730),};
        for (int i = 0; i < points.length; i++) {
            s = new Slugger2(tileMap);
            s.setPosition(points[i].x, points[i].y);
            enemies.add(s);
        }
    }
    int count = 0;

    public void update() {

        // update player
        player.update();
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety());

        // set background
        bg.setPosition(tileMap.getx(), tileMap.gety());

        // attack enemies
        player.checkAttack(enemies);

        //update all enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
            if (e.isDead()) {
                count++;
                enemies.remove(i);
                i--;
                explosion.add(new Explosion(e.getx(), e.gety()));
                
                if (count == 8) {
                    bgMusic.stop();
                    gsm.setState(GameStateManager.LEVEL_1_STATE);

                }
            }
        }

        // update explosions
        for (int i = 0; i < explosion.size(); i++) {
            explosion.get(i).update();
            if (explosion.get(i).shouldRemove()) {
                explosion.remove(i);
                i--;
            }
        }

    }

    public void draw(Graphics2D g) {

        // draw background
        bg.draw(g);

        // draw tilemap
        tileMap.draw(g);

        // draw player
        player.draw(g);

        // draw enemies
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }

        // draw explosion
        for (int i = 0; i < explosion.size(); i++) {
            explosion.get(i).setMapPosition((int) tileMap.getx(),
                    (int) tileMap.gety());
            explosion.get(i).draw(g);
        }

        // draw hud
        hud.draw(g);
    }

    public void KeyPressed(int k) {
        if (k == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setRight(true);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(true);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(true);
        }
        if (k == KeyEvent.VK_W) {
            player.setJumping(true);
        }
        if (k == KeyEvent.VK_E) {
            player.setGliding(true);
        }
        if (k == KeyEvent.VK_R) {
            player.setScratching();
        }
        if (k == KeyEvent.VK_F) {
            player.setFiring();
        }
    }

    public void KeyReleased(int k) {
        if (k == KeyEvent.VK_LEFT) {
            player.setLeft(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setRight(false);
        }
        if (k == KeyEvent.VK_UP) {
            player.setUp(false);
        }
        if (k == KeyEvent.VK_DOWN) {
            player.setDown(false);
        }
        if (k == KeyEvent.VK_W) {
            player.setJumping(false);
        }
        if (k == KeyEvent.VK_E) {
            player.setGliding(false);
        }
    }

}
