package uet.oop.bomberman.entities.Tile.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Mover;
import uet.oop.bomberman.entities.Tile.Brick;

public class SpeedItem extends Item{
    public SpeedItem(int x, int y, Image img, Brick brick) {
        super(x,y,img, brick);
    }

    public void Contact(Bomber bomber) {
        bomber.setSpeedItem(true);
        Erase();
    }
}
