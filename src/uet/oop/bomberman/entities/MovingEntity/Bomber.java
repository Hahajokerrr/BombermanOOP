package uet.oop.bomberman.entities.MovingEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;


public class Bomber extends Mover {

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }


    @Override
    public void update() {
        switch (getState()) {
            case UP:
                setY(getY() - getSpeed());
                break;
            case DOWN:
                setY(getY() + getSpeed());
                break;
            case LEFT:
                setX(getX() - getSpeed());
                break;
            case RIGHT:
                setX(getX() + getSpeed());
                break;
            case STOP:
            default:
                break;
        }
    }

}
