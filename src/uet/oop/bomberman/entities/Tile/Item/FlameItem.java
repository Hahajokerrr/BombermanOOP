package uet.oop.bomberman.entities.Tile.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Tile.Brick;

import java.time.chrono.Era;

public class FlameItem extends Item{
    public FlameItem(int x, int y, Image img, Brick brick) {
        super(x,y,img,brick);
    }

    public void Contact() {
        Erase();
    }

    public void update() {

    }
}
