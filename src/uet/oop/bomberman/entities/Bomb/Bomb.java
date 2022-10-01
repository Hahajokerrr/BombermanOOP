package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends AnimatedEntity {
    private boolean isPlaced = false;
    private long placedTime;
    public static final long DETONATE_TIME = 2000;

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public long getPlacedTime() {
        return placedTime;
    }

    public void setPlacedTime(long placedTime) {
        this.placedTime = placedTime;
    }

    public Bomb() {
        x = -50;
        y = -50;
    }

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public boolean DetonateBomb() {
        if (System.currentTimeMillis() - placedTime >= DETONATE_TIME) {
            x = -50;
            y = -50;
            isPlaced = false;
            placedTime = Long.MAX_VALUE;
            return true;
        }
        return false;
    }

    public void update() {
        animate();
        setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 100).getFxImage());
    }

}
