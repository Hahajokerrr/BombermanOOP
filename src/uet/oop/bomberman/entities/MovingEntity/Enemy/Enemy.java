package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Mover;
import uet.oop.bomberman.entities.MovingEntity.State;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Mover {

    protected List<Entity> stillObjects;
    protected List<Entity> entities;
    protected int AfterKill;
    public Enemy(int x, int y, Image img) {
        super(x,y,img);
        setState(State.LEFT);
        setAlive(true);
        setSpeed(STANDARD_SPEED);
        AfterKill = 0;
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
    }
    public void RandomDirection() {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 100 + 1;
        int randomInt = (int) randomDouble;
        switch (state) {
            case UP:
                setY(getY() + 1);
                if (randomInt <= 33) setState(State.DOWN);
                else if (randomInt <= 66) setState(State.LEFT);
                else setState(State.RIGHT);
                break;
            case DOWN:
                setY(getY() - 1);
                if (randomInt <= 33) setState(State.UP);
                else if (randomInt <= 66) setState(State.LEFT);
                else setState(State.RIGHT);
                break;
            case LEFT:
                setX(getX() + 1);
                if (randomInt <= 33) setState(State.DOWN);
                else if (randomInt <= 66) setState(State.UP);
                else setState(State.RIGHT);
                break;
            case RIGHT:
                setX(getX() - 1);
                if (randomInt <= 33) setState(State.DOWN);
                else if (randomInt <= 66) setState(State.LEFT);
                else setState(State.UP);
                break;
        }
        if(y<32) y = 32;
        if(x<32) x = 32;
        if(y > 32*14) y = 32*14;
        if(x > 32*19) x = 32*19;
    }
    @Override
    public void update() {

    }
}
