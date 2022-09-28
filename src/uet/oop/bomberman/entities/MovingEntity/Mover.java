package uet.oop.bomberman.entities.MovingEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Mover extends Entity {
    public Mover(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        state = State.STOP;
        speed = 2;
    }

    protected State state;
    protected int speed;

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

    public abstract void update();
}
