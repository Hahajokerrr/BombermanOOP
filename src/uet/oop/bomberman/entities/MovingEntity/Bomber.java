package uet.oop.bomberman.entities.MovingEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.BombManager;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Item.BombItem;
import uet.oop.bomberman.entities.Tile.Item.FlameItem;
import uet.oop.bomberman.entities.Tile.Item.SpeedItem;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Bomber extends Mover {

    public static final int POWER_UP_SPEED= 2;
    private boolean canMove;
    private boolean SpeedItem = false;
    private int BombItem = 1;
    private int FlameItem = 1;
    public static final int WIDTH = 24;
    public static final int HEIGHT = 28;
    private BombManager bombManager = new BombManager();

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(STANDARD_SPEED);
        canMove = true;
    }



    public BombManager getBombManager() {
        return bombManager;
    }
    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isSpeedItem() {
        return SpeedItem;
    }

    public void setSpeedItem(boolean speedItem) {
        SpeedItem = speedItem;
    }

    public int getBombItem() {
        return BombItem;
    }

    public void setBombItem(int bombItem) {
        BombItem = bombItem;
    }

    public int getFlameItem() {
        return FlameItem;
    }

    public void setFlameItem(int flameItem) {
        FlameItem = flameItem;
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
            if(collision(entities.get(i))) {
                if (entities.get(i) instanceof FlameSegment) {
                    setAlive(false);
                }
                if (entities.get(i) instanceof SpeedItem) {
                    ((SpeedItem) entities.get(i)).Contact(this);
                }
                if (entities.get(i) instanceof BombItem) {
                    ((BombItem) entities.get(i)).Contact();
                    BombItem++;
                }
                if (entities.get(i) instanceof FlameItem) {
                    ((FlameItem) entities.get(i)).Contact();
                    FlameItem++;
                }
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            for(int j=0; j< stillObjects.size(); j++) {
                if(stillObjects.get(j).collision(entities.get(i))) {
                    if (stillObjects.get(j) instanceof Brick && entities.get(i) instanceof FlameSegment) {
                        ((Brick) stillObjects.get(j)).setBroken(true);
                    }
                }
            }
        }
    }

    public void SetCheckedSpeed() {
        if(SpeedItem) {
            setSpeed(POWER_UP_SPEED);
        } else {
            setSpeed(STANDARD_SPEED);
        }
    }

    public void UpdateBombManager() {
        bombManager.setMaxBombNum(BombItem);
        bombManager.setRadius(FlameItem);
    }


    @Override
    public void update() {
        Contact();
        UpdateBombManager();
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
