package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovingEntity.State;

public class Oneal extends Enemy{
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        setState(State.LEFT);
    }


}
