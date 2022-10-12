package uet.oop.bomberman.entities.MovingEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Grass;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;
import java.util.List;

public abstract class Mover extends AnimatedEntity {
    protected Sound dieSound = new Sound("D:\\bomberman-starter-starter-2\\res\\sound\\Die.wav");

    public Mover(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        state = State.STOP;
        speed = STANDARD_SPEED;
        alive = true;
    }

    protected boolean alive;
    public static final int STANDARD_SPEED = 1;
    protected State state;
    protected int speed;
    protected List<Entity> stillObjects = new ArrayList<>();
    protected List<Entity> entities = new ArrayList<>();

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setStillObjects(List<Entity> stillObjects) {
        this.stillObjects = stillObjects;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean canMoveStillObject(List<Entity> entityList) {
        for (int i = 0; i < entityList.size(); i++) {
            if ((entityList.get(i) instanceof Wall || entityList.get(i) instanceof Brick) && this.collision(entityList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public abstract void update();
}
