package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.MovingEntity.State;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    private int GuardRadius = Sprite.SCALED_SIZE * 3;
    private int vertical = 0;
    private int horizontal = 0;
    private int wandering = 0;


    public Oneal(int x, int y, Image img, Bomber bomber) {
        super(x, y, img, bomber);
        setState(State.LEFT);
        speed = 1;
        Score = 400;
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
        if(wandering > 200) {
            wandering = -1;
        }
    }

    @Override
    public void ContactEntities() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Bomber && this.collision(entities.get(i))) {
                ((Bomber) entities.get(i)).setAlive(false);
            }
            if (entities.get(i) instanceof Bomb && this.collision(entities.get(i))) {
                speed = 0;
            }
            if (entities.get(i) instanceof FlameSegment && this.collision(entities.get(i))) {
                Kill();
            }
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

    public void update() {
        if (alive) {
            animate();
            setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 50).getFxImage());
            if (Chasing()) {
                speed = 1;
                CalculateDirection();
                move();
                ContactEntities();
            } else {
                speed = animate % 2;
                Wandering();
                move();
                ContactEntities();
            }
        } else {
            setImg(Sprite.oneal_dead.getFxImage());
            AfterKill();
        }
    }
}