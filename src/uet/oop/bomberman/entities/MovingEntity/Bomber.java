package uet.oop.bomberman.entities.MovingEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;


public class Bomber extends Mover {

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(STANDARD_SPEED);

    }


    @Override
    public void update() {
        animate();
        switch (getState()) {
            case UP:
                setY(getY() - speed);
                setImg(Sprite.player_up.getFxImage());
                if(speed > 0) {
                    setImg(Sprite.movingSprite(Sprite.player_up,Sprite.player_up_1, Sprite.player_up_2, animate, 50).getFxImage());
                }
                break;
            case DOWN:
                setY(getY() + speed);
                setImg(Sprite.player_down.getFxImage());
                if(speed > 0) {
                    setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 50).getFxImage());
                }
                break;
            case LEFT:
                setX(getX() - speed);
                setImg(Sprite.player_left.getFxImage());
                if(speed > 0) {
                    setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 50).getFxImage());
                }
                break;
            case RIGHT:
                setX(getX() + speed);
                setImg(Sprite.player_right.getFxImage());
                if(speed > 0) {
                    setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 50).getFxImage());
                }
                break;
            default:
                break;
        }
    }

}
