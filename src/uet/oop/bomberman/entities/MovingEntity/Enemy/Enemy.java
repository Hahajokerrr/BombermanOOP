package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import javafx.scene.paint.Stop;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Mover;
import uet.oop.bomberman.entities.MovingEntity.State;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Wall;
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

    public void AfterKill() {
        AfterKill++;
        if(AfterKill >= 50) setImg(Sprite.mob_dead1.getFxImage());
        if(AfterKill >= 100) setImg(Sprite.mob_dead2.getFxImage());
        if(AfterKill >= 150) setImg(Sprite.mob_dead3.getFxImage());
        if(AfterKill >= 200) {
            Erase();
            AfterKill = 0;
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
        for(int i=0; i<entities.size(); i++) {
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
        if(y<32) y = 33;
        if(x<32) x = 33;
        if(y > 32*14) y = 32*14 - 1;
        if(x > 32*19) x = 32*19 - 1;
    }
    @Override
    public void update() {

    }
}
