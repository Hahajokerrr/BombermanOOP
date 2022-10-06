package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovingEntity.State;

public class Kondoria extends Enemy{
    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
        setState(State.LEFT);
    }



}
