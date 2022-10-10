package uet.oop.bomberman.entities.Tile.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.Tile.Brick;

import java.time.chrono.Era;

public class BombItem extends Item{
    public BombItem(int x, int y, Image img, Brick brick) {
        super(x,y,img,brick);
    }

    public void Contact() {
        Erase();
    }

    public void update() {

    }
}
