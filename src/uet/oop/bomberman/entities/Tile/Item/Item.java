package uet.oop.bomberman.entities.Tile.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import java.time.chrono.Era;

public abstract class Item extends Entity {

    public Item(int x, int y, Image img) {
        super(x,y,img);
    }
    private boolean isConsumed = false;

    public void update() {
        if(isConsumed) {
            Erase();
        }
    }
}
