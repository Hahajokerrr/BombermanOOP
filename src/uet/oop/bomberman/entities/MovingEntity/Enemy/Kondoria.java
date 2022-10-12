package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.MovingEntity.State;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Kondoria extends Enemy {
    private int GuardRadius = Sprite.SCALED_SIZE * 4;
    private int vertical = 0;
    private int horizontal = 0;
    private int health = 2;
    private int wandering = 0;

    public Kondoria(int x, int y, Image img, Bomber bomber) {
        super(x, y, img, bomber);
        setState(State.LEFT);
        Score = 500;
    }

    public boolean Chasing() {
        if (Math.abs(bomber.getX() - x) <= GuardRadius && Math.abs(bomber.getY() - y) <= GuardRadius) {
            return true;
        } else return false;
    }

    public void CalculateDirection() {
        int difX = bomber.getXRound() - this.x;
        int difY = bomber.getYRound() - this.y;
        if (difX > 0) horizontal = 1;
        else if (difX < 0) horizontal = -1;
        else horizontal = 0;
        if (difY > 0) vertical = 1;
        else if (difY < 0) vertical = -1;
        else vertical = 0;
    }

    @Override
    public void Kill() {
        if (health == 0) {
            setAlive(false);
            bomber.setScore(bomber.getScore() + this.Score);
        } else {
            health--;
        }
    }

    @Override
    public void ContactEntities() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Bomber && this.collision(entities.get(i))) {
                ((Bomber) entities.get(i)).setAlive(false);
            }
            if (entities.get(i) instanceof FlameSegment && this.collision(entities.get(i))) {
                Kill();
            }
        }
    }

    public void Wandering() {
        wandering++;
        if(wandering == 0) {
            double randomDouble1 = Math.random();
            randomDouble1 = randomDouble1 * 100 + 1;
            int randomInt1 = (int) randomDouble1;

            double randomDouble2 = Math.random();
            randomDouble2 = randomDouble2 * 100 + 1;
            int randomInt2 = (int) randomDouble2;

            if (randomInt1 <= 33) horizontal = 1;
            else if (randomInt1 <= 66) horizontal = -1;
            else horizontal = 0;

            if (randomInt2 <= 33) vertical = 1;
            else if (randomInt2 <= 66) vertical = -1;
            else vertical = 0;
        }
        if(wandering > 300) {
            wandering = -1;
        }
    }

    public void move() {
        switch (horizontal) {
            case 1:
                x += speed;
                if (!canMoveStillObject(stillObjects)) {
                    x -= speed;
                }
                break;
            case -1:
                x -= speed;
                if (!canMoveStillObject(stillObjects)) {
                    x += speed;
                }
                break;
            case 0:
                break;
        }
        switch (vertical) {
            case 1:
                y += speed;
                if (!canMoveStillObject(stillObjects)) {
                    y -= speed;
                }
                break;
            case -1:
                y -= speed;
                if (!canMoveStillObject(stillObjects)) {
                    y += speed;
                }
                break;
            case 0:
                break;
        }
    }

    public boolean canMoveStillObject(List<Entity> entityList) {
        for (int i = 0; i < entityList.size(); i++) {
            if (entityList.get(i) instanceof Wall && this.collision(entityList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void update() {
        if (alive) {
            ContactEntities();
            animate();
            setImg(Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 50).getFxImage());
            if (Chasing()) {
                speed = 1;
                CalculateDirection();
                move();
            } else {
                speed = animate % 2;
                Wandering();
                move();
            }
        } else {
            setImg(Sprite.kondoria_dead.getFxImage());
            AfterKill();
        }
    }
}