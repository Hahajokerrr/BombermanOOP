package uet.oop.bomberman.entities.MovingEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.Wall;

import java.util.ArrayList;
import java.util.List;

public abstract class Mover extends AnimatedEntity {
    public Mover(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        state = State.STOP;
        speed = STANDARD_SPEED;
    }

    public static final int STANDARD_SPEED = 2;
    protected State state;
    protected int speed;
    protected List<Entity> stillObjects = new ArrayList<>();

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

    public boolean canMove(List<Entity> entityList) {
        for(int i=0; i<entityList.size(); i++) {
            if(entityList.get(i) instanceof Wall && this.collision(entityList.get(i))) {
                return (!this.collision(entityList.get(i)));
            }
        }
        return true;
    }

    public abstract void update();
}
