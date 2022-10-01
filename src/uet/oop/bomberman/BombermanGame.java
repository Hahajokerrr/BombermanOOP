package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.BombManager;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.MovingEntity.Mover;
import uet.oop.bomberman.entities.MovingEntity.State;


import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    int i = 0;

    private GraphicsContext gc;
    private Canvas canvas;
    private Scene scene;
    Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    Bomb bomb1 = new Bomb();
    Bomb bomb2 = new Bomb();
    Bomb bomb3 = new Bomb();

    private List<Entity> entities = new ArrayList<>();
    private BombManager bombManager = new BombManager();
    private List<Entity> stillObjects = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        entities.add(bomb1);
        entities.add(bomb2);
        entities.add(bomb3);

        bombManager.addBomb(bomb1);
        bombManager.addBomb(bomb2);
        bombManager.addBomb(bomb3);

        // Tao scene
        scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);

        stage.show();
        createMap();
        entities.add(bomberman);
        bomberman.setStillObjects(stillObjects);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                HandleInput();
                render();
                update();
            }
        };
        timer.start();


    }

    public void HandleInput() {
        EventHandler<KeyEvent> eventHandler1 = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case UP:
                    case W:
                        bomberman.setSpeed(Bomber.STANDARD_SPEED);
                        bomberman.setState(State.UP);
                        break;
                    case DOWN:
                    case S:
                        bomberman.setSpeed(Bomber.STANDARD_SPEED);
                        bomberman.setState(State.DOWN);
                        break;
                    case LEFT:
                    case A:
                        bomberman.setSpeed(Bomber.STANDARD_SPEED);
                        bomberman.setState(State.LEFT);
                        break;
                    case RIGHT:
                    case D:
                        bomberman.setSpeed(Bomber.STANDARD_SPEED);
                        bomberman.setState(State.RIGHT);
                        break;
                    case SPACE:
                        bombManager.PlaceBomb(bomberman);
                        break;
                    default:
                        break;
                }
            }
        };
        scene.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler1);
        EventHandler<KeyEvent> eventHandler2 = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case UP:
                    case DOWN:
                    case LEFT:
                    case RIGHT:
                    case W:
                    case A:
                    case D:
                    case S:
                        bomberman.setSpeed(0);
                    case SPACE:
                        bombManager.DonePlacing();
                    default:
                        break;
                }
            }
        };
        scene.addEventFilter(KeyEvent.KEY_RELEASED, eventHandler2);
    }


    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        bombManager.DetonateBomb();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
