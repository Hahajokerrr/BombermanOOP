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
import uet.oop.bomberman.entities.MovingEntity.Enemy.Balloom;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Item.BombItem;
import uet.oop.bomberman.entities.Tile.Item.FlameItem;
import uet.oop.bomberman.entities.Tile.Item.SpeedItem;
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
    Bomber bomberman = new Bomber(6, 6, Sprite.player_right.getFxImage());
    Bomb bomb1 = new Bomb(bomberman);
    Bomb bomb2 = new Bomb(bomberman);
    Bomb bomb3 = new Bomb(bomberman);

    Balloom balloom = new Balloom(6, 1, Sprite.balloom_left1.getFxImage());

    SpeedItem speedItem = new SpeedItem(8,8,Sprite.powerup_speed.getFxImage());
    BombItem bombItem = new BombItem(9,9,Sprite.powerup_bombs.getFxImage());
    FlameItem flameItem = new FlameItem(11,11,Sprite.powerup_flames.getFxImage());

    private List<Entity> entities = new ArrayList<>();
    //private BombManager bombManager = new BombManager();
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
        entities.add(balloom);
        entities.add(speedItem);
        entities.add(bombItem);
        entities.add(flameItem);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                entities.add(bomb1.getFlameAt(i).getFlameSegmentAt(j));
                entities.add(bomb2.getFlameAt(i).getFlameSegmentAt(j));
                entities.add(bomb3.getFlameAt(i).getFlameSegmentAt(j));
            }
        }

        bomberman.getBombManager().addBomb(bomb1);
        bomberman.getBombManager().addBomb(bomb2);
        bomberman.getBombManager().addBomb(bomb3);

        // Tao scene
        scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);

        stage.show();
        createMap();

        entities.add(bomberman);
        bomberman.setStillObjects(stillObjects);
        bomberman.setEntities(entities);

        balloom.setStillObjects(stillObjects);
        balloom.setEntities(entities);

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
                        bomberman.SetCheckedSpeed();
                        bomberman.setState(State.UP);
                        break;
                    case DOWN:
                    case S:
                        bomberman.SetCheckedSpeed();
                        bomberman.setState(State.DOWN);
                        break;
                    case LEFT:
                    case A:
                        bomberman.SetCheckedSpeed();
                        bomberman.setState(State.LEFT);
                        break;
                    case RIGHT:
                    case D:
                        bomberman.SetCheckedSpeed();
                        bomberman.setState(State.RIGHT);
                        break;
                    case SPACE:
                        bomberman.getBombManager().PlaceBomb(bomberman);
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
                        bomberman.getBombManager().DonePlacing();
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
        for (int i = 1; i < WIDTH; i++) {
            for (int j = 1; j < HEIGHT; j++) {
                Entity object;
                if (j % 5 == 0 && i % 5 == 0) {
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                }
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);
        bomberman.getBombManager().DetonateBomb();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
