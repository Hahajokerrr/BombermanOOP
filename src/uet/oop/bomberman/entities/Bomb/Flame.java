package uet.oop.bomberman.entities.Bomb;

import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Flame extends AnimatedEntity {
    private int direction;
    private int radius;

    private int BrickRadius = radius;

    private FlameSegment[] flameSegments = new FlameSegment[3];


    public Flame(int x, int y, int direction, int radius) {
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.radius = radius;
        for (int i = 0; i < 3; i++) {
            flameSegments[i] = new FlameSegment(-50, -50, false);
        }
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getBrickRadius() {
        return BrickRadius;
    }

    public void setBrickRadius(int brickRadius) {
        BrickRadius = brickRadius;
    }

    public int CalculateRealRadius(List<Entity> stillObjects) {
        int res = radius;
            for (int j = 0; j < stillObjects.size(); j++) {
                for (int i = 0; i < radius; i++) {
                    if (flameSegments[i].collision(stillObjects.get(j))) {
                        if (stillObjects.get(j) instanceof Wall) {
                            res = i;
                        }
                        if (stillObjects.get(j) instanceof Brick) {
                            BrickRadius = i;
                        }
                    }
                }
            }
        return res;
    }

    public void createFlame(int _x, int _y, List<Entity> stillObjects) {
        int flameX = _x;
        int flameY = _y;
        boolean last = false;
        for (int i = 0; i < radius; i++) {
            last = i == radius - 1 ? true : false;

            switch (direction) {
                case 0:
                    flameY -= Sprite.SCALED_SIZE;
                    flameSegments[i].setImg(Sprite.explosion_vertical.getFxImage());
                    break;
                case 1:
                    flameX += Sprite.SCALED_SIZE;
                    flameSegments[i].setImg(Sprite.explosion_horizontal.getFxImage());
                    break;
                case 2:
                    flameY += Sprite.SCALED_SIZE;
                    flameSegments[i].setImg(Sprite.explosion_vertical.getFxImage());
                    break;
                case 3:
                    flameX -= Sprite.SCALED_SIZE;
                    flameSegments[i].setImg(Sprite.explosion_horizontal.getFxImage());
                    break;
            }
            flameSegments[i].setX(flameX);
            flameSegments[i].setY(flameY);
            flameSegments[i].setLast(last);
        }
        for (int i = CalculateRealRadius(stillObjects); i < radius; i++) {
            flameSegments[i].Erase();
        }
        if(BrickRadius < radius) {
            for (int i = BrickRadius + 1; i < radius; i++) {
                flameSegments[i].Erase();
            }
        }
    }


    public void creatFlame0() {
        for (int i = 0; i < radius; i++) {
            switch (direction) {
                case 0:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_vertical_top_last.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_vertical.getFxImage());
                    }
                    break;
                case 1:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_horizontal_right_last.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_horizontal.getFxImage());
                    }
                    break;
                case 2:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_vertical_down_last.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_vertical.getFxImage());
                    }
                    break;
                case 3:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_horizontal_left_last.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_horizontal.getFxImage());
                    }
                    break;
            }
        }
    }

    public void creatFlame1() {
        for (int i = 0; i < radius; i++) {
            switch (direction) {
                case 0:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_vertical_top_last1.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_vertical1.getFxImage());
                    }
                    break;
                case 1:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_horizontal1.getFxImage());
                    }
                    break;
                case 2:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_vertical_down_last1.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_vertical1.getFxImage());
                    }
                    break;
                case 3:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_horizontal1.getFxImage());
                    }
                    break;
            }
        }
    }

    public void creatFlame2() {
        for (int i = 0; i < radius; i++) {
            switch (direction) {
                case 0:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_vertical_top_last2.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_vertical2.getFxImage());
                    }
                    break;
                case 1:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_horizontal2.getFxImage());
                    }
                    break;
                case 2:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_vertical_down_last2.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_vertical2.getFxImage());
                    }
                    break;
                case 3:
                    if (flameSegments[i].isLast()) {
                        flameSegments[i].setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
                    } else {
                        flameSegments[i].setImg(Sprite.explosion_horizontal2.getFxImage());
                    }
                    break;
            }
        }
    }

    public FlameSegment getFlameSegmentAt(int i) {
        return flameSegments[i];
    }

    public void EraseFlame() {
        for (int i = 0; i < radius; i++) {
            flameSegments[i].setX(-50);
            flameSegments[i].setY(-50);
        }
    }

    public void PostDetonate() {
        setBrickRadius(radius);
    }

    public void update() {

    }

}
