package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Bomb.FlameSegment;

import java.io.OutputStream;
import java.util.List;

public class Bomb extends AnimatedEntity {
    private Bomber bomber;
    private boolean isPlaced = false;
    private long placedTime;
    // Giúp Bomber di chuyển khỏi bom trong lần đặt đầu.
    private int moveOut = -1;
    public Flame[] flames = new Flame[4];
    public static final long DETONATE_TIME = 2000;

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public int getMoveOut() {
        return moveOut;
    }

    public void setMoveOut(int moveOut) {
        this.moveOut = moveOut;
    }

    public long getPlacedTime() {
        return placedTime;
    }

    public void setPlacedTime(long placedTime) {
        this.placedTime = placedTime;
    }

    public Bomb(Bomber _bomber) {
        x = -50;
        y = -50;
        bomber = _bomber;
        for (int i = 0; i < 4; i++) {
            flames[i] = new Flame(this.x, this.y, i, 1);
        }
    }

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void setDetonateRadius(int radius) {
        for(int i=0; i<4; i++) {
            flames[i].setRadius(radius);
        }
    }

    public boolean DetonateBomb() {
        if (System.currentTimeMillis() - placedTime >= DETONATE_TIME) {
            this.Erase();
            isPlaced = false;
            placedTime = Long.MAX_VALUE;
            setMoveOut(-1);
            for(int i=0; i<4; i++) {
                flames[i].PostDetonate();
            }
            return true;
        }
        return false;
    }

    public Flame getFlameAt(int i) {
        return flames[i];
    }

    public long getCurrentTime() {
        return System.currentTimeMillis() - placedTime;
    }

    public void update() {
        if (isPlaced) {
            if (System.currentTimeMillis() - placedTime >= 1700) {
                for (int i = 0; i < 4; i++) {
                    flames[i].createFlame(this.x, this.y, bomber.getStillObjects());
                }
                if (getCurrentTime() < 1800) {
                    setImg(Sprite.bomb_exploded.getFxImage());
                    for (int i = 0; i < 4; i++) {
                        flames[i].creatFlame0();
                    }
                } else if (getCurrentTime() < 1900) {
                    setImg(Sprite.bomb_exploded1.getFxImage());
                    for (int i = 0; i < 4; i++) {
                        flames[i].creatFlame1();
                    }
                } else {
                    setImg(Sprite.bomb_exploded2.getFxImage());
                    for (int i = 0; i < 4; i++) {
                        flames[i].creatFlame2();
                    }
                }
            } else {
                animate();
                setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 100).getFxImage());
            }
            if(!this.collision(bomber)) {
                bomber.setCanMove(true);
                moveOut++;
            } else if (bomber.collision(this)){
                if(moveOut == -1) {
                    bomber.setCanMove(true);
                }
                if(moveOut >= 0){
                    bomber.setCanMove(false);
                }
            }
        } else {
            setAnimate(0);
            for (int i = 0; i < 4; i++) {
                flames[i].EraseFlame();
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        for (int i = 0; i < 4; i++) {
            flames[i].getFlameSegmentAt(0).render(gc);
            flames[i].getFlameSegmentAt(1).render(gc);
        }
    }
}
