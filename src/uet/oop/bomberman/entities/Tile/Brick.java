package uet.oop.bomberman.entities.Tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import java.time.chrono.Era;

public class Brick extends Entity {
    private boolean broken;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        broken = false;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    @Override
    public void update() {
        if (broken == true) {
            setX(-50);
        }
    }
}
