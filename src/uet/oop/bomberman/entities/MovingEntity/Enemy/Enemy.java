package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import javafx.scene.paint.Stop;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Mover;
import uet.oop.bomberman.entities.MovingEntity.State;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Mover {

    protected List<Entity> stillObjects;
    protected List<Entity> entities;
    protected Bomber bomber;
    protected int AfterKill;
    protected int Score;


    public Enemy(int x, int y, Image img, Bomber bomber) {
        super(x, y, img);
        setState(State.LEFT);
        setAlive(true);
        setSpeed(STANDARD_SPEED);
        AfterKill = 0;
        this.bomber = bomber;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    @Override
    public boolean collision(Entity other) {
        // TODO: Kiem tra va cham cua 2 thuc the.
        int leftA, leftB;
        int rightA, rightB;
        int topA, topB;
        int bottomA, bottomB;

        //Calculate the sides of rect of this
        leftA = x;
        rightA = x + 30;
        topA = y;
        bottomA = y + 30;

        //Calculate the sides of rect of other
        leftB = other.getX();
        rightB = other.getX() + Sprite.SCALED_SIZE;
        topB = other.getY();
        bottomB = other.getY() + Sprite.SCALED_SIZE;

        if (bottomA <= topB) {
            return false;
        }

        if (topA >= bottomB) {
            return false;
        }

        if (rightA <= leftB) {
            return false;
        }

        if (leftA >= rightB) {
            return false;
        }
        //If none of the sides from A are outside B
        return true;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    @Override
    public void setStillObjects(List<Entity> stillObjects) {
        this.stillObjects = stillObjects;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void Kill() {
        setAlive(false);
        bomber.setScore(bomber.getScore() + this.Score);
        dieSound.play();
    }

    public void AfterKill() {
        if(AfterKill == 0) bomber.enemyNum[bomber.level-1]--;
        AfterKill++;
        if (AfterKill >= 50) setImg(Sprite.mob_dead1.getFxImage());
        if (AfterKill >= 100) setImg(Sprite.mob_dead2.getFxImage());
        if (AfterKill >= 150) setImg(Sprite.mob_dead3.getFxImage());
        if (AfterKill == 200) {
            Erase();
            AfterKill = 201;
        }
    }

    public void ContactStillObject() {
        for (int i = 0; i < stillObjects.size(); i++) {
            if ((stillObjects.get(i) instanceof Wall || stillObjects.get(i) instanceof Brick) && this.collision(stillObjects.get(i))) {
                RandomDirection();
            }
        }
    }

    public void ContactEntities() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Bomber && this.collision(entities.get(i))) {
                ((Bomber) entities.get(i)).setAlive(false);
            }
            if (entities.get(i) instanceof Bomb && this.collision(entities.get(i))) {
                RandomDirection();
            }
            if (entities.get(i) instanceof FlameSegment && this.collision(entities.get(i))) {
                Kill();
            }
        }
    }

    public void RandomDirection() {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 100 + 1;
        int randomInt = (int) randomDouble;
        switch (state) {
            case UP:
                setY(getY() + 2);
                if (randomInt <= 33) setState(State.DOWN);
                else if (randomInt <= 66) setState(State.LEFT);
                else setState(State.RIGHT);
                break;
            case DOWN:
                setY(getY() - 2);
                if (randomInt <= 33) setState(State.UP);
                else if (randomInt <= 66) setState(State.LEFT);
                else setState(State.RIGHT);
                break;
            case LEFT:
                setX(getX() + 2);
                if (randomInt <= 33) setState(State.DOWN);
                else if (randomInt <= 66) setState(State.UP);
                else setState(State.RIGHT);
                break;
            case RIGHT:
                setX(getX() - 2);
                if (randomInt <= 33) setState(State.DOWN);
                else if (randomInt <= 66) setState(State.LEFT);
                else setState(State.UP);
                break;
        }
        if (y < Sprite.SCALED_SIZE) y = 33;
        if (x < Sprite.SCALED_SIZE) x = 33;
        if (y > Sprite.SCALED_SIZE * (BombermanGame.HEIGHT - 1))
            y = Sprite.SCALED_SIZE * (BombermanGame.HEIGHT - 1) - 1;
        if (x > Sprite.SCALED_SIZE * (BombermanGame.WIDTH - 1)) x = Sprite.SCALED_SIZE * (BombermanGame.WIDTH - 1) - 1;
    }

    @Override
    public void update() {

    }
}
