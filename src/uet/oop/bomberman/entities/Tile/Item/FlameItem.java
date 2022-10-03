package uet.oop.bomberman.entities.Tile.Item;

import javafx.scene.image.Image;

import java.time.chrono.Era;

public class FlameItem extends Item{
    public FlameItem(int x, int y, Image img) {
        super(x,y,img);
    }

    public void Contact() {
        Erase();
    }

    public void update() {

    }
}
