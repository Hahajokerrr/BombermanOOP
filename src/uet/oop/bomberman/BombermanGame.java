package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.BombManager;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Enemy.*;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Item.BombItem;
import uet.oop.bomberman.entities.Tile.Item.FlameItem;
import uet.oop.bomberman.entities.Tile.Item.Item;
import uet.oop.bomberman.entities.Tile.Item.SpeedItem;
import uet.oop.bomberman.entities.Tile.Portal;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.MovingEntity.Mover;
import uet.oop.bomberman.entities.MovingEntity.State;


import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Font.*;
import static javafx.scene.text.Font.font;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 15;
    int i = 0;

    private GraphicsContext gc;
    private Canvas canvas;
    private Scene GameScene;
    private Scene MenuScene;
    private Scene pausedMenuScene;
    private Scene Victory;
    private Scene Defeated;
    private FileLeverLoader leverLoader = new FileLeverLoader();
    private int level = 1;
    private int map = 0;
    private boolean paused = true;
    Bomber bomberman = new Bomber(3, 3, Sprite.player_right.getFxImage());
    Bomb bomb1 = new Bomb(bomberman);
    Bomb bomb2 = new Bomb(bomberman);
    Bomb bomb3 = new Bomb(bomberman);

    VBox root = new VBox();
    VBox menu = new VBox();
    VBox pausedMenu = new VBox();
    VBox Win = new VBox();
    private int win = 0;
    VBox Lose = new VBox();
    private int lose = 0;



    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    Text Stat = new Text("Level " + level + "            Score: " + bomberman.getScore());


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {

        StartSceneHandler(stage);
        PausedScenehandler(stage);
        winHandler(stage);
        loseHandler(stage);

        //createMap();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if ((map < level && bomberman.LevelUp == true) || map == 0) {
                    createMap();
                    initializeBomber();
                }
                HandleInput(stage);
                render();
                if (paused == false) {
                    update(stage);
                }
                Stat.setText("Level " + level + "            Score: " + bomberman.getScore());
            }
        };
        timer.start();
    }

    public void initializeBomber(){
        bomberman.setStillObjects(stillObjects);
        bomberman.setEntities(entities);
        entities.add(bomb1);
        entities.add(bomb2);
        entities.add(bomb3);
        entities.add(bomberman);

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
    }

    public void StartSceneHandler(Stage stage) {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        root.getChildren().add(Stat);
        root.getChildren().add(canvas);

        root.setStyle("-fx-background-color: white; -fx-text-fill: yellow;");

        // Tao scene
        GameScene = new Scene(root, WIDTH * 32, HEIGHT * 32 + 15);
        MenuScene = new Scene(menu, WIDTH * 32, HEIGHT * 32 + 15, Color.BLACK);
        stage.setScene(MenuScene);

        Button start = new Button("START");
        Button exit = new Button("EXIT");

        Text title = new Text("BOMBERMAN");
        title.setStyle("-fx-font: 80px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%); -fx-stroke: black; -fx-stroke-width: 1");
        start.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-base: #b6e7c9;");
        start.setPadding(new Insets(20, 109, 20, 109));
        exit.setPadding(new Insets(20, 122, 20, 122));
        Font font = Font.font("Tahoma", FontWeight.BOLD, 30);
        start.setFont(font);
        exit.setFont(font);
        title.setFont(font);

        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(20);
        menu.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        menu.getChildren().addAll(title, start, exit);
        start.setOnAction(event -> {
            paused = false;
            stage.setScene(GameScene);
        });

        exit.setOnAction(event -> {
            stage.close();
        });

        stage.setTitle("Bomberman");
        stage.show();
    }

    public void PausedScenehandler(Stage stage) {
        Button exit = new Button("EXIT");
        Button resume = new Button("RESUME");
        pausedMenuScene = new Scene(pausedMenu, WIDTH * 32, HEIGHT * 32 + 15, Color.BLACK);

        pausedMenu.getChildren().addAll(resume, exit);

        pausedMenu.setAlignment(Pos.CENTER);
        pausedMenu.setSpacing(20);
        pausedMenu.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        exit.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-base: #b6e7c9;");
        resume.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-base: #b6e7c9;");
        exit.setPadding(new Insets(20, 109, 20, 109));
        resume.setPadding(new Insets(20, 80, 20, 80));
        Font font = Font.font("Tahoma", FontWeight.BOLD, 30);
        resume.setFont(font);
        exit.setFont(font);


        resume.setOnAction(event -> {
            paused = false;
            GameSceneTrans(stage);
        });

        exit.setOnAction(event -> {
            stage.close();
        });
    }

    public void winHandler(Stage stage) {
        Text title = new Text("VICTORY");
        title.setStyle("-fx-font: 80px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%); -fx-stroke: black; -fx-stroke-width: 1");

        Button exit = new Button("EXIT");

        Victory = new Scene(Win, WIDTH * 32, HEIGHT * 32 + 15, Color.BLACK);

        Win.getChildren().addAll(title, exit);

        exit.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-base: #b6e7c9;");
        exit.setPadding(new Insets(20, 109, 20, 109));
        Font font = Font.font("Tahoma", FontWeight.BOLD, 30);
        exit.setFont(font);

        Win.setAlignment(Pos.CENTER);
        Win.setSpacing(20);
        Win.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        exit.setOnAction(event -> {
            stage.close();
        });
    }

    public void loseHandler(Stage stage) {
        Text title = new Text("DEFEATED");
        title.setStyle("-fx-font: 80px Tahoma; -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%); -fx-stroke: black; -fx-stroke-width: 1");

        Button exit = new Button("EXIT");

        Defeated = new Scene(Lose, WIDTH * 32, HEIGHT * 32 + 15, Color.BLACK);

        Lose.getChildren().addAll(title, exit);

        exit.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-base: #b6e7c9;");
        exit.setPadding(new Insets(20, 109, 20, 109));
        Font font = Font.font("Tahoma", FontWeight.BOLD, 30);
        exit.setFont(font);

        Lose.setAlignment(Pos.CENTER);
        Lose.setSpacing(20);
        Lose.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        exit.setOnAction(event -> {
            stage.close();
        });
    }

    public void PausedSceneTrans(Stage stage) {
        stage.setScene(pausedMenuScene);
    }

    public void GameSceneTrans(Stage stage) {
        stage.setScene(GameScene);
    }

    public void winTrans(Stage stage) {
        win++;
        if(win >= 200) {
            stage.setScene(Victory);
        }
    }

    public void loseTrans(Stage stage) {
        lose++;
        if(lose >= 200) {
            stage.setScene(Defeated);
        }
    }


    public void HandleInput(Stage stage) {
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
                    case ESCAPE:
                        if(!paused) {
                            paused = true;
                            PausedSceneTrans(stage);
                        }
                    default:
                        break;
                }
            }
        };
        GameScene.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler1);
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
        GameScene.addEventFilter(KeyEvent.KEY_RELEASED, eventHandler2);
    }


    public void createMap() {
        map++;
        leverLoader.loadLevel(level);
        entities.clear();
        stillObjects.clear();
        for (int i = 0; i < leverLoader.getHeight(); i++) {
            for (int j = 0; j < leverLoader.getWidth(); j++) {
                Entity objectWall;
                Brick objectBrick;
                Balloom balloom;
                Doll doll;
                Minvo minvo;
                Oneal oneal;
                Kondoria kondoria;
                FlameItem flameItem;
                BombItem bombItem;
                SpeedItem speedItem;
                Portal portal;
                if (leverLoader.getMapAt(i, j) == '#') {
                    objectWall = new Wall(j, i, Sprite.wall.getFxImage());
                } else {
                    objectWall = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(objectWall);

                switch (leverLoader.getMapAt(i, j)) {
                    case '*':
                        objectBrick = new Brick(j, i, Sprite.brick.getFxImage());
                        stillObjects.add(objectBrick);
                        break;
                    case 'p':
                        bomberman.setX(j * 32);
                        bomberman.setY(j * 32);
                        break;
                    case '1':
                        balloom = new Balloom(j, i, Sprite.balloom_left1.getFxImage(), bomberman);
                        entities.add(balloom);
                        balloom.setEntities(entities);
                        balloom.setStillObjects(stillObjects);
                        break;
                    case '2':
                        doll = new Doll(j, i, Sprite.doll_left1.getFxImage(), bomberman);
                        entities.add(doll);
                        doll.setEntities(entities);
                        doll.setStillObjects(stillObjects);
                        break;
                    case '3':
                        minvo = new Minvo(j, i, Sprite.minvo_left1.getFxImage(), bomberman);
                        balloom = new Balloom(-50, -50, Sprite.balloom_left1.getFxImage(), bomberman);
                        doll = new Doll(-50, -50, Sprite.doll_left1.getFxImage(), bomberman);
                        entities.add(minvo);
                        entities.add(balloom);
                        entities.add(doll);
                        minvo.setEntities(entities);
                        minvo.setStillObjects(stillObjects);
                        doll.setEntities(entities);
                        doll.setStillObjects(stillObjects);
                        balloom.setEntities(entities);
                        balloom.setStillObjects(stillObjects);
                        minvo.setDoll(doll);
                        minvo.setBalloom(balloom);
                        break;
                    case '4':
                        oneal = new Oneal(j, i, Sprite.oneal_left1.getFxImage(), bomberman);
                        entities.add(oneal);
                        oneal.setEntities(entities);
                        oneal.setStillObjects(stillObjects);
                        break;
                    case '5':
                        kondoria = new Kondoria(j, i, Sprite.kondoria_left1.getFxImage(), bomberman);
                        entities.add(kondoria);
                        kondoria.setEntities(entities);
                        kondoria.setStillObjects(stillObjects);
                        break;
                    case 'f':
                        objectBrick = new Brick(j, i, Sprite.brick.getFxImage());
                        flameItem = new FlameItem(j, i, Sprite.powerup_flames.getFxImage(), objectBrick);
                        stillObjects.add(objectBrick);
                        entities.add(flameItem);
                        flameItem.setBrick(objectBrick);
                        break;
                    case 's':
                        objectBrick = new Brick(j, i, Sprite.brick.getFxImage());
                        speedItem = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage(), objectBrick);
                        stillObjects.add(objectBrick);
                        entities.add(speedItem);
                        speedItem.setBrick(objectBrick);
                        break;
                    case 'b':
                        objectBrick = new Brick(j, i, Sprite.brick.getFxImage());
                        bombItem = new BombItem(j, i, Sprite.powerup_bombs.getFxImage(), objectBrick);
                        stillObjects.add(objectBrick);
                        entities.add(bombItem);
                        bombItem.setBrick(objectBrick);
                        break;
                    case 'x':
                        objectBrick = new Brick(j, i, Sprite.brick.getFxImage());
                        portal = new Portal(j, i, Sprite.portal.getFxImage(), objectBrick);
                        stillObjects.add(objectBrick);
                        entities.add(portal);
                        break;
                }
            }
        }
    }

    public void levelhandler(Stage stage) {
        if(bomberman.isAlive() == false) {
            loseTrans(stage);
        }
        if (bomberman.enemyNum[map - 1] <= 0) {
            if (map == 1) {
                bomberman.canLevelUp = true;
                level = map + 1;
            } else if(map == 2) {
                winTrans(stage);
            }
        } else bomberman.canLevelUp = false;
        bomberman.level = level;
        System.out.println(level + " " + map + " " + bomberman.enemyNum[map - 1]);
    }

    public void update(Stage stage) {
        entities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);
        bomberman.getBombManager().DetonateBomb();
        levelhandler(stage);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
