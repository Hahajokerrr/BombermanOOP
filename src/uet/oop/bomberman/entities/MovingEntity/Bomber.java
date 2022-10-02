package uet.oop.bomberman.entities.MovingEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Bomber extends Mover {

    private boolean canMove;
    public static final int WIDTH = 24;
    public static final int HEIGHT = 32;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(STANDARD_SPEED);
        canMove = true;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
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
        rightA = x + WIDTH;
        topA = y;
        bottomA = y + HEIGHT;

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

    public void Contact() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof FlameSegment && collision(entities.get(i))) {
                setAlive(false);
            }
        }
    }

    @Override
    public void update() {
        Contact();
        if (alive) {
            animate();
            switch (getState()) {
                case UP:
                    setY(getY() - speed);
                    if (!canMoveStillObject(stillObjects)) {
                        setY(getY() + speed);
                    }
                    if(!canMove) {
                        setY(getY() + speed + 1);
                    }
                    setImg(Sprite.player_up.getFxImage());
                    if (speed > 0) {
                        setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, 50).getFxImage());
                    }
                    break;
                case DOWN:
                    setY(getY() + speed);
                    if (!canMoveStillObject(stillObjects)) {
                        setY(getY() - speed);
                    }
                    if(!canMove) {
                        setY(getY() - speed - 1);
                    }
                    setImg(Sprite.player_down.getFxImage());
                    if (speed > 0) {
                        setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 50).getFxImage());
                    }
                    break;
                case LEFT:
                    setX(getX() - speed);
                    if (!canMoveStillObject(stillObjects)) {
                        setX(getX() + speed);
                    }
                    if(!canMove) {
                        setX(getX() + speed + 1);
                    }
                    setImg(Sprite.player_left.getFxImage());
                    if (speed > 0) {
                        setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 50).getFxImage());
                    }
                    break;
                case RIGHT:
                    setX(getX() + speed);
                    if (!canMoveStillObject(stillObjects) || !canMove) {
                        setX(getX() - speed);
                    }
                    if(!canMove) {
                        setX(getX() - 1);
                    }
                    setImg(Sprite.player_right.getFxImage());
                    if (speed > 0) {
                        setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 50).getFxImage());
                    }
                    break;
                default:
                    break;
            }
        } else {
            setImg(Sprite.player_dead1.getFxImage());
        }
    }
}
