package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.MovingEntity.State;

public class Oneal extends Enemy{
    public Oneal(int x, int y, Image img, Bomber bomber) {
        super(x, y, img, bomber);
        setState(State.LEFT);
    }


}
